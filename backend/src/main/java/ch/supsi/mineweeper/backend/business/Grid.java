package ch.supsi.mineweeper.backend.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {
    public static final int size = 9;
    public static final int maxNumBomb = size*size-1;
    public static final int minNumBomb = 1;
    private int numFlags = 0;
    private int numberBombs = 0;
    private Cell[][] grid;

    public Grid(int bombs) {
        this.numberBombs = bombs;
        int i = 0;
        List<Cell> tmp = new ArrayList<>();
        for(int x = 0; x < numberBombs; x++){
            tmp.add(new BombCell());
        }
        for(int x = numberBombs; x < size*size; x++){
            tmp.add(new NormalCell());
        }
        Collections.shuffle(tmp);
        this.grid = new Cell[size][size];
        for(int y = 0; y<size;y++){
            for (int x = 0; x < size; x++) {
                grid[x][y]= tmp.get(i++);
            }
        }
        setNumbers();
    }

    private void setNumbers(){
        for(int y = 0; y<size;y++){
            for (int x = 0; x < size; x++) {
                if(grid[x][y].isaBomb()){
                    for (int j = y-1; j <= y+1; j++) {
                        for (int k = x-1; k <= x+1; k++) {
                            if((j==y)&&(k==x)){
                                continue;
                            }else{
                                if((j>=0)&&(j<9)&&(k>=0)&&(k<9)){
                                    if(!grid[k][j].isaBomb())
                                        ((NormalCell) grid[k][j]).addNearBombs();
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
                if(grid[x][y].isaBomb()){
                    grid[x][y].setFlag(false);
                }
            }
        }
    }

    public int getNumberBombs() {
        return numberBombs;
    }
    public int getNumberOfOpenCell(){
        int numOfCellOpen=0;
        for(int y = 0; y<size; y++){
            for (int x = 0; x < size; x++) {

                if(grid[x][y].isClicked()){
                    numOfCellOpen++;
                }

            }
        }
        return numOfCellOpen;
    }
}
