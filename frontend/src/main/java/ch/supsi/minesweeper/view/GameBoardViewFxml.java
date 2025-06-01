package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.Model.AbstractModel;
import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.controller.PlayerEventHandler;
import ch.supsi.minesweeper.Model.GameModel;
import ch.supsi.minesweeper.controller.ControlledFxView;
import ch.supsi.minesweeper.controller.GameController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameBoardViewFxml implements ControlledFxView {

    private static GameBoardViewFxml myself;

    private PlayerEventHandler playerEventHandler;

    private GameModel gameModel;

    private GameController gameController;

    private Button[][] buttonMatrix = new Button[9][9];

    @FXML
    private GridPane containerPane;

    private GameBoardViewFxml() {}

    public static GameBoardViewFxml getInstance() {
        if (myself == null) {
            myself = new GameBoardViewFxml();
            try {
                URL fxmlUrl = GameBoardViewFxml.class.getResource("/gameboard.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }

    @Override
    public void initialize(EventHandler eventHandler, AbstractModel model) {
        this.playerEventHandler = (PlayerEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
        createButton();
        this.createBehaviour();
        this.disableButtons();
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update(String sentence) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button btn = buttonMatrix[x][y];
                if(gameModel.getState(x,y)){
                    btn.setDisable(false);
                }
                if (gameModel.isFlag(x,y)) {
                    btn.setText("\uD83D\uDEA9");
                    btn.setStyle("-fx-background-color: yellow;");
                } else if (gameModel.isClicked(x,y)) {
                    if (gameModel.isAbomb(x,y)) {
                        btn.setText("\uD83D\uDCA3");
                        btn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                        createDelayedDisabling();
                    } else {
                        int near = gameModel.getNearBombs(x,y);
                        btn.setText(near > 0 ? String.valueOf(near) : "");
                        btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                    }
                } else {
                    btn.setText("");
                    btn.setStyle("");
                }
            }
        }
        // feedback bar
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(this.getClass().getSimpleName() + " updated..." + dateFormat.format(date));
    }

    private void createButton() {
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                Button tmp = new Button();
                tmp.setAlignment(Pos.CENTER);
                tmp.setFocusTraversable(false);
                tmp.setMnemonicParsing(false);
                tmp.setMinSize(37.0,37.0);
                tmp.setTextAlignment(TextAlignment.CENTER);
                buttonMatrix[y][x]=tmp;
            }
        }
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                containerPane.add(buttonMatrix[y][x],y,x);
            }
        }
    }

    private void createBehaviour() {
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                int fy = y;
                int fx = x;
                buttonMatrix[y][x].setOnMouseClicked(event -> {
                    click(fy, fx, event.getButton());
                });
            }
        }
    }

    private void click(int row, int col, MouseButton button) {
        if (button == MouseButton.SECONDARY) {
            playerEventHandler.clickRight(row,col);
        } else if (button == MouseButton.PRIMARY) {
            playerEventHandler.clickLeft(row, col);
        }
    }

    private void createDelayedDisabling() {
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
        pause.setOnFinished(event -> disableButtons());
        pause.play();
    }

    public void disableButtons() {
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                buttonMatrix[y][x].setDisable(true);
            }
        }
    }

}
