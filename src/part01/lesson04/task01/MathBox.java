package part01.lesson04.task01;

import java.util.*;

public class MathBox {
    private Set<Number> set = new HashSet<>();

    public MathBox(Number[] array) {
        set.addAll(Arrays.asList(array));
    }

    /**
     * @return - this sum of the elements
     */
    public double summator() {
        double result = 0;
        for (Number n : set) {
            result += n.doubleValue();
        }
        return result;
    }

    /**
     * @param splitterNumber - this number is the divisor
     */
    public void splitter(Number splitterNumber) {
        if (splitterNumber.doubleValue() == 0) {
            try {
                throw new InputNumberException("Сannot be divided by zero!");
            } catch (InputNumberException e) {
                e.printStackTrace();
            }
        }
        List<Number> list = new ArrayList<>();
        Iterator<Number> iter = set.iterator();
        while (iter.hasNext()) {
            Number number = iter.next();
            iter.remove();
            list.add(number.doubleValue() / splitterNumber.doubleValue());
        }
        set.addAll(list);
    }

    /**
     * @param i - this number need
     */
    public void delete(Integer i) {
        set.remove(i.doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return set.equals(mathBox.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    /**
     * @return string of the elements
     */
    @Override
    public String toString() {
        return set.toString();
    }
}
