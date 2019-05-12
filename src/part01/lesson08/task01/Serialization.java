package part01.lesson08.task01;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * stringBuilder, оно же data - хранит последовательность символов - состояние объекта,
 * separators - разделители, которые отделяют поля объекта
 * Объект в stringBuilder хранится следующим образом:
 * тип сериализуемого объекта_ разделитель_поля объекта
 * Если поле объекта имеет ссылочный тип, то в stringBuilder записываем следующим образом:
 * тип поля_разделитель_имя поля_разделитель_значение поля
 * Если поле сложное, например Point, то вызываем рекурсивную сериализацию
 * fieldNames - стек, нужен для того, чтобы при случае рекурсивного вызова
 * понять название поля (например - points)
 */
public class Serialization {

    private StringBuilder stringBuilder = new StringBuilder();
    private String[] separators = new String[]{",", " ", "#", ";"};
    private int indexOfSeparator = 0;
    private String data;
    private Stack<String> fieldNames = new Stack<>();

    public void serialize(Object object, String file) {
        Class c = object.getClass();
        stringBuilder.
                append(c.getName()).
                append(separators[indexOfSeparator]);
        if (indexOfSeparator > 0) {
            stringBuilder.
                    append(fieldNames.pop()).
                    append(separators[indexOfSeparator]);
        }
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String type = field.getType().getName();
            try {
                field.setAccessible(true);
                indexOfSeparator++;
                if (isStandardType(type)) {
                    stringBuilder.
                            append(type).
                            append(separators[indexOfSeparator]).
                            append(field.getName()).
                            append(separators[indexOfSeparator]).
                            append(field.get(object)).
                            append(separators[indexOfSeparator - 1]);
                } else if (type.equals("java.util.List")) {
                    stringBuilder.append(type).
                            append(separators[indexOfSeparator]).
                            append(field.getName()).
                            append(separators[indexOfSeparator]);
                    for (int i = 0; i < ((List) field.get(object)).size(); i++) {
                        fieldNames.push(field.getName());
                        String elementType = ((List) field.get(object)).get(i).getClass().getName();
                        if (isStandardType(elementType)) {
                            stringBuilder.
                                    append(elementType).
                                    append(separators[indexOfSeparator + 1]).
                                    append(((List) field.get(object)).get(i)).
                                    append(separators[indexOfSeparator]);
                        } else {
                            indexOfSeparator++;
                            serialize(((List) field.get(object)).get(i), file);
                            stringBuilder.replace(
                                    stringBuilder.length() - 1,
                                    stringBuilder.length(),
                                    separators[indexOfSeparator - 1]);
                            indexOfSeparator--;
                        }
                    }

                } else {
                    fieldNames.push(field.getName());
                    serialize(field.get(object), file);
                }
                indexOfSeparator--;
                stringBuilder.replace(
                        stringBuilder.length() - 1,
                        stringBuilder.length(),
                        separators[indexOfSeparator]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (indexOfSeparator == 0) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(stringBuilder);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isStandardType(String type) {
        return type.equals("int") ||
                type.equals("java.lang.String") ||
                type.equals("java.lang.Integer");
    }

    public Object deSerialize(String file) {
        Object object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            StringBuilder stringBuilder = (StringBuilder) objectInputStream.readObject();
            data = stringBuilder.toString();
            //System.out.println(data);
            object = doSerializePart(data);

        } catch (IOException |
                ClassNotFoundException |
                NoSuchFieldException |
                IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    private Object doSerializePart(String data) throws NoSuchFieldException, IllegalAccessException {
        Object object = null;
        boolean isFirst = true;
        boolean isSecond = false;
        for (String str : data.split(separators[indexOfSeparator])) {
            if (isFirst) {
                object = getObject(str);
                isFirst = false;
                isSecond = true;
                continue;
            }
            if (isSecond && indexOfSeparator > 0) {
                isSecond = false;
                continue;
            }
            indexOfSeparator++;
            String[] s = str.split(separators[indexOfSeparator]);
            if (s[0].equals("java.lang.String")) {
                Field field = object.getClass().getDeclaredField(s[1]);
                field.setAccessible(true);
                field.set(object, s[2]);
            } else if (s[0].equals("java.lang.Integer") || s[0].equals("int")) {
                Field field = object.getClass().getDeclaredField(s[1]);
                field.setAccessible(true);
                field.set(object, Integer.parseInt(s[2]));
            } else if (s[0].equals("java.util.List")) {
                if (s[2].split(separators[indexOfSeparator + 1])[0].equals("java.lang.Integer")) {
                    List<Integer> innerObject = new ArrayList<>();
                    for (int i = 2; i < s.length; i++) {
                        String[] typeValue = s[i].split(separators[indexOfSeparator + 1]);
                        innerObject.add(Integer.parseInt(typeValue[1]));
                    }
                    Field field = object.getClass().getDeclaredField(s[1]);
                    field.setAccessible(true);
                    field.set(object, innerObject);
                } else {
                    List<Object> innerObject = new ArrayList<>();
                    for (int i = 2; i < s.length; i++) {
                        indexOfSeparator++;
                        innerObject.add(doSerializePart(s[i]));
                        indexOfSeparator--;
                    }
                    Field field = object.getClass().getDeclaredField(s[1]);
                    field.setAccessible(true);
                    field.set(object, innerObject);
                }
            } else {
                Object innerObject = doSerializePart(str);
                Field field = object.getClass().getDeclaredField(s[1]);
                field.setAccessible(true);
                field.set(object, innerObject);
            }
            indexOfSeparator--;
        }
        return object;
    }

    private Object getObject(String name) {
        Object object = null;
        try {
            Class clazz = Class.forName(name);
            object = clazz.getConstructor().newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                ClassNotFoundException |
                NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}