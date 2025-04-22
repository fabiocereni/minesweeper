package ch.supsi.minesweeper.model;

public class Cell {
    private boolean isaBomb;
    private int nearBombs;
    private boolean clicked;
    private boolean isFlag;

    public Cell() {
        this.isaBomb = false;
        this.nearBombs = 0;
        this.clicked = false;
        this.isFlag = false;
    }

    public boolean isIsaBomb() {
        return isaBomb;
    }

    public void setIsaBomb(boolean isaBomb) {
        this.isaBomb = isaBomb;
    }

    public int getNearBombs() {
        return nearBombs;
    }

    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }

    public void addNearBombs(){
        nearBombs +=1;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        this.isFlag = flag;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }


}
