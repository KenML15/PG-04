package model.painters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.queue.PriorityLinkedQueue;
import model.queue.QueueException;
import controller.MainController.QueueRow;

public class PriorityQueuePainter {

    public static void render(GraphicsContext gc, PriorityLinkedQueue<QueueRow> queue) {
        double width  = gc.getCanvas().getWidth();
        double height = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, width, height);

        //fondo oscuro
        gc.setFill(Color.web("#0D1B4B"));
        gc.fillRect(0, 0, width, height);

        if (queue == null || queue.isEmpty()) {
            gc.setFill(Color.web("#4A90D9"));
            gc.setFont(Font.font("Arial", 13));
            gc.fillText("Cola vacía", 30, 50);
            return;
        }

        try {
            int size = queue.size();
            QueueRow[] items = new QueueRow[size];
            for (int i = 0; i < size; i++) items[i] = queue.deQueue();
            for (int i = size - 1; i >= 0; i--) queue.enQueue(items[i]);

            int boxH   = 52;
            int boxW   = (int)(width - 30);
            int startX = 15;
            int startY = 20;
            int gap    = 10;

            for (int i = 0; i < size; i++) {
                QueueRow row = items[i];
                int y = startY + i * (boxH + gap);

                gc.setFill(Color.web("#0A1440"));
                gc.fillRoundRect(startX + 2, y + 3, boxW, boxH, 8, 8);

                Color nodeColor = switch (row.getService()) {
                    case "Cajas"              -> Color.web("#1A8C7B"); //prioridad 1
                    case "Gestión de Cuentas" -> Color.web("#2E5FAC"); //prioridad 2
                    default                   -> Color.web("#1F3868"); //prioridad 3
                };
                gc.setFill(nodeColor);
                gc.fillRoundRect(startX, y, boxW, boxH, 8, 8);

                // Borde
                gc.setStroke(Color.web("#4A90D9"));
                gc.setLineWidth(1.2);
                gc.strokeRoundRect(startX, y, boxW, boxH, 8, 8);


                if (i == 0) {
                    gc.setFill(Color.web("#E8A020"));
                    gc.fillRoundRect(startX + 5, y + 5, 48, 18, 4, 4);
                    gc.setFill(Color.web("#0D1B4B"));
                    gc.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    gc.fillText("FRONT", startX + 8, y + 17);
                }
                if (i == size - 1) {
                    gc.setFill(Color.web("#E74C3C"));
                    gc.fillRoundRect(startX + 5, y + boxH - 22, 40, 16, 4, 4);
                    gc.setFill(Color.WHITE);
                    gc.setFont(Font.font("Arial", FontWeight.BOLD, 9));
                    gc.fillText("REAR", startX + 9, y + boxH - 10);
                }

                gc.setFill(Color.WHITE);
                gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                gc.fillText("[" + row.getId() + "] " + row.getName(), startX + 60, y + 20);
                gc.setFont(Font.font("Arial", 10));
                gc.setFill(Color.web("#ADBDD4"));
                gc.fillText(row.getService() + "  ·  " + row.getDate(), startX + 60, y + 36);

                if (i < size - 1) {
                    int midX = startX + boxW / 2;
                    int arrowY = y + boxH;
                    gc.setStroke(Color.web("#4A90D9"));
                    gc.setLineWidth(1.5);
                    gc.strokeLine(midX, arrowY, midX, arrowY + gap);
                    // Punta de flecha
                    gc.strokeLine(midX - 5, arrowY + gap - 5, midX, arrowY + gap);
                    gc.strokeLine(midX + 5, arrowY + gap - 5, midX, arrowY + gap);
                }

                if (i == size - 1) {
                    gc.setFill(Color.web("#8896A5"));
                    gc.setFont(Font.font("Arial", 10));
                    int midX = startX + boxW / 2;
                    gc.fillText("→ NULL", midX - 20, y + boxH + gap + 12);
                }
            }
        } catch (QueueException e) {
            e.printStackTrace();
        }
    }
}