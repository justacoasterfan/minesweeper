package com.triagetrio.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 600, 600);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Minesweeper");

        PrimaryController primaryController = new PrimaryController();

        primaryController.initBoard();
        populate();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void populate() { 
        Random random = new Random();

        int repeat = 0;
        while (repeat < 10 )   {    
            repeat ++;
            int x =  random.nextInt(9);
            int y = random.nextInt(9);
            if (map [x][y] == true)
                repeat --;
            else
                map [x][y] = true;  
       /*Risiko für Endlosschleife wenn immer wieder Zahlen 
       von bereits belegtem Feld gewählt werden */
     
        }  
    }  

    public static boolean map[][] = new boolean[9][9];

    public static byte proximityBombNumber(int x, int y) {
        //check 8 surrounding fields for bombs
        byte numberBombs = 0;
        if (map[x-1][y+1] == true) numberBombs ++;
        if (map[x+0][y+1] == true) numberBombs ++;
        if (map[x+1][y+1] == true) numberBombs ++;
        if (map[x-1][y+0] == true) numberBombs ++;
        if (map[x+1][y+0] == true) numberBombs ++;
        if (map[x-1][y-1] == true) numberBombs ++;
        if (map[x+0][y-1] == true) numberBombs ++;
        if (map[x+1][y-1] == true) numberBombs ++;

        return numberBombs;
    }

    public static boolean hasBomb(int x, int y){
   
        if (map [x][y] == true)
            return true;
        else
            return false;
    }

    public static void showEndScreen (boolean hasWon){
        if (hasWon == true)
            System.out.println ("Your win are belong to you");
        else
            System.out.println ("*boom* You have perished");
    }

}