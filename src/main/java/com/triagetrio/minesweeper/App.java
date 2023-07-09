package com.triagetrio.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


/**
 * JavaFX App Class, does nothing besides basic window setup and fxml switching
 * java --module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar minesweeper.jar
 */
public class App extends Application {

    private static Scene scene;

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/game.fxml"), Options.windowWidth, Options.windowHeight);
        primaryStage = stage;
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Minesweeper");

        //load icon from assets folder
        ClassLoader classLoader = (new App()).getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("assets/classic/flag.png");
        Image image = new Image(input);
        stage.getIcons().add(image);
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