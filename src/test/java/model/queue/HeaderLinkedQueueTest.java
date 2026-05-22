package model.queue;

import org.junit.jupiter.api.Test;
import java.util.Random;

class HeaderLinkedQueueTest {

    @Test
    void headerLinkedQueueTest() {
        HeaderLinkedQueue<Integer> headerQueue = new HeaderLinkedQueue<>();
        Random random = new Random();

        try {

            int targetValue = 99;
            System.out.println("enQueue(" + targetValue + ") [Elemento Objetivo]");
            headerQueue.enQueue(targetValue);

            for (int i = 0; i < 4; i++) {
                int value = random.nextInt(50);
                System.out.println("enQueue(" + value + ")");
                headerQueue.enQueue(value);
            }

            System.out.println("\nQueue Status:");
            System.out.println("Size: " + headerQueue.size());
            System.out.println("¿Contiene al 99?: " + headerQueue.contains(targetValue));
            System.out.println("Posición lineal del 99 (indexOf): " + headerQueue.indexOf(targetValue));
            System.out.println(headerQueue);

            System.out.println("\n--- Desencolando elementos ---");
            while (!headerQueue.isEmpty()) {
                System.out.println("deQueue(" + headerQueue.deQueue() + ")");
                System.out.println(headerQueue);
            }

        } catch (QueueException e) {
            throw new RuntimeException("Error en HeaderLinkedQueueTest: " + e.getMessage(), e);
        }
    }
}