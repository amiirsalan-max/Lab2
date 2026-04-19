package aod.lab2;

import java.util.Random;

public class BSTPerformanceTest {

    // Mindre storlekar → säkrare (undviker stack overflow)
    private static final int[] SIZES = {5000, 10000, 20000, 40000};

    // Antal sökningar (samma för alla tester)
    private static final int SEARCHES = 1000;

    private static final Random rand = new Random();

    public static void main(String[] args) {

        System.out.println("===== SLUMPADE TAL =====");
        long[] randomTimes = testRandom();

        System.out.println("\n===== SORTERADE TAL =====");
        long[] sortedTimes = testSorted();

        System.out.println("\n===== KVOTER T(2n)/T(n) =====");

        printQuotients("Slumpade", randomTimes);
        printQuotients("Sorterade", sortedTimes);
    }

    // =========================
    // Slumpade tal
    // =========================
    private static long[] testRandom() {
        long[] times = new long[SIZES.length];

        for (int i = 0; i < SIZES.length; i++) {
            int n = SIZES[i];
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();

            // fyll trädet
            for (int j = 0; j < n; j++) {
                tree.add(rand.nextInt(n * 10));
            }

            // mät tid
            long start = System.nanoTime();

            for (int j = 0; j < SEARCHES; j++) {
                tree.searchFor(rand.nextInt(n * 10));
            }

            long end = System.nanoTime();

            times[i] = end - start;

            System.out.println("n = " + n + " | tid = " + times[i] + " ns");
        }

        return times;
    }

    // =========================
    // Sorterade tal
    // =========================
    private static long[] testSorted() {
        long[] times = new long[SIZES.length];

        for (int i = 0; i < SIZES.length; i++) {
            int n = SIZES[i];
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();

            // sorterad inmatning → sämsta fall
            for (int j = 0; j < n; j++) {
                tree.add(j);
            }

            long start = System.nanoTime();

            for (int j = 0; j < SEARCHES; j++) {
                tree.searchFor(rand.nextInt(n * 2));
            }

            long end = System.nanoTime();

            times[i] = end - start;

            System.out.println("n = " + n + " | tid = " + times[i] + " ns");
        }

        return times;
    }

    // =========================
    // Kvoter
    // =========================
    private static void printQuotients(String name, long[] times) {
        System.out.println("\n" + name + ":");

        for (int i = 0; i < times.length - 1; i++) {
            double q = (double) times[i + 1] / times[i];
            System.out.println("T(" + SIZES[i + 1] + ")/T(" + SIZES[i] + ") = " + q);
        }
    }
}