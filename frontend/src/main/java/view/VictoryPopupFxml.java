package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.GameModel;


public class VictoryPopupFxml {

    public static void showVictory() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.UTILITY);

        Label message = new Label("HAI VINTO COMPLIMENTI!!!");

        Button newGameButton = new Button("Nuova Partita");
        Button exitButton = new Button("Esci");

        newGameButton.setOnAction(e -> {
            GameModel.getInstance().newGame();
            popup.close();
        });

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(newGameButton, exitButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(20);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(message, buttons);

        Scene scene = new Scene(layout, 350, 150);
        popup.setScene(scene);
        popup.setTitle("Vittoria!");
        popup.showAndWait();
    }
}