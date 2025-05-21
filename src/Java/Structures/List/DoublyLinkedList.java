package Java.Structures.List;

import java.util.Iterator;

public class DoublyLinkedList<T> implements LinkedList<T> {

    private static class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public Node(T value) {
            this(value, null, null);
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

    private Node<T> getNode(int index) throws LinkedListException {
        if (index < 0 || index >= count) {
            throw new LinkedListException("Incorrect index");
        }
        Node<T> curr;
        if (index < count / 2) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = count - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    @Override
    public T get(int index) throws LinkedListException {
        return getNode(index).value;
    }

    @Override
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, null, head);
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        count++;
    }

    @Override
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value, tail, null);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
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
        } else if (index == count) {
            addLast(value);
        } else {
            Node<T> nextNode = getNode(index);
            Node<T> prevNode = nextNode.prev;
            Node<T> newNode = new Node<>(value, prevNode, nextNode);
            prevNode.next = newNode;
            nextNode.prev = newNode;
            count++;
        }
    }

    @Override
    public T removeFirst() throws LinkedListException {
        checkEmpty();
        T value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        count--;
        return value;
    }

    @Override
    public T removeLast() throws LinkedListException {
        checkEmpty();
        T value = tail.value;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        count--;
        return value;
    }

    @Override
    public T remove(int index) throws LinkedListException {
        if (index == 0) return removeFirst();
        if (index == count - 1) return removeLast();
        Node<T> node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        count--;
        return node.value;
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
        return count == 0;
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
