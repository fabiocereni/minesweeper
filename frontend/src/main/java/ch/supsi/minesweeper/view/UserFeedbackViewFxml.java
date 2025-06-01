package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.Model.AbstractModel;
import ch.supsi.minesweeper.Model.GameModel;
import ch.supsi.minesweeper.controller.l10n.TranslationsController;
import ch.supsi.minesweeper.controller.UncontrolledFxView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;

public class UserFeedbackViewFxml implements UncontrolledFxView {

    private static UserFeedbackViewFxml myself;

    private GameModel gameModel;

    @FXML
    private ScrollPane containerPane;

    @FXML
    private Text userFeedbackBar;

    private final TranslationsController translationsController;

    private UserFeedbackViewFxml() {
        this.translationsController = TranslationsController.getInstance();
    }

    public static UserFeedbackViewFxml getInstance() {
        if (myself == null) {
            myself = new UserFeedbackViewFxml();
            try {
                URL fxmlUrl = UserFeedbackViewFxml.class.getResource("/userfeedbackbar.fxml");
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
    public void initialize(AbstractModel model) {
        this.gameModel = (GameModel) model;
        changeLanguage();
    }

    private void changeLanguage() {
        this.userFeedbackBar.setText(this.translationsController.translate("label.userFeedback"));
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update(String sentence) {
        this.userFeedbackBar.setText(sentence);
    }
}