package com.triagetrio.minesweeper;

import java.io.IOException;
import java.util.Random;


//This is where all game logic happens
public class GameController {

    public static void resetGame() {
        leftClickCount = 0;
        discoveredBombs = 0;
        for(byte x = 0; x < 9; x++) {
            for(byte y = 0; y < 9; y++) {

                Map.removeBomb(x, y);
                Map.setUnrevealed(x, y);
                Map.removeFlag(x, y);
                Map.setUnblocked(x, y);
                Map.setUnexploded(x, y);

                GameGUIController.setFieldImage(x, y, Options.Textures.unrevealed);
            }
        }
    }
    
    private static int leftClickCount = 0;
    //called whenever field is left clicked with information on position
    public static void leftClickHandler(byte x, byte y) throws IOException {
        
        if (!Map.isRevealed(x, y) && !Map.hasFlag(x, y)) {
            if(!Map.hasBomb(x, y)) {
                byte surroundingBombs = proximityBombNumber(x, y);
                if (surroundingBombs == 0)
                    GameGUIController.setFieldImage(x, y, Options.Textures.revealed_empty);
                else
                    GameGUIController.setFieldImage(x, y, Options.Textures.texturePack + "/" + Byte.toString( surroundingBombs ));
                Map.setRevealed(x, y);
            }
            else {
                GameGUIController.setFieldImage(x, y, Options.Textures.bomb_exploded);
                Map.setExploded(x, y);
                showEndScreen(false);
            }
        }

        //clicked, increment click counter by one
        leftClickCount++;
        
        //check if first move to place bombs and block starting fields
        if (leftClickCount == 1) {

            for (byte i = 0; i < 8; i++) {
            // If current adjacent field is valid block it
                if ( isValidField((byte) (x + Map.xOffset[i]), (byte) (y + Map.yOffset[i]) ))
                    Map.setBlocked(x + Map.xOffset[i], y + Map.yOffset[i]);
            }
            //place the bombs around starting field
            populate();
        }
            
    }

    private static byte discoveredBombs;
    //called whenever field is right or middle clicked with information on position
    public static void rightClickHandler(byte x, byte y) throws IOException {
        if(!Map.isRevealed(x, y)) {
            if(!Map.hasFlag(x, y)) {
                Map.placeFlag(x, y);
                //check if flag is placed on bomb field and add one discovered bomb if true
                if(Map.hasBomb(x, y))
                    discoveredBombs++;
                GameGUIController.setFieldImage(x, y, Options.Textures.flag);
            }
            else {
                Map.removeFlag(x, y);

                //check if flag was on bomb field and remove one discovered bomb if true
                if(Map.hasBomb(x, y))
                    discoveredBombs--;

                GameGUIController.setFieldImage(x, y, Options.Textures.unrevealed);
            }
        }

        //check if all bombs were discovered
        if(discoveredBombs == Options.bombCount) {
            showEndScreen(true);
        }
    }


    //Randomly place 10 bombs on map
    private static void populate() { 
        Random random = new Random();

        int repeat = 0;
        while (repeat < Options.bombCount )   {    
            repeat ++;
            int x =  random.nextInt(9);
            int y = random.nextInt(9);
            if (Map.hasBomb(x, y) || Map.isBlocked(x, y))
                repeat --;
            else
                Map.placeBomb(x, y);
        }  
        /*Geringes Risiko für Endlosschleife wenn immer wieder Zahlen 
       von bereits belegtem Feld gewählt werden, jedoch unwahrscheinlich
       aufgrund von nur 12% Bombenverteilung, daher zu vernachlässigen*/
    }  

  
    private static void showEndScreen(boolean hasWon) throws IOException{

        uncoverRemaining();

        if (hasWon) {
            System.out.println("Your win are belong to you");
            // try {
            //         App.setRoot("/won.fxml");
            //     } catch (IOException e) {
            //         e.printStackTrace();
            //     }
        }
        else {
            System.out.println("*boom* You have perished");
            // try {
            //         App.setRoot("/lost.fxml");
            //     } catch (IOException e) {
            //         e.printStackTrace();
            //     }
        } 

        //needed to refresh
        throw new IOException();
    }


    private static void uncoverRemaining() {
        for(byte x = 0; x < 9; x++) {
            for(byte y = 0; y < 9; y++) {
                if(!Map.isRevealed(x, y)) {
                    if(!Map.hasFlag(x, y)) {
                        Map.setRevealed(x, y);
                        if(!Map.hasBomb(x, y)) {
                            byte surroundingBombs = proximityBombNumber(x, y);
                            if (surroundingBombs == 0)
                                GameGUIController.setFieldImage(x, y, Options.Textures.revealed_empty);
                            else
                                GameGUIController.setFieldImage(x, y, Options.Textures.texturePack + "/" + Byte.toString( surroundingBombs ));
                        }
                        else if(!Map.isExploded(x, y))
                            GameGUIController.setFieldImage(x, y, Options.Textures.bomb);
                    }
                }
            }
        }
    }


    /**
     * Returns number of bombs around given field
     * @param x X-Coordinate of Field to check
     * @param y Y-Coordinate of Field to check
     * @return Number of Bombs around given field
     */
    private static byte proximityBombNumber(byte x, byte y) {
        //check all 8 surrounding fields for bombs

        byte numberBombs = 0;

        for (byte i = 0; i < 8; i++) {
          // If current adjacent cell is valid
          if ( isValidField((byte) (x + Map.xOffset[i]), (byte) (y + Map.yOffset[i]) ) && (Map.hasBomb(x + Map.xOffset[i], y + Map.yOffset[i])) )
            numberBombs ++;
        }
        return numberBombs;
    }

    //simple utility method for proximityBombBumber and field blocking to check if a selected field even exists
    private static boolean isValidField(byte x, byte y) {
        // Returns true if valid
        return (x >= 0 && y >= 0 && x < 9 && y < 9);
    }
}
