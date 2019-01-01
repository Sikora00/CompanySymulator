package services.store;

import entities.Office;
import entities.Wallet;
import javafx.collections.ObservableMap;
import services.store.reducers.OfficeReducer;
import services.store.reducers.OfficeState;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Object> reducers = new HashMap<>();

    public Store() {
        reducers.put("Office", new OfficeReducer());
        reducers.put("Wallet", new Wallet());
    }

    public OfficeState getOfficeState() {
        OfficeReducer reducer = (OfficeReducer) reducers.get("Office");
        return reducer.getState();
    }

    public ObservableMap<String, Office> getOffices() {
        return getOfficeState().getOffices();
    }

    public Office getWarszawaOffice() {
        return getOffices().get("Warszawa");
    }

    public Office getPoznanOffice() {
        return getOffices().get("Poznań");
    }

    public Office getWroclawOffice() {
        return getOffices().get("Wrocław");
    }

    public double getProductivityFromAllOffices() {
        double result = 0;
        result += getWarszawaOffice().getDoubleProductivity();
        result += getPoznanOffice().getDoubleProductivity();
        result += getWroclawOffice().getDoubleProductivity();
        return result;
    }

    public Wallet getWallet() {
        return (Wallet) reducers.get("Wallet");
    }

}
