package model.linkedList;

import model.Node;

public class CircularLinkedList<T> implements List<T> {

    private Node<T> head; // Inicio de la lista
    private Node<T> tail; // Fin de la lista
    private ListType listType;

    public CircularLinkedList() {
        this.head = this.tail = null;
    }

    @Override
    public void setListType(ListType listType) {
        this.listType = listType;
    }

    @Override
    public ListType getListType() {
        return this.listType;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            return 0;
        }
        Node<T> aux = head;
        int count = 0;
        do {
            count++;
            aux = aux.next;
        } while (aux != head);
        return count;
    }

    @Override
    public void clear() {
        head = tail = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        Node<T> aux = head;
        do {
            if (equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        } while (aux != head);
        return false;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
            tail.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    @Override
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
            tail.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public void addInSortedList(T element) {

    }

    @Override
    public void remove(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }

        Node<T> prev = tail;
        Node<T> curr = head;
        do {
            if (equals(curr.data, element)) {
                if (head == tail) {
                    clear();
                } else {
                    prev.next = curr.next;
                    if (curr == head) {
                        head = head.next;
                    }
                    if (curr == tail) {
                        tail = prev;
                    }
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    @Override
    public T removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        T data = head.data;
        if (head == tail) {
            clear();
        } else {
            head = head.next;
            tail.next = head;
        }
        return data;
    }

    @Override
    public T removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        T data = tail.data;
        if (head == tail) {
            clear();
        } else {
            Node<T> aux = head;
            while (aux.next != tail) {
                aux = aux.next;
            }
            tail = aux;
            tail.next = head;
        }
        return data;
    }

    @Override
    public void sort() throws ListException {

    }

    @Override
    public int indexOf(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        Node<T> aux = head;
        int index = 1;
        do {
            if (equals(aux.data, element)) {
                return index;
            }
            index++;
            aux = aux.next;
        } while (aux != head);
        return -1;
    }

    @Override
    public T getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        return tail.data;
    }

    @Override
    public T getPrev(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        Node<T> prev = tail;
        Node<T> curr = head;
        do {
            if (equals(curr.data, element)) {
                return prev.data;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
        return null;
    }

    @Override
    public T getNext(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        Node<T> aux = head;
        do {
            if (equals(aux.data, element)) {
                return aux.next.data;
            }
            aux = aux.next;
        } while (aux != head);
        return null;
    }

    @Override
    public T get(int index) throws ListException {
        Node<T> node = getNodeByIndex(index);
        return (node != null) ? node.data : null;
    }

    @Override
    public Node<T> getNodeByIndex(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Linked List is empty");
        }
        if (index < 1) return null;

        Node<T> aux = head;
        int i = 1;
        do {
            if (index == i) {
                return aux;
            }
            i++;
            aux = aux.next;
        } while (aux != head);

        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "HEAD → TAIL (Lista Vacía)";
        StringBuilder sb = new StringBuilder("HEAD → ");
        Node<T> cur = head;
        do {
            sb.append("[").append(cur.data).append("]");
            sb.append(" → ");
            cur = cur.next;
        } while (cur != head);
        sb.append("HEAD");
        return sb.toString();
    }

    private boolean equals(T a, T b) {
        if (a == null) return b == null;
        return a.equals(b);
    }
}
