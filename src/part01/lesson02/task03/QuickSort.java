package part01.lesson02.task03;

import java.util.Comparator;

public class QuickSort implements Sort {
    @Override
    public void sort(Person[] array, Comparator<Person> c) {
        quickSort(array, c, 0, array.length - 1);
    }

    private void quickSort(Person[] array, Comparator<Person> c, int begin, int end) {
        if (begin < end) {
            int indexPivot = partition(array, c, begin, end);
            quickSort(array, c, begin, indexPivot - 1);
            quickSort(array, c, indexPivot + 1, end);
        }
    }

    private int partition(Person[] array, Comparator<Person> c, int begin, int end) {
        Person pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j <= end; j++) {
            if (c.compare(array[j], pivot) <= 0) {
                i++;
                Person p = array[i];
                array[i] = array[j];
                array[j] = p;
            }
        }
        return i;
    }
}
