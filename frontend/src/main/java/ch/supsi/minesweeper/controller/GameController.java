package ch.supsi.minesweeper.controller;


import ch.supsi.minesweeper.Model.GameModel;
import ch.supsi.minesweeper.controller.l10n.TranslationsController;
import ch.supsi.minesweeper.view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private GameModel gameModel;
    private List<DataView> views;
    private final TranslationsController translationsController;

    private GameController () {
        this.gameModel = GameModel.getInstance();
        this.translationsController = TranslationsController.getInstance();
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
        views.forEach(dataView -> dataView.update(translationsController.translate("label.infoBombs") + ": " + gameModel.numberOfBombs() + " - " +  translationsController.translate("label.infoFlags") + " : " + gameModel.numberOfFlagRemaining()));
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
    public void clickRight(int row, int col) {
        gameModel.clickRight(row, col);
        views.forEach(dataView -> dataView.update(""));
    }

    @Override
    public void clickLeft(int row, int col) {
        gameModel.clickLeft(row, col);
        if(gameModel.isGameWon()){
            views.forEach(dataView -> dataView.update(translationsController.translate("label.win")));
        }else if(gameModel.isGameLost()){
            views.forEach(dataView -> dataView.update(translationsController.translate("label.lost")));
        }else{
            views.forEach(dataView -> dataView.update(""));
        }
    }

    @Override
    public void revealEmptyCells(int startRow, int startCol){
        gameModel.revealEmptyCells(startRow, startCol);
        views.forEach(dataView -> dataView.update(""));
    }

    @Override
    public void about() {
        views.forEach(dataView -> dataView.update(translationsController.translate("label.infoAbout")));
    }

    @Override
    public void help() {
        views.forEach(dataView -> dataView.update(translationsController.translate("label.infoHelp") + " \uD83D\uDCA3"));
    }


}
