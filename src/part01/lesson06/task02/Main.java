package part01.lesson06.task02;

public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator();
        String[] array = {"AAAA", "BBBB", "CCCC", "DDDD"};
        System.out.println("".length());
        generator.getFiles("src/part01/lesson06/task02/", 3, 10000, array, 0.3);
    }
}
