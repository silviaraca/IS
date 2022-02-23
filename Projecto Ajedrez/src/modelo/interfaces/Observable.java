package modelo.interfaces;

public interface Observable<T> {
	void addObserver(T[][] o);
	void removeObserver(T[][] o);
	void addObserver(T o);
	void removeObserver(T o);
}
