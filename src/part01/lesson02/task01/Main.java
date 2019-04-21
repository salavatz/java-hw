package part01.lesson02.task01;

public class Main {

    public static void main(String[] args) {
        //nullPointerExceptionModeling();
        //arrayIndexOutOfBoundsExceptionModeling();
        myExceptionModeling();
    }

    public static void myExceptionModeling() {
        try {
            throw new MyException("message");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void arrayIndexOutOfBoundsExceptionModeling() {
        int[] array = new int[4];
        int num = array[4];
    }

    public static void nullPointerExceptionModeling() {
        String str = null;
        str.equals("message");
    }
}
