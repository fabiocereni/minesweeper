package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.controller.EventHandler;

public interface PlayerEventHandler extends EventHandler {

    void move();

    // add all the relevant missing behaviours
    // ...

    void openCell(int row, int col);

    void toggleFlag(int row, int col);

}
