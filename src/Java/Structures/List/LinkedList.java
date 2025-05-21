package Java.Structures.List;

public interface LinkedList<T> extends Iterable<T> {
    void addFirst(T value);
    void addLast(T value);
    void insert(int index, T value) throws Exception;

    T getFirst() throws Exception;
    T getLast() throws Exception;
    T get(int index) throws Exception;

    T removeFirst() throws Exception;
    T removeLast() throws Exception;
    T remove(int index) throws Exception;

    int size();
    boolean isEmpty();
    void clear();
}
