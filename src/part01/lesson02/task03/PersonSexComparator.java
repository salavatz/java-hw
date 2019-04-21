package part01.lesson02.task03;

import java.util.Comparator;

public class PersonSexComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getSex() == Sex.WOMAN && o2.getSex() == Sex.MAN) {
            return 1;
        }
        else if (o1.getSex() == Sex.MAN && o2.getSex() == Sex.WOMAN) {
            return -1;
        }
        else {
            return 0;
        }
    }
}