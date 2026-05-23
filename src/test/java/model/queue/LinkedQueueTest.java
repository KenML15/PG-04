package model.queue;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class LinkedQueueTest {

    @Test
    void linkedQueueTest() {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        Random random = new Random();

        try {
            System.out.println("=== LinkedQueue: encolando 50 valores no repetidos ===");
            Set<Integer> used = new HashSet<>();
            while (linkedQueue.size() < 50) {
                int val = random.nextInt(200) + 1;
                if (!used.contains(val)) {
                    used.add(val);
                    linkedQueue.enQueue(val);
                    System.out.println("enQueue(" + val + ") | FRONT = [" + linkedQueue.front() + "]");
                }
            }

            System.out.println("\n--- Contenido de la cola (LinkedQueue) ---");
            System.out.println("Size: " + linkedQueue.size());
            System.out.println("peek/front: " + linkedQueue.peek());
            System.out.println(linkedQueue);

            System.out.println("\n--- peek() y front() ---");
            System.out.println("peek()  = " + linkedQueue.peek());
            System.out.println("front() = " + linkedQueue.front());

            System.out.println("\n--- deQueue() de los 3 primeros ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("deQueue() -> " + linkedQueue.deQueue());
            }

            System.out.println("\n--- Búsqueda y eliminación de 20 valores aleatorios ---");
            for (int i = 0; i < 20; i++) {
                int target = random.nextInt(200) + 1;
                int pos = linkedQueue.indexOf(target);
                if (pos != -1) {
                    System.out.println("indexOf(" + target + ") = " + pos + " → eliminando...");
                    int size = linkedQueue.size();
                    Integer[] temp = new Integer[size];
                    for (int j = 0; j < size; j++) temp[j] = linkedQueue.deQueue();
                    for (int j = 0; j < size; j++) {
                        if (temp[j] != target) linkedQueue.enQueue(temp[j]);
                    }
                    System.out.println("  Eliminado. Nuevo size: " + linkedQueue.size());
                } else {
                    System.out.println("indexOf(" + target + ") = -1 (no encontrado)");
                }
            }

            System.out.println("\nEstado final | Size: " + linkedQueue.size());

        } catch (QueueException e) {
            throw new RuntimeException("Error en LinkedQueueTest: " + e.getMessage(), e);
        }
    }
}