package Java.Structures.Stack;

public class LinkedStack<T> implements Stack<T> {

    private static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head = null;
    private int size = 0;

    @Override
    public void push(T value) {
        head = new Node<>(value, head);
        size++;
    }

    @Override
    public T pop() throws Exception {
        if (head == null) {
            throw new Exception("Stack is empty");
        }
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T peek() throws Exception {
        if (head == null) {
            throw new Exception("Stack is empty");
        }
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }
}
