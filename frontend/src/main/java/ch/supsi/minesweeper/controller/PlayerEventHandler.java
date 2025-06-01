package ch.supsi.minesweeper.controller;
public interface PlayerEventHandler extends EventHandler {

    void clickRight(int row, int col);

    void clickLeft(int row, int col);

}
