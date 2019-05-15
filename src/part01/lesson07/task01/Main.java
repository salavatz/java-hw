package part01.lesson07.task01;

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
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(20);
        }
        return array;
    }
}
