package part01.lesson05.task01;

import java.util.Comparator;

public class AnimalOwnerNameComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getOwner().getName().compareTo(o2.getOwner().getName());
    }
}