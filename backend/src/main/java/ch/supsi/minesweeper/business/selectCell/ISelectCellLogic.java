package ch.supsi.minesweeper.business.selectCell;

import ch.supsi.minesweeper.business.Grid;

public interface ISelectCellLogic {

    Grid selectCell(int row, int col, Grid grid);

}
