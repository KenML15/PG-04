package model.stack;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class LinkedStackTest {

    @Test
    void linkedStackTest() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        Random random = new Random();

        try {
            System.out.println("=== LinkedStack: apilando 50 valores no repetidos ===");
            Set<Integer> used = new HashSet<>();
            while (linkedStack.size() < 50) {
                int val = random.nextInt(200) + 1;
                if (!used.contains(val)) {
                    used.add(val);
                    linkedStack.push(val);
                    System.out.println("push(" + val + ") | TOP = [" + linkedStack.top() + "]");
                }
            }

            System.out.println("\n--- Contenido de la pila (LinkedStack) ---");
            System.out.println("Size: " + linkedStack.size());
            System.out.println("peek/top: " + linkedStack.peek());
            System.out.println(linkedStack);

            // b. Peek/top y pop
            System.out.println("\n--- peek() y top() ---");
            System.out.println("peek() = " + linkedStack.peek());
            System.out.println("top()  = " + linkedStack.top());

            System.out.println("\n--- pop() de los 3 primeros ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("pop() -> " + linkedStack.pop());
            }

            System.out.println("\n--- Búsqueda y eliminación de 20 valores aleatorios ---");
            for (int i = 0; i < 20; i++) {
                int target = random.nextInt(200) + 1;
                int pos = linkedStack.indexOf(target);
                if (pos != -1) {
                    System.out.println("indexOf(" + target + ") = " + pos + " → eliminando...");
                    int size = linkedStack.size();
                    Integer[] temp = new Integer[size];
                    for (int j = 0; j < size; j++) temp[j] = linkedStack.pop();
                    boolean removed = false;
                    for (int j = size - 1; j >= 0; j--) {
                        if (!removed && temp[j] == target) { removed = true; continue; }
                        linkedStack.push(temp[j]);
                    }
                    System.out.println("  Eliminado. Nuevo size: " + linkedStack.size());
                } else {
                    System.out.println("indexOf(" + target + ") = -1 (no encontrado)");
                }
            }

            System.out.println("\nEstado final | Size: " + linkedStack.size());

        } catch (StackException e) {
            throw new RuntimeException("Error en LinkedStackTest: " + e.getMessage(), e);
        }
    }
}