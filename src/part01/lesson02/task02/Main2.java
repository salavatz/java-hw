package part01.lesson02.task02;

import java.security.SecureRandom;
import java.util.Arrays;

public class Main2 {

    public static void main(String[] args) {
        int n = 10;
        int[] array = getRandomArray(n);
        System.out.println(Arrays.toString(array));
        printPerfectSquare(array);

    }

    public static int[] getRandomArray(int n) {
        int[] array = new int[n];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(1000) - 500;
        }
        return array;
    }

    public static void printPerfectSquare(int[] array) {
        for (int element : array) {
            if (element < 0) {
                try {
                    throw new SqrtException("The number is negative");
                } catch (SqrtException e) {
                    e.printStackTrace();
                }
            }
            else if (Math.pow((int) Math.sqrt(element), 2) == element) {
                System.out.println(element);
            }
        }
    }

}
