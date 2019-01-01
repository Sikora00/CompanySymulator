package entities;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import services.Container;

import java.util.Timer;
import java.util.TimerTask;

public class Wallet {
    private SimpleDoubleProperty cash = new SimpleDoubleProperty(10000);

    public Wallet() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> Container.getInstance().getStore().getWallet().addCash(Container.getInstance().getStore().getProductivityFromAllOffices()));
            }
        }, 0, 1000);
    }

    public void addCash(double value) {
        cash.set(cash.get() + value);
    }

    public void spendCash(double value) {
        cash.set(cash.get() - value);
    }

    public double getCash() {
        return cash.get();
    }

    public SimpleDoubleProperty cashProperty() {
        return cash;
    }
}
