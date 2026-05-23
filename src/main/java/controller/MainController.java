package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.linkedList.CircularDoublyLinkedList;
import model.linkedList.CircularLinkedList;
import model.linkedList.ListException;
import model.painters.CircularDoublyLinkedListPainter;
import model.painters.CircularLinkedListPainter;
import model.painters.LinkedStackPainter;
import model.painters.PriorityQueuePainter;
import model.queue.PriorityLinkedQueue;
import model.queue.QueueException;
import model.stack.LinkedStack;
import model.stack.StackException;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML private Canvas canvasCLL;
    @FXML private TableView<CLLRow>    tableCLL;
    @FXML private TableColumn<CLLRow,Integer> cllColIndex, cllColData, cllColNext, cllColPrev, cllColHead, cllColTail;
    @FXML private TextField  txtCLLValue;
    @FXML private Button     btnCLLAddFirst, btnCLLAddLast, btnCLLAddRandom,
            btnCLLSearch, btnCLLRemove, btnCLLRemoveFirst, btnCLLClear;
    @FXML private ListView<String> listCLLLog;


    @FXML private Canvas canvasCDLL;
    @FXML private TableView<Product>   tableCDLL;
    @FXML private TableColumn<Product,Integer>   cdllColId, cdllColStock;
    @FXML private TableColumn<Product,String>    cdllColName, cdllColType, cdllColDate;
    @FXML private TableColumn<Product,Double>    cdllColPrice;
    @FXML private TextField  txtCDLLId, txtCDLLName, txtCDLLPrice, txtCDLLStock;
    @FXML private ComboBox<String> cbCDLLType;
    @FXML private DatePicker dpCDLLDate;
    @FXML private Button     btnCDLLAdd, btnCDLLSearch, btnCDLLSortName, btnCDLLSortPrice,
            btnCDLLRemove, btnCDLLRemoveAll, btnCDLLRemoveFirst;
    @FXML private ListView<String> listCDLLLog;

    @FXML private Canvas canvasStack;
    @FXML private TableView<StackRow>   tableStack;
    @FXML private TableColumn<StackRow,String> stackColNode, stackColNext;
    @FXML private TableColumn<StackRow,Integer> stackColData;
    @FXML private TextField  txtStackValue;
    @FXML private Button     btnStackPush, btnStackPushRandom, btnStackSearch,
            btnStackPop, btnStackRemove, btnStackClear;
    @FXML private ListView<String> listStackLog;


    @FXML private Canvas canvasQueue;
    @FXML private TableView<QueueRow>   tableQueue;
    @FXML private TableColumn<QueueRow,Integer> queueColId, queueColAge;
    @FXML private TableColumn<QueueRow,String>  queueColName, queueColService, queueColDate;
    @FXML private TextField  txtQueueId, txtQueueName, txtQueueAge;
    @FXML private ComboBox<String> cbQueueService;
    @FXML private DatePicker dpQueueDate;
    @FXML private Label      lblQueuePriorityInfo;
    @FXML private Button     btnQueueEnqueue, btnQueueSearch, btnQueueCompareFront,
            btnQueuePeek, btnQueueDequeue, btnQueueSort, btnQueueClear;
    @FXML private ListView<String> listQueueLog;


    private final CircularLinkedList<Integer>       cll   = new CircularLinkedList<>();
    private final CircularDoublyLinkedList<Product> cdll  = new CircularDoublyLinkedList<>();
    private final LinkedStack<Integer>              stack = new LinkedStack<>();
    private final PriorityLinkedQueue<QueueRow>     queue = new PriorityLinkedQueue<>();

    private final Random rnd = new Random();
    private int cdllNextId = 1;
    private int queueNextId = 1;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupCLLTable();
        setupCDLLTable();
        setupStackTable();
        setupQueueTable();
        setupCombos();
        bindButtons();
    }

    private void setupCLLTable() {
        cllColIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        cllColData .setCellValueFactory(new PropertyValueFactory<>("data"));
        cllColNext .setCellValueFactory(new PropertyValueFactory<>("nextData"));
        cllColPrev .setCellValueFactory(new PropertyValueFactory<>("prevData"));
        cllColHead .setCellValueFactory(new PropertyValueFactory<>("headData"));
        cllColTail .setCellValueFactory(new PropertyValueFactory<>("tailData"));
    }

    private void setupCDLLTable() {
        cdllColId   .setCellValueFactory(new PropertyValueFactory<>("id"));
        cdllColName .setCellValueFactory(new PropertyValueFactory<>("name"));
        cdllColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cdllColStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        cdllColType .setCellValueFactory(new PropertyValueFactory<>("type"));
        cdllColDate .setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setupStackTable() {
        stackColNode.setCellValueFactory(new PropertyValueFactory<>("node"));
        stackColData.setCellValueFactory(new PropertyValueFactory<>("data"));
        stackColNext.setCellValueFactory(new PropertyValueFactory<>("nextNode"));
    }

    private void setupQueueTable() {
        queueColId     .setCellValueFactory(new PropertyValueFactory<>("id"));
        queueColName   .setCellValueFactory(new PropertyValueFactory<>("name"));
        queueColAge    .setCellValueFactory(new PropertyValueFactory<>("age"));
        queueColService.setCellValueFactory(new PropertyValueFactory<>("service"));
        queueColDate   .setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setupCombos() {
        cbCDLLType.setItems(FXCollections.observableArrayList(
                "Electrónicos","Tecnológicos","Línea Blanca","Comestible","Médico","Suministros"));
        cbQueueService.setItems(FXCollections.observableArrayList(
                "Cajas","Gestión de Cuentas","Préstamos","Inversiones","Seguros","Atención al Cliente"));
    }


    private void bindButtons() {
        // CLL
        btnCLLAddFirst  .setOnAction(e -> cllAddFirst());
        btnCLLAddLast   .setOnAction(e -> cllAddLast());
        btnCLLAddRandom .setOnAction(e -> cllAddRandom());
        btnCLLSearch    .setOnAction(e -> cllSearch());
        btnCLLRemove    .setOnAction(e -> cllRemove());
        btnCLLRemoveFirst.setOnAction(e -> cllRemoveFirst());
        btnCLLClear     .setOnAction(e -> cllClear());
        // CDLL
        btnCDLLAdd         .setOnAction(e -> cdllAdd());
        btnCDLLSearch      .setOnAction(e -> cdllSearch());
        btnCDLLSortName    .setOnAction(e -> cdllSortName());
        btnCDLLSortPrice   .setOnAction(e -> cdllSortPrice());
        btnCDLLRemove      .setOnAction(e -> cdllRemove());
        btnCDLLRemoveAll   .setOnAction(e -> cdllRemoveAll());
        btnCDLLRemoveFirst .setOnAction(e -> cdllRemoveFirst());
        // Stack
        btnStackPush      .setOnAction(e -> stackPush());
        btnStackPushRandom.setOnAction(e -> stackPushRandom());
        btnStackSearch    .setOnAction(e -> stackSearch());
        btnStackPop       .setOnAction(e -> stackPop());
        btnStackRemove    .setOnAction(e -> stackRemoveValue());
        btnStackClear     .setOnAction(e -> stackClear());
        // Queue
        btnQueueEnqueue     .setOnAction(e -> queueEnqueue());
        btnQueueSearch      .setOnAction(e -> queueSearch());
        btnQueueCompareFront.setOnAction(e -> queueCompareFront());
        btnQueuePeek        .setOnAction(e -> queuePeek());
        btnQueueDequeue     .setOnAction(e -> queueDequeue());
        btnQueueSort        .setOnAction(e -> queueSort());
        btnQueueClear       .setOnAction(e -> queueClear());
    }

    private void cllAddFirst() {
        try {
            int val = Integer.parseInt(txtCLLValue.getText().trim());
            if (cllContains(val)) { logCLL(" Valor duplicado: " + val); return; }
            cll.addFirst(val);
            logCLL("addFirst(" + val + ") HEAD = [" + val + "] + ...");
            refreshCLL();
        } catch (NumberFormatException ex) { logCLL(" Ingrese un número válido"); }
    }

    private void cllAddLast() {
        try {
            int val = Integer.parseInt(txtCLLValue.getText().trim());
            if (cllContains(val)) { logCLL(" Valor duplicado: " + val); return; }
            cll.addLast(val);
            logCLL("addLast(" + val + ")");
            refreshCLL();
        } catch (NumberFormatException ex) { logCLL(" Ingrese un número válido"); }
    }

    private void cllAddRandom() {
        int added = 0;
        int attempts = 0;
        while (added < 30 && attempts < 300) {
            int val = rnd.nextInt(100) + 1;
            if (!cllContains(val)) { cll.add(val); added++; }
            attempts++;
        }
        logCLL("Agregados " + added + " valores aleatorios");
        refreshCLL();
    }

    private void cllSearch() {
        try {
            int val = Integer.parseInt(txtCLLValue.getText().trim());
            boolean found = cll.contains(val);
            if (found) {
                int idx = cll.indexOf(val);
                logCLL("contains(" + val + ") = true → indexOf = " + idx);
            } else {
                logCLL("contains(" + val + ") = false");
            }
        } catch (Exception ex) { logCLL(" Error: " + ex.getMessage()); }
    }

    private void cllRemove() {
        try {
            int val = Integer.parseInt(txtCLLValue.getText().trim());
            cll.remove(val);
            logCLL("remove(" + val + ")");
            refreshCLL();
        } catch (Exception ex) { logCLL(" Error: " + ex.getMessage()); }
    }

    private void cllRemoveFirst() {
        try {
            int removed = cll.removeFirst();
            logCLL("removeFirst() → " + removed);
            refreshCLL();
        } catch (ListException ex) { logCLL(" " + ex.getMessage()); }
    }

    private void cllClear() {
        cll.clear();
        logCLL("Lista limpiada");
        refreshCLL();
    }

    private boolean cllContains(int val) {
        try { return !cll.isEmpty() && cll.contains(val); }
        catch (Exception e) { return false; }
    }

    private void refreshCLL() {
        ObservableList<CLLRow> rows = FXCollections.observableArrayList();
        try {
            int size = cll.size();
            Integer head = cll.isEmpty() ? null : cll.getFirst();
            Integer tail = cll.isEmpty() ? null : cll.getLast();
            for (int i = 1; i <= size; i++) {
                Integer data = cll.get(i);
                Integer next = cll.getNext(data);
                Integer prev = cll.getPrev(data);
                rows.add(new CLLRow(i, data, next, prev, head, tail));
            }
        } catch (Exception ignored) {}
        tableCLL.setItems(rows);
        CircularLinkedListPainter.render(canvasCLL.getGraphicsContext2D(), cll);
    }

    private void logCLL(String msg) { listCLLLog.getItems().add(0, msg); }

    private void cdllAdd() {
        try {
            String name  = txtCDLLName.getText().trim();
            double price = Double.parseDouble(txtCDLLPrice.getText().trim());
            int    stock = Integer.parseInt(txtCDLLStock.getText().trim());
            String type  = cbCDLLType.getValue();
            LocalDate date = dpCDLLDate.getValue() != null ? dpCDLLDate.getValue() : LocalDate.now();
            if (name.isEmpty() || type == null) { logCDLL(" Complete todos los campos"); return; }
            Product p = new Product(cdllNextId++, name, price, stock, type, date);
            cdll.add(p);
            logCDLL("add(Product[" + p.getId() + ", " + p.getName() + "]) HEAD = [" + cdll.getFirst() + "] + ...");
            refreshCDLL();
            clearCDLLForm();
        } catch (NumberFormatException ex) { logCDLL("⚠ Precio y stock deben ser numéricos"); }
        catch (Exception ex) { logCDLL(" " + ex.getMessage()); }
    }

    private void cdllSearch() {
        String name = txtCDLLName.getText().trim();
        try {
            int size = cdll.size();
            for (int i = 1; i <= size; i++) {
                Product p = cdll.get(i);
                if (p.getName().equalsIgnoreCase(name)) {
                    logCDLL("contains(" + name + ") = true → indexOf = " + i);
                    return;
                }
            }
            logCDLL("contains(" + name + ") = false");
        } catch (Exception ex) { logCDLL(" " + ex.getMessage()); }
    }

    private void cdllSortName() {
        try {
            int size = cdll.size();
            // Bubble sort por nombre
            for (int i = 1; i <= size - 1; i++) {
                for (int j = 1; j <= size - i; j++) {
                    Product a = cdll.get(j);
                    Product b = cdll.get(j + 1);
                    if (a.getName().compareTo(b.getName()) > 0) {
                        // swap data
                        Product tmp = new Product(a.getId(), a.getName(), a.getPrice(), a.getStock(), a.getType(), a.getDate());
                        cdll.getNodeByIndex(j).data = b;
                        cdll.getNodeByIndex(j + 1).data = tmp;
                    }
                }
            }
            logCDLL("Ordenado por nombre (A→Z)");
            refreshCDLL();
        } catch (Exception ex) { logCDLL(" " + ex.getMessage()); }
    }

    private void cdllSortPrice() {
        try {
            int size = cdll.size();
            for (int i = 1; i <= size - 1; i++) {
                for (int j = 1; j <= size - i; j++) {
                    Product a = cdll.get(j);
                    Product b = cdll.get(j + 1);
                    if (a.getPrice() > b.getPrice()) {
                        Product tmp = new Product(a.getId(), a.getName(), a.getPrice(), a.getStock(), a.getType(), a.getDate());
                        cdll.getNodeByIndex(j).data = b;
                        cdll.getNodeByIndex(j + 1).data = tmp;
                    }
                }
            }
            logCDLL("Ordenado por precio (menor→mayor)");
            refreshCDLL();
        } catch (Exception ex) { logCDLL(" " + ex.getMessage()); }
    }

    private void cdllRemove() {
        String name = txtCDLLName.getText().trim();
        try {
            int size = cdll.size();
            for (int i = 1; i <= size; i++) {
                Product p = cdll.get(i);
                if (p.getName().equalsIgnoreCase(name)) {
                    cdll.remove(p);
                    logCDLL("remove(" + name + ")");
                    refreshCDLL();
                    return;
                }
            }
            logCDLL("⚠ Producto no encontrado: " + name);
        } catch (Exception ex) { logCDLL(" " + ex.getMessage()); }
    }

    private void cdllRemoveAll() {
        cdll.clear();
        cdllNextId = 1;
        logCDLL("Todos los productos eliminados");
        refreshCDLL();
    }

    private void cdllRemoveFirst() {
        try {
            Product removed = cdll.removeFirst();
            logCDLL("removeFirst() → " + removed.getName());
            refreshCDLL();
        } catch (Exception ex) { logCDLL(" " + ex.getMessage()); }
    }

    private void refreshCDLL() {
        ObservableList<Product> items = FXCollections.observableArrayList();
        try {
            int size = cdll.size();
            for (int i = 1; i <= size; i++) items.add(cdll.get(i));
        } catch (Exception ignored) {}
        tableCDLL.setItems(items);
        CircularDoublyLinkedListPainter.render(canvasCDLL.getGraphicsContext2D(), cdll);
    }

    private void clearCDLLForm() {
        txtCDLLId.clear(); txtCDLLName.clear(); txtCDLLPrice.clear();
        txtCDLLStock.clear(); cbCDLLType.setValue(null); dpCDLLDate.setValue(null);
    }

    private void logCDLL(String msg) { listCDLLLog.getItems().add(0, msg); }

    private void stackPush() {
        try {
            int val = Integer.parseInt(txtStackValue.getText().trim());
            stack.push(val);
            logStack("push(" + val + ") TOP = [" + val + "] + ...");
            refreshStack();
        } catch (NumberFormatException ex) { logStack("⚠ Ingrese un número válido"); }
        catch (StackException ex) { logStack("⚠ " + ex.getMessage()); }
    }

    private void stackPushRandom() {
        int added = 0;
        while (added < 10) {
            try {
                stack.push(rnd.nextInt(100) + 1);
                added++;
            } catch (StackException e) { break; }
        }
        logStack("Apilados " + added + " valores aleatorios");
        refreshStack();
    }

    private void stackSearch() {
        try {
            int val = Integer.parseInt(txtStackValue.getText().trim());
            int pos = stack.indexOf(val);   // usa indexOf directamente del LinkedStack
            if (pos != -1) {
                logStack("indexOf(" + val + ") = " + pos);
            } else {
                logStack("indexOf(" + val + ") = -1 (no encontrado)");
            }
        } catch (NumberFormatException ex) { logStack("⚠ Ingrese un número válido"); }
        catch (StackException ex) { logStack("⚠ " + ex.getMessage()); }
    }

    private void stackPop() {
        try {
            int val = stack.pop();
            logStack("pop() → " + val);
            refreshStack();
        } catch (StackException ex) { logStack("⚠ " + ex.getMessage()); }
    }

    private void stackRemoveValue() {
        try {
            int val = Integer.parseInt(txtStackValue.getText().trim());
            int size = stack.size();
            Integer[] items = new Integer[size];
            for (int i = 0; i < size; i++) items[i] = stack.pop();
            boolean found = false;
            for (int i = size - 1; i >= 0; i--) {
                if (!found && items[i] == val) { found = true; continue; }
                stack.push(items[i]);
            }
            if (found) logStack("remove(" + val + ") ✓");
            else logStack("⚠ Valor no encontrado: " + val);
            refreshStack();
        } catch (NumberFormatException ex) { logStack("⚠ Ingrese un número válido"); }
        catch (StackException ex) { logStack("⚠ " + ex.getMessage()); }
    }

    private void stackClear() {
        stack.clear();
        logStack("Pila limpiada");
        refreshStack();
    }

    private void refreshStack() {
        ObservableList<StackRow> rows = FXCollections.observableArrayList();
        int size = stack.size();
        Integer[] items = new Integer[size];
        try {
            for (int i = 0; i < size; i++) items[i] = stack.pop();
            for (int i = size - 1; i >= 0; i--) stack.push(items[i]);
        } catch (StackException ignored) {}
        for (int i = 0; i < size; i++) {
            String node = "N" + (i + 1);
            String next = (i + 1 < size) ? "N" + (i + 2) : "NULL";
            rows.add(new StackRow(node, items[i], next));
        }
        tableStack.setItems(rows);
        LinkedStackPainter.render(canvasStack.getGraphicsContext2D(), stack);
    }

    private void logStack(String msg) { listStackLog.getItems().add(0, msg); }


    private void queueEnqueue() {
        try {
            String name    = txtQueueName.getText().trim();
            int    age     = Integer.parseInt(txtQueueAge.getText().trim());
            String service = cbQueueService.getValue();
            LocalDate date = dpQueueDate.getValue() != null ? dpQueueDate.getValue() : LocalDate.now();
            if (name.isEmpty() || service == null) { logQueue("Complete todos los campos"); return; }

            // Prioridad según servicio (personalizable)
            int priority = switch (service) {
                case "Cajas"             -> 1;
                case "Gestión de Cuentas"-> 2;
                default                  -> 3;
            };

            QueueRow row = new QueueRow(queueNextId++, name, age, service, date.toString());
            queue.enQueue(row, priority);
            logQueue("enQueue(" + name + ") FRONT = [" + name + "] + ...");
            refreshQueue();
            clearQueueForm();
        } catch (NumberFormatException ex) { logQueue("Edad debe ser numérica"); }
        catch (QueueException ex) { logQueue("" + ex.getMessage()); }
    }

    private void queueSearch() {
        String name = txtQueueName.getText().trim();
        try {
            int size = queue.size();
            QueueRow[] items = new QueueRow[size];
            for (int i = 0; i < size; i++) items[i] = queue.deQueue();
            boolean found = false;
            for (int i = 0; i < size; i++) {
                if (items[i].getName().equalsIgnoreCase(name)) {
                    logQueue("indexOf(" + name + ") = " + (i + 1));
                    found = true;
                }
            }
            // Restaurar
            for (int i = size - 1; i >= 0; i--) queue.enQueue(items[i]);
            if (!found) logQueue("indexOf(" + name + ") = -1 (no encontrado)");
        } catch (QueueException ex) { logQueue("" + ex.getMessage()); }
    }

    private void queueCompareFront() {
        try {
            QueueRow front = queue.front();
            logQueue("front() → " + front.getName() + " | Service: " + front.getService());
        } catch (QueueException ex) { logQueue("" + ex.getMessage()); }
    }

    private void queuePeek() {
        try {
            QueueRow front = queue.peek();
            logQueue("peek() → [" + front.getId() + "] " + front.getName());
        } catch (QueueException ex) { logQueue(" " + ex.getMessage()); }
    }

    private void queueDequeue() {
        try {
            QueueRow removed = queue.deQueue();
            logQueue("deQueue() → " + removed.getName());
            refreshQueue();
        } catch (QueueException ex) { logQueue(" " + ex.getMessage()); }
    }

    private void queueSort() {
        logQueue("Cola ya ordenada por prioridad (estructura PriorityLinkedQueue)");
        refreshQueue();
    }

    private void queueClear() {
        queue.clear();
        queueNextId = 1;
        logQueue("Cola limpiada");
        refreshQueue();
    }

    private void refreshQueue() {
        ObservableList<QueueRow> rows = FXCollections.observableArrayList();
        int size = queue.size();
        QueueRow[] items = new QueueRow[size];
        try {
            for (int i = 0; i < size; i++) items[i] = queue.deQueue();
            for (int i = size - 1; i >= 0; i--) queue.enQueue(items[i]);
        } catch (QueueException ignored) {}
        for (QueueRow r : items) rows.add(r);
        tableQueue.setItems(rows);
        PriorityQueuePainter.render(canvasQueue.getGraphicsContext2D(), queue);
    }

    private void clearQueueForm() {
        txtQueueId.clear(); txtQueueName.clear(); txtQueueAge.clear();
        cbQueueService.setValue(null); dpQueueDate.setValue(null);
    }

    private void logQueue(String msg) { listQueueLog.getItems().add(0, msg); }


    public static class CLLRow {
        private final int index, data, nextData, prevData, headData, tailData;
        public CLLRow(int i, int d, Integer n, Integer p, Integer h, Integer t) {
            index=i; data=d;
            nextData = n!=null?n:0; prevData = p!=null?p:0;
            headData = h!=null?h:0; tailData = t!=null?t:0;
        }
        public int getIndex()    { return index; }
        public int getData()     { return data; }
        public int getNextData() { return nextData; }
        public int getPrevData() { return prevData; }
        public int getHeadData() { return headData; }
        public int getTailData() { return tailData; }
    }

    public static class StackRow {
        private final String node, nextNode;
        private final int data;
        public StackRow(String node, int data, String nextNode) {
            this.node=node; this.data=data; this.nextNode=nextNode;
        }
        public String getNode()     { return node; }
        public int    getData()     { return data; }
        public String getNextNode() { return nextNode; }
    }

    public static class QueueRow {
        private int id, age;
        private String name, service, date;
        public QueueRow(int id, String name, int age, String service, String date) {
            this.id=id; this.name=name; this.age=age; this.service=service; this.date=date;
        }
        public int    getId()      { return id; }
        public String getName()    { return name; }
        public int    getAge()     { return age; }
        public String getService() { return service; }
        public String getDate()    { return date; }
    }
}