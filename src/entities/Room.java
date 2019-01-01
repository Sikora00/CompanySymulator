package entities;

import exceptions.NotEnoughCashException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.Container;
import services.ExceptionsHandler;

public class Room {
    private ObservableList<Participant> participants = FXCollections.observableArrayList();
    private SimpleBooleanProperty isBought = new SimpleBooleanProperty(false);
    private int cost = 3000;
    private SimpleBooleanProperty isNextToBuy = new SimpleBooleanProperty(false);

    public Room() {
        for (int i = 0; i < 5; i++) {
            participants.add(new Participant(20, 1000, false));
        }
        participants.add(new Participant(50, 3000, true));
    }

    public SimpleDoubleProperty getProductivity() {
        SimpleDoubleProperty result = new SimpleDoubleProperty(0);

        for (Participant participant : participants) {
            if (participant.isBought().get()) {
                result.add(participant.getProductivityPerSecond());
            }

            participant.isBought().addListener((observable, oldValue, newValue) -> result.set(getDoubleProductivity()));
        }
        return result;
    }

    public double getDoubleProductivity() {
        double doubleResult = 0;
        for (Participant participant : participants) {
            if (participant.isBought().get()) {
                doubleResult += participant.getProductivityPerSecond();
            }
        }
        return doubleResult;
    }

    public SimpleBooleanProperty getIsBought() {
        return isBought;
    }

    public void buy() {
        Wallet wallet = Container.getInstance().getStore().getWallet();
        if (wallet.getCash() >= cost) {
            isBought.set(true);
            wallet.spendCash(cost);
        } else {
            ExceptionsHandler.handle(new NotEnoughCashException());
        }
    }

    public ObservableList<Participant> getParticipants() {
        return participants;
    }

    public int getCost() {
        return cost;
    }

    public boolean isIsNextToBuy() {
        return isNextToBuy.get();
    }

    public SimpleBooleanProperty isNextToBuyProperty() {
        return isNextToBuy;
    }

    public void setIsNextToBuy(boolean isNextToBuy) {
        this.isNextToBuy.set(isNextToBuy);
    }
}
