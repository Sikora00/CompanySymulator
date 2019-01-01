package components.BuyRoomComponent;

import entities.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.ExceptionsHandler;

import java.io.IOException;

public class BuyRoomComponent extends AnchorPane {

    public Room room;

    @FXML
    private Label label;

    public BuyRoomComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/BuyRoomComponent/BuyRoomComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }
    }

    public void loadRoom() {
        label.setText("Kup nową salę " + room.getCost() + "$");
    }

    @FXML
    private void buyRoom() {
        room.buy();
    }
}