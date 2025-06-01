package ch.supsi.mineweeper.backend.business.save;

import ch.supsi.mineweeper.backend.business.Grid;

import java.nio.file.Path;

public interface IOpenLogic {
    Grid open(Path path);
}
