package aod.lab2;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        System.out.println("===== TEST AV add() och toString() =====");
        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(13);

        System.out.println("Trädets innehåll (inorder):");
        System.out.println(tree);
        System.out.println("Förväntat: 1 3 4 6 7 8 10 13 14");
        System.out.println();

        System.out.println("===== TEST AV size() =====");
        System.out.println("Storlek: " + tree.size());
        System.out.println("Förväntat: 9");
        System.out.println();

        System.out.println("===== TEST AV searchFor() =====");
        System.out.println("Finns 6? " + tree.searchFor(6));
        System.out.println("Förväntat: true");
        System.out.println();

        System.out.println("Finns 2? " + tree.searchFor(2));
        System.out.println("Förväntat: false");
        System.out.println();

        System.out.println("===== TEST AV DUBLETTER =====");
        tree.add(6);
        System.out.println("Trädet efter att lägga till 6 igen:");
        System.out.println(tree);
        System.out.println("Storlek efter dublettförsök: " + tree.size());
        System.out.println("Förväntat innehåll: oförändrat");
        System.out.println("Förväntad storlek: 9");
        System.out.println();

        System.out.println("===== TEST AV remove() bladnod =====");
        System.out.println("Tar bort 1: " + tree.remove(1));
        System.out.println("Förväntat: true");
        System.out.println("Trädet nu: " + tree);
        System.out.println("Förväntat: 3 4 6 7 8 10 13 14");
        System.out.println("Storlek: " + tree.size());
        System.out.println("Förväntat: 8");
        System.out.println();

        System.out.println("===== TEST AV remove() nod med ett barn =====");
        System.out.println("Tar bort 14: " + tree.remove(14));
        System.out.println("Förväntat: true");
        System.out.println("Trädet nu: " + tree);
        System.out.println("Förväntat: 3 4 6 7 8 10 13");
        System.out.println("Storlek: " + tree.size());
        System.out.println("Förväntat: 7");
        System.out.println();

        System.out.println("===== TEST AV remove() nod med två barn =====");
        System.out.println("Tar bort 3: " + tree.remove(3));
        System.out.println("Förväntat: true");
        System.out.println("Trädet nu: " + tree);
        System.out.println("Förväntat: 4 6 7 8 10 13");
        System.out.println("Storlek: " + tree.size());
        System.out.println("Förväntat: 6");
        System.out.println();

        System.out.println("===== TEST AV remove() roten =====");
        System.out.println("Tar bort 8: " + tree.remove(8));
        System.out.println("Förväntat: true");
        System.out.println("Trädet nu: " + tree);
        System.out.println("Storlek: " + tree.size());
        System.out.println();

        System.out.println("===== TEST AV remove() värde som inte finns =====");
        System.out.println("Tar bort 99: " + tree.remove(99));
        System.out.println("Förväntat: false");
        System.out.println("Trädet nu: " + tree);
        System.out.println();

        System.out.println("===== TEST AV clear() =====");
        tree.clear();
        System.out.println("Trädet efter clear(): '" + tree + "'");
        System.out.println("Förväntat: tom sträng");
        System.out.println("Storlek: " + tree.size());
        System.out.println("Förväntat: 0");
    }
}