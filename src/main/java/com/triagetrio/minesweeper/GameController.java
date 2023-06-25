package com.triagetrio.minesweeper;

import java.util.Random;


//This is where all game logic happens
public class GameController {

    //Called at end of App.start()
    public static void startGame() {

    }
    
    //called whenever field is left clicked with information on position
    public static void leftClickHandler(byte x, byte y) {
        
    }

    //called whenever field is right or middle clicked with information on position
    public static void rightClickHandler(byte x, byte y) {

    }


    //Randomly place 10 bombs on map
    private static void populate() { 
        Random random = new Random();

        int repeat = 0;
        while (repeat < 10 )   {    
            repeat ++;
            int x =  random.nextInt(9);
            int y = random.nextInt(9);
            if (Map.hasBomb(x, y))
                repeat --;
            else
                Map.placeBomb(x, y);
        }  
        /*Geringes Risiko für Endlosschleife wenn immer wieder Zahlen 
       von bereits belegtem Feld gewählt werden, jedoch unwahrscheinlich
       aufgrund von nur 12% Bombenverteilung, daher zu vernachlässigen*/
    }  

    public static void showEndScreen (boolean hasWon){
        if (hasWon == true)
            System.out.println ("Your win are belong to you");
        else
            System.out.println ("*boom* You have perished");
    }


    /**
     * Returns number of bombs around given field
     * @param x
     * @param y
     * @return
     */
    private static byte proximityBombNumber(byte x, byte y) {
        //check all 8 surrounding fields for bombs

        byte numberBombs = 0;

        //Direction arrays (contain coordinate offsets to check)
        final byte xOffset[] = { -1, 1, -1, 0, 1, -1, 0, 1 };
        final byte yOffset[] = { 0, 0, -1, -1, -1, 1, 1, 1 };

        for (byte i = 0; i < 8; i++) {
          // If current adjacent cell is valid
          if ( isValidField((byte) (x + xOffset[i]), (byte) (y + yOffset[i]) ) && (Map.hasBomb(x + xOffset[i], y + yOffset[i])) )
            numberBombs ++;
        }
        return numberBombs;
    }

    //simple utility method for proximityBombBumber to check if a selected field even exists
    private static boolean isValidField(byte x, byte y) {
        // Returns true if valid
        return (x >= 0 && y >= 0 && x < 9 && y < 9);
    }
}
