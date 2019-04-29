package part01.lesson06.task01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static Set<String> set = new TreeSet<>();

    public static void main(String[] args) {
        readFile("src/part01/lesson06/task01/in.txt");
        writeFile("src/part01/lesson06/task01/out.txt");
    }

    private static void readFile(String s) {
        File file = new File(s);
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                for (String str : line.split(" ")) {
                    String result = str.replaceAll("[^а-яА-Я]", "").toLowerCase();
                    if (!result.equals("")) {
                        list.add(result);
                    }
                }
            }
            set.addAll(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(String path) {
        try (PrintWriter writer = new PrintWriter(path)) {
            for (String s : set) {
                writer.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
