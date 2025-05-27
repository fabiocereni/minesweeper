package Model;

import Interface.AbstractModel;
import Interface.GameEventHandler;
import Interface.PlayerEventHandler;
import javafx.scene.input.MouseButton;
import model.Cell;
import model.GameLogic;

public class GameModel extends AbstractModel implements PlayerEventHandler, GameEventHandler {
    GameLogic gameLogic;
    private static GameModel myself;

    public GameModel(){
        this.gameLogic = GameLogic.getInstance();
    }
    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }
        return myself;
    }

    @Override
    public void newGame() {
        gameLogic.newGame();
    }

    @Override
    public void save() {
        gameLogic.save();
    }

    @Override
    public void quit() {
        gameLogic.quit();
    }

    @Override
    public void move() {
        gameLogic.move();
    }

    @Override
    public void openCell(int row, int col) {
        gameLogic.openCell(row,col);
    }

    @Override
    public void toggleFlag(int row, int col) {
        gameLogic.toggleFlag(row,col);
    }

    @Override
    public void selectCell(int row, int col) {
        gameLogic.selectCell(row,col);
    }

    @Override
    public void handleClick(int row, int col, MouseButton button) {
        gameLogic.handleClick(row,col,button);
    }
    public Cell getCell(int x, int y){
        return gameLogic.getCell(x,y);
    }
    public int numberOfFlaggedCells(){
        return gameLogic.numberOfFlaggedCells();
    }

    @Override
    public void revealEmptyCells(int startRow, int startCol){
        gameLogic.revealEmptyCells(startRow,startCol);
    }
}
