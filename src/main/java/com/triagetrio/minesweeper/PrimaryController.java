package com.triagetrio.minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

    

public class PrimaryController {

    @FXML public GridPane gridPane;

    public Node[][] gridPaneArray = new Node[9][9];


    private static void onMouseClick(MouseEvent event) throws IOException {
        Node sourceComponent = (Node) event.getSource();
        String clickedCard = (String) sourceComponent.getUserData();

        //get column and row from clicked card
        String[] parts = clickedCard.split("\\|");
        int column = Integer.parseInt(parts[0].trim());
        int row = Integer.parseInt(parts[1].trim());
        System.out.println(clickedCard + " parsed to " + column + " " + row);
    }

    public void initBoard() throws FileNotFoundException {
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                FileInputStream input = new FileInputStream("C:/Users/marlo/minesweeper/src/main/java/com/triagetrio/minesweeper/resources/assets/unrevealed.png");
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setUserData(column + "|" + row);
                imageView.setOnMouseClicked(event -> {
                    try {
                        onMouseClick(event);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gridPane.add(imageView, column, row);

                for(Node node : gridPane.getChildren())
                    gridPaneArray[GridPane.getColumnIndex(node)][GridPane.getRowIndex(node)] = node;
            }
        }
    }
}
