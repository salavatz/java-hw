package part01.lesson09.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String javaFilePath = "src/part01/lesson09/task01/SomeClass.java";
        String interfacePath = "src/part01/lesson09/task01/Worker.java";
        String className = "part01.lesson09.task01.SomeClass";
        compile(createJavaFile(javaFilePath, interfacePath, className));
        load(className).doWork();
    }

    private static Worker load(String className) {
        ClassLoader classLoader = new CustomClassLoader();
        try {
            Class<?> object = classLoader.loadClass(className);
            return (Worker) object.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void compile(File javaFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, javaFile.getPath());
    }

    private static File createJavaFile(String javaFilePath, String interfacePath, String className) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputCode = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            inputCode.append("\t\t").append(line).append("\n");
        }
        scanner.close();
        File file = new File(javaFilePath);
        try {
            int size = className.split("\\.").length;
            String classContent = Files.lines(Paths.get(interfacePath)).
                    reduce("", (a, b) -> a + "\n" + b).
                    replace("interface", "class " + className.split("\\.")[size - 1] + " implements").
                    replace("void", "public void").
                    replace(");", "){\n" + inputCode + "\t}");
            Files.write(file.toPath(), classContent.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}

