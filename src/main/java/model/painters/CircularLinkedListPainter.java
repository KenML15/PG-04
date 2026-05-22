package model.painters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.linkedList.CircularLinkedList;

public class CircularLinkedListPainter {

    public static void render(GraphicsContext gc, CircularLinkedList<Integer> list) {
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, width, height);

        if (list == null || list.isEmpty()) {
            drawPlaceholder(gc, "Simulación (Circular Linked List)");
            return;
        }

        try {
            int size = list.size();
            int radius = 22;
            int startX = 60;
            int startY = (int) (height / 2);
            int spacing = 80;

            for (int i = 1; i <= size; i++) {
                Integer data = list.get(i);
                int cx = startX + (i - 1) * spacing;

                // Nodo
                gc.setFill(Color.LIGHTBLUE);
                gc.fillOval(cx - radius, startY - radius, radius * 2, radius * 2);
                gc.setStroke(Color.BLUE);
                gc.setLineWidth(2);
                gc.strokeOval(cx - radius, startY - radius, radius * 2, radius * 2);

                // Texto interno
                gc.setFill(Color.BLACK);
                gc.setFont(new Font("Arial", 12));
                gc.fillText(String.valueOf(data), cx - 8, startY + 4);

                // Enlaces
                if (i < size) {
                    gc.setStroke(Color.GREEN);
                    gc.strokeLine(cx + radius, startY, cx + spacing - radius, startY);

                    // Punta de flecha simple
                    gc.strokeLine(cx + spacing - radius - 5, startY - 4, cx + spacing - radius, startY);
                    gc.strokeLine(cx + spacing - radius - 5, startY + 4, cx + spacing - radius, startY);
                } else {
                    // Enlace circular de retorno (Rojo)
                    gc.setStroke(Color.RED);
                    gc.strokeLine(cx, startY - radius, cx, startY - 60);
                    gc.strokeLine(cx, startY - 60, startX, startY - 60);
                    gc.strokeLine(startX, startY - 60, startX, startY - radius);

                    // Flecha entrando a la cabeza
                    gc.strokeLine(startX - 4, startY - radius - 5, startX, startY - radius);
                    gc.strokeLine(startX + 4, startY - radius - 5, startX, startY - radius);
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
        gc.fillText(title + " - Esperando elementos...", 50, 50);
    }
}