package part01.lesson08.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    public String name;
    private int age;
    private Integer integer;
    private Point point;
    private Point point2;
    private List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
    public List<Point> points = new ArrayList<>(Arrays.asList());

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", integer=" + integer +
                ", point=(" + point.getX() + ", " + point.getY() + ")" +
                ", point2=(" + point2.getX() + ", " + point2.getY() + ")" +
                ", list=[" + list.get(0) + ", " + list.get(1) + ", " + +list.get(2) + "]" +
                ", points=[(" + points.get(0).getX() + ", " + points.get(0).getY() + "), (" +
                points.get(1).getX() + ", " + points.get(1).getY() + ")]" +
                '}';
    }
}
