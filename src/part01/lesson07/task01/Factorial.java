package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Factorial implements Callable<BigInteger> {

    private BigInteger[] array;
    private BigInteger number;
    private static Map<BigInteger, BigInteger> factorials = new HashMap<>();

    private Factorial setNumber(BigInteger number) {
        this.number = number;
        return this;
    }

    public BigInteger[] calculate(int[] array) {
        this.array = new BigInteger[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = BigInteger.valueOf(array[i]);
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < array.length; i++) {
            Future<BigInteger> future = executor.submit(new Factorial().setNumber(this.array[i]));
            try {
                this.array[i] = future.get();
            } catch (InterruptedException | ExecutionException e) {
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
            factorials.put(BigInteger.valueOf(i), result);
        }
        return result;
    }

    @Override
    public BigInteger call() {
        if (factorials.get(number) != null) {
            return factorials.get(number);
        }
        BigInteger result = factorial(number);
        factorials.put(number, result);
        return result;
    }
}
