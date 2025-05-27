package controller;


import javafx.scene.input.MouseButton;
import Interface.GameEventHandler;
import model.GameLogic;
import Interface.PlayerEventHandler;
import view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;

    private GameLogic gameLogic;

    private List<DataView> views;

    private GameController () {
        this.gameLogic = GameLogic.getInstance();
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
        // do whatever you must do to start a new game
        gameLogic.newGame();
        // then update your views
        this.views.forEach(DataView::update);
    }

    @Override
    public void save() {
        // do whatever you must do to start a new game

        // then update your views
        this.views.forEach(DataView::update);
    }

    @Override
    public void quit() {
        this.gameLogic.quit();
    }

    // add all the relevant methods to handle all those defined by the GameEventHandler interface
    // ...

    @Override
    public void move() {
        this.gameLogic.move();
        views.forEach(DataView::update);
    }

    @Override
    public void openCell(int row, int col) {
        this.gameLogic.openCell(row, col);
        views.forEach(DataView::update);
    }

    @Override
    public void toggleFlag(int row, int col) {
        this.gameLogic.toggleFlag(row, col);
        views.forEach(DataView::update);
    }

    @Override
    public void selectCell(int row, int col) {
        this.gameLogic.selectCell(row, col);
        views.forEach(DataView::update);
    }


    @Override
    public void handleClick(int row, int col, MouseButton button) {
        this.gameLogic.handleClick(row, col, button);
        views.forEach(DataView::update);
    }


}
