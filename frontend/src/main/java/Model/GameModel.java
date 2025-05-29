package Model;

import Interface.AbstractModel;
import Interface.GameEventHandler;
import Interface.PlayerEventHandler;
import application.GameApp;
import javafx.scene.input.MouseButton;
import business.Cell;
import business.GameLogic;
import view.ConfirmExitPopupFxml;

public class GameModel extends AbstractModel implements PlayerEventHandler, GameEventHandler {
    private static GameModel myself;
    GameApp gameApp;

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
    public void move() {
        gameApp.move();
    }

    @Override
    public void openCell(int row, int col) {
        gameApp.openCell(row,col);
    }

    @Override
    public void toggleFlag(int row, int col) {
        gameApp.toggleFlag(row,col);
    }

    @Override
    public void selectCell(int row, int col) {
        gameApp.selectCell(row,col);
    }

    @Override
    public void handleClick(int row, int col, MouseButton button) {
        gameApp.handleClick(row,col,button);
    }
    public Cell getCell(int x, int y){
        return gameApp.getCell(x,y);
    }
    public int numberOfFlagRemaining(){
        return gameApp.numberOfFlagRemaining();
    }
    public int numberOfBombs() {
        return gameApp.numberOfBombs();
    }

    @Override
    public void revealEmptyCells(int startRow, int startCol){
        gameApp.revealEmptyCells(startRow,startCol);
    }


    // BISOGNA TROVARE UN MODO PER RIMUOVERLO
    @Override
    public void about() {
    }

    @Override
    public void help() {

    }


}
