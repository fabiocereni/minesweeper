package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.data.GameSettingsData;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

public class SettingsController {

    @FXML
    private Spinner<Integer> bombsSpinner;

    @FXML
    private ComboBox<String> supportedLanguages;

    @FXML
    public void initialize() {
        GameSettingsData settings = GameSettingsData.getInstance();
    }

    @FXML
    public void handleSave() {
        int bombs = bombsSpinner.getValue();

        GameSettingsData settings = GameSettingsData.getInstance();
        settings.setNumBombs(bombs);
        settings.save();
    }

}
