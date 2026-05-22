package model.linkedList;

import org.junit.jupiter.api.Test;
import java.util.Random;

class CircularLinkedListTest {

    @Test
    void circularLinkedListTest() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Random random = new Random();

        try {

            System.out.println("Ejecutando inserciones...");
            list.add(20);
            list.addFirst(10);
            list.addLast(30);

            for (int i = 0; i < 3; i++) {
                int val = random.nextInt(50) + 40;
                System.out.println("add(" + val + ")");
                list.add(val);
            }

            System.out.println("\n--- Estado Actual de la Lista Circular ---");
            System.out.println("Size: " + list.size());
            System.out.println("Primer elemento (getFirst): " + list.getFirst());
            System.out.println("Último elemento (getLast): " + list.getLast());
            System.out.println(list);

            System.out.println("\n--- Verificación de Índices y Búsquedas ---");
            System.out.println("¿Contiene el número 20?: " + list.contains(20));
            System.out.println("Posición (indexOf) del número 20: " + list.indexOf(20));
            if (list.size() >= 3) {
                System.out.println("Elemento en el índice 3 (get): " + list.get(3));
            }

            System.out.println("\n--- Removiendo Elementos ---");
            System.out.println("Removiendo el 20...");
            list.remove(20);
            System.out.println("Estado tras remover:");
            System.out.println(list);

            System.out.println("\n¿Sigue apuntando circularmente? Siguiente del último (" + list.getLast() + ") es el primero: " + list.getFirst());

        } catch (ListException e) {
            throw new RuntimeException("Error en pruebas de Lista Circular: " + e.getMessage(), e);
        }
    }
}