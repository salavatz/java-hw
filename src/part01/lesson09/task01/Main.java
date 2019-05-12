package part01.lesson09.task01;

import org.apache.commons.jci.compilers.CompilationResult;
import org.apache.commons.jci.compilers.JavaCompiler;
import org.apache.commons.jci.compilers.JavaCompilerFactory;
import org.apache.commons.jci.readers.FileResourceReader;
import org.apache.commons.jci.stores.FileResourceStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //createJavaFile();
        JavaCompiler compiler = new JavaCompilerFactory().createCompiler("eclipse");
        CompilationResult result = compiler.compile(
                new String[]{"SomeClass.java"},
                new FileResourceReader(new File("src/part01/lesson09/task01")),
                new FileResourceStore(new File("src/part01/lesson09/task01"))
        );
    }

    private static void createJavaFile() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while (!(line = scanner.nextLine()).isEmpty ()) {
            stringBuilder.append("        ").append(line).append("\n");
        }
        scanner.close();
        try (PrintWriter writer = new PrintWriter("src/part01/lesson09/task01/SomeClass.java")){
            String str = "package part01.lesson09.task01;\n" +
                    "\n" +
                    "public class SomeClass {\n" +
                    "    public void doWork(){\n";
            writer.write(str);
            writer.write(String.valueOf(stringBuilder));
            writer.write("    }\n}");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
