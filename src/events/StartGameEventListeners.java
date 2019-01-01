package events;

import java.util.ArrayList;

public class StartGameEventListeners {
    private static ArrayList<OnStartGame> list = new ArrayList<>();

    public static void addToList(OnStartGame object) {
        StartGameEventListeners.list.add(object);
    }

    public static void startGame() {
        for (OnStartGame item : list) {
            item.onStartGame();
        }
    }
}
