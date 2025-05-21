package Java.Structures.List;

import java.util.Iterator;

public class SinglyLinkedList<T> implements LinkedList<T> {

    private static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this(value, null);
        }

        public Node() {
            this(null, null);
        }
    }


    private Node<T> head = null;
    private Node<T> tail = null;
    private int count = 0;


    private void checkEmpty() throws LinkedListException {
        if (isEmpty()) {
            throw new LinkedListException("List is empty");
        }
    }

    @Override
    public T getFirst() throws LinkedListException {
        checkEmpty();
        return head.value;
    }

    @Override
    public T getLast() throws LinkedListException {
        checkEmpty();
        return tail.value;
    }

    private Node<T> getItem(int index) throws LinkedListException {
        if (index < 0 || index >= count) {
            throw new LinkedListException("Incorrect index");
        }
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    @Override
    public T get(int index) throws LinkedListException {
        return getItem(index).value;
    }

    @Override
    public void addFirst(T value) {
        head = new Node<>(value, head);
        if (tail == null) {
            tail = head;
        }
        count++;
    }

    @Override
    public void addLast(T value) {
        if (tail == null) {
            head = tail = new Node<>(value);
        } else {
            tail = tail.next = new Node<>(value);
        }
        count++;
    }

    @Override
    public void insert(int index, T value) throws LinkedListException {
        if (index < 0 || index > count) {
            throw new LinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            Node<T> prev = getItem(index - 1);
            prev.next = new Node<>(value, prev.next);
            if (prev.next.next == null) {
                tail = prev.next;
            }
            count++;
        }
    }

    @Override
    public T removeFirst() throws LinkedListException {
        checkEmpty();
        T value = getFirst();
        head = head.next;
        if (head == null) {
            tail = null;
        }
        count--;
        return value;
    }

    @Override
    public T removeLast() throws LinkedListException {
        return remove(count - 1);
    }

    @Override
    public T remove(int index) throws LinkedListException {
        if (index == 0) {
            return removeFirst();
        } else {
            Node<T> prev = getItem(index - 1);
            T value = prev.next.value;
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            count--;
            return value;
        }
    }

    @Override
    public void clear() {
        head = tail = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        };
    }
}
