package model.linkedList;

import model.Node;

// Al colocar "model.linkedList.List<T>" le dejas claro al compilador que es tu interfaz y no la de Java
public class CircularDoublyLinkedList<T> implements List<T> {

    private Node<T> head; // Inicio de la lista
    private Node<T> tail; // Fin de la lista
    private ListType listType;

    public CircularDoublyLinkedList() {
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
            return 0; // Es mejor retornar 0 que lanzar excepción si solo queremos saber el tamaño
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
            throw new ListException("Circular Doubly Linked List is empty");
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
            head.next = head;
            head.prev = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
        }
    }

    @Override
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
            head.next = head;
            head.prev = head;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
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
            throw new ListException("Circular Doubly Linked List is empty");
        }

        Node<T> aux = head;
        do {
            if (equals(aux.data, element)) {

                if (head == tail) {
                    clear();
                    return;
                }


                aux.prev.next = aux.next;
                aux.next.prev = aux.prev;


                if (aux == head) {
                    head = aux.next;
                }

                if (aux == tail) {
                    tail = aux.prev;
                }
                return;
            }
            aux = aux.next;
        } while (aux != head);
    }

    @Override
    public T removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is empty");
        }
        T data = head.data;
        if (head == tail) {
            clear();
        } else {
            head = head.next;
            tail.next = head;
            head.prev = tail;
        }
        return data;
    }

    @Override
    public T removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is empty");
        }
        T data = tail.data;
        if (head == tail) {
            clear();
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        return data;
    }

    @Override
    public void sort() throws ListException {

    }

    @Override
    public int indexOf(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is empty");
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
            throw new ListException("Circular Doubly Linked List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is empty");
        }
        return tail.data;
    }

    @Override
    public T getPrev(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is empty");
        }
        Node<T> aux = head;
        do {
            if (equals(aux.data, element)) {
                return aux.prev.data; // ¡Ventaja del enlace doble! O(1) directo.
            }
            aux = aux.next;
        } while (aux != head);
        return null;
    }

    @Override
    public T getNext(T element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Circular Doubly Linked List is empty");
        }
        Node<T> aux = head;
        do {
            if (equals(aux.data, element)) {
                return aux.next.data; // O(1) directo.
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
            throw new ListException("Circular Doubly Linked List is empty");
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
        if (isEmpty()) return "HEAD ←→ TAIL (Lista Vacía)";
        StringBuilder sb = new StringBuilder("HEAD ←→ ");
        Node<T> cur = head;
        do {
            sb.append("[").append(cur.data).append("]");
            sb.append(" ←→ ");
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

