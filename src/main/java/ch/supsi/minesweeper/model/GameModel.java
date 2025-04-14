package ch.supsi.minesweeper.model;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;



    private int counter;

    private GameModel() {
        super();
        counter=0;
    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }

        return myself;
    }

    @Override
    public void newGame() {

    }

    @Override
    public void save() {

    }

    @Override
    public void move() {
        counter++;
        return;
    }
    public int getCounter() {
        return counter;
    }

    // add all the relevant missing behaviours
    // ...

}
