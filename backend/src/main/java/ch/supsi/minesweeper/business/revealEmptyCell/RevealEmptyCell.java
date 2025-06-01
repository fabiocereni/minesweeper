package ch.supsi.minesweeper.business.revealEmptyCell;

import ch.supsi.minesweeper.business.Cell;
import ch.supsi.minesweeper.business.Grid;

import java.util.LinkedList;
import java.util.Queue;

public class RevealEmptyCell implements IRevealEmptyCell {

    private static RevealEmptyCell myself;

    private RevealEmptyCell() {
    }

    public static RevealEmptyCell getInstance() {
        if (myself == null) {
            myself = new RevealEmptyCell();
        }
        return myself;
    }

    @Override
    public void revealEmptyCells(int startRow, int startCol, Grid grid) {
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
}
