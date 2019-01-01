package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Office {
    private ObservableList<Room> rooms = FXCollections.observableArrayList();
    private SimpleIntegerProperty nextRoomToBuy;

    public Office() {
        for (int i = 0; i < 6; i++) {
            rooms.add(new Room());
        }
        nextRoomToBuy = new SimpleIntegerProperty(rooms.size() - 1);
        rooms.get(rooms.size() - 1).setIsNextToBuy(true);
        addListenerToNextRoomToBuy();
    }

    private void addListenerToNextRoomToBuy() {
        rooms.get(nextRoomToBuy.get()).getIsBought().addListener((observable, oldValue, newValue) -> {
            rooms.get(nextRoomToBuy.get()).setIsNextToBuy(false);
            nextRoomToBuy.set(nextRoomToBuy.get() - 1);
            if (nextRoomToBuy.get() >= 0) {
                rooms.get(nextRoomToBuy.get()).setIsNextToBuy(true);
                addListenerToNextRoomToBuy();
            }
        });
    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public double getDoubleProductivity() {
        double doubleResult = 0;
        for (Room room : rooms) {
            doubleResult += room.getDoubleProductivity();
        }
        return doubleResult;
    }


}
