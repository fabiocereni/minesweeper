package ch.supsi.mineweeper.backend.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public sealed abstract class Cell permits NormalCell, BombCell{

    @JsonProperty
    private boolean clicked;
    @JsonProperty
    private boolean isFlag;
    @JsonProperty
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
