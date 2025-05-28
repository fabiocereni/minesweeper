package controller;


import Model.GameModel;
import javafx.scene.input.MouseButton;
import Interface.GameEventHandler;
import Interface.PlayerEventHandler;
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
        this.views.forEach(DataView::update);
    }

    @Override
    public void save() {
        // BISOGNA AGGIUNGERE IL SALVATAGGIO
        this.views.forEach(DataView::update);
    }

    @Override
    public void quit() {
        gameModel.quit();
    }

    @Override
    public void move() {
        gameModel.move();
        views.forEach(DataView::update);
    }

    @Override
    public void openCell(int row, int col) {
        gameModel.openCell(row, col);
        views.forEach(DataView::update);
    }

    @Override
    public void toggleFlag(int row, int col) {
        gameModel.toggleFlag(row, col);
        views.forEach(DataView::update);
    }

    @Override
    public void selectCell(int row, int col) {
        gameModel.selectCell(row, col);
        views.forEach(DataView::update);
    }

    @Override
    public void handleClick(int row, int col, MouseButton button) {
        gameModel.handleClick(row, col, button);
        views.forEach(DataView::update);
    }

    @Override
    public void revealEmptyCells(int startRow, int startCol){
        gameModel.revealEmptyCells(startRow, startCol);
        views.forEach(DataView::update);
    }


}
