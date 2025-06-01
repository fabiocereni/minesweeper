package ch.supsi.minesweeper.backend.business.save;

import ch.supsi.minesweeper.backend.business.Grid;

import java.nio.file.Path;

public interface IOpenLogic {
    Grid open(Path path);
}
