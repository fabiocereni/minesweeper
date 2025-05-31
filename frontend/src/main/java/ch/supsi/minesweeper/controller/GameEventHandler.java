package ch.supsi.minesweeper.controller;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    void quit();

    void about();

    void help();
}
