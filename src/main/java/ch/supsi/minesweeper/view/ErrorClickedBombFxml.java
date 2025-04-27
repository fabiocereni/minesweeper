package ch.supsi.minesweeper.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorClickedBombFxml {

    public static void showError() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.UTILITY); // stile semplice (senza icone strane)

        Label message = new Label("La bomba è stata cliccata: HAI PERSO!!!");
        Button okButton = new Button("OK");

        okButton.setOnAction(e -> popup.close());

        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(message, okButton);

        Scene scene = new Scene(layout, 350, 150);
        popup.setTitle("BOOM!");
        popup.setScene(scene);
        popup.showAndWait();
    }

}
