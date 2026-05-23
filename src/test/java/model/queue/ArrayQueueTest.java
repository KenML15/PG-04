package model.queue;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class ArrayQueueTest {

    @Test
    void arrayQueueTest() {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(60);
        Random random = new Random();

        try {

            System.out.println("=== ArrayQueue: encolando 50 valores no repetidos ===");
            Set<Integer> used = new HashSet<>();
            while (arrayQueue.size() < 50) {
                int val = random.nextInt(200) + 1;
                if (!used.contains(val)) {
                    used.add(val);
                    arrayQueue.enQueue(val);
                    System.out.println("enQueue(" + val + ") | FRONT = [" + arrayQueue.front() + "]");
                }
            }

            System.out.println("\n--- Contenido de la cola (ArrayQueue) ---");
            System.out.println("Size: " + arrayQueue.size());
            System.out.println("peek/front: " + arrayQueue.peek());
            System.out.println(arrayQueue);

            System.out.println("\n--- peek() y front() ---");
            System.out.println("peek()  = " + arrayQueue.peek());
            System.out.println("front() = " + arrayQueue.front());

            System.out.println("\n--- deQueue() de los 3 primeros ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("deQueue() -> " + arrayQueue.deQueue());
            }

            System.out.println("\n--- Búsqueda y eliminación de 20 valores aleatorios ---");
            for (int i = 0; i < 20; i++) {
                int target = random.nextInt(200) + 1;
                int pos = arrayQueue.indexOf(target);
                if (pos != -1) {
                    System.out.println("indexOf(" + target + ") = " + pos + " → eliminando...");
                    int size = arrayQueue.size();
                    Integer[] temp = new Integer[size];
                    for (int j = 0; j < size; j++) temp[j] = arrayQueue.deQueue();
                    for (int j = 0; j < size; j++) {
                        if (temp[j] != target) arrayQueue.enQueue(temp[j]);
                    }
                    System.out.println("  Eliminado. Nuevo size: " + arrayQueue.size());
                } else {
                    System.out.println("indexOf(" + target + ") = -1 (no encontrado)");
                }
            }

            System.out.println("\nEstado final | Size: " + arrayQueue.size());

        } catch (QueueException e) {
            throw new RuntimeException("Error en ArrayQueueTest: " + e.getMessage(), e);
        }
    }
}