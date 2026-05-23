package model.queue;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class HeaderLinkedQueueTest {

    @Test
    void headerLinkedQueueTest() {
        HeaderLinkedQueue<Integer> headerQueue = new HeaderLinkedQueue<>();
        Random random = new Random();

        try {
            System.out.println("=== HeaderLinkedQueue: encolando 50 valores no repetidos ===");
            Set<Integer> used = new HashSet<>();
            while (headerQueue.size() < 50) {
                int val = random.nextInt(200) + 1;
                if (!used.contains(val)) {
                    used.add(val);
                    headerQueue.enQueue(val);
                    System.out.println("enQueue(" + val + ") | FRONT = [" + headerQueue.front() + "]");
                }
            }

            System.out.println("\n--- Contenido de la cola (HeaderLinkedQueue) ---");
            System.out.println("Size: " + headerQueue.size());
            System.out.println("peek/front: " + headerQueue.peek());
            System.out.println(headerQueue);

            System.out.println("\n--- peek() y front() ---");
            System.out.println("peek()  = " + headerQueue.peek());
            System.out.println("front() = " + headerQueue.front());

            System.out.println("\n--- deQueue() de los 3 primeros ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("deQueue() -> " + headerQueue.deQueue());
            }

            System.out.println("\n--- Búsqueda y eliminación de 20 valores aleatorios ---");
            for (int i = 0; i < 20; i++) {
                int target = random.nextInt(200) + 1;
                int pos = headerQueue.indexOf(target);
                if (pos != -1) {
                    System.out.println("indexOf(" + target + ") = " + pos + " → eliminando...");
                    int size = headerQueue.size();
                    Integer[] temp = new Integer[size];
                    for (int j = 0; j < size; j++) temp[j] = headerQueue.deQueue();
                    for (int j = 0; j < size; j++) {
                        if (temp[j] != target) headerQueue.enQueue(temp[j]);
                    }
                    System.out.println("  Eliminado. Nuevo size: " + headerQueue.size());
                } else {
                    System.out.println("indexOf(" + target + ") = -1 (no encontrado)");
                }
            }

            System.out.println("\nEstado final | Size: " + headerQueue.size());

        } catch (QueueException e) {
            throw new RuntimeException("Error en HeaderLinkedQueueTest: " + e.getMessage(), e);
        }
    }
}