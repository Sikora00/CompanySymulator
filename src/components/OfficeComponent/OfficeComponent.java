package components.OfficeComponent;

import components.RoomComponent.RoomComponent;
import entities.Office;
import entities.Room;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.ExceptionsHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OfficeComponent extends AnchorPane implements Initializable {

    @FXML
    private VBox row;

    public Office office;

    public OfficeComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/OfficeComponent/OfficeComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadOffice() {
        addRoomComponents();
        office.getRooms().addListener((ListChangeListener<Room>) c -> {
            OfficeComponent.this.addRoomComponents();
        });
    }

    private void addRoomComponents() {
        OfficeComponent.this.row.getChildren().clear();
        for (Room room : office.getRooms()) {
            RoomComponent roomComponent = new RoomComponent();
            roomComponent.room = room;
            roomComponent.loadRoom();
            OfficeComponent.this.row.getChildren().add(roomComponent);
        }
    }
}