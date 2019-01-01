package components.WalletComponent;

import entities.Wallet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.Container;
import services.ExceptionsHandler;

import java.io.IOException;

public class WalletComponent extends AnchorPane {

    @FXML
    private Label label;

    public WalletComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/WalletComponent/WalletComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            ExceptionsHandler.handle(exception);
        }

        Wallet wallet = Container.getInstance().getStore().getWallet();
        label.setText(Double.toString(wallet.getCash()) + "$");
        wallet.cashProperty().addListener((observable, oldValue, newValue) -> label.setText(newValue.toString() + "$"));
    }
}