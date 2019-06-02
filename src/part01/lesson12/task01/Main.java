package part01.lesson12.task01;

import javassist.CannotCompileException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws InterruptedException, CannotCompileException {
        heapException();
        metaspaceException();
    }

    /**
     * условие на VM, для получения Exception: -XX:MaxMetaspaceSize=64m
     * @throws CannotCompileException
     */
    private static void metaspaceException() throws CannotCompileException {
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("abc" + i).toClass();
        }
    }

    private static void heapException() {
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            int[] n = new int[i * i];
            if (i % 100 == 0) { //в тех итерациях, когда данное условие не выполнено, объекты n удаляются gc
                lists.add(n);
            }
        }
    }
}
