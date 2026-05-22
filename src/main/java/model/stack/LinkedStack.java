package model.stack;

import model.Node;


public class LinkedStack<T> implements MyStack<T> {
    private Node<T> topNode; // Puntero al elemento en el tope
    private int counter;

    public LinkedStack() {
        this.topNode = null;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        topNode = null;
        counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public T peek() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }
        return topNode.data;
    }

    @Override
    public T top() throws StackException {
        return peek();
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        if (!isEmpty()) {
            newNode.next = topNode; // El nuevo nodo apunta hacia abajo en la pila
        }
        topNode = newNode;
        counter++;
    }

    @Override
    public T pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }
        T data = topNode.data;
        topNode = topNode.next; // El tope se mueve al nodo de abajo
        counter--;
        return data;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Pila Vacía (LinkedStack)";
        StringBuilder sb = new StringBuilder("TOP -> ");
        Node<T> aux = topNode;
        while (aux != null) {
            sb.append("[").append(aux.data).append("]\n       ");
            aux = aux.next;
        }
        return sb.toString().trim();
    }
}