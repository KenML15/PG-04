package model.stack;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class ArrayStackTest {

    @Test
    void arrayStackTest() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(60);
        Random random = new Random();

        try {
            System.out.println("=== ArrayStack: apilando 50 valores no repetidos ===");
            Set<Integer> used = new HashSet<>();
            while (arrayStack.size() < 50) {
                int val = random.nextInt(200) + 1;
                if (!used.contains(val)) {
                    used.add(val);
                    arrayStack.push(val);
                    System.out.println("push(" + val + ") | TOP = [" + arrayStack.top() + "]");
                }
            }

            System.out.println("\n--- Contenido de la pila (ArrayStack) ---");
            System.out.println("Size: " + arrayStack.size());
            System.out.println("peek/top: " + arrayStack.peek());
            System.out.println(arrayStack);

            // b. Peek/top y pop
            System.out.println("\n--- peek() y top() ---");
            System.out.println("peek() = " + arrayStack.peek());
            System.out.println("top()  = " + arrayStack.top());

            System.out.println("\n--- pop() de los 3 primeros ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("pop() -> " + arrayStack.pop());
            }

            System.out.println("\n--- Búsqueda y eliminación de 20 valores aleatorios ---");
            for (int i = 0; i < 20; i++) {
                int target = random.nextInt(200) + 1;
                int pos = arrayStack.indexOf(target);
                if (pos != -1) {
                    System.out.println("indexOf(" + target + ") = " + pos + " → eliminando...");
                    int size = arrayStack.size();
                    Integer[] temp = new Integer[size];
                    for (int j = 0; j < size; j++) temp[j] = arrayStack.pop();
                    boolean removed = false;
                    for (int j = size - 1; j >= 0; j--) {
                        if (!removed && temp[j] == target) { removed = true; continue; }
                        arrayStack.push(temp[j]);
                    }
                    System.out.println("  Eliminado. Nuevo size: " + arrayStack.size());
                } else {
                    System.out.println("indexOf(" + target + ") = -1 (no encontrado)");
                }
            }

            System.out.println("\nEstado final | Size: " + arrayStack.size());

        } catch (StackException e) {
            throw new RuntimeException("Error en ArrayStackTest: " + e.getMessage(), e);
        }
    }
}