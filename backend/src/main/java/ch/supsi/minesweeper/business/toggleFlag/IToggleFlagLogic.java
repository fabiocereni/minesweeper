package ch.supsi.minesweeper.business.toggleFlag;

import ch.supsi.minesweeper.business.Grid;

public interface IToggleFlagLogic {

    Grid toggleFlag(int row, int col, Grid grid);

}
