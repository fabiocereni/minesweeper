package ch.supsi.mineweeper.backend.business;

public non-sealed class NormalCell extends Cell {

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
