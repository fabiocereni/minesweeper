    package application;

    import business.Cell;
    import business.Grid;
    import javafx.scene.input.MouseButton;

    import java.util.LinkedList;
    import java.util.Queue;

    public class GameLogic{

        private static GameLogic myself;

        private int counter;

        private Grid grid;

        private GameLogic() {
            super();
            counter=0;
            grid = new Grid();
            grid.disableButtons();
        }

        public static GameLogic getInstance() {
            if (myself == null) {
                myself = new GameLogic();
            }
            return myself;
        }


        public void newGame() {
            counter = 0;
            grid = new Grid();
            grid.defaultGrid();
            grid.placeBombs();
            grid.activateButtons();
        }


        public void save() {
        }


        public void quit() {
            //ConfirmExitPopupFxml.showConfirmExit();
        }


        public void move() {
            counter++;
        }


        public void openCell(int row, int col) {

        }


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


        public void handleClick(int row, int col, MouseButton button) {
            if (button == MouseButton.SECONDARY) {
                toggleFlag(row, col);
            } else if (button == MouseButton.PRIMARY) {
                selectCell(row, col);
            }
        }


        public void selectCell(int row, int col) {
            Cell cell = grid.getCell(row, col);
            cell.setClicked(true);

            if(cell.isIsaBomb()){
                //VictoryPopupFxml.showVictory();
                //ErrorClickedBombFxml.showError();
                grid.showAll();
                //grid.defaultGrid();
                grid.disableButtons();
            }

            if(cell.getNearBombs() == 0){
                revealEmptyCells(row, col);
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

        public void revealEmptyCells(int startRow, int startCol) {
            int size = Grid.size;
            boolean[][] visited = new boolean[size][size];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{startRow, startCol});
            visited[startRow][startCol] = true;

            while (!queue.isEmpty()) {
                int[] position = queue.poll();
                int row = position[0];
                int col = position[1];
                Cell cell = grid.getCell(row, col);
                cell.setClicked(true);

                if (cell.getNearBombs() == 0) {
                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            int newRow = row + dr;
                            int newCol = col + dc;
                            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                                if (!visited[newRow][newCol]) {
                                    Cell adjacentCell = grid.getCell(newRow, newCol);
                                    if (!adjacentCell.isIsaBomb() && !adjacentCell.isClicked()) {
                                        queue.add(new int[]{newRow, newCol});
                                        visited[newRow][newCol] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // add all the relevant missing behaviours
        // ...


    }
