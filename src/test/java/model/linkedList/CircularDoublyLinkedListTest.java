package model.linkedList;

import model.Product;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class CircularDoublyLinkedListTest {

    @Test
    void circularDoublyLinkedListTest() {
        CircularDoublyLinkedList<Product> productList = new CircularDoublyLinkedList<>();

        try {

            Product p1 = new Product(101, "Arroz Sabanero", 1200.0, 50, "Cajas", LocalDate.now());
            Product p2 = new Product(102, "Leche Pinito", 950.0, 30, "Cajas", LocalDate.now());
            Product p3 = new Product(103, "Apertura Cuenta Premium", 0.0, 1, "Gestión de Cuentas", LocalDate.now());

            System.out.println("Insertando productos en la lista doble circular...");
            productList.add(p1);
            productList.addFirst(p2);
            productList.addLast(p3);

            System.out.println("\n--- Estado de la Lista Doble ---");
            System.out.println("Size: " + productList.size());
            System.out.println("Primer producto: " + productList.getFirst().getName());
            System.out.println("Último producto: " + productList.getLast().getName());
            System.out.println(productList);


            System.out.println("\n--- Verificación de Enlaces Bidireccionales ---");
            Product enIndice2 = productList.get(2);
            System.out.println("Producto en índice 2: " + enIndice2.getName());


            System.out.println("¿Contiene el producto p1?: " + productList.contains(p1));
            System.out.println("Posición lineal del producto p1: " + productList.indexOf(p1));


            System.out.println("\n--- Eliminación de Productos ---");
            System.out.println("Removiendo el producto p1 (" + p1.getName() + ")...");
            productList.remove(p1);
            System.out.println("Estado final de la lista:");
            System.out.println(productList);

        } catch (ListException e) {
            throw new RuntimeException("Error en pruebas de Lista Doble Circular: " + e.getMessage(), e);
        }
    }
}