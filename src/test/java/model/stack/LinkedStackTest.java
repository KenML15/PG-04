package model.stack;

import org.junit.jupiter.api.Test;

import java.util.Random;

class LinkedStackTest {

    @Test
    void linkedStackTest() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        Random random = new Random();

        try {

            for (int i = 0; i < 5; i++) {
                int value = random.nextInt(100);
                System.out.println("push(" + value + ")");
                linkedStack.push(value);
            }

            System.out.println("\n--- Estado de la Pila Enlazada ---");
            System.out.println("Stack size: " + linkedStack.size());
            System.out.println("Peek - Elemento en el tope: " + linkedStack.peek());
            System.out.println(linkedStack);


            System.out.println("\n--- Extrayendo del tope (pop) ---");
            while (!linkedStack.isEmpty()) {
                System.out.println("pop() -> " + linkedStack.pop());
                System.out.println(linkedStack);
            }

            System.out.println("\n¿La pila quedó vacía?: " + linkedStack.isEmpty());

        } catch (StackException e) {
            throw new RuntimeException("Error en las pruebas de LinkedStack: " + e.getMessage(), e);
        }
    }
}