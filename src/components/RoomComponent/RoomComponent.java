package components.RoomComponent;

import components.BuyRoomComponent.BuyRoomComponent;
import components.ParticipantComponent.ParticipantComponent;
import components.RoomSummaryComponent.RoomSummaryComponent;
import entities.Participant;
import entities.Room;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import services.Container;
import services.ExceptionsHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomComponent extends AnchorPane {

    @FXML
    private Pane layout;

    @FXML
    private BuyRoomComponent buyRoomComponent;

    public Room room;

    private Stage stage;

    public RoomComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/RoomComponent/RoomComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }

        stage = Container.getInstance().getStage();
        stage.widthProperty().addListener((observable, oldValue, newValue) -> addComponents());
    }

    private void addBaseLayout() {
        this.getChildren().clear();
        if (stage.widthProperty().get() > 800) {
            HBox newLayout = new HBox();
            newLayout.getStyleClass().add("room-hbox");
            this.getChildren().add(newLayout);
            AnchorPane.setTopAnchor(newLayout, 20.0);
            newLayout.setId("layout");
            newLayout.getStyleClass().add(0, "room");
            this.layout = newLayout;
        } else {
            HBox hbox = new HBox();
            VBox newLayout = new VBox();
            newLayout.setAlignment(Pos.CENTER);


            AnchorPane.setTopAnchor(hbox, 20.0);
            AnchorPane.setLeftAnchor(hbox, 0.0);
            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            Region region2 = new Region();
            HBox.setHgrow(region2, Priority.ALWAYS);
            hbox.getChildren().addAll(region, newLayout, region2);

            this.getChildren().add(hbox);
            newLayout.setId("layout");
            newLayout.getStyleClass().add(0, "room");
            this.layout = newLayout;
        }
    }


    public void loadRoom() {
        addComponents();
        room.getParticipants().addListener((ListChangeListener<Participant>) c -> {
            RoomComponent.this.addParticipants();
        });

        room.isNextToBuyProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                addBuyRoomComponent();
            }
        });

        room.getIsBought().addListener((observable, oldValue, newValue) -> addParticipants());
    }

    public void addComponents() {
        addBaseLayout();
        if (room.getIsBought().get()) {
            addParticipants();
        } else {
            if (room.isIsNextToBuy()) {
                addBuyRoomComponent();
            }
        }
    }

    private void addParticipants() {

        layout.getChildren().clear();
        for (Participant participant : room.getParticipants()) {
            ParticipantComponent participantComponent = new ParticipantComponent();
            participantComponent.participant = participant;
            participantComponent.loadParticipant();
            layout.getChildren().add(participantComponent);
        }

        RoomSummaryComponent summaryComponent = new RoomSummaryComponent();
        summaryComponent.room = room;
        summaryComponent.loadRoom();
        layout.getChildren().add(summaryComponent);

        int btnCount = layout.getChildren().size();
        for (Node child : layout.getChildren()) {
            Pane c = (Pane) child;
            c.prefWidthProperty().bind(layout.widthProperty().divide(btnCount).subtract(1));
        }
    }

    private void addBuyRoomComponent() {
        layout.getChildren().clear();
        BuyRoomComponent buyRoomComponent = new BuyRoomComponent();
        buyRoomComponent.room = room;
        buyRoomComponent.loadRoom();
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        layout.getChildren().addAll(region, buyRoomComponent, region2);
    }
}