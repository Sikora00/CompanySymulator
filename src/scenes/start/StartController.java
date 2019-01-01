package scenes.start;

import javafx.fxml.Initializable;
import events.StartGameEventListeners;
import services.Container;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onStartButtonClicked() {
        StartGameEventListeners.startGame();
    }
}
