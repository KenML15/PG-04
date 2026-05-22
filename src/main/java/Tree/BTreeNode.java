package Tree;

public class BTreeNode<T> {
    public T data;
    public BTreeNode<T> left, right;
    public String path;//ruta de inserción de elementos, ejemplo, inserto como root, left, rigth

    public BTreeNode(T data) {
        this.data = data;
        this.left = right = null;
    }

    public BTreeNode(T data, String path){
        this.data = data;
        this.left = right = null;
        this.path = path;
    }

}
