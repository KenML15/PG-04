module com.example.pg04 {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml;
    opens model to javafx.fxml;
    opens model.linkedList to javafx.fxml;
    opens model.stack to javafx.fxml;
    opens model.queue to javafx.fxml;
    opens model.painters to javafx.fxml;

    exports controller;
    exports model;
    exports model.linkedList;
    exports model.stack;
    exports model.queue;
    exports model.painters;
}