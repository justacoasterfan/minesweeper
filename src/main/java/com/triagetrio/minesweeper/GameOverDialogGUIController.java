package com.triagetrio.minesweeper;

import java.io.InputStream;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverDialogGUIController {

    public static void showGameOver(Stage primaryStage, boolean hasWon) {
        final Stage dialog = new Stage();
        ClassLoader classLoader = (new App()).getClass().getClassLoader();
        InputStream iconInput = classLoader.getResourceAsStream("assets/classic/flag.png");
        Image icon = new Image(iconInput);
        dialog.getIcons().add(icon);
        dialog.setResizable(false);

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        if(hasWon) {
            InputStream input = classLoader.getResourceAsStream("assets/" + Options.Textures.game_won + ".png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);
            dialogVbox.getChildren().add(imageView);
        }
        else {
            InputStream input = classLoader.getResourceAsStream("assets/" + Options.Textures.game_lost + ".png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);
            dialogVbox.getChildren().add(imageView);
        }
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
}
