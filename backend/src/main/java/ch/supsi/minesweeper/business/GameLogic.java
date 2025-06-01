    package ch.supsi.minesweeper.business;

    import ch.supsi.minesweeper.business.lost.GameLostLogic;
    import ch.supsi.minesweeper.business.lost.IGameLostLogic;
    import ch.supsi.minesweeper.business.newGame.INewGameLogic;
    import ch.supsi.minesweeper.business.newGame.NewGameLogic;
    import ch.supsi.minesweeper.business.save.IOpenLogic;
    import ch.supsi.minesweeper.business.save.ISaveLogic;
    import ch.supsi.minesweeper.business.save.OpenLogic;
    import ch.supsi.minesweeper.business.save.SaveLogic;
    import ch.supsi.minesweeper.business.selectCell.ISelectCellLogic;
    import ch.supsi.minesweeper.business.selectCell.SelectCellLogic;
    import ch.supsi.minesweeper.business.toggleFlag.IToggleFlagLogic;
    import ch.supsi.minesweeper.business.toggleFlag.ToggleFlagLogic;
    import ch.supsi.minesweeper.business.won.GameWonLogic;
    import ch.supsi.minesweeper.business.won.IGameWonLogic;
    import ch.supsi.minesweeper.data.GameSettingsData;

    import java.nio.file.Path;

    public class GameLogic {

        private static GameLogic myself;

        private int counter;

        private Grid grid;

        private boolean playing;

        private final ISaveLogic saveLogic;
        private final IOpenLogic openLogic;
        private final INewGameLogic newGameLogic;
        private final IToggleFlagLogic toggleFlagLogic;
        private final ISelectCellLogic selectCellLogic;
        private final IGameWonLogic gameWonLogic;
        private final IGameLostLogic gameLostLogic;

        private GameLogic() {
            saveLogic = SaveLogic.getInstance();
            openLogic = OpenLogic.getInstance();
            newGameLogic = NewGameLogic.getInstance();
            toggleFlagLogic = ToggleFlagLogic.getInstance();
            selectCellLogic = SelectCellLogic.getInstance();
            gameWonLogic = GameWonLogic.getInstance();
            gameLostLogic = GameLostLogic.getInstance();

            counter = 0;
            playing = false;
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
            grid = newGameLogic.newGame(grid, counter);
            playing = true;
        }

        public void toggleFlag(int row, int col) {
            grid = toggleFlagLogic.toggleFlag(row, col, grid);
        }

        public void selectCell(int row, int col) {
            grid = selectCellLogic.selectCell(row, col, grid);
        }

        public boolean isGameWon() {
            boolean won = gameWonLogic.isGameWon(grid);
            if (won) {
                playing = false;
            }
            return won;
        }

        public boolean isGameLost() {
            boolean lost = gameLostLogic.isGameLost(grid);
            if (lost) {
                playing = false;
            }
            return lost;
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
            saveLogic.setFileName(path.toString());
            playing=true;
        }

        public boolean isGamePlaying() {
            return playing;
        }

        public boolean isPathAcquired(){return saveLogic.isPathAcquired();}
    }
