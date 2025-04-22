package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.Cell;
import ch.supsi.minesweeper.model.GameModel;
import ch.supsi.minesweeper.model.PlayerEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
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

        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                Button tmp = new Button(y+","+x);
                tmp.setAlignment(Pos.CENTER);
                tmp.setFocusTraversable(false);
                tmp.setMnemonicParsing(false);
                tmp.setPrefHeight(37.0);
                tmp.prefWidth(37.0);
                tmp.setTextAlignment(TextAlignment.CENTER);
                buttonMatrix[y][x]=tmp;
            }
        }

        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                containerPane.add(buttonMatrix[y][x],y,x);
            }
        }
        this.createBehaviour();
    }

    private void createBehaviour() {
        // add event handlers for all necessary buttons
        for (int y=0; y<9; y++){
            for (int x=0; x<9 ; x++){
                //buttonMatrix[y][x].setOnAction(event -> this.playerEventHandler.move());
                int fy = y;
                int fx = x;
                buttonMatrix[y][x].setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        this.playerEventHandler.toggleFlag(fy, fx);
                    }
                });
            }
        }
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update() {
        // get your data from the model, if needed
        for(int y = 0; y<9;y++){
            for (int x = 0; x < 9; x++) {
                if(gameModel.isaBomb(x,y)){
                    buttonMatrix[x][y].setText("B");
                    buttonMatrix[x][y].setStyle("-fx-background-color: red;");
                } else if (gameModel.isFlagged(x, y)) {
                    buttonMatrix[x][y].setText("F");
                    buttonMatrix[x][y].setStyle("-fx-background-color: green;");
                } else {
                    buttonMatrix[x][y].setText(""+gameModel.getNumberOfCell(x,y));
                }
            }
        }

        // then update this view here
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(this.getClass().getSimpleName() + " updated..." + dateFormat.format(date));
    }

}
