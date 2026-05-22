package model.linkedList;

import model.Node;

public interface List<T> {
    public int size() throws ListException;
    public void clear();
    public boolean isEmpty();
    public boolean contains(T element) throws ListException;
    public void add(T element);
    public void addFirst(T element);
    public void addLast(T element);
    public void addInSortedList(T element);
    public void remove(T element) throws ListException;
    public T removeFirst() throws ListException;
    public T removeLast() throws ListException;
    public void sort() throws ListException;
    public int indexOf(T element) throws ListException;
    public T getFirst() throws ListException;
    public T getLast() throws ListException;
    public T getPrev(T element) throws ListException;
    public T getNext(T element) throws ListException;
    public T get(int index) throws ListException;
    public Node<T> getNodeByIndex(int index) throws ListException;

    // Métodos para control del tipo de lista
    public void setListType(ListType listType);
    public ListType getListType();
}