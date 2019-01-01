package services.store.reducers;

import entities.Office;
import entities.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class OfficeState {
    private ObservableMap<String, Office> offices = FXCollections.observableHashMap();

    public OfficeState() {
        ObservableMap<String, Office> initOffices = FXCollections.observableHashMap();
        initOffices.put("Warszawa", new Office());
        initOffices.put("Poznań", new Office());
        initOffices.put("Wrocław", new Office());
        offices.putAll(initOffices);
    }

    public ObservableMap<String, Office> getOffices() {
        return offices;
    }
}
