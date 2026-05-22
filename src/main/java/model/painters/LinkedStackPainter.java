package model.painters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.stack.LinkedStack;

public class LinkedStackPainter {

    public static void render(GraphicsContext gc, LinkedStack<Integer> stack) {
        double width = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, width, height);

        if (stack == null || stack.isEmpty()) {
            drawPlaceholder(gc, "Simulación de Estructura LIFO (Linked Stack)");
            return;
        }

        try {
            int size = stack.size();
            int boxWidth = 100;
            int boxHeight = 35;
            int cx = (int) (width / 2) - (boxWidth / 2); // Centrado dinámico
            int startY = 50;

            // Vaciado visual clonado temporalmente para no destruir la pila original
            Integer[] items = new Integer[size];
            int idx = 0;
            while (!stack.isEmpty()) {
                items[idx++] = stack.pop();
            }
            // Restauración íntegra
            for (int i = size - 1; i >= 0; i--) {
                stack.push(items[i]);
            }

            // Dibujar bloques apilados
            for (int i = 0; i < size; i++) {
                int y = startY + (i * (boxHeight + 12));

                gc.setFill(Color.LIGHTPINK);
                gc.fillRect(cx, y, boxWidth, boxHeight);
                gc.setStroke(Color.DARKRED);
                gc.setLineWidth(1.5);
                gc.strokeRect(cx, y, boxWidth, boxHeight);

                gc.setFill(Color.BLACK);
                gc.setFont(new Font("Arial", 13));
                gc.fillText("[" + items[i] + "]", cx + 32, y + 22);

                if (i == 0) {
                    gc.setFill(Color.BLUE);
                    gc.setFont(new Font("Arial Bold", 12));
                    gc.fillText("← TOP Index", cx + boxWidth + 10, y + 22);
                }

                if (i < size - 1) {
                    gc.setStroke(Color.GRAY);
                    gc.strokeLine(cx + (boxWidth / 2.0), y + boxHeight, cx + (boxWidth / 2.0), y + boxHeight + 12);
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
        gc.fillText(title + " - Pila vacía", 50, 50);
    }
}