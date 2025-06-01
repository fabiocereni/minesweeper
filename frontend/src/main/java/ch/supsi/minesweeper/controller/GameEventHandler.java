package ch.supsi.minesweeper.controller;

import javafx.stage.Stage;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    void saveAs(Stage stage);

    void open(Stage stage);

    void quit();

    void about();

    void help();

}
