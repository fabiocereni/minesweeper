package model;

import controller.EventHandler;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    // add all the relevant missing behaviours
    // ...
    void quit();

}
