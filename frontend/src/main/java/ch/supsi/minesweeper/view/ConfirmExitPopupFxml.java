package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.l10n.TranslationsController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmExitPopupFxml {
    public static void showConfirmExit() {

        TranslationsController translationsController = TranslationsController.getInstance();

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.UTILITY); // finestra semplice

        Label message = new Label(TranslationsController.getInstance().translate("label.titleQuit"));

        Button yesButton = new Button(translationsController.translate("label.buttonYesQuit"));
        Button noButton = new Button(translationsController.translate("label.buttonNoQuit"));

        yesButton.setOnAction(e -> {
            System.exit(0); // Chiude completamente il programma
        });

        noButton.setOnAction(e -> {
            popup.close(); // Chiude solo il popup
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(yesButton, noButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(20);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(message, buttons);

        Scene scene = new Scene(layout, 300, 150);
        popup.setScene(scene);
        popup.setTitle("Conferma uscita");
        popup.showAndWait(); // aspetta che l'utente scelga
    }
}