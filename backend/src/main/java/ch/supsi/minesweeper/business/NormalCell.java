package ch.supsi.minesweeper.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public non-sealed class NormalCell extends Cell {

    @JsonProperty("nearBombs")
    private int nearBombs;

    public NormalCell() {
        super();
        this.nearBombs = 0;
    }

    public int getNearBombs() {
        return nearBombs;
    }

    @Override
    public boolean isExpandable() {
        return nearBombs == 0;
    }

    public void setNearBombs(int nearBombs) {
        this.nearBombs = nearBombs;
    }

    public void addNearBombs(){
        nearBombs +=1;
    }

    @Override
    public boolean isaBomb() {
        return false;
    }

}
