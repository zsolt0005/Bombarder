package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class Bomb extends Canvas {

    GraphicsContext gc;

    int power = 0;

    Timeline move;

    public Bomb(){
        super(Settings.tileSizeY / 2, Settings.tileSizeX / 2);

        gc = getGraphicsContext2D();

        move = new Timeline(new KeyFrame(Duration.millis(1), e->move()));
        move.setCycleCount(Animation.INDEFINITE);
        move.play();

        power = ThreadLocalRandom.current().nextInt(1, Settings.maxBombDestroy + 1);

        if(power == 3)
            gc.drawImage(Settings.bomb.get(1), 0, 0, getWidth(), getHeight());
        else
            gc.drawImage(Settings.bomb.get(0), 0, 0, getWidth(), getHeight());

        setLayoutY(GameHandler.ship.getLayoutY());

        double optimalX = ( ( Math.round(GameHandler.ship.getLayoutX() / Settings.tileSizeX) ) * Settings.tileSizeX) + Settings.tileSizeX / 2;
        setLayoutX(optimalX);

        Settings.group.getChildren().add(this);
    }

    void move(){
        if(power <= 0)
            destroy();

        setLayoutY(getLayoutY() + Settings.shipSpeed * 2);

        if(getLayoutY() > Settings.scene.getHeight() - getHeight())
            destroy();
    }

    public void destroy(){
        move.stop();
        gc.clearRect(0,0, getWidth(), getHeight());

        Settings.group.getChildren().remove(this);

        if(GameHandler.ship != null)
            GameHandler.ship.bomb = null;
    }

}
