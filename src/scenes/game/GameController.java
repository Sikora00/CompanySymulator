package scenes.game;

import components.MainComponent.MainComponent;
import components.SnackBar.SnackBar;
import entities.Office;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import services.Container;
import services.store.Store;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements Initializable {

    public static GameController instance;

    @FXML
    public FlowPane bottom;
    @FXML
    TabPane tabPane;
    @FXML
    MainComponent warsawMainComponent;
    @FXML
    MainComponent poznanMainComponent;
    @FXML
    MainComponent wroclawMainComponent;

    Store store = Container.getInstance().getStore();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Make tabs occupy full width
        tabPane.tabMinWidthProperty().bind(tabPane.widthProperty().divide(tabPane.getTabs().size()).subtract(20));

        warsawMainComponent.office = store.getWarszawaOffice();
        warsawMainComponent.loadOffice();

        poznanMainComponent.office = store.getPoznanOffice();
        poznanMainComponent.loadOffice();

        wroclawMainComponent.office = store.getWroclawOffice();
        wroclawMainComponent.loadOffice();
        instance = this;
    }

    public void openSnackBar(String text) {
        if (bottom.getChildren().size() == 0) {
            bottom.getChildren().add(new SnackBar(text));
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> bottom.getChildren().clear());
                }
            }, 2000);
        }
    }

}
