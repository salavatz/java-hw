package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Factorial implements Callable<BigInteger[]> {

    private BigInteger[] array;
    private static Map<BigInteger, BigInteger> factorials = new HashMap<>();

    private Factorial setArray(BigInteger[] array) {
        this.array = array;
        return this;
    }

    public BigInteger[] calculate(int[] array) {
        this.array = new BigInteger[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = BigInteger.valueOf(array[i]);
        }
        int sizeOfSubarray = array.length % 10 == 0 ? array.length / 10 : array.length / 10 + 1; //верхнее округление
        int sizeOfLastSubarray = array.length - 10 * sizeOfSubarray;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Future<BigInteger[]> future = executor.submit(new Factorial().setArray(Arrays.copyOfRange(
                    this.array,
                    i * sizeOfSubarray,
                    i < 9 ? (i + 1) * sizeOfSubarray : array.length)));
            try {
                System.arraycopy(
                        future.get(),
                        0,
                        this.array,
                        i * sizeOfSubarray,
                        i < 9 || array.length % 10 == 0 ? array.length / 10 : sizeOfLastSubarray);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return this.array;
    }

    private BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.valueOf(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (long i = 2; i <= number.longValue(); i++) {
            BigInteger j = BigInteger.valueOf(i);
            result = result.multiply(j);
        }
        return result;
    }

    @Override
    public BigInteger[] call() {
        for (int i = 0; i < array.length; i++) {
            BigInteger number = array[i];
            if (factorials.get(number) != null) {
                array[i] = factorials.get(number);
                continue;
            }
            array[i] = factorial(array[i]);
            factorials.put(number, array[i]);
        }
        return array;
    }
}
