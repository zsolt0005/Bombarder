package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class GameHandler {

    // <editor-fold desc="Variables">

        // Classes
    SoundHandler sh;
    public static Map map;
    public static Ship ship;

        // ImageViews
    ImageView background;
    ImageView scoreBg;
    ImageView sound;

        // Labels
    Label scoreLabel;

        // LayoutManagers
    StackPane scoreHolder;

        // TimeLines
    Timeline t_score;

    // </editor-fold>

    void start(){
        init();
        prepare();
    }

    void init(){
        // Images
            // Backgrounds
        Settings.background.add(new Image(Paths.get("img/Background/01.png").toUri().toString()));
        Settings.background.add(new Image(Paths.get("img/Background/02.png").toUri().toString()));
        Settings.background.add(new Image(Paths.get("img/Background/03.png").toUri().toString()));
        Settings.background.add(new Image(Paths.get("img/Background/04.png").toUri().toString()));
            // Bombs
        Settings.bomb.add(new Image(Paths.get("img/Bomb/01.png").toUri().toString()));
        Settings.bomb.add(new Image(Paths.get("img/Bomb/02.png").toUri().toString()));
            // Explosion
        for(int i = 0; i < 18; i++)
            Settings.explosion.add(new Image(Paths.get("img/Explosion/skeleton-Fx3_" + i + ".png").toUri().toString()));
            // SpaceShip
                // Idle
        for(int i = 0; i < 14; i++)
            Settings.spaceShip_idle.add(new Image(Paths.get("img/SpaceShip/Move/skeleton-MovingNIdle_" + i + ".png").toUri().toString()));
                // Destroy
        for(int i = 0; i < 22; i++)
            Settings.spaceShip_destroy.add(new Image(Paths.get("img/SpaceShip/Destroy/skeleton-Destroy_" + i + ".png").toUri().toString()));

        // Ui
        Settings.ui.add(new Image(Paths.get("img/UI/music.png").toUri().toString()));
        Settings.ui.add(new Image(Paths.get("img/UI/music_off.png").toUri().toString()));
        Settings.ui.add(new Image(Paths.get("img/UI/score.png").toUri().toString()));


        // Sounds
        Settings.bg = new MediaPlayer(new Media(Paths.get("sound/bg.mp3").toUri().toString()));
        Settings.bg.setVolume(Settings.musicVolume);
        Settings.bg.setCycleCount(MediaPlayer.INDEFINITE);

        sh = new SoundHandler();
    }

    void prepare(){

        // <editor-fold desc="Load css">

        Settings.scene.getStylesheets().add(Paths.get("css/global.css").toUri().toString());

        // </editor-fold>

        // <editor-fold desc="Background">

        background = new ImageView(Settings.background.get(ThreadLocalRandom.current().nextInt(0, Settings.background.size())));
        background.setFitWidth(Settings.scene.getWidth());
        background.setFitHeight(Settings.scene.getHeight());
        background.setFocusTraversable(false);

        Settings.group.getChildren().add(background);

        // </editor-fold>

        // <editor-fold desc="Ui -> Score">

        scoreHolder = new StackPane();
        scoreHolder.setPrefWidth(Settings.scene.getWidth() * 0.05);
        scoreHolder.setPrefHeight(Settings.scene.getHeight() * 0.05);
        scoreHolder.setLayoutY(Settings.scene.getHeight() * 0.025);
        scoreHolder.setLayoutX( (Settings.scene.getWidth() - scoreHolder.getPrefWidth()) / 2 );
        scoreHolder.setFocusTraversable(false);

        scoreBg = new ImageView(Settings.ui.get(2));
        scoreBg.setFitWidth(scoreHolder.getPrefWidth());
        scoreBg.setFitHeight(scoreHolder.getPrefHeight());
        scoreBg.setOpacity(0.5);
        scoreBg.setFocusTraversable(false);

        scoreLabel = new Label();
        scoreLabel.setText(Settings.score + "");
        scoreLabel.setStyle("-fx-text-fill: white");
        scoreLabel.setFocusTraversable(false);

        scoreHolder.getChildren().addAll(scoreBg, scoreLabel);

        Settings.group.getChildren().add(scoreHolder);

        // </editor-fold>

        // <editor-fold desc="Ui -> sound">

        sound = new ImageView(Settings.ui.get(0));
        sound.setPreserveRatio(true);
        sound.setLayoutY(Settings.scene.getHeight() * 0.025);

        sound.setFitWidth(Settings.scene.getWidth() * 0.025);
        sound.setLayoutX(Settings.scene.getWidth() - (sound.getFitWidth() * 2) );

        sound.setOnMouseClicked(e->{
            Settings.isPlayingSound = !Settings.isPlayingSound;

            if(Settings.isPlayingSound){
                sh.play();
                sound.setImage(Settings.ui.get(0));
            }else{
                sh.pause();
                sound.setImage(Settings.ui.get(1));
            }
        });
        sound.setFocusTraversable(false);

        Settings.group.getChildren().add(sound);

        // </editor-fold>

        // <editor-fold desc="Generate map">

        map = new Map();

        // </editor-fold>

        // <editor-fold desc="SpaceShip">

        ship = new Ship();

        // </editor-fold>

        // <editor-fold desc="Timers">

        t_score = new Timeline(new KeyFrame(Duration.millis(100),e->updateScore()));
        t_score.setCycleCount(Animation.INDEFINITE);
        t_score.play();

        // </editor-fold>
    }

    void updateScore(){
        scoreLabel.setText(Settings.score + "");
    }

    static void restart(){
        // Remove map
        map.map.getChildren().clear();
        Settings.group.getChildren().remove(map.map);

        // Remove ship & bomb
        if(ship.bomb != null)
            ship.bomb.destroy();
        Settings.group.getChildren().remove(ship);

        // Create new map and ship and reset score
        map = new Map();
        ship = new Ship();
        Settings.score = 0;
    }
}

    // <editor-fold desc="">
    // </editor-fold>