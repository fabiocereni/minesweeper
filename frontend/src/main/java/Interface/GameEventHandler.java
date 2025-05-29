package Interface;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    void quit();

    void revealEmptyCells(int startRow, int startCol);

    void about();

    void help();
}
