package application;

import business.Cell;
import business.GameLogic;
import javafx.scene.input.MouseButton;

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

    public void quit() {
        gameLogic.quit();
    }

    public void move() {
        gameLogic.move();
    }

    public void openCell(int row, int col) {
        gameLogic.openCell(row,col);
    }

    public void toggleFlag(int row, int col) {
        gameLogic.toggleFlag(row,col);
    }

    public void selectCell(int row, int col) {
        gameLogic.selectCell(row,col);
    }

    public void handleClick(int row, int col, MouseButton button) {
        gameLogic.handleClick(row,col,button);
    }

    public Cell getCell(int x, int y){
        return gameLogic.getCell(x,y);
    }

    public int numberOfFlagRemaining(){
        return gameLogic.numberOfFlagRemaining();
    }

    public void revealEmptyCells(int startRow, int startCol){
        gameLogic.revealEmptyCells(startRow,startCol);
    }

}
