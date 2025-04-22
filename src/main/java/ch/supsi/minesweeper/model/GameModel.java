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
        counter = 0;
        grid.defaultGrid();
        grid.placeBombs();
        notifyListeners();
    }

    @Override
    public void save() {

    }

    @Override
    public void move() {
        counter++;
    }

    @Override
    public void openCell(int row, int col) {

    }

    @Override
    public void toggleFlag(int row, int col) {
        grid.getCell(row, col).setFlag(true);
    }

    public boolean isFlagged(int row, int col) {
        return grid.getCell(row, col).isFlag();
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
