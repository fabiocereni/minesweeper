package ch.supsi.minesweeper.business;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,      // Jackson “vede” ogni campo private
        getterVisibility = JsonAutoDetect.Visibility.NONE,    // ignoriamo i getter se non sono annotati
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class Grid {
    @JsonIgnore
    private static Grid myself;

    @JsonIgnore
    public static final int size = 9;

    @JsonIgnore
    public static final int maxNumBomb = size*size-1;

    @JsonIgnore
    public static final int minNumBomb = 1;

    @JsonProperty("numFlags")
    private int numFlags = 0;

    @JsonProperty("numberbombs")
    private int numberBombs = 0;

    @JsonProperty("grid")
    private Cell[][] grid;

    private Grid() {}

    public static Grid getInstance() {
        if (myself == null) {
            myself = new Grid();
        }
        return myself;
    }

    public static void setInstance(Grid newInstance) {
        if (newInstance != null) {
            myself = newInstance;
        } else {
           System.err.println("Grid non può essere null");
        }
    }

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

    public static Grid getMyself() {
        return myself;
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
