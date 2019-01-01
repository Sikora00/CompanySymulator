package components.MainComponent;

import components.OfficeComponent.OfficeComponent;
import entities.Office;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import services.ExceptionsHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainComponent extends AnchorPane implements Initializable {

    @FXML
    public OfficeComponent officeComponent;
    public Office office;

    public MainComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/MainComponent/MainComponent.fxml"));
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
        officeComponent.office = office;
        officeComponent.loadOffice();
    }
}