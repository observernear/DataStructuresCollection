package Java.Structures.Queue;

public interface Deque<T> extends Queue<T> {
    void addFirst(T value);
    void addLast(T value);

    T removeFirst() throws Exception;
    T removeLast() throws Exception;

    T getFirst() throws Exception;
    T getLast() throws Exception;
}
