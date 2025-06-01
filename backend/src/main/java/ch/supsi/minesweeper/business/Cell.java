package ch.supsi.minesweeper.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BombCell.class,  name = "bomb"),
        @JsonSubTypes.Type(value = NormalCell.class, name = "normal")
})
public sealed abstract class Cell permits NormalCell, BombCell {

    @JsonProperty("clicked")
    private boolean clicked;

    @JsonProperty("isFlag")
    private boolean isFlag;

    @JsonProperty("state")
    private boolean state;

    public Cell() {
        this.clicked = false;
        this.isFlag = false;
        this.state = false;
    }

    public boolean isState() {
        return state;
    }

    @JsonIgnore
    public abstract boolean isaBomb();

    @JsonIgnore
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
