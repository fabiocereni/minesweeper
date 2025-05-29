
import Model.GameModel;
import controller.GameController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Interface.AbstractModel;
import Interface.GameEventHandler;
import Interface.PlayerEventHandler;
import view.*;

import java.util.List;

public class MainFx extends Application {

    public static final String APP_TITLE = "mine sweeper";

    private final AbstractModel gameModel;
    private final ControlledFxView menuBarView;
    private final ControlledFxView gameBoardView;
    private final UncontrolledFxView userFeedbackView;
    private final GameEventHandler gameEventHandler;
    private final PlayerEventHandler playerEventHandler;

    public MainFx() {
        // GAME MODEL
        this.gameModel = GameModel.getInstance();

        // VIEWS
        this.menuBarView = MenuBarViewFxml.getInstance();
        this.gameBoardView = GameBoardViewFxml.getInstance();
        this.userFeedbackView = UserFeedbackViewFxml.getInstance();

        // CONTROLLERS
        this.gameEventHandler = GameController.getInstance();
        this.playerEventHandler = GameController.getInstance();

        // SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.gameEventHandler, this.gameModel);
        this.gameBoardView.initialize(this.playerEventHandler, this.gameModel);
        this.userFeedbackView.initialize(this.gameModel);
        GameController.getInstance().initialize(List.of(this.menuBarView, this.gameBoardView, this.userFeedbackView));
    }

    @Override
    public void start(Stage primaryStage) {
        VBox homeScreen = new VBox(20);
        homeScreen.setAlignment(Pos.CENTER);

        Button newGameButton = new Button("New Game");
        Button quitButton = new Button("Quit");

        newGameButton.setPrefWidth(200);
        newGameButton.setPrefHeight(30);

        quitButton.setPrefWidth(200);
        quitButton.setPrefHeight(30);

        homeScreen.getChildren().addAll(newGameButton, quitButton);
        Scene homeScene = new Scene(new StackPane(homeScreen), 400, 300);

        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setTop(this.menuBarView.getNode());
        mainBorderPane.setCenter(this.gameBoardView.getNode());
        mainBorderPane.setBottom(this.userFeedbackView.getNode());
        Scene gameScene = new Scene(mainBorderPane);

        newGameButton.setOnAction(e -> primaryStage.setScene(gameScene));
        quitButton.setOnAction(e -> primaryStage.close());

        primaryStage.setTitle(APP_TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
