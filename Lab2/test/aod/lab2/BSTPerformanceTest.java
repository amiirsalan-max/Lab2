package aod.lab2;

import java.util.Random;

/**
 * Klass för att testa tidsåtgången för sökning i BinarySearchTree.
 *
 * @author Abdisalan Mahad Omar
 * @date 2026-04-19
 */
public class BSTPerformanceTest {

	public static void main(String[] args) {
		int[] sizes = { 5000, 10000, 20000, 40000 };
		int repetitions = 1000;
		Random rand = new Random();

		System.out.println("=== TEST AV SLUMPADE TAL ===");

		for (int n : sizes) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<>();

			try {
				for (int i = 0; i < n; i++) {
					tree.add(rand.nextInt(n * 10));
				}

				long startTime = System.nanoTime();
				for (int i = 0; i < repetitions; i++) {
					tree.searchFor(rand.nextInt(n * 10));
				}
				long endTime = System.nanoTime();
				long searchTime = endTime - startTime;

				System.out.println("n = " + n);
				System.out.println("Tid: " + searchTime + " ns");
				System.out.println();

			} catch (Exception e) {
				System.out.println("n = " + n);
				System.out.println("Programmet failed");
				System.out.println();
			}
		}

		System.out.println("=== TEST AV SORTERADE TAL ===");

		for (int n : sizes) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<>();

			try {
				for (int i = 0; i < n; i++) {
					tree.add(i);
				}

				long startTime = System.nanoTime();
				for (int i = 0; i < repetitions; i++) {
					tree.searchFor(rand.nextInt(n * 2));
				}
				long endTime = System.nanoTime();
				long searchTime = endTime - startTime;

				System.out.println("n = " + n);
				System.out.println("Tid: " + searchTime + " ns");
				System.out.println();

			} catch (Throwable e) {
				System.out.println("n = " + n);
				System.out.println("Programmet failed");
				System.out.println();
			}
		}
	}
}