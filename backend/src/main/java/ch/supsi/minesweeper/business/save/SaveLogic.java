package ch.supsi.minesweeper.business.save;

import ch.supsi.minesweeper.business.Grid;
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

    private boolean pathAcquired;

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
            //adesso questo if si potrebbe togliere dato che save lo posso cliccare quando ho già un path impostato
            if (fileName == null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                fileName = LocalDateTime.now().format(formatter) + ".json";
            }
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, Grid.getInstance());

            //this.fileName = file.getName();
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
            this.pathAcquired=true;
            System.out.println("Saved file as " + fileName);
        } catch (IOException e) {
            System.err.println("Error, save failed " + e.getMessage());
        }
    }
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public boolean isPathAcquired(){return this.pathAcquired;}

}
