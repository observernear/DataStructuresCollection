package Java.Structures.Tree;

public class AVLTree<T extends Comparable<T>> {

    private static class Node<T> {
        T value;
        Node<T> left, right;
        int height;

        Node(T value) {
            this.value = value;
            height = 1;
        }
    }

    private Node<T> root;

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    private int height(Node<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) return new Node<>(value);

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, value);
        } else {
            return node; // дубликаты не вставляются
        }

        return balance(node);
    }

    private Node<T> deleteRecursive(Node<T> node, T value) {
        if (node == null) return null;

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Удаление
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node<T> min = findMin(node.right);
                node.value = min.value;
                node.right = deleteRecursive(node.right, min.value);
            }
        }

        return balance(node);
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node<T> balance(Node<T> node) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) return false;
        int cmp = value.compareTo(node.value);
        if (cmp == 0) return true;
        return (cmp < 0) ? containsRecursive(node.left, value) : containsRecursive(node.right, value);
    }

    private void inorderRecursive(Node<T> node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.value + " ");
            inorderRecursive(node.right);
        }
    }
}
