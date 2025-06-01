package ch.supsi.mineweeper.backend.business.save;

import java.nio.file.Path;

public interface ISaveLogic {

    String save(String filename);
    void saveAs(Path path);

}
