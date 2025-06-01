package ch.supsi.minesweeper.business.revealEmptyCell;

import ch.supsi.minesweeper.business.Grid;

public interface IRevealEmptyCell {

    void revealEmptyCells(int startRow, int startCol, Grid grid);

}
