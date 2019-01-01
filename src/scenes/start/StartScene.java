package scenes.start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import services.ExceptionsHandler;

import java.io.IOException;

public class StartScene extends Scene {
    public StartController controller;

    private StartScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    public static StartScene create() {
        FXMLLoader loader = new FXMLLoader(StartScene.class.getClass().getResource("/scenes/start/startScene.fxml"));

        AnchorPane grid = null;
        try {
            grid = loader.load();
        } catch (IOException e) {
            ExceptionsHandler.handle(e);
        }
        StartScene self = new StartScene(grid, 600, 400);
        self.controller = loader.getController();
        return self;
    }
}
