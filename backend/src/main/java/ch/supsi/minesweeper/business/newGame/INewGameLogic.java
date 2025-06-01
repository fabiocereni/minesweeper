package ch.supsi.minesweeper.business.newGame;

import ch.supsi.minesweeper.business.Grid;

public interface INewGameLogic {
    Grid newGame(Grid grid, int counter);
}
