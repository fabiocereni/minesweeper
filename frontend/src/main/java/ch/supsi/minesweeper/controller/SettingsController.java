package ch.supsi.minesweeper.controller;

import ch.supsi.mineweeper.backend.data.GameSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;

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
