package Java.Structures.Tree;

public class BinarySearchTree<T extends Comparable<T>> {

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) return new Node<>(value);
        if (value.compareTo(node.value) < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertRecursive(node.right, value);
        }
        return node;
    }

    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) return false;
        if (value.equals(node.value)) return true;
        if (value.compareTo(node.value) < 0)
            return containsRecursive(node.left, value);
        else
            return containsRecursive(node.right, value);
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    private Node<T> deleteRecursive(Node<T> node, T value) {
        if (node == null) return null;

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // 0,1,2 потомка
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 2 потомка: заменяем на минимум в правом поддереве
            Node<T> minNode = findMin(node.right);
            node.value = minNode.value;
            node.right = deleteRecursive(node.right, minNode.value);
        }

        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node<T> node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.value + " ");
            inorderRecursive(node.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }
}
