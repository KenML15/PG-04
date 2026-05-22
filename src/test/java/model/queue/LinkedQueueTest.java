package model.queue;

import org.junit.jupiter.api.Test;

import java.util.Random;

class LinkedQueueTest {

    @Test
    void linkedQueueTest(){
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        try{
            for(int i = 0; i <10; i++) {
                int value = new Random().nextInt(50);


                System.out.println("enQueue(" + value + ")");
                linkedQueue.enQueue(value);
            }

            System.out.println("Queue size: " + linkedQueue.size());
            System.out.println("Peek - Front: " + linkedQueue.peek());
            System.out.println(linkedQueue);

            for(int i = 0; i<5; i++){
                System.out.println("deQueue(" + linkedQueue.deQueue() + ")");
                System.out.println(linkedQueue);
            }

        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
    }
}