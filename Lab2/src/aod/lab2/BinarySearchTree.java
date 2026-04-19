package aod.lab2;

/**
 * En enkel implementation av ett binärt sökträd.
 *
 * Trädet lagrar element av typen T. Mindre värden hamnar till vänster
 * och större värden hamnar till höger.
 *
 * Klassen använder rekursion för att lägga till, söka och ta bort
 * element. Dubbletter ignoreras.
 *
 * @param <T> typen som lagras i trädet
 *
 * @author Abdisalan Mahad Omar
 * @date 2026-04-19
 */
public final class BinarySearchTree<T
    extends Comparable<? super T>> implements Tree<T> {

    /**
     * Roten i trädet.
     */
    private BSTNode root;

    /**
     * Antal element i trädet.
     */
    private int size;

    /**
     * Inre nodklass.
     */
    private class BSTNode {
        private T item;
        private BSTNode left;
        private BSTNode right;

        BSTNode(final T newItem) {
            this.item = newItem;
            this.left = null;
            this.right = null;
        }

        public T getItem() {
            return item;
        }

        public void setItem(final T newItem) {
            this.item = newItem;
        }

        public BSTNode getLeft() {
            return left;
        }

        public void setLeft(final BSTNode newLeft) {
            this.left = newLeft;
        }

        public BSTNode getRight() {
            return right;
        }

        public void setRight(final BSTNode newRight) {
            this.right = newRight;
        }
    }

    /**
     * Skapar ett tomt träd.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Lägger till ett element i trädet.
     *
     * @param item elementet som ska läggas till
     */
    @Override
    public void add(final T item) {
        if (item == null) {
            throw new IllegalArgumentException(
                "Null-värden får inte lagras i trädet."
            );
        }
        root = addRecursive(root, item);
    }

    /**
     * Hjälpmetod för add.
     *
     * @param current aktuell nod
     * @param item värdet som ska läggas till
     * @return roten till delträdet
     */
    private BSTNode addRecursive(final BSTNode current, final T item) {
        if (current == null) {
            size++;
            return new BSTNode(item);
        }

        int comparison = item.compareTo(current.getItem());

        if (comparison < 0) {
            current.setLeft(addRecursive(current.getLeft(), item));
        } else if (comparison > 0) {
            current.setRight(addRecursive(current.getRight(), item));
        }

        return current;
    }

    /**
     * Söker efter ett element i trädet.
     *
     * @param itemToSearchFor elementet som söks
     * @return true om elementet finns, annars false
     */
    @Override
    public boolean searchFor(final T itemToSearchFor) {
        if (itemToSearchFor == null) {
            return false;
        }
        return searchRecursive(root, itemToSearchFor);
    }

    /**
     * Hjälpmetod för sökning.
     *
     * @param current aktuell nod
     * @param item värdet som söks
     * @return true om värdet finns, annars false
     */
    private boolean searchRecursive(final BSTNode current, final T item) {
        if (current == null) {
            return false;
        }

        int comparison = item.compareTo(current.getItem());

        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return searchRecursive(current.getLeft(), item);
        } else {
            return searchRecursive(current.getRight(), item);
        }
    }

    /**
     * Returnerar antalet element i trädet.
     *
     * @return trädets storlek
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tömmer trädet.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Tar bort ett element ur trädet.
     *
     * @param itemToRemove elementet som ska tas bort
     * @return true om elementet togs bort, annars false
     */
    @Override
    public boolean remove(final T itemToRemove) {
        if (itemToRemove == null) {
            return false;
        }

        if (!searchFor(itemToRemove)) {
            return false;
        }

        root = removeRecursive(root, itemToRemove);
        size--;
        return true;
    }

    /**
     * Hjälpmetod för remove.
     *
     * @param current aktuell nod
     * @param item värdet som ska tas bort
     * @return roten till delträdet
     */
    private BSTNode removeRecursive(final BSTNode current, final T item) {
        if (current == null) {
            return null;
        }

        int comparison = item.compareTo(current.getItem());

        if (comparison < 0) {
            current.setLeft(removeRecursive(current.getLeft(), item));
            return current;
        }

        if (comparison > 0) {
            current.setRight(removeRecursive(current.getRight(), item));
            return current;
        }

        if (current.getLeft() == null && current.getRight() == null) {
            return null;
        }

        if (current.getLeft() == null) {
            return current.getRight();
        }

        if (current.getRight() == null) {
            return current.getLeft();
        }

        T smallestValue = findMin(current.getRight());
        current.setItem(smallestValue);
        current.setRight(
            removeRecursive(current.getRight(), smallestValue)
        );

        return current;
    }

    /**
     * Hämtar minsta värdet i ett delträd.
     *
     * @param current roten till delträdet
     * @return minsta värdet
     */
    private T findMin(final BSTNode current) {
        BSTNode temp = current;

        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }

        return temp.getItem();
    }

    /**
     * Returnerar trädets innehåll i sorterad ordning.
     *
     * @return en sträng med trädets innehåll
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    /**
     * Inorder-traversering.
     *
     * @param current aktuell nod
     * @param sb strängbyggare
     */
    private void inOrder(final BSTNode current, final StringBuilder sb) {
        if (current == null) {
            return;
        }

        inOrder(current.getLeft(), sb);
        sb.append(current.getItem()).append(" ");
        inOrder(current.getRight(), sb);
    }
}