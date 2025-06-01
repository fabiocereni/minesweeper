package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.business.Grid;
import ch.supsi.minesweeper.controller.l10n.TranslationsController;
import ch.supsi.minesweeper.data.GameSettingsData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class SettingsView {
    private final Stage stage;
    private final Spinner<Integer> bombsSpinner;
    private final ComboBox<String> languageComboBox;
    private final TranslationsController translationsController = TranslationsController.getInstance();

    public SettingsView() {
        GameSettingsData settings = GameSettingsData.getInstance();
        Label titleLabel = new Label(translationsController.translate("label.title2Settings"));
        Label bombLabel = new Label(translationsController.translate("label.bombsSettings"));
        bombsSpinner = new Spinner<>();
        bombsSpinner.setEditable(true);
        bombsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1, Grid.size*Grid.size - 1 , settings.getNumBombs()
                )
        );
        Label languageLabel = new Label(translationsController.translate("label.languageSettings"));
        languageComboBox = new ComboBox<>();
        List<String> supportedLanguages = translationsController.getSupportedLanguageTags();
        languageComboBox.getItems().addAll(supportedLanguages);
        languageComboBox.setValue(supportedLanguages.get(0));
        Button saveButton = new Button(translationsController.translate("label.buttonSettings"));
        saveButton.setOnAction(event -> saveSettings());
        VBox layout = new VBox(10, titleLabel,
                new HBox(10, bombLabel, bombsSpinner),
                new HBox(10, languageLabel, languageComboBox),
                saveButton
        );
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #f4f4f4");
        this.stage = new Stage();
        this.stage.setTitle(translationsController.translate("label.titleSettings"));
        this.stage.setScene(new Scene(layout));
        this.stage.setResizable(false);
    }

    private void saveSettings() {
        int bombs = bombsSpinner.getValue();
        String languageTag = languageComboBox.getValue();
        GameSettingsData settings = GameSettingsData.getInstance();
        settings.setNumBombs(bombs);
        translationsController.setLanguage(languageTag);
        settings.save();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, translationsController.translate("label.successSettings"));
        alert.showAndWait();
        stage.close();
    }

    public void show() {
        stage.show();
    }
}
