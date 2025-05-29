package view;

import business.Grid;
import data.GameSettings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SettingsView {

    private final Stage stage;
    private final Spinner<Integer> bombsSpinner;

    public SettingsView() {
        GameSettings settings = GameSettings.getInstance();

        Label titleLabel = new Label("Impostazioni Partita");

        Label bombLabel = new Label("Numero Bombe:");
        bombsSpinner = new Spinner<>();
        bombsSpinner.setEditable(true);
        bombsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1, Grid.size*Grid.size - 1 , settings.getNumBombs()
                )
        );

        Button saveButton = new Button("Salva");
        saveButton.setOnAction(event -> saveSettings());

        VBox layout = new VBox(10, titleLabel,
                new HBox(10, bombLabel, bombsSpinner),
                saveButton
        );
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #f4f4f4");

        this.stage = new Stage();
        this.stage.setTitle("Impostazioni");
        this.stage.setScene(new Scene(layout));
        this.stage.setResizable(false);
    }

    private void saveSettings() {
        int bombs = bombsSpinner.getValue();

        GameSettings settings = GameSettings.getInstance();
        settings.setNumBombs(bombs);
        settings.save();

        // opzionale: feedback
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Impostazioni salvate con successo!");
        alert.showAndWait();

        stage.close();
    }

    public void show() {
        stage.show();
    }
}
