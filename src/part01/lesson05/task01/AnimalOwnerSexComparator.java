package part01.lesson05.task01;

import java.util.Comparator;

public class AnimalOwnerSexComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        if (o1.getOwner().getSex().equals(Sex.WOMAN) && o2.getOwner().getSex().equals(Sex.MAN)) {
            return 1;
        } else if (o1.getOwner().getSex().equals(Sex.MAN) && o2.getOwner().getSex().equals(Sex.WOMAN)) {
            return -1;
        } else {
            return 0;
        }
    }
}
