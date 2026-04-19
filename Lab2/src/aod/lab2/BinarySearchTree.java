package aod.lab2;

/**
 * En implementation av ett binärt sökträd (BST).
 *
 * Trädet lagrar element av typen T, där T måste kunna jämföras
 * med hjälp av Comparable.
 *
 * Dubletter hanteras genom att ignoreras:
 * om ett värde redan finns i trädet läggs det inte till igen.
 *
 * @param <T> typen som lagras i trädet
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Tree<T> {

    /** Referens till trädets rot. */
    private BSTNode root;

    /** Antal element i trädet. */
    private int size;

    /**
     * Inre nodklass för BST.
     * Behöver inte egen generic eftersom den använder T från ytterklassen.
     */
    private class BSTNode {
        T item;
        BSTNode left;
        BSTNode right;

        BSTNode(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Skapar ett tomt binärt sökträd.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Lägger till item i trädet på rätt plats enligt BST-reglerna.
     * Om item redan finns ignoreras det.
     *
     * @param item elementet som ska läggas till
     */
    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Null-värden får inte lagras i trädet.");
        }
        root = addRecursive(root, item);
    }

    /**
     * Rekursiv hjälpfunktion för add.
     *
     * Basfall:
     * - Om aktuell nod är null skapas en ny nod.
     *
     * Rekursionssteg:
     * - Mindre värde går vänster.
     * - Större värde går höger.
     * - Lika värde ignoreras.
     *
     * @param current aktuell nod
     * @param item värde att lägga till
     * @return roten till det uppdaterade delträdet
     */
    private BSTNode addRecursive(BSTNode current, T item) {
        if (current == null) {
            size++;
            return new BSTNode(item);
        }

        int comparison = item.compareTo(current.item);

        if (comparison < 0) {
            current.left = addRecursive(current.left, item);
        } else if (comparison > 0) {
            current.right = addRecursive(current.right, item);
        }
        // Dublett: gör inget

        return current;
    }

    /**
     * Söker efter ett element i trädet.
     *
     * @param itemToSearchFor element att söka efter
     * @return true om elementet finns, annars false
     */
    @Override
    public boolean searchFor(T itemToSearchFor) {
        if (itemToSearchFor == null) {
            return false;
        }
        return searchRecursive(root, itemToSearchFor);
    }

    /**
     * Rekursiv hjälpfunktion för sökning.
     *
     * Basfall:
     * - null => elementet finns inte
     * - lika => elementet hittat
     *
     * Rekursionssteg:
     * - mindre går vänster
     * - större går höger
     *
     * @param current aktuell nod
     * @param item värde att söka efter
     * @return true om värdet finns, annars false
     */
    private boolean searchRecursive(BSTNode current, T item) {
        if (current == null) {
            return false;
        }

        int comparison = item.compareTo(current.item);

        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return searchRecursive(current.left, item);
        } else {
            return searchRecursive(current.right, item);
        }
    }

    /**
     * Returnerar antal element i trädet.
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
     * Returnerar true om elementet fanns och togs bort,
     * annars false.
     *
     * @param itemToRemove elementet som ska tas bort
     * @return true om borttagning skedde, annars false
     */
    @Override
    public boolean remove(T itemToRemove) {
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
     * Rekursiv hjälpfunktion för remove.
     *
     * Fall:
     * 1. Noden finns inte -> returnera null
     * 2. Värdet är mindre/större -> sök vidare vänster/höger
     * 3. Noden hittad:
     *    - inget barn -> returnera null
     *    - ett barn -> returnera barnet
     *    - två barn -> ersätt med minsta värdet i höger delträd
     *
     * @param current aktuell nod
     * @param item värdet som ska tas bort
     * @return roten till det uppdaterade delträdet
     */
    private BSTNode removeRecursive(BSTNode current, T item) {
        if (current == null) {
            return null;
        }

        int comparison = item.compareTo(current.item);

        if (comparison < 0) {
            current.left = removeRecursive(current.left, item);
            return current;
        }

        if (comparison > 0) {
            current.right = removeRecursive(current.right, item);
            return current;
        }

        // Här har vi hittat noden som ska bort.

        // Fall 1: bladnod
        if (current.left == null && current.right == null) {
            return null;
        }

        // Fall 2: endast höger barn
        if (current.left == null) {
            return current.right;
        }

        // Fall 3: endast vänster barn
        if (current.right == null) {
            return current.left;
        }

        // Fall 4: två barn
        // Ersätt med minsta värdet i höger delträd
        T smallestValue = findMin(current.right);
        current.item = smallestValue;
        current.right = removeRecursive(current.right, smallestValue);

        return current;
    }

    /**
     * Hämtar minsta värdet i ett delträd.
     *
     * @param current roten till delträdet
     * @return minsta värdet i delträdet
     */
    private T findMin(BSTNode current) {
        while (current.left != null) {
            current = current.left;
        }
        return current.item;
    }

    /**
     * Returnerar trädets innehåll i sorterad ordning
     * med inorder-traversering.
     *
     * @return en strängrepresentation av trädet
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    /**
     * Rekursiv inorder-traversering.
     *
     * @param current aktuell nod
     * @param sb strängbyggare
     */
    private void inOrder(BSTNode current, StringBuilder sb) {
        if (current == null) {
            return;
        }

        inOrder(current.left, sb);
        sb.append(current.item).append(" ");
        inOrder(current.right, sb);
    }
}