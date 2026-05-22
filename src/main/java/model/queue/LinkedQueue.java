package model.queue;

import model.Node;

public class LinkedQueue<T> implements MyQueue<T> {
    private Node<T> frontNode;
    private Node<T> backNode;
    private int counter;

    public LinkedQueue() {
        this.frontNode = this.backNode = null;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        frontNode = backNode = null;
        counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return frontNode == null;
    }

    @Override
    public int indexOf(T element) throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        Node<T> aux = frontNode;
        int index = 1;
        while (aux != null) {
            if (aux.data.equals(element)) return index;
            index++;
            aux = aux.next;
        }
        return -1;
    }

    @Override
    public void enQueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            frontNode = backNode = newNode;
        } else {
            backNode.next = newNode;
            backNode = newNode;
        }
        counter++;
    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        T data = frontNode.data;
        frontNode = frontNode.next;
        if (frontNode == null) {
            backNode = null;
        }
        counter--;
        return data;
    }

    @Override
    public void enQueue(T element, Integer priority) throws QueueException {
        enQueue(element);
    }

    @Override
    public boolean contains(T element) throws QueueException {
        return indexOf(element) != -1;
    }

    @Override
    public T peek() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        return frontNode.data;
    }

    @Override
    public T front() throws QueueException {
        return peek();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Cola Vacía (LinkedQueue)";
        StringBuilder sb = new StringBuilder("FRENTE -> ");
        Node<T> aux = frontNode;
        while (aux != null) {
            sb.append("[").append(aux.data).append("] ");
            aux = aux.next;
        }
        sb.append("<- FINAL");
        return sb.toString();
    }
}