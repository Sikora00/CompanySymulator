package scenes.game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import services.ExceptionsHandler;

import java.io.IOException;

public class GameScene extends Scene {
    public GameController controller;

    public GameScene(Parent root) {
        super(root);
    }

    public static GameScene create() {
        FXMLLoader loader = new FXMLLoader(GameScene.class.getClass().getResource("/scenes/game/gameScene.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            ExceptionsHandler.handle(e);
        }
        GameScene self = new GameScene(pane);
        self.controller = loader.getController();
        return self;
    }
}
