package part01.lesson04.task02;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        ObjectBox objectBox = new ObjectBox(new HashSet<>());
        objectBox.add(Integer.valueOf(2));
        objectBox.add(Double.valueOf(3));
        objectBox.add(new String("sd"));
        System.out.println(objectBox.dump());
        objectBox.delete(Integer.valueOf(2));
        System.out.println(objectBox.dump());


    }
}
