package ch.supsi.minesweeper.model;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;

    private int counter;

    private Grid field;


    private GameModel() {
        super();
        counter=0;
        field = new Grid();
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
    }

    public int getCounter() {
        return counter;
    }

    // add all the relevant missing behaviours
    // ...

}
