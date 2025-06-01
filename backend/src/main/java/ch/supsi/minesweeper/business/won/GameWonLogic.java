package ch.supsi.minesweeper.business.won;

import ch.supsi.minesweeper.business.Grid;

public class GameWonLogic implements IGameWonLogic {

    private static GameWonLogic myself;

    private GameWonLogic() {
    }

    public static GameWonLogic getInstance() {
        if (myself == null) {
            myself = new GameWonLogic();
        }
        return myself;
    }

    @Override
    public boolean isGameWon(Grid grid) {
        if (grid.getNumberOfOpenCell() == ((Grid.size * Grid.size) - grid.getNumberBombs())) {
            System.out.println("vittoria");
            grid.showAll();
            grid.disableButtons();
            return true;
        } else {
            return false;
        }
    }
}
