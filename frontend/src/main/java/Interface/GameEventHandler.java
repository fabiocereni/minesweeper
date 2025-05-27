package Interface;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    // add all the relevant missing behaviours
    // ...
    void quit();

    void revealEmptyCells(int startRow, int startCol);
}
