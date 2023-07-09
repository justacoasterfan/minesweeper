package com.triagetrio.minesweeper;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

    


//Handles all GUI stuff for primary.fxml
public class GameGUIController {

    @FXML
    private GridPane gameGrid;

    private static ImageView smiley;

    @FXML
    private AnchorPane anchorPane;

    private static Node[][] gridPaneArray = new Node[9][9];


    //Fill gameGrid with imageViews and assign unrevealed.png as well as onMouseClick() lambda, keep refernces to all Nodes in gridPaneArray
    @FXML
    public void initialize() throws FileNotFoundException {

        //iterate through gridPane and configure imageViews
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                ClassLoader classLoader = (new App()).getClass().getClassLoader();
                InputStream input = classLoader.getResourceAsStream("assets/" + Options.Textures.unrevealed + ".png");
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(64);
                imageView.setFitHeight(64);
                imageView.setUserData(column + "|" + row);
                imageView.setOnMouseClicked(event -> { onMouseClick(event); });
                gameGrid.add(imageView, column, row);

                //add each node to the array to make them accessible later on
                for(Node node : gameGrid.getChildren())
                    gridPaneArray[GridPane.getColumnIndex(node)][GridPane.getRowIndex(node)] = node;
            }
        }

        // set up smiley
        ClassLoader classLoader = (new App()).getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("assets/" + Options.Textures.unrevealed + ".png");
        Image image = new Image(input);
        smiley = new ImageView(image);
        smiley.setFitWidth(96);
        smiley.setFitHeight(96);
        smiley.setOnMouseClicked(event -> { smileyClicked(); });
        smiley.setX(Options.windowWidth/2 - 48);
        smiley.setY(Options.windowHeight - 672 - 2);

        anchorPane.getChildren().add(smiley);


        GameController.resetGame();
        
    }

    //lambda called on imageView click
    private static void onMouseClick(MouseEvent event) {

        //get node and user data of clicked card
        Node sourceComponent = (Node) event.getSource();
        String clickedCard = (String) sourceComponent.getUserData();

        //get column and row from node user data
        String[] parts = clickedCard.split("\\|");
        byte column = Byte.parseByte(parts[0].trim());
        byte row = Byte.parseByte(parts[1].trim());

        //check if left click and call corresponding functions
        if (event.getButton() == MouseButton.PRIMARY)
            GameController.leftClickHandler(column, row);
        else
            GameController.rightClickHandler(column, row);
    }

    /**
     * Change Image of given field to image given as name String
     * @param x X-Coordinate of Field
     * @param y Y-Coordinate of Field
     * @param imageName Name of the image (bomb, bomb_exploded, unrevealed, revealed_empty, flag, 1-8)
     */
    public static void setFieldImage(byte x, byte y, String imageName) {

        //load specified image from assets folder
        ClassLoader classLoader = (new App()).getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("assets/" + imageName + ".png");
        Image image = new Image(input);

        //get imageView component from gridPaneArray and set new image
        ((ImageView)gridPaneArray[x][y]).setImage(image);
    }


    //lambda called on smiley click
    private void smileyClicked() {
        GameController.smileyClickHandler();
    }

    //smiley state changer
    public static void setSmileyState(String state) {
        //load specified image from assets folder
        ClassLoader classLoader = (new App()).getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("assets/" + state + ".png");
        Image image = new Image(input);

        smiley.setImage(image);
    }


    // Options menu handling

    @FXML
    private void restart() {
        GameController.resetGame();
    }

    @FXML
    private void setTexturesMC() {
        Options.Textures.texturePack = "mc";
        Options.Textures.reload();
        GameController.resetGame();
    }

    @FXML
    private void setTexturesClassic() {
        Options.Textures.texturePack = "classic";
        Options.Textures.reload();
        GameController.resetGame();
    }

    @FXML
    private void setTexturesPatrick() {
        Options.Textures.texturePack = "patrick";
        Options.Textures.reload();
        GameController.resetGame();
    }

    @FXML
    private void setBombNumber5() {
        Options.bombCount = 5;
        GameController.resetGame();
    }

    @FXML
    private void setBombNumber10() {
        Options.bombCount = 10;
        GameController.resetGame();
    }

    @FXML
    private void setBombNumber15() {
        Options.bombCount = 15;
        GameController.resetGame();
    }

    @FXML
    private void setBombNumber20() {
        Options.bombCount = 20;
        GameController.resetGame();
    }

    @FXML
    private void setBombNumber25() {
        Options.bombCount = 25;
        GameController.resetGame();
    }
}