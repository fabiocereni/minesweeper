package business;

import java.util.Random;

public class Grid {
    public static final int size = 9;
    public static final int maxNumBomb = size*size-1;
    public static final int minNumBomb = 1;
    private static int bombs = 10;
    private int numFlags = 0;

    private Cell[][] grid;

    public Grid() {
        this.grid = new Cell[size][size];
        for(int y = 0; y<size;y++){
            for (int x = 0; x < size; x++) {
                grid[x][y]=new Cell();
            }
        }
    }

    public void defaultGrid() {
        for(int y = 0; y<size; y++){
            for (int x = 0; x < size; x++) {
                grid[x][y].setIsaBomb(false);
                grid[x][y].setFlag(false);
                grid[x][y].setClicked(false);
                grid[x][y].setNearBombs(0);
            }
        }
    }

    public void placeBombs(){
        Random random = new Random();
        int numMinePlaced = 0;

        while(numMinePlaced < bombs){
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            if(!grid[x][y].isIsaBomb()){
                grid[x][y].setIsaBomb(true);
                numMinePlaced++;
            }
        }
        setNumbers();
    }

    private void setNumbers(){
        for(int y = 0; y<size;y++){
            for (int x = 0; x < size; x++) {
                if(grid[x][y].isIsaBomb()){
                    for (int j = y-1; j <= y+1; j++) {
                        for (int k = x-1; k <= x+1; k++) {
                            if((j==y)&&(k==x)){
                                continue;
                            }else{
                                if((j>=0)&&(j<9)&&(k>=0)&&(k<9)){
                                    grid[k][j].addNearBombs();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Cell getCell(int x, int y){
        return grid[x][y];
    }

    public int getNumFlags(){
        return numFlags;
    }

    public void incrementNumFlags(){
        numFlags++;
    }

    public void decrementNumFlags(){
        numFlags--;
    }

    public void activateButtons(){
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                this.grid[y][x].setOnState();
            }
        }
    }

    public void disableButtons(){
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                grid[y][x].setOffState();
            }
        }
    }

    public void showAll() {
        for(int y = 0; y<size; y++){
            for (int x = 0; x < size; x++) {
                grid[x][y].setClicked(true);
            }
        }
    }




}
