package aod.lab2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Testklass för BinarySearchTree.
 *
 * @author Abdisalan Mahad Omar
 * @date 2026-04-19
 */
public class BinarySearchTreeTest {

	@Test
	public void testAddOneElement() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(10);

		assertEquals(1, tree.size());
		assertTrue(tree.searchFor(10));
	}

	@Test
	public void testAddSeveralElements() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(10);
		tree.add(5);
		tree.add(15);

		assertEquals(3, tree.size());
		assertTrue(tree.searchFor(5));
		assertTrue(tree.searchFor(10));
		assertTrue(tree.searchFor(15));
	}

	@Test
	public void testDuplicateNotAdded() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(10);
		tree.add(10);

		assertEquals(1, tree.size());
	}

	@Test
	public void testSearchExistingElement() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);
		tree.add(10);

		assertTrue(tree.searchFor(3));
	}

	@Test
	public void testSearchMissingElement() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);
		tree.add(10);

		assertFalse(tree.searchFor(99));
	}

	@Test
	public void testSizeAfterAdditions() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(1);
		tree.add(2);
		tree.add(3);

		assertEquals(3, tree.size());
	}

	@Test
	public void testClear() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(1);
		tree.add(2);

		tree.clear();

		assertEquals(0, tree.size());
		assertFalse(tree.searchFor(1));
	}

	@Test
	public void testToString() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);
		tree.add(10);
		tree.add(1);
		tree.add(6);

		assertEquals("1 3 6 8 10", tree.toString());
	}

	@Test
	public void testRemoveLeaf() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);
		tree.add(10);
		tree.add(1);

		assertTrue(tree.remove(1));
		assertEquals(3, tree.size());
		assertFalse(tree.searchFor(1));
	}

	@Test
	public void testRemoveNodeWithOneChild() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);
		tree.add(10);
		tree.add(14);

		assertTrue(tree.remove(10));
		assertEquals(3, tree.size());
		assertFalse(tree.searchFor(10));
	}

	@Test
	public void testRemoveNodeWithTwoChildren() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);
		tree.add(10);
		tree.add(1);
		tree.add(6);

		assertTrue(tree.remove(3));
		assertEquals(4, tree.size());
		assertFalse(tree.searchFor(3));
	}

	@Test
	public void testRemoveMissingElement() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(8);
		tree.add(3);

		assertFalse(tree.remove(99));
		assertEquals(2, tree.size());
	}
}