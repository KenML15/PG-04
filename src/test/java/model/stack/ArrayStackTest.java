package model.stack;

import org.junit.jupiter.api.Test;
import java.util.Random;

class ArrayStackTest {

    @Test
    void arrayStackTest() {

        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        Random random = new Random();

        try {

            System.out.println("Insertando elementos en la pila...");
            for (int i = 0; i < 5; i++) {
                int value = random.nextInt(50);
                System.out.println("push(" + value + ")");
                arrayStack.push(value);
            }

            System.out.println("\n--- Estado Actual de la Pila ---");
            System.out.println("Stack size: " + arrayStack.size());
            System.out.println("Peek (Tope sin extraer): " + arrayStack.peek());
            System.out.println("Top (Tope sin extraer): " + arrayStack.top());
            System.out.println(arrayStack);

            System.out.println("\n--- Desapilando (pop) elementos ---");
            for (int i = 0; i < 5; i++) {
                System.out.println("pop() -> " + arrayStack.pop());
                System.out.println(arrayStack);
            }

            System.out.println("\n¿La pila se encuentra vacía?: " + arrayStack.isEmpty());

        } catch (StackException e) {
            throw new RuntimeException("Error en las pruebas de ArrayStack: " + e.getMessage(), e);
        }
    }
}