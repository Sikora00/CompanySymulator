package components.ParticipantComponent;

import entities.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import services.ExceptionsHandler;

import java.io.IOException;

public class ParticipantComponent extends AnchorPane {
    @FXML
    private Button button;

    public Participant participant;

    public ParticipantComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/ParticipantComponent/ParticipantComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }
    }

    @FXML
    protected void buy(ActionEvent event) {
        participant.buy();
    }

    public void loadParticipant() {
        button.setText(Double.toString(participant.getCost()) + "$");
        if (participant.isSepcial()) {
            button.getStyleClass().add("participant__button--special");
        }
        if (!participant.isBought().get()) {
            button.getStyleClass().add("participant__button--disabled");
        }

        participant.isBought().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                button.setText(Integer.toString(participant.getProductivityPerSecond()) + "$/s");
                button.getStyleClass().remove("participant__button--disabled");
            } else {
                button.setText(Double.toString(participant.getCost()) + "$");
                button.getStyleClass().add("participant__button--disabled");
            }
        });
    }

}