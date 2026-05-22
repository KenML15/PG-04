package model.painters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.linkedList.CircularDoublyLinkedList;
import model.Product;

public class CircularDoublyLinkedListPainter {

    public static void render(GraphicsContext gc, CircularDoublyLinkedList<Product> list) {
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, width, height);

        if (list == null || list.isEmpty()) {
            drawPlaceholder(gc, "Simulación (Circular Doubly Linked List)");
            return;
        }

        try {
            int size = list.size();
            int startX = 60;
            int startY = 80;
            int spacing = 120;

            for (int i = 1; i <= size; i++) {
                Product p = list.get(i);
                int cx = startX + (i - 1) * spacing;

                // Dibujar nodo rectangular del producto
                gc.setFill(Color.LIGHTGOLDENRODYELLOW);
                gc.fillRect(cx - 45, startY - 20, 90, 40);
                gc.setStroke(Color.DARKGOLDENROD);
                gc.setLineWidth(1.5);
                gc.strokeRect(cx - 45, startY - 20, 90, 40);

                // Atributos visibles del producto en el Canvas
                gc.setFill(Color.BLACK);
                gc.setFont(new Font("Arial", 11));
                gc.fillText("ID: " + p.getId(), cx - 38, startY - 2);
                gc.fillText(p.getName(), cx - 38, startY + 12);

                if (i < size) {
                    // Enlace next (Azul, superior)
                    gc.setStroke(Color.BLUE);
                    gc.strokeLine(cx + 45, startY - 6, cx + spacing - 45, startY - 6);
                    gc.strokeLine(cx + spacing - 45 - 5, startY - 10, cx + spacing - 45, startY - 6);

                    // Enlace prev (Naranja, inferior)
                    gc.setStroke(Color.ORANGE);
                    gc.strokeLine(cx + 45, startY + 6, cx + spacing - 45, startY + 6);
                    gc.strokeLine(cx + 45 + 5, startY + 10, cx + 45, startY + 6);
                } else {
                    // Enlace circular extremo (Rojo)
                    gc.setStroke(Color.RED);
                    gc.strokeLine(cx, startY - 20, cx, startY - 50);
                    gc.strokeLine(cx, startY - 50, startX, startY - 50);
                    gc.strokeLine(startX, startY - 50, startX, startY - 20);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawPlaceholder(GraphicsContext gc, String title) {
        gc.setFill(Color.web("#f4f4f9"));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.DARKSLATEGRAY);
        gc.setFont(new Font("Arial", 14));
        gc.fillText(title + " - Esperando productos...", 50, 50);
    }
}