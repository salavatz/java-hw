package part01.lesson08.task01;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person = new Person();
        person.setAge(10);
        person.setName("Alex");
        Point p = new Point();
        p.setX(11);
        p.setY(13);
        person.setPoint(p);
        person.setInteger(100);
        Point p2 = new Point();
        p2.setX(17);
        p2.setY(19);
        person.setPoint2(p2);
        person.setInteger(200);
        person.points.add(p);
        person.points.add(p2);

        Serialization serialization2 = new Serialization();
        serialization2.serialize(person, "src/part01/lesson08/task01/file.txt");
        System.out.println((serialization2.deSerialize("src/part01/lesson08/task01/file.txt")));
    }
}