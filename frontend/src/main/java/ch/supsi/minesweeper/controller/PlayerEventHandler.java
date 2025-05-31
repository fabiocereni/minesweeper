package ch.supsi.minesweeper.controller;
public interface PlayerEventHandler extends EventHandler {

    void move();

    // add all the relevant missing behaviours
    // ...

    void openCell(int row, int col);

    void toggleFlag(int row, int col);

    void selectCell(int row, int col);
    void clickRight(int row, int col);
    void clickLeft(int row, int col);
}
