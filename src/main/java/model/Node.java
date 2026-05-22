package model;

public class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;
    public Integer priority;

    public Node() {
        this.data = null;
        this.next = this.prev = null;
        this.priority = null;
    }

    public Node(T data) {
        this.data = data;
        this.next = this.prev = null;
        this.priority = null;
    }

    public Node(T data, Integer priority) {
        this.data = data;
        this.priority = priority;
        this.next = this.prev = null;
    }
}