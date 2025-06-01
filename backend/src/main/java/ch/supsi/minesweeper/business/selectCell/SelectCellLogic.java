package ch.supsi.minesweeper.business.selectCell;

import ch.supsi.minesweeper.business.Cell;
import ch.supsi.minesweeper.business.Grid;
import ch.supsi.minesweeper.business.revealEmptyCell.IRevealEmptyCell;
import ch.supsi.minesweeper.business.revealEmptyCell.RevealEmptyCell;

public class SelectCellLogic implements ISelectCellLogic {

    private static SelectCellLogic myself;
    private final IRevealEmptyCell revealEmptyCellLogic;

    private SelectCellLogic() {
        this.revealEmptyCellLogic = RevealEmptyCell.getInstance();
    }

    public static SelectCellLogic getInstance() {
        if (myself == null) {
            myself = new SelectCellLogic();
        }
        return myself;
    }


    @Override
    public Grid selectCell(int row, int col, Grid grid) {
        Cell cell = grid.getCell(row, col);
        if(!cell.isFlag()) {
            cell.setClicked(true);
            if (cell.isExpandable()) {
                revealEmptyCellLogic.revealEmptyCells(row, col, grid);
            }
        }
        return grid;
    }
}
