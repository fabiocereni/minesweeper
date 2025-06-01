package ch.supsi.mineweeper.backend.business.save;

import ch.supsi.mineweeper.backend.business.Grid;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveLogic implements ISaveLogic {

    private static SaveLogic myself;
    private final Grid grid;

    private SaveLogic() {
        grid = Grid.getInstance();
    }

    public static SaveLogic getInstance() {
        if (myself == null) {
            myself = new SaveLogic();
        }
        return myself;
    }

    @Override
    public String save(String fileName) {
        try {
            if (fileName == null || fileName.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                fileName = LocalDateTime.now().format(formatter) + ".json";
            }

            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, Grid.getInstance());

            return fileName;
        } catch (IOException e) {
            System.err.println("Error, save failed " + e.getMessage());
            return null;
        }
    }

    @Override
    public void saveAs(Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), Grid.getInstance());
        } catch (IOException e) {
            System.err.println("Error, save failed " + e.getMessage());
        }
    }


}
