package components.SnackBar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.ExceptionsHandler;

import java.io.IOException;

public class SnackBar extends AnchorPane {

    @FXML
    private Label label;

    private String text;

    public SnackBar(String text) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/SnackBar/SnackBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }

        label.setText(text);
    }
}