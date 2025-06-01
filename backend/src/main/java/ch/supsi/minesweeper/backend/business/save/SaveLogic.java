package ch.supsi.minesweeper.backend.business.save;

import ch.supsi.minesweeper.backend.business.Grid;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveLogic implements ISaveLogic {

    private static SaveLogic myself;
    private final Grid grid;
    private String fileName;

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
    public void save() {
        try {
            if (fileName == null || fileName.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                fileName = LocalDateTime.now().format(formatter) + ".json";
            }
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, Grid.getInstance());

            this.fileName = file.getName();
            System.out.println("Saved file " + fileName);

        } catch (IOException e) {
            System.err.println("Error, save failed " + e.getMessage());
        }
    }

    @Override
    public void saveAs(Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), Grid.getInstance());
            this.fileName = path.toString();
            System.out.println("Saved file as " + fileName);
        } catch (IOException e) {
            System.err.println("Error, save failed " + e.getMessage());
        }
    }


}
