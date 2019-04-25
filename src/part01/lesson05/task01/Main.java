package part01.lesson05.task01;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AnimalFile animalFile = new AnimalFile();
        animalFile.add(new Animal(25, "Simba",
                new Person("Alex", 30, Sex.MAN), 7));
        animalFile.add(new Animal(100, "Misty",
                new Person("Alice", 35, Sex.WOMAN), 4));
        animalFile.add(new Animal(20, "Lucy",
                new Person("Alice", 35, Sex.WOMAN), 4));
        animalFile.add(new Animal(50, "Kitty",
                new Person("Bob", 25, Sex.MAN), 5));
        animalFile.add(new Animal(75, "Oreo",
                new Person("Emma", 20, Sex.WOMAN), 1));
        animalFile.add(new Animal(0, "Lucy",
                new Person("Ann", 40, Sex.WOMAN), 6));
        animalFile.add(new Animal(10, "Lucy",
                new Person("Ann", 40, Sex.WOMAN), 6));
        animalFile.add(new Animal(12, "Lucy",
                new Person("Ann", 41, Sex.WOMAN), 6));
        animalFile.add(new Animal(16, "Bars",
                new Person("Ann", 40, Sex.WOMAN), 6));
        animalFile.add(new Animal(14, "Bars",
                new Person("Ann", 40, Sex.WOMAN), 3));
        animalFile.add(new Animal(14, "Bars",
                new Person("Ann", 40, Sex.WOMAN), 3));

        print(animalFile.find("Lucy"));

        animalFile.printSortedOrder();

        animalFile.changeAnimal(14, new Animal(14, "Bars",
                new Person("Ann", 40, Sex.WOMAN), 45));

        animalFile.printSortedOrder();
    }

    private static void print(Set<Animal> set) {
        for (Animal animal: set) {
            System.out.println("Nickname: " + animal.getNickname() + ", " +
                    " Id: " + animal.getId() + ", " +
                    " Owner: " + animal.getOwner().getSex() + ", " +
                    " " + animal.getOwner().getName() + ", " +
                    " " + animal.getOwner().getAge() + ", " +
                    " Weight: " + animal.getWeight());
        }
    }
}
