package services;

import javafx.stage.Stage;
import services.store.Store;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private Map<String, Object> services = new HashMap<>();
    private static Container instance;

    private Container() {
        services.put("Store", new Store());
    }

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }

        return instance;
    }

    public void setStage(Stage stage) {
        services.put("Stage", stage);
    }

    public Stage getStage() {
        return (Stage) services.get("Stage");
    }

    public Object get(String key) {
        return services.get(key);
    }

    public Store getStore() {
        return (Store) services.get("Store");
    }
}
