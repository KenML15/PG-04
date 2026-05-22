package model.queue;

import org.junit.jupiter.api.Test;
import java.util.Random;

class ArrayQueueTest {

    @Test
    void arrayQueueTest() {

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(5);
        Random random = new Random();

        try {
            for (int i = 0; i < 5; i++) {
                int value = random.nextInt(50);
                System.out.println("enQueue(" + value + ")");
                arrayQueue.enQueue(value);
            }

            System.out.println("\nQueue Status:");
            System.out.println("Size: " + arrayQueue.size());
            System.out.println("Peek / Front element: " + arrayQueue.peek());
            System.out.println("IndexOf primer elemento: " + arrayQueue.indexOf(arrayQueue.peek()));
            System.out.println(arrayQueue);

            System.out.println("\n--- Ejecutando 2 deQueue y re-encolando ---");
            System.out.println("deQueue() -> " + arrayQueue.deQueue());
            System.out.println("deQueue() -> " + arrayQueue.deQueue());

            int newValue1 = random.nextInt(100);
            int newValue2 = random.nextInt(100);
            System.out.println("enQueue(" + newValue1 + ")");
            arrayQueue.enQueue(newValue1);
            System.out.println("enQueue(" + newValue2 + ")");
            arrayQueue.enQueue(newValue2);

            System.out.println("\nEstado tras movimientos circulares:");
            System.out.println(arrayQueue);

            // 3. Vaciar por completo
            System.out.println("\n--- Vaciando la cola por completo ---");
            while (!arrayQueue.isEmpty()) {
                System.out.println("deQueue(" + arrayQueue.deQueue() + ")");
            }
            System.out.println("¿Está vacía?: " + arrayQueue.isEmpty());

        } catch (QueueException e) {
            throw new RuntimeException("Error en ArrayQueueTest: " + e.getMessage(), e);
        }
    }
}