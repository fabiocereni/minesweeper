    package model;

    import javafx.scene.input.MouseButton;

    public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

        private static GameModel myself;

        private int counter;

        private Grid grid;

        private GameModel() {
            super();
            counter=0;
            grid = new Grid();
            grid.disableButtons();
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
            grid = new Grid();
            grid.defaultGrid();
            grid.placeBombs();
            grid.activateButtons();
        }

        @Override
        public void save() {
        }

        @Override
        public void quit() {
            //ConfirmExitPopupFxml.showConfirmExit();
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
            Cell cell = grid.getCell(row, col);
            if (!cell.isClicked()) { // opzionale: evita di flaggare celle già aperte
                if(grid.getNumFlags() == Grid.maxNumBomb){
                    // ALERT non puoi mettere altre flag
                }else{
                    cell.setFlag(!cell.isFlag()); // toggle: true -> false, false -> true
                    if(cell.isFlag()){
                        grid.incrementNumFlags();
                    } else {
                        grid.decrementNumFlags();
                    }
                }
            }
        }

        @Override
        public void handleClick(int row, int col, MouseButton button) {
            if (button == MouseButton.SECONDARY) {
                toggleFlag(row, col);
            } else if (button == MouseButton.PRIMARY) {
                selectCell(row, col);
            }
        }

        @Override
        public void selectCell(int row, int col) {
            Cell cell = grid.getCell(row, col);
            cell.setClicked(true);

            if(cell.isIsaBomb()){
                //VictoryPopupFxml.showVictory();
                //ErrorClickedBombFxml.showError();
                grid.defaultGrid();
                grid.disableButtons();
            }
        }

        public boolean isFlagged(int row, int col) {
            return grid.getCell(row, col).isFlag();
        }

        public int numberOfFlaggedCells() {
            return grid.getNumFlags();
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

        public Cell getCell(int x, int y) {
            return grid.getCell(x, y);
        }

        // add all the relevant missing behaviours
        // ...


    }
