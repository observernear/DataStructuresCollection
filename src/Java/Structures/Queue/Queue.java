package Java.Structures.Queue;

public interface Queue<T> {
    void add(T value);

    T remove() throws Exception;

    T element() throws Exception;

    int size();

    default boolean empty() {
        return size() == 0;
    }
}
