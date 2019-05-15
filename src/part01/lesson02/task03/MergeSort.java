package part01.lesson02.task03;

import java.util.Comparator;

public class MergeSort implements Sort{

    @Override
    public void sort(Person[] array, Comparator<Person> c) {
        int n = array.length;
        if (n < 2) {
            return;
        }
        int mid = (n + 1) / 2;
        Person[] l = new Person[mid];
        Person[] r = new Person[n - mid];
        for (int i = 0; i < mid; i++) {
            l[i] = array[i];
        }
        for (int i = 0; i < n - mid; i++) {
            r[i] = array[mid + i];
        }
        sort(l, c);
        sort(r, c);
        merge(array, l, r, mid, n - mid, c);
    }

    private void merge(Person[] a, Person[] l, Person[] r, int left, int right, Comparator<Person> c) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (c.compare(l[i], r[j]) <= 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}