package ch.supsi.minesweeper.business.save;

import ch.supsi.minesweeper.business.Grid;

import java.nio.file.Path;

public interface IOpenLogic {
    Grid open(Path path);
}
