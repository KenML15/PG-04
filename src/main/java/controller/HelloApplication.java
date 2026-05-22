ppackage controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 740);
        stage.setTitle("PG-04 · Listas Enlazadas, Pilas y Colas · IF-3001 UCR");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}