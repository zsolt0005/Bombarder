package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    GameHandler gh = new GameHandler();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Settings.stage = primaryStage;

        start();
    }

    void start(){
        // Base stage settings
        Settings.stage.setScene(Settings.scene);
        Settings.stage.setTitle(Settings.title);
        Settings.stage.setResizable(Settings.canResize);
        Settings.stage.show();

        // Call for gameHandler
        gh.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
