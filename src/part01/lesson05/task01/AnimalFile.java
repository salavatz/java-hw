package part01.lesson05.task01;

import java.util.*;

public class AnimalFile {
    TreeSet<Animal> set = new TreeSet<>(
            new AnimalNicknameComparator()
                    .thenComparing(new AnimalIdComparator()));

    public void add(Animal animal) {
        if (set.contains(animal)) {
            try {
                throw new AnimalExistException("This animal has already been added");
            } catch (AnimalExistException e) {
                e.printStackTrace();
            }
            return;
        }
        set.add(animal);
    }

    public Set<Animal> find(String nickname) {
        return set.subSet(
                new Animal(0, nickname, null, 0),
                new Animal(999, nickname, null, 0)
        );
    }

    public void changeAnimal(int id, Animal modifiedAnimal) {
        set.removeIf(animal -> animal.getId() == id);
        set.add(modifiedAnimal);
    }

    public void printSortedOrder() {
        Comparator<Animal> comparator = new AnimalOwnerSexComparator().
                thenComparing(new AnimalOwnerNameComparator()).
                thenComparing(new AnimalOwnerAgeComparator()).
                thenComparing(new AnimalNicknameComparator()).
                thenComparing(new AnimalWeightComparator());
        List<Animal> list = new ArrayList<>(set);
        Collections.sort(list, comparator);
        for (Animal animal : list) {
            System.out.println(" Owner: " + animal.getOwner().getSex() + ", " +
                    " " + animal.getOwner().getName() + ", " +
                    " " + animal.getOwner().getAge() + ", " +
                    "Nickname: " + animal.getNickname() + ", " +
                    " Weight: " + animal.getWeight());
        }
    }
}
