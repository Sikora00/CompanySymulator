package services;

import exceptions.NotEnoughCashException;
import scenes.game.GameController;

public class ExceptionsHandler {

    public static void handle(Throwable e) {
        GameController gameController = GameController.instance;
        if (gameController != null) {
            if (e instanceof NotEnoughCashException) {
                gameController.openSnackBar("Brakuje Ci pieniędzy!");
                return;
            }
        }
        e.printStackTrace();
    }
}
