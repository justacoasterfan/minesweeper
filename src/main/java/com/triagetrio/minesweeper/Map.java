package com.triagetrio.minesweeper;

//Hide all Array stuff to reduce new things
public class Map {
    //Initialize all Map Arrays
    private static boolean bombMap[][] = new boolean[9][9];
    private static boolean flagMap[][] = new boolean[9][9];
    private static boolean revealedMap[][] = new boolean[9][9];
    private static boolean blockedMap[][] = new boolean[9][9];

    //Direction arrays (contain coordinate offsets for all neighboring fields)
    public static final byte xOffset[] = { -1, 1, -1, 0, 1, -1, 0, 1 };
    public static final byte yOffset[] = { 0, 0, -1, -1, -1, 1, 1, 1 };

    //Wrapper methods to reduce having to work with Arrays ( as little new stuff as possible ;) )
    public static boolean hasBomb(byte x, byte y) { return bombMap[x][y]; }
    public static boolean isRevealed(byte x, byte y) { return revealedMap[x][y]; }
    public static boolean hasFlag(byte x, byte y) { return flagMap[x][y]; }
    public static boolean isBlocked(byte x, byte y) { return blockedMap[x][y]; }

    public static void placeBomb(byte x, byte y) { bombMap[x][y] = true; }
    public static void setRevealed(byte x, byte y) { revealedMap[x][y] = true; }
    public static void placeFlag(byte x, byte y) { flagMap[x][y] = true; }
    public static void setBlocked(byte x, byte y) { blockedMap[x][y] = true; }

    public static void removeBomb(byte x, byte y) { bombMap[x][y] = false; }
    public static void setUnrevealed(byte x, byte y) { revealedMap[x][y] = false; }
    public static void removeFlag(byte x, byte y) { flagMap[x][y] = false; }
    public static void setUnblocked(byte x, byte y) { blockedMap[x][y] = false; }


    //Byte versions
    public static boolean hasBomb(int x, int y) { return bombMap[x][y]; }
    public static boolean isRevealed(int x, int y) { return revealedMap[x][y]; }
    public static boolean hasFlag(int x, int y) { return flagMap[x][y]; }
    public static boolean isBlocked(int x, int y) { return blockedMap[x][y]; }

    public static void placeBomb(int x, int y) { bombMap[x][y] = true; }
    public static void setRevealed(int x, int y) { revealedMap[x][y] = true; }
    public static void placeFlag(int x, int y) { flagMap[x][y] = true; }
    public static void setBlocked(int x, int y) { blockedMap[x][y] = true; }

    public static void removeBomb(int x, int y) { bombMap[x][y] = false; }
    public static void setUnrevealed(int x, int y) { revealedMap[x][y] = false; }
    public static void removeFlag(int x, int y) { flagMap[x][y] = false; }
    public static void setUnblocked(int x, int y) { blockedMap[x][y] = false; }
}
