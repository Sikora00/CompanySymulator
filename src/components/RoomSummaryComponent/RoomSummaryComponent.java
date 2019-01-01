package components.RoomSummaryComponent;

import entities.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.ExceptionsHandler;

import java.io.IOException;

public class RoomSummaryComponent extends AnchorPane {
    @FXML
    private Label label;

    public Room room;

    public RoomSummaryComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/RoomSummaryComponent/RoomSummaryComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }
    }

    public void loadRoom() {
        room.getProductivity().addListener((observable, oldValue, newValue) -> label.setText(newValue.toString() + "$/s"));
        label.setText(Double.toString(room.getProductivity().get()) + "$/s");
    }

}