package ch.supsi.minesweeper.model;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;

    private int counter;

    private Grid grid;


    private GameModel() {
        super();
        counter=0;
        grid = new Grid();
    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }

        return myself;
    }

    @Override
    public void newGame() {
        grid.placeBombs();

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

    public boolean isaBomb(int x, int y){
        return grid.getCell(x,y).isIsaBomb();
    }

    public int getNumberOfCell(int x, int y){
        return grid.getCell(x,y).getNearBombs();
    }


    // add all the relevant missing behaviours
    // ...

}
