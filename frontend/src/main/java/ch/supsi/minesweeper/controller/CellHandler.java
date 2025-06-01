package ch.supsi.minesweeper.controller;

public interface CellHandler {

    boolean isFlag(int row, int col);

    boolean isAbomb(int row, int col);

    boolean isClicked(int row, int col);

    int getNearBombs(int row, int col);

    boolean getState(int row, int col);

}
