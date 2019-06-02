package part01.lesson11.task01;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        int[] array = getRandomArray(100);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(factorial.calculate(array)));
    }

    private static int[] getRandomArray(int size) {
        return new Random().ints(size,0,20).toArray();
    }
}
