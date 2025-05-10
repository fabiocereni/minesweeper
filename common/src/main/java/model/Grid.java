package model;

import java.util.Random;

public class Grid {
    private static final int size = 9;
    private static final int maxNumBomb = size * size - 1;
    private static final int minNumBomb = 1;

    private static int bombs = 10;
    private int numFlags = 0;

    private Cell[][] grid;

    public Grid() {
        this.grid = new Cell[size][size];
        initCell();
    }

    public int getNumFlags() {
        return numFlags;
    }

    /* Additional methods */
    public void incrementNumFlags(){
        numFlags++;
    }

    public void decrementNumFlags(){
        numFlags--;
    }

    private void initCell() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void resetGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j].setBomb(false);
                grid[i][j].setFlag(false);
                grid[i][j].setClicked(false);
                grid[i][j].setNearBombs(0);
            }
        }
    }

    public void placeBombs() {
        Random random = new Random();
        int numMinePlaced = 0;

        while (numMinePlaced < bombs) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);

            if (!grid[y][x].isBomb()) {
                grid[y][x].setBomb(true);
                numMinePlaced++;
            }
        }
        setNumberAdjacentBombs();
    }

    private void setNumberAdjacentBombs() {
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++) {
                if (grid[x][y].isBomb()){
                    for (int j = y - 1; j <= y + 1; j++) {
                        for (int k = x - 1; k <= x + 1; k++) {
                            if ((j == y) && (k == x)){
                                continue;
                            } else {
                                if ((j >= 0) && (j < 9) && (k >= 0) && (k < 9)) {
                                    grid[k][j].addNearBombs();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void activateButton() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                grid[y][x].setOnState();
            }
        }
    }

    public void disableButton() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                grid[y][x].setOffState();
            }
        }
    }




}