package ch.supsi.minesweeper.business.lost;

import ch.supsi.minesweeper.business.Grid;

public class GameLostLogic implements IGameLostLogic {

    private static GameLostLogic myself;

    private GameLostLogic() {
    }

    public static GameLostLogic getInstance() {
        if (myself == null) {
            myself = new GameLostLogic();
        }
        return myself;
    }


    @Override
    public boolean isGameLost(Grid grid) {
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
}
