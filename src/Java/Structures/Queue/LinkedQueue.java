package Java.Structures.Queue;

public class LinkedQueue<T> implements Queue<T> {

    private static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void add(T value) {
        if (head == null) {
            head = tail = new Node<>(value, null);
        } else {
            tail.next = new Node<>(value, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public T remove() throws Exception {
        if (head == null) {
            throw new Exception("Queue is empty");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public T element() throws Exception {
        if (head == null) {
            throw new Exception("Queue is empty");
        }
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }
}
