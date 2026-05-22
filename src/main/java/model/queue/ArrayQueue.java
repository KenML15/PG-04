package model.queue;

public class ArrayQueue<T> implements MyQueue<T> {
    private T[] array;
    private int frontIndex;
    private int backIndex;
    private int counter;
    private static final int DEFAULT_CAPACITY = 50;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.frontIndex = 0;
        this.backIndex = -1;
        this.counter = 0;
    }

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        frontIndex = 0;
        backIndex = -1;
        counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    @Override
    public int indexOf(T element) throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        int curr = frontIndex;
        for (int i = 1; i <= counter; i++) {
            if (array[curr] != null && array[curr].equals(element)) {
                return i;
            }
            curr = (curr + 1) % array.length;
        }
        return -1;
    }

    @Override
    public void enQueue(T element) throws QueueException {
        if (counter == array.length) {
            throw new QueueException("Queue overflow: capacity exceeded");
        }
        backIndex = (backIndex + 1) % array.length;
        array[backIndex] = element;
        counter++;
    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue underflow: queue is empty");
        T element = array[frontIndex];
        array[frontIndex] = null;
        frontIndex = (frontIndex + 1) % array.length;
        counter--;
        return element;
    }

    @Override
    public void enQueue(T element, Integer priority) throws QueueException {
        // En una cola secuencial estándar basada en arreglos, ignoramos o metemos normal la prioridad
        enQueue(element);
    }

    @Override
    public boolean contains(T element) throws QueueException {
        return indexOf(element) != -1;
    }

    @Override
    public T peek() throws QueueException {
        if (isEmpty()) throw new QueueException("Queue is empty");
        return array[frontIndex];
    }

    @Override
    public T front() throws QueueException {
        return peek();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Cola Vacía (ArrayQueue)";
        StringBuilder sb = new StringBuilder("FRENTE -> ");
        int curr = frontIndex;
        for (int i = 0; i < counter; i++) {
            sb.append("[").append(array[curr]).append("] ");
            curr = (curr + 1) % array.length;
        }
        sb.append("<- FINAL");
        return sb.toString();
    }
}