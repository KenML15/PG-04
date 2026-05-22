package model.queue;

import model.Node;

public class PriorityLinkedQueue<T> implements MyQueue<T> {
    private Node<T> frontNode;
    private int counter;

    public PriorityLinkedQueue() {
        this.frontNode = null;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        frontNode = null;
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
    public void enQueue(T element) throws QueueException {
        enQueue(element, 10);
    }

    @Override
    public void enQueue(T element, Integer priority) throws QueueException {
        Node<T> newNode = new Node<>(element, priority);

        // Caso 1: Vacía o tiene mayor prioridad (menor número) que el frente actual
        if (isEmpty() || priority < frontNode.priority) {
            newNode.next = frontNode;
            frontNode = newNode;
        } else {
            // Caso 2: Buscar posición intermedia o final
            Node<T> aux = frontNode;
            while (aux.next != null && aux.next.priority <= priority) {
                aux = aux.next;
            }
            newNode.next = aux.next;
            aux.next = newNode;
        }
        counter++;
    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        T data = frontNode.data;
        frontNode = frontNode.next;
        counter--;
        return data;
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
        if (isEmpty()) return "Cola Vacía (PriorityQueue)";
        StringBuilder sb = new StringBuilder("FRENTE ");
        Node<T> aux = frontNode;
        while (aux != null) {
            sb.append("[P:").append(aux.priority).append(" | ").append(aux.data).append("] -> ");
            aux = aux.next;
        }
        sb.append("FINAL");
        return sb.toString();
    }
}