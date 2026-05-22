package model.queue;

import model.Node;

public class HeaderLinkedQueue<T> implements MyQueue<T> {
    private Node<T> head; // Nodo cabecera vacío de control
    private Node<T> backNode;
    private int counter;

    public HeaderLinkedQueue() {
        this.head = new Node<>(); // Inicializado vacío
        this.backNode = head;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        head.next = null;
        backNode = head;
        counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int indexOf(T element) throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        Node<T> aux = head.next; // Saltamos la cabecera
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
        backNode.next = newNode;
        backNode = newNode;
        counter++;
    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        Node<T> firstRealNode = head.next;
        T data = firstRealNode.data;

        head.next = firstRealNode.next; // Desenganchamos
        if (head.next == null) { // Si quedó vacía
            backNode = head;
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
        return head.next.data;
    }

    @Override
    public T front() throws QueueException {
        return peek();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Cola Vacía (HeaderLinkedQueue)";
        StringBuilder sb = new StringBuilder("CABECERA_VACÍA -> FRENTE -> ");
        Node<T> aux = head.next;
        while (aux != null) {
            sb.append("[").append(aux.data).append("] ");
            aux = aux.next;
        }
        sb.append("<- FINAL");
        return sb.toString();
    }
}