package Tree;

import java.util.Random;

public class BTree<T extends Comparable<T>> implements Tree<T> {
    private BTreeNode<T> root; //representa la univa entrada del arbol

    //constructor
    public BTree() {
        this.root = null;

    }


    @Override
    public int size() throws TreeException {
        if (isEmpty()) throw new TreeException("Binary Tree is empty");
        return size(root);
    }

    public int size(BTreeNode<T> node) {
        if (node == null) return 0;
        return size(node.left) + size(node.right) + 1;

    }

    @Override
    public void clear() {
        this.root = null;

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(T element) throws TreeException {
        if (isEmpty()) throw new TreeException("Binary Tree is empty");
        return false;
    }
    private boolean binarySearch(BTreeNode<T> node, T element) {
        if (node == null) return false;
        else if (equals(node.data, element))return true;
        else return binarySearch(node.left, element)
                    || binarySearch(node.right, element);


    }


    @Override
    public void add(T element) {
        this.root = add(root, element, "root");

    }

    private BTreeNode<T> add(BTreeNode<T> node, T element, String path) {
        if (node == null) {
            node = new BTreeNode<>(element, path);
        } else {
            //debemos establecer algún criterio para insertar elementos
            int value = new Random().nextInt(10);
            if (value % 2 == 0) // si es par inserte por al izquierda
                node.left = add(node.left, element, path + "/left");
            else
                node.right = add(node.right, element, path + "/rigth");


        }
        return node;
    }

    public BTreeNode<T> add(BTreeNode<T> node, T element) {
        if (node == null) {
            node = new BTreeNode<>(element);
        } else {
            //debemos establecer algún criterio para insertar elementos
            int value = new Random().nextInt(0, 10);
            if (value % 2 == 0) // si es par inserte por al izquierda
                node.left = add(node.left, element);
            else
                node.right = add(node.right, element);


        }
        return node;
    }

    @Override
    public void remove(T element) throws TreeException {

    }

    @Override
    public int height(T element) throws TreeException {
        return 0;
    }

    @Override
    public int height() throws TreeException {
        return 0;
    }

    @Override
    public T min() throws TreeException {
        return null;
    }

    @Override
    public T max() throws TreeException {
        return null;
    }

    @Override
    public String preOrder() throws TreeException {
        if (isEmpty()) throw new TreeException("Binary Tree is empty");
        return preOrder(root);
    }

    //recorrido N-L-R
    private String preOrder(BTreeNode<T> node) {
        String result = "";
        if (node != null) {
            result += node.data + "(" + node.path + ")";
            result += preOrder(node.left);
            result += preOrder(node.right);
        }
        return result;
    }

    @Override
    public String inOrder() throws TreeException {
        if (isEmpty()) throw new TreeException("Binary Tree is empty");
        return inOrder(root);
    }

    //recorrido L-N-R
    private String inOrder(BTreeNode<T> node) {
        String result = "";
        if (node != null) {
            result += inOrder(node.left);
            result += node.data + "(" + node.path + ")" + " ";
            result += inOrder(node.right);
        }
        return result;
    }


    @Override
    public String postOrder() throws TreeException {
        if (isEmpty()) throw new TreeException("Binary Tree is empty");
        return postOrder(root);
    }

    //recorrido L-R-N
    private String postOrder(BTreeNode<T> node) {
        String result = "";
        if (node != null) {

            result += postOrder(node.left);
            result += postOrder(node.right);
            result += node.data + "(" + node.path + ")";
        }
        return result;
    }

    @Override
    public String nodeHeight() throws TreeException {
        return "";
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Binary Tree is empty";
        String result = "Binary Tree Tour\n";
        try {
            result += "PreOrder (N-L-R):  " + preOrder() + "\n";
            result += "InOrder (L):   " + inOrder() + "\n";
            result += "PostOrder: " + postOrder() + "\n";
            result += "Height:    " + height() + "\n";
        } catch (TreeException e) {
            result += e.getMessage();
        }
        return result;
    }

    //ayudas
    private boolean equals(T a, T b) {
        return a == null ? b == null : a.equals(b);
    }

}
