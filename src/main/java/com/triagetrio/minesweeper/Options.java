package com.triagetrio.minesweeper;


// contains all game options
public class Options {
    
    public static byte bombCount = 10;

    public static final double windowWidth = 576;
    public static final double windowHeight = 676;

    public class Textures {

        public static String texturePack = "classic";

        public static String unrevealed = texturePack + "/unrevealed";
        public static String revealed_empty = texturePack + "/revealed_empty";
        public static String bomb = texturePack + "/bomb";
        public static String bomb_exploded = texturePack + "/bomb_exploded";
        public static String flag = texturePack + "/flag";
        public static String flag_wrong = texturePack + "/flag_wrong";
        public static String smiley_regular = texturePack + "/smiley_regular";
        public static String smiley_lost = texturePack + "/smiley_lost";
        public static String smiley_won = texturePack + "/smiley_won";
        public static String game_won = texturePack + "/game_won";
        public static String game_lost = texturePack + "/game_lost";
        

        // called on texture pack change to "apply" new textures
        public static void reload() {
            unrevealed = texturePack + "/unrevealed";
            revealed_empty = texturePack + "/revealed_empty";
            bomb = texturePack + "/bomb";
            bomb_exploded = texturePack + "/bomb_exploded";
            flag = texturePack + "/flag";
            flag_wrong = texturePack + "/flag_wrong";
            smiley_regular = texturePack + "/smiley_regular";
            smiley_lost = texturePack + "/smiley_lost";
            smiley_won = texturePack + "/smiley_won";
            game_won = texturePack + "/game_won";
            game_lost = texturePack + "/game_lost";
        }
    }

}
