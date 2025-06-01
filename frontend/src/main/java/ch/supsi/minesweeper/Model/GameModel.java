package ch.supsi.minesweeper.Model;

import ch.supsi.minesweeper.application.GameApp;
import ch.supsi.minesweeper.view.ConfirmExitPopupFxml;

import java.nio.file.Path;

public class GameModel extends AbstractModel{

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

    public void newGame() {
        gameApp.newGame();
    }

    public void quit() {
        ConfirmExitPopupFxml.showConfirmExit();
    }

    public void clickRight(int row, int col) {
        gameApp.toggleFlag(row,col);
    }

    public void clickLeft(int row, int col) {
        gameApp.selectCell(row, col);
    }

    public boolean isFlag(int row, int col) {
        return gameApp.isFlag(row, col);
    }

    public boolean isAbomb(int row, int col) {
        return gameApp.isAbomb(row, col);
    }

    public boolean isClicked(int row, int col) {
        return gameApp.isClicked(row, col);
    }

    public int getNearBombs(int row, int col) {
        return gameApp.getNearBombs(row, col);
    }

    public boolean getState(int row, int col) {
        return gameApp.getState(row, col);
    }

    public boolean isGameWon() { return gameApp.isGameWon(); };

    public boolean isGameLost() { return gameApp.isGameLost(); };

    public int numberOfFlagRemaining(){
        return gameApp.numberOfFlagRemaining();
    }

    public int numberOfBombs() {
        return gameApp.numberOfBombs();
    }

    public void saveAs(Path path) {
        gameApp.saveAs(path);
    }

    public void save() {
        gameApp.save();
    }

    public void open(Path path) {
        gameApp.open(path);
    }

    public boolean isGamePlaying() { return gameApp.isGamePlaying(); }

    public boolean isPathAcquired() { return gameApp.isPathAcquired(); }

}
