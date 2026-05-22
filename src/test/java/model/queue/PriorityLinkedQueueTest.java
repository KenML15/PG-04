package model.queue;

import org.junit.jupiter.api.Test;
import java.util.Random;

class PriorityLinkedQueueTest {

    @Test
    void priorityLinkedQueueTest() {
        PriorityLinkedQueue<String> priorityQueue = new PriorityLinkedQueue<>();
        Random random = new Random();

        try {


            String[] tickets = {"Cliente Regular A", "Adulto Mayor / Preferencial", "Cliente VIP Corporativo", "Cliente Regular B"};
            int[] prioridades = {3, 1, 2, 3};

            for (int i = 0; i < tickets.length; i++) {
                System.out.println("enQueue('" + tickets[i] + "', Prioridad: " + prioridades[i] + ")");
                priorityQueue.enQueue(tickets[i], prioridades[i]);
            }

            System.out.println("\nEstado actual de la cola ordenado por prioridad:");
            System.out.println("Size: " + priorityQueue.size());
            System.out.println("Próximo en atender (peek): " + priorityQueue.peek());
            System.out.println(priorityQueue);

            System.out.println("\n--- Atendiendo de acuerdo a prioridad de paso ---");
            int turno = 1;
            while (!priorityQueue.isEmpty()) {
                System.out.println("Turno #" + (turno++) + " -> Atendido: " + priorityQueue.deQueue());
                System.out.println(priorityQueue);
            }

        } catch (QueueException e) {
            throw new RuntimeException("Error en PriorityLinkedQueueTest: " + e.getMessage(), e);
        }
    }
}