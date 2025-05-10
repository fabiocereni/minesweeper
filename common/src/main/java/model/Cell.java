package model;

public class Cell {
    private boolean isBomb;
    private boolean isClicked;
    private boolean isFlag;
    private int nearBombs;

    private boolean state;

    public Cell() {
        this.isBomb = false;
        this.isClicked = false;
        this.isFlag = false;
        this.nearBombs = 0;
        this.state = false;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public int getNearBombs() {
        return nearBombs;
    }

    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    /* additional methods */
    public void setOnState() {
        this.state = true;
    }

    public void setOffState() {
        this.state = false;
    }

    public void addNearBombs() {
        nearBombs += 1;
    }


}