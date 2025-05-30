package controller;


import Model.GameModel;
import javafx.scene.input.MouseButton;
import Interface.GameEventHandler;
import Interface.PlayerEventHandler;
import view.ConfirmExitPopupFxml;
import view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private GameModel gameModel;
    private List<DataView> views;

    private GameController () {
        this.gameModel = GameModel.getInstance();
    }

    public static GameController getInstance() {
        if (myself == null) {
            myself = new GameController();
        }
        return myself;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void newGame() {
        gameModel.newGame();
        this.views.forEach(dataView -> dataView.update("Number bombs: " + gameModel.numberOfBombs() + " - Flag remaining: " + gameModel.numberOfFlagRemaining()));
    }

    @Override
    public void save() {
        // BISOGNA AGGIUNGERE IL SALVATAGGIO
        this.views.forEach(dataView -> dataView.update("Saved"));
    }

    @Override
    public void quit() {
        gameModel.quit();
    }

    @Override
    public void move() {
        gameModel.move();
    }

    @Override
    public void openCell(int row, int col) {
        gameModel.openCell(row, col);
    }

    @Override
    public void toggleFlag(int row, int col) {
        gameModel.toggleFlag(row, col);
    }

    @Override
    public void selectCell(int row, int col) {
        gameModel.selectCell(row, col);
    }

    @Override
    public int handleClick(int row, int col, MouseButton button) {

        switch (gameModel.handleClick(row, col, button)){
            case -1:
                views.forEach(dataView -> dataView.update("Hai Perso"));
                break;
            case 0:
                views.forEach(dataView -> dataView.update("Number bombs: " + gameModel.numberOfBombs() + " - Flag remaining: " + gameModel.numberOfFlagRemaining()));
                break;
            case 1:
                views.forEach(dataView -> dataView.update("Vittoria!"));
                break;
            default:
                views.forEach(dataView -> dataView.update(""));
                break;
        }


        return 0;
    }

    @Override
    public void revealEmptyCells(int startRow, int startCol){
        gameModel.revealEmptyCells(startRow, startCol);
        views.forEach(dataView -> dataView.update(""));
    }

    @Override
    public void about() {
        views.forEach(dataView -> dataView.update("MineSweeper v1.0 by group03"));
    }

    @Override
    public void help() {
        views.forEach(dataView -> dataView.update("Please don’t click on the bombs \uD83D\uDCA3"));
    }


}
