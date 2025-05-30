package ch.supsi.minesweeper.controller;

import javafx.scene.input.MouseButton;
public interface PlayerEventHandler extends EventHandler {

    void move();

    // add all the relevant missing behaviours
    // ...

    void openCell(int row, int col);

    void toggleFlag(int row, int col);

    void selectCell(int row, int col);

    int handleClick(int row, int col, MouseButton button);
}
