package ch.supsi.minesweeper.business.toggleFlag;

import ch.supsi.minesweeper.business.Cell;
import ch.supsi.minesweeper.business.Grid;

public class ToggleFlagLogic implements IToggleFlagLogic {

    private static ToggleFlagLogic myself;

    private ToggleFlagLogic() {
    }

    public static ToggleFlagLogic getInstance() {
        if (myself == null) {
            myself = new ToggleFlagLogic();
        }
        return myself;
    }

    @Override
    public Grid toggleFlag(int row, int col, Grid grid) {
        Cell cell = grid.getCell(row, col);
        if (!cell.isClicked()) {
            if((grid.getNumFlags() == Grid.maxNumBomb)&&(!cell.isFlag())){
                // ALERT non puoi mettere altre flag
            }else{
                cell.setFlag(!cell.isFlag());
                if(cell.isFlag()){
                    grid.incrementNumFlags();
                } else {
                    grid.decrementNumFlags();
                }
            }
        }
        return grid;
    }

}
