package ch.supsi.minesweeper.Model;

import ch.supsi.minesweeper.controller.CellHandler;
import ch.supsi.minesweeper.controller.GameEventHandler;
import ch.supsi.minesweeper.controller.PlayerEventHandler;
import ch.supsi.mineweeper.backend.application.GameApp;
import ch.supsi.minesweeper.view.ConfirmExitPopupFxml;

public class GameModel extends AbstractModel implements PlayerEventHandler, GameEventHandler, CellHandler{
    private static GameModel myself;
    private final GameApp gameApp;
    private GameModel(){
        this.gameApp = GameApp.getInstance();
    }
    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }
        return myself;
    }
    @Override
    public void newGame() {
        gameApp.newGame();
    }
    @Override
    public void save() {
        gameApp.save();
    }
    @Override
    public void quit() {
        ConfirmExitPopupFxml.showConfirmExit();
    }
    @Override
    public void clickRight(int row, int col) {
        gameApp.toggleFlag(row,col);
    }
    @Override
    public void clickLeft(int row, int col) {
        gameApp.selectCell(row, col);
    }
    @Override
    public boolean isFlag(int row, int col) {
        return gameApp.isFlag(row, col);
    }
    @Override
    public boolean isAbomb(int row, int col) {
        return gameApp.isAbomb(row, col);
    }
    @Override
    public boolean isClicked(int row, int col) {
        return gameApp.isClicked(row, col);
    }
    @Override
    public int getNearBombs(int row, int col) {
        return gameApp.getNearBombs(row, col);
    }
    @Override
    public boolean getState(int row, int col) {
        return gameApp.getState(row, col);
    }
    // BISOGNA TROVARE UN MODO PER RIMUOVERLO
    @Override
    public void about() {}
    @Override
    public void help() {}
    public boolean isGameWon(){return gameApp.isGameWon();};
    public boolean isGameLost(){return gameApp.isGameLost();};
    public int numberOfFlagRemaining(){
        return gameApp.numberOfFlagRemaining();
    }
    public int numberOfBombs() {
        return gameApp.numberOfBombs();
    }
}
