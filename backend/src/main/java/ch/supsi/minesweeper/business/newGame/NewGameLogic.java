package ch.supsi.minesweeper.business.newGame;

import ch.supsi.minesweeper.business.Grid;
import ch.supsi.minesweeper.data.GameSettingsData;

public class NewGameLogic implements INewGameLogic {

    private static NewGameLogic myself;

    private NewGameLogic() {
    }

    public static NewGameLogic getInstance() {
        if (myself == null) {
            myself = new NewGameLogic();
        }
        return myself;
    }


    @Override
    public Grid newGame(Grid grid, int counter) {
        int bombs = GameSettingsData.getInstance().getNumBombs();
        grid = new Grid(bombs);
        Grid.setInstance(grid);
        grid.activateButtons();
        return grid;
    }
}
