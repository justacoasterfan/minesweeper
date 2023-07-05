package com.triagetrio.minesweeper;

public class Options {
    
    public static final byte bombCount = 10;

    public class Textures {

        public static String texturePack = "classic";

        public static String unrevealed = texturePack + "/unrevealed";
        public static String revealed_empty = texturePack + "/revealed_empty";
        public static String bomb = texturePack + "/bomb";
        public static String bomb_exploded = texturePack + "/bomb_exploded";
        public static String flag = texturePack + "/flag";

        public static void reload() {
            unrevealed = texturePack + "/unrevealed";
            revealed_empty = texturePack + "/revealed_empty";
            bomb = texturePack + "/bomb";
            bomb_exploded = texturePack + "/bomb_exploded";
            flag = texturePack + "/flag";
        }


    }

}
