package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[][] a;
    private static final int n = 8;

    @Override
    public void load(T[][] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i/n][i%n];
        a[i/n][i%n] = a[j/n][j%n];
        a[j/n][j%n] = temp;
        plan += "" + a[i/n][i%n] + "<->" + a[j/n][j%n] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < n*n - 1; i++) {
                if (a[i/n][i%n].compareTo(a[(i+1)/n][(i+1)%n]) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
        System.out.println("1");
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}