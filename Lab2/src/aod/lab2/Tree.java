package aod.lab2;

/**
 * Interface för ett träd.
 *
 * Definierar grundläggande metoder för att hantera en datastruktur som lagrar
 * element i ett träd.
 *
 * @param <T> typen som lagras i trädet
 *
 * @author Abdisalan Mahad Omar
 * @date 2026-04-19
 */
public interface Tree<T> {
	void add(T item);

	boolean searchFor(T itemToSearchFor);

	int size();

	void clear();

	boolean remove(T itemToRemove);
}