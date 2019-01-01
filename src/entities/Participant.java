package entities;

import exceptions.NotEnoughCashException;
import javafx.beans.property.SimpleBooleanProperty;
import services.Container;
import services.ExceptionsHandler;

public class Participant {
    private int productivityPerSecond;
    private SimpleBooleanProperty isBought = new SimpleBooleanProperty(false);
    private double cost;
    private boolean isSepcial;

    public int getProductivityPerSecond() {
        return productivityPerSecond;
    }

    public Participant(int productivityPerSecond, double cost, boolean isSepcial) {
        this.productivityPerSecond = productivityPerSecond;
        this.cost = cost;
        this.isSepcial = isSepcial;
    }

    public void setProductivityPerSecond(int productivityPerSecond) {
        this.productivityPerSecond = productivityPerSecond;
    }

    public SimpleBooleanProperty isBought() {
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

    public double getCost() {
        return cost;
    }

    public boolean isSepcial() {
        return isSepcial;
    }
}
