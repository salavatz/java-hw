package part01.lesson04.task03;

public class Main {
    public static void main(String[] args) {
        Number[] array = {0.0, Double.valueOf(1.1), 2, 3, 5, 8};

        Number number = 4;
        MathBox mathBox = new MathBox(array);
        System.out.println("Sum (" + mathBox.toString() + ") = " + mathBox.summator());
        mathBox.splitter(number);
        System.out.println("Sum (" + mathBox.toString() + ") = " + mathBox.summator());
        mathBox.delete(Double.valueOf(2));
        System.out.println("Sum (" + mathBox.toString() + ") = " + mathBox.summator());

        Number[] array2 = {0.0, 0.5, 1.25, 0.275, 0.75};
        MathBox mathBox1 = new MathBox(array2);
        Object[] array3 = {"sadsd", new Object(), 2};
        MathBox mathBox2 = new MathBox(array3);
        mathBox2.add(new Object());

    }
}

