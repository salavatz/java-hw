package part01.lesson04.task01;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Number[] array = {Integer.valueOf(0), Double.valueOf(1.1), 2, 3, 5, 8};

        Number number = 4;
        MathBox mathBox = new MathBox(array);
        System.out.println("Sum (" + mathBox.toString() + ") = " + mathBox.summator());
        mathBox.splitter(number);
        System.out.println("Sum (" + mathBox.toString() + ") = " + mathBox.summator());
        mathBox.delete(Integer.valueOf(2));
        System.out.println("Sum (" + mathBox.toString() + ") = " + mathBox.summator());

        Number[] array2 = {0.0, 0.5, 1.25, 0.275, 0.75};
        MathBox mathBox1 = new MathBox(array2);

        System.out.println(mathBox.equals(mathBox));
        System.out.println(mathBox.equals(mathBox1));

        Set mathBoxes = new HashSet();
        mathBoxes.add(mathBox);
        mathBoxes.add(mathBox1);
        mathBox1.splitter(0);
        System.out.println(mathBoxes);
    }
}

