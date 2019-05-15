package part01.lesson05.task01;

import java.util.Comparator;

public class AnimalOwnerAgeComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getOwner().getAge() - o2.getOwner().getAge();
    }
}