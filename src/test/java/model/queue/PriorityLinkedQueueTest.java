package model.queue;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class PriorityLinkedQueueTest {

    @Test
    void priorityLinkedQueueTest() {
        PriorityLinkedQueue<Integer> priorityQueue = new PriorityLinkedQueue<>();
        Random random = new Random();

        try {
            System.out.println("=== PriorityLinkedQueue: encolando 30 valores con prioridad aleatoria ===");
            Set<Integer> used = new HashSet<>();
            while (priorityQueue.size() < 30) {
                int val = random.nextInt(100) + 1;
                if (!used.contains(val)) {
                    used.add(val);
                    int priority = random.nextInt(3) + 1; // 1, 2 ó 3
                    priorityQueue.enQueue(val, priority);
                    System.out.println("enQueue(" + val + ", P:" + priority + ") | FRONT = [" + priorityQueue.front() + "]");
                }
            }

            System.out.println("\n--- Contenido de la cola (PriorityLinkedQueue) ordenada por prioridad ---");
            System.out.println("Size: " + priorityQueue.size());
            System.out.println("peek/front: " + priorityQueue.peek());
            System.out.println(priorityQueue);

            System.out.println("\n--- peek() y front() ---");
            System.out.println("peek()  = " + priorityQueue.peek());
            System.out.println("front() = " + priorityQueue.front());

            System.out.println("\n--- deQueue() de los 3 primeros (mayor prioridad) ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("deQueue() -> " + priorityQueue.deQueue());
            }

            System.out.println("\n--- Búsqueda y eliminación de 20 valores aleatorios ---");
            for (int i = 0; i < 20; i++) {
                int target = random.nextInt(100) + 1;
                int pos = priorityQueue.indexOf(target);
                if (pos != -1) {
                    System.out.println("indexOf(" + target + ") = " + pos + " → eliminando...");
                    int size = priorityQueue.size();
                    Integer[] temp = new Integer[size];
                    for (int j = 0; j < size; j++) temp[j] = priorityQueue.deQueue();
                    for (int j = 0; j < size; j++) {
                        if (temp[j] != target) priorityQueue.enQueue(temp[j]);
                    }
                    System.out.println("  Eliminado. Nuevo size: " + priorityQueue.size());
                } else {
                    System.out.println("indexOf(" + target + ") = -1 (no encontrado)");
                }
            }

            System.out.println("\nEstado final atendiendo por prioridad:");
            while (!priorityQueue.isEmpty()) {
                System.out.println("deQueue() -> " + priorityQueue.deQueue());
            }

        } catch (QueueException e) {
            throw new RuntimeException("Error en PriorityLinkedQueueTest: " + e.getMessage(), e);
        }
    }
}