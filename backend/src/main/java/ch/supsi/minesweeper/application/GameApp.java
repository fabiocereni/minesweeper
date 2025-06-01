package ch.supsi.minesweeper.application;

import ch.supsi.minesweeper.business.GameLogic;

import java.nio.file.Path;

public class GameApp {
    private static GameApp myself;
    private GameLogic gameLogic;
    private GameApp() {
        this.gameLogic = GameLogic.getInstance();
    }
    public static GameApp getInstance() {
        if (myself == null) {
            myself = new GameApp();
        }
        return myself;
    }
    public void newGame() {
        gameLogic.newGame();
    }
    public void save() {
        gameLogic.save();
    }
    public void toggleFlag(int row, int col) {
        gameLogic.toggleFlag(row,col);
    }
    public void selectCell(int row, int col) {
        gameLogic.selectCell(row,col);
    }
    public int numberOfFlagRemaining(){
        return gameLogic.numberOfFlagRemaining();
    }
    public int numberOfBombs() {
        return gameLogic.numberOfBombs();
    }
    public boolean isGameWon(){return gameLogic.isGameWon();}
    public boolean isGameLost(){return gameLogic.isGameLost();}
    public boolean isFlag(int row, int col) {
        return gameLogic.isFlag(row, col);
    }
    public boolean isAbomb(int row, int col) {
        return gameLogic.isAbomb(row, col);
    }
    public boolean isClicked(int row, int col) {
        return gameLogic.isClicked(row, col);
    }
    public int getNearBombs(int row, int col) {
        return gameLogic.getNearBombs(row, col);
    }
    public boolean getState(int row, int col) {
        return gameLogic.getState(row, col);
    }

    public void save(String nomeFile) {
        gameLogic.save();
    }

    public void saveAs(Path path) {
        gameLogic.saveAs(path);
    }

    public void open(Path path) {
        gameLogic.open(path);
    }

    public boolean isGamePlaying(){return gameLogic.isGamePlaying();}
    public boolean isPathAcquired(){return gameLogic.isPathAcquired();}

}
