package ch.supsi.minesweeper.model;

import java.util.Random;

public class Grid {
    private static final int size = 9;
    private static final int maxNumBomb = size*size-1;
    private static final int minNumBomb = 1;
    private static int bombs;
    private static int flags;

    private Cell[][] field;

    public Grid() {
        this.field = new Cell[size][size];
        for(int y = 0; y<size;y++){
            for (int x = 0; x < size; x++) {
                field[y][x]=new Cell();
            }
        }
    }

    private void placeBombs(){
        Random random = new Random();
        int numMinePlaced = 0;

        while(numMinePlaced < bombs){
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            if(!field[x][y].isIsaBomb()){
                field[x][y].setIsaBomb(true);
                numMinePlaced++;
            }
        }
    }

    private void setNumbers(){
        for(int y = 0; y<size;y++){
            for (int x = 0; x < size; x++) {

                if(field[y][x].isIsaBomb()){

                    field[y-1][x-1].addNearBombs();
                    field[y-1][x].addNearBombs();
                    field[y-1][x-1].addNearBombs();




                }


            }
        }
    }


}
