    package ch.supsi.minesweeper.backend.business;

    import ch.supsi.minesweeper.backend.business.save.IOpenLogic;
    import ch.supsi.minesweeper.backend.business.save.ISaveLogic;
    import ch.supsi.minesweeper.backend.business.save.OpenLogic;
    import ch.supsi.minesweeper.backend.business.save.SaveLogic;
    import ch.supsi.minesweeper.backend.data.GameSettingsData;

    import java.nio.file.Path;
    import java.util.LinkedList;
    import java.util.Queue;

    public class GameLogic {

        private static GameLogic myself;

        private int counter;

        private Grid grid;

        private static final ISaveLogic saveLogic = SaveLogic.getInstance();
        private static final IOpenLogic openLogic = OpenLogic.getInstance();

        private GameLogic() {
            counter = 0;
            int bombs = GameSettingsData.getInstance().getNumBombs();
            grid = new Grid(bombs);
            Grid.setInstance(grid);
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
            int bombs = GameSettingsData.getInstance().getNumBombs();
            grid = new Grid(bombs);
            Grid.setInstance(grid);
            grid.activateButtons();
        }

        public void toggleFlag(int row, int col) {
            Cell cell = grid.getCell(row, col);
            if (!cell.isClicked()) {
                if((grid.getNumFlags() == Grid.maxNumBomb)&&(!cell.isFlag())){
                    // ALERT non puoi mettere altre flag
                }else{
                    cell.setFlag(!cell.isFlag());
                    if(cell.isFlag()){
                        grid.incrementNumFlags();
                    } else {
                        grid.decrementNumFlags();
                    }
                }
            }
        }

        public void selectCell(int row, int col) {
            Cell cell = grid.getCell(row, col);
            if(!cell.isFlag()) {
                cell.setClicked(true);
                if (cell.isExpandable()) {
                    revealEmptyCells(row, col);
                }
            }
        }

        public boolean isGameWon(){
            if (grid.getNumberOfOpenCell() == ((Grid.size * Grid.size) - grid.getNumberBombs())) {
                System.out.println("vittoria");
                grid.showAll();
                grid.disableButtons();
                return true;
            }else{
                return false;
            }
        }

        public boolean isGameLost(){
            for(int y = 0; y < Grid.size;y++) {
                for (int x = 0; x < Grid.size;x++) {
                    if(grid.getCell(x,y).isClicked()&&grid.getCell(x,y).isaBomb()){
                        grid.showAll();
                        grid.disableButtons();
                        return true;
                    }
                }
            }
            return false;
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
                                    if (!adjacentCell.isaBomb() && !adjacentCell.isClicked()) {
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

        public int numberOfFlagRemaining(){
            return grid.getNumberBombs()-grid.getNumFlags();
        }
        public int numberOfBombs() {
            return grid.getNumberBombs();
        }
        public boolean isFlag(int row, int col) {
            return grid.getCell(row,col).isFlag();
        }
        public boolean isAbomb(int row, int col) {
            return grid.getCell(row,col).isaBomb();
        }
        public boolean isClicked(int row, int col) {
            return grid.getCell(row,col).isClicked();
        }
        public int getNearBombs(int row, int col) {
            return grid.getCell(row,col).getNearBombs();
        }
        public boolean getState(int row, int col) {
            return grid.getCell(row,col).getState();
        }
        public int getCounter() {
            return counter;
        }
        public boolean isaBomb(int x, int y){
            return grid.getCell(x,y).isaBomb();
        }
        public int getNumberOfCell(int x, int y){
            return grid.getCell(x,y).getNearBombs();
        }
        public Cell getCell(int x, int y) {
            return grid.getCell(x, y);
        }
        public int numberOfFlaggedCells() {
            return grid.getNumFlags();
        }

        public Grid getGrid() {
            return grid;
        }

        public void save() {
            saveLogic.save();
        }

        public void saveAs(Path path) {
            saveLogic.saveAs(path);
        }

        public void open(Path path) {
            grid = openLogic.open(path);
        }

    }
