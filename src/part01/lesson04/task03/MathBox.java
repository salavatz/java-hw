package part01.lesson04.task03;

import java.util.*;

public class MathBox extends ObjectBox {

    public MathBox(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof Number) {
                collection.add(array[i]);
            }
            else {
                try {
                    throw new ElementTypeInArrayException("Input array contains non Number type");
                } catch (ElementTypeInArrayException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void add(Object o) {
        if (o instanceof Number) {
            super.add(o);
        }
        else {
            try {
                throw new ElementTypeInArrayException("Input object non Number type");
            } catch (ElementTypeInArrayException e) {
                e.printStackTrace();
            }
        }
    }

    public double summator() {
        double result = 0;
        for (Object n : collection) {
            result += ((Number)n).doubleValue();
        }
        return result;
    }

    public void splitter(Number splitterNumber) {
        List<Number> list = new ArrayList<>();
        Iterator<Object> iter = collection.iterator();
        while (iter.hasNext()) {
            Number number = (Number)iter.next();
            iter.remove();
            list.add(number.doubleValue() / splitterNumber.doubleValue());
        }
        collection.addAll(list);
    }

    public void delete(Integer i) {
        super.delete(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return collection.equals(mathBox.collection);
    }

    @Override
    public String toString() {
        return this.dump();
    }
}

