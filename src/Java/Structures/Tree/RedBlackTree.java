package Java.Structures.Tree;

public class RedBlackTree<T extends Comparable<T>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        T value;
        Node left, right, parent;
        boolean color;

        Node(T value, boolean color, Node parent) {
            this.value = value;
            this.color = color;
            this.parent = parent;
        }
    }

    private Node root;

    // Публичный метод вставки
    public void insert(T value) {
        root = bstInsert(root, value, null);
        fixInsert(findNode(root, value));
    }

    // Публичный метод проверки наличия
    public boolean contains(T value) {
        return findNode(root, value) != null;
    }

    // Вставка по правилам BST
    private Node bstInsert(Node node, T value, Node parent) {
        if (node == null) {
            return new Node(value, RED, parent);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = bstInsert(node.left, value, node);
        } else if (cmp > 0) {
            node.right = bstInsert(node.right, value, node);
        }
        return node;
    }

    // Поиск узла с заданным значением
    private Node findNode(Node node, T value) {
        while (node != null) {
            int cmp = value.compareTo(node.value);
            if (cmp == 0) return node;
            node = (cmp < 0) ? node.left : node.right;
        }
        return null;
    }

    // Восстановление свойств после вставки
    private void fixInsert(Node node) {
        Node parent, grandParent;

        while (node != root && node.color == RED && node.parent.color == RED) {
            parent = node.parent;
            grandParent = parent.parent;

            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                if (uncle != null && uncle.color == RED) {
                    // Случай 1: дядя красный — перекрасить
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandParent.color = RED;
                    node = grandParent;
                } else {
                    if (node == parent.right) {
                        // Случай 2: узел — правый ребёнок
                        node = parent;
                        rotateLeft(node);
                        parent = node.parent;
                    }
                    // Случай 3: узел — левый ребёнок
                    parent.color = BLACK;
                    grandParent.color = RED;
                    rotateRight(grandParent);
                }
            } else {
                // зеркальный случай для правого поддерева
                Node uncle = grandParent.left;

                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandParent.color = RED;
                    node = grandParent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                        parent = node.parent;
                    }
                    parent.color = BLACK;
                    grandParent.color = RED;
                    rotateLeft(grandParent);
                }
            }
        }

        root.color = BLACK;
    }

    // Левый поворот вокруг node
    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null)
            rightChild.left.parent = node;

        rightChild.parent = node.parent;

        if (node.parent == null)
            root = rightChild;
        else if (node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;

        rightChild.left = node;
        node.parent = rightChild;
    }

    // Правый поворот вокруг node
    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null)
            leftChild.right.parent = node;

        leftChild.parent = node.parent;

        if (node.parent == null)
            root = leftChild;
        else if (node == node.parent.left)
            node.parent.left = leftChild;
        else
            node.parent.right = leftChild;

        leftChild.right = node;
        node.parent = leftChild;
    }

    // Inorder обход для проверки
    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.value + (node.color == RED ? "(R) " : "(B) "));
            inorderRecursive(node.right);
        }
    }
}
