package part01.lesson11.task01;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Factorial implements Callable<BigInteger> {

    private BigInteger[] array;
    private BigInteger number;
    private static Map<BigInteger, BigInteger> factorials = new HashMap<>();

    private Factorial setNumber(BigInteger number) {
        this.number = number;
        return this;
    }

    public BigInteger[] calculate(int[] array) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        this.array =  Arrays.stream(array)
                .mapToObj(x-> {
                    try {
                        return executor.submit(new Factorial().setNumber(BigInteger.valueOf(x))).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .toArray(BigInteger[]::new);
        executor.shutdown();
        return this.array;
    }

    private BigInteger factorial(BigInteger number) {
        return Stream.iterate(BigInteger.TWO, i -> i.add(BigInteger.ONE))
                .limit(number.equals(BigInteger.ZERO) ? 0 : number.longValue() - 1)
                .reduce((a, b) -> {
                    BigInteger result = a.multiply(b);
                    factorials.put(a, result);
                    return result;
                }).orElse(BigInteger.ONE);
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
