package ch.supsi.mineweeper.backend.business;

public sealed abstract class Cell permits NormalCell, BombCell{
    private boolean clicked;
    private boolean isFlag;
    private boolean state;
    public Cell() {
        this.clicked = false;
        this.isFlag = false;
        this.state = false;
    }
    public abstract boolean isaBomb();
    public abstract boolean isExpandable();
    public abstract int getNearBombs();
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
    public void setOnState(){this.state=true;}
    public void setOffState(){this.state=false;}
    public boolean getState(){return state;}



}
