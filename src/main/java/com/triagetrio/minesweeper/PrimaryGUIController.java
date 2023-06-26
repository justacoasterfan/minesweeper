package com.triagetrio.minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

    


//Handles all GUI stuff for primary.fxml
public class PrimaryGUIController {

    @FXML
    public GridPane gameGrid;

    private static Node[][] gridPaneArray = new Node[9][9];


    //lambda called on imageView click
    private static void onMouseClick(MouseEvent event) throws IOException {
        Node sourceComponent = (Node) event.getSource();
        String clickedCard = (String) sourceComponent.getUserData();

        //get column and row from clicked card
        String[] parts = clickedCard.split("\\|");
        byte column = Byte.parseByte(parts[0].trim());
        byte row = Byte.parseByte(parts[1].trim());

        //Debug println
        //System.out.println(clickedCard + " parsed to " + column + " " + row + " HasBomb: " + App.hasBomb(column, row));

        if (event.getButton() == MouseButton.PRIMARY)
            GameController.leftClickHandler(column, row);
        else
            GameController.rightClickHandler(column, row);
    }


    //Fill gameGrid with imageViews and assign unrevealed.png as well as onMouseClick() lambda, keep refernces to all Nodes in gridPaneArray
    @FXML
    public void initialize() throws FileNotFoundException {
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                FileInputStream input = new FileInputStream("src\\main\\resources\\assets\\unrevealed.png");
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(64);
                imageView.setFitHeight(64);
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
                gameGrid.add(imageView, column, row);

                for(Node node : gameGrid.getChildren())
                    gridPaneArray[GridPane.getColumnIndex(node)][GridPane.getRowIndex(node)] = node;
            }
        }
    }

    /**
     * Change Image of given field to image given as name String
     * @param x X-Coordinate of Field
     * @param y Y-Coordinate of Field
     * @param imageName Name of the image (bomb, bomb_exploded, unrevealed, revealed_empty, flag, 1-8)
     */
    public static void setFieldImage(byte x, byte y, String imageName) {
        try{
            FileInputStream input = new FileInputStream("src\\main\\resources\\assets\\" + imageName + ".png");
            Image image = new Image(input);
            ((ImageView)gridPaneArray[x][y]).setImage(image);   
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
