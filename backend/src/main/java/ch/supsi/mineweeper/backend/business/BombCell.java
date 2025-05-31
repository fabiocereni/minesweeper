package ch.supsi.mineweeper.backend.business;

public non-sealed class BombCell extends Cell{
    public BombCell() {
        super();
    }
    @Override
    public boolean isaBomb(){
        return true;
    }
    @Override
    public boolean isExpandable() {
        return false;
    }
    @Override
    public int getNearBombs() {
        return -1;
    }
}
