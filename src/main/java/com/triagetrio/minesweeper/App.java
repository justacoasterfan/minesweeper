package com.triagetrio.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App Class, does nothing besides basic window setup and fxml switching
 * java --module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar minesweeper.jar
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/primary.fxml"), 600, 600);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Minesweeper");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}