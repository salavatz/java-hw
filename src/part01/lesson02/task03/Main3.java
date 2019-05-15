package part01.lesson02.task03;

import java.security.SecureRandom;
import java.util.Comparator;

public class Main3 {
    public static void main(String[] args) {
        int n = 10000;
        Person[] array = getArray(n);
        Person[] sourceArray = array.clone();
        printArray(array);
        Comparator<Person> comparator = new PersonSexComparator().thenComparing(
                new PersonAgeComparator().thenComparing(
                        new PersonNameComparator()
                )
        );
        long start = System.nanoTime();
        Sort mergeSort = new MergeSort();
        mergeSort.sort(array, comparator);
        System.out.println("Time of the merge sort: " + (System.nanoTime() - start) );
        printArray(array);
        start = System.nanoTime();
        Sort quickSort = new QuickSort();
        quickSort.sort(sourceArray, comparator);
        System.out.println("Time of the quick sort: " + (System.nanoTime() - start) );
        printArray(sourceArray);
    }

    private static Person[] getArray(int n) {
        Person[] array = new Person[n];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < n; i++) {
            array[i] = new Person(50 + random.nextInt(40),
                    random.nextBoolean() ? Sex.MAN : Sex.WOMAN,
                    "Name" + random.nextInt(100));
        }
        return array;
    }

    private static void printArray(Person[] array) {
        for (Person person : array) {
            System.out.println(person.getSex() + " " + person.getAge() + " " + person.getName());
        }
    }
}
