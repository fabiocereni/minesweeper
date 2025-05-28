package view;

import data.GameSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SettingsController {


    @FXML private Spinner<Integer> bombsSpinner;

    @FXML
    public void initialize() {
        GameSettings settings = GameSettings.getInstance();

    }

    @FXML
    public void handleSave() {
        int bombs = bombsSpinner.getValue();

        GameSettings settings = GameSettings.getInstance();
        settings.setNumBombs(bombs);
        settings.save();
    }
}
