package part01.lesson02.task03;

import part01.lesson02.task01.MyException;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    private boolean condition = false;

    @Override
    public int compare(Person o1, Person o2) {
        if(o1 == o2) {
            return 0;
        }
        int result = o1.getName().compareTo(o2.getName());
        if (result != 0) {
            return result;
        }
        if (condition) {
            return 0;
        }
        try {
            condition = true;
            throw new MyException("message");
        } catch (MyException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
