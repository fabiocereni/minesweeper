package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.Model.AbstractModel;
import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.controller.GameEventHandler;
import ch.supsi.minesweeper.Model.GameModel;
import ch.supsi.minesweeper.controller.ControlledFxView;
import ch.supsi.minesweeper.controller.l10n.TranslationsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class MenuBarViewFxml implements ControlledFxView {

    private static MenuBarViewFxml myself;
    private GameEventHandler gameEventHandler;
    private GameModel gameModel;
    private TranslationsController translationsController;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private MenuItem preferencesMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem helpMenuItem;

    private MenuBarViewFxml() {
        translationsController = TranslationsController.getInstance();
    }

    public static MenuBarViewFxml getInstance() {
        if (myself == null) {
            myself = new MenuBarViewFxml();
            try {
                URL fxmlUrl = MenuBarViewFxml.class.getResource("/menubar.fxml");
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
        this.createBehaviour();
        this.changeLanguage();
        this.gameEventHandler = (GameEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
    }

    private void changeLanguage() {
        this.fileMenu.setText(translationsController.translate("label.file"));
        this.newMenuItem.setText(translationsController.translate("label.new"));
        this.editMenu.setText(translationsController.translate("label.edit"));
        this.helpMenu.setText(translationsController.translate("label.help"));
        this.openMenuItem.setText(translationsController.translate("label.open"));
        this.saveMenuItem.setText(translationsController.translate("label.save"));
        this.saveAsMenuItem.setText(translationsController.translate("label.saveAs"));
        this.quitMenuItem.setText(translationsController.translate("label.quit"));
        this.preferencesMenuItem.setText(translationsController.translate("label.preferences"));
        this.aboutMenuItem.setText(translationsController.translate("label.about"));
        this.helpMenuItem.setText(translationsController.translate("label.help"));
    }

    private void createBehaviour() {
        this.newMenuItem.setOnAction(event -> this.gameEventHandler.newGame());
        this.quitMenuItem.setOnAction(event -> this.gameEventHandler.quit());
        this.saveMenuItem.setOnAction(event -> this.gameEventHandler.save());
        this.saveAsMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.saveAs(stage);
        });

        this.openMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.open(stage);
        });

        this.preferencesMenuItem.setOnAction(event -> {
            SettingsView settingsView = new SettingsView();
            settingsView.show();
        });
        this.aboutMenuItem.setOnAction(event -> this.gameEventHandler.about());
        this.helpMenuItem.setOnAction(event -> this.gameEventHandler.help());
    }

    @Override
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void update(String sentence) {
        // get your data from the model, if needed
        // then update this view here
        if(gameModel.isGamePlaying()){
            saveAsMenuItem.setDisable(false);
        }else{
            saveMenuItem.setDisable(true);
            saveAsMenuItem.setDisable(true);
        }
        if(gameModel.isPathAcquired()){
            saveMenuItem.setDisable(false);
        }else{
            saveMenuItem.setDisable(true);
        }
        System.out.println(this.getClass().getSimpleName() + " updated..." + System.currentTimeMillis());
    }

}
