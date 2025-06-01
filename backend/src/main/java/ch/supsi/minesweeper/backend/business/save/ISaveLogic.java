package ch.supsi.minesweeper.backend.business.save;

import java.nio.file.Path;

public interface ISaveLogic {

    void save();
    void saveAs(Path path);
    void setFileName(String fileName);
    public boolean isPathAcquired();

}
