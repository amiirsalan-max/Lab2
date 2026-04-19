package aod.lab2;

public interface Tree<T> {

    void add(T item);

    boolean searchFor(T itemToSearchFor);

    int size();

    void clear();

    boolean remove(T itemToRemove);
}