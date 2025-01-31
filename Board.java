package ConcentrationGame;

import java.util.Collections;
import java.util.ArrayList;

/**
 * A Board class for Concentration
 */
public class Board {
    private static String[] tileValues = {"lion", "lion",
            "penguin", "penguin",
            "dolphin", "dolphin",
            "fox", "fox",
            "monkey", "monkey",
            "turtle", "turtle"};

    private Tile[][] gameboard = new Tile[3][4];

    /**
     * Constructor for the game. Creates the 2D gameboard
     * by populating it with card values
     */
    public Board() {
        ArrayList<String> valuesList = new ArrayList<>();
        Collections.addAll(valuesList, tileValues);
        Collections.shuffle(valuesList);

        int index = 0;
        for (int row = 0; row < gameboard.length; row++) {
            for (int col = 0; col < gameboard[row].length; col++) {
                gameboard[row][col] = new Tile(valuesList.get(index));
                index++;
            }
        }
    }

    /**
     * Returns a string representation of the board.
     */
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (Tile[] row : gameboard) {
            for (Tile tile : row) {
                if (tile.isShowingValue()) {
                    boardString.append(tile.getValue()).append("\t");
                } else {
                    boardString.append(tile.getHidden()).append("\t");
                }
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    /**
     * Determines if all tiles have been matched.
     */
    public boolean allTilesMatch() {
        for (Tile[] row : gameboard) {
            for (Tile tile : row) {
                if (!tile.matched()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sets the tile to show its value.
     */
    public void showValue(int row, int column) {
        gameboard[row][column].show();
    }

    /**
     * Checks if two tiles match and updates their state.
     */
    public String checkForMatch(int row1, int col1, int row2, int col2) {
        Tile tile1 = gameboard[row1][col1];
        Tile tile2 = gameboard[row2][col2];

        if (tile1.equals(tile2)) {
            tile1.foundMatch();
            tile2.foundMatch();
            return "It's a match!";
        } else {
            tile1.hide();
            tile2.hide();
            return "Not a match.";
        }
    }

    /**
     * Validates the selected tile position.
     */
    public boolean validateSelection(int row, int col) {
        return row >= 0 && row < gameboard.length &&
                col >= 0 && col < gameboard[0].length &&
                !gameboard[row][col].matched();
    }
}

