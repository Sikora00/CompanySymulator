import events.OnStartGame;
import events.StartGameEventListeners;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import scenes.game.GameScene;
import scenes.start.StartScene;
import services.Container;
import services.ExceptionsHandler;

public class Main extends Application implements OnStartGame {
    Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Scene scene = StartScene.create();
        Container.getInstance().setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Company Simulator");

        StartGameEventListeners.addToList(this);
        stage.show();
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            ExceptionsHandler.handle(throwable);
        });
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @Override
    public void onStartGame() {
        stage.setScene(GameScene.create());
        stage.setMaximized(true);
    }
}
