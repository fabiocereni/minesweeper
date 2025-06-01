package ch.supsi.minesweeper.business.save;

import ch.supsi.minesweeper.business.Grid;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class OpenLogic implements IOpenLogic {

    private static OpenLogic myself;

    private OpenLogic() {}

    public static OpenLogic getInstance() {
        if (myself == null) {
            myself = new OpenLogic();
        }
        return myself;
    }

    @Override
    public Grid open(Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Grid loaded = mapper.readValue(path.toFile(), Grid.class);
            Grid.setInstance(loaded);
            return loaded;
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
            return null;
        }
    }
}
