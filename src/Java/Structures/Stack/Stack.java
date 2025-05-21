package Java.Structures.Stack;

public interface Stack<T> {
    void push(T value);

    T pop() throws Exception;

    T peek() throws Exception;

    int size();

    default boolean empty() {
        return size() == 0;
    }
}
