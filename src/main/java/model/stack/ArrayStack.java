package model.stack;

public class ArrayStack<T> implements MyStack<T> {
    private T[] array;
    private int topIndex; // Apunta al índice actual del tope
    private static final int DEFAULT_CAPACITY = 50;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.topIndex = -1; // -1 indica que la pila está vacía
    }

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= topIndex; i++) {
            array[i] = null;
        }
        topIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public T peek() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }
        return array[topIndex];
    }

    @Override
    public T top() throws StackException {
        return peek();
    }

    @Override
    public void push(T element) throws StackException {
        if (topIndex == array.length - 1) {
            throw new StackException("Stack overflow: Array capacity exceeded");
        }
        array[++topIndex] = element;
    }

    @Override
    public T pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack underflow: Stack is empty");
        }
        T element = array[topIndex];
        array[topIndex] = null;
        topIndex--;
        return element;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Pila Vacía (ArrayStack)";
        StringBuilder sb = new StringBuilder("TOP -> ");
        for (int i = topIndex; i >= 0; i--) {
            sb.append("[").append(array[i]).append("]\n       ");
        }
        return sb.toString().trim();
    }
}