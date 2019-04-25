package part01.lesson05.task01;

import java.util.Comparator;

public class AnimalIdComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getId() - o2.getId();
    }
}
