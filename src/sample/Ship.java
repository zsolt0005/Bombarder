package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Ship extends Canvas {

    GraphicsContext gc;

    public boolean isDead = false;

    Timeline t_move;
    Timeline t_animation;

    int animationFrame = 0;

    public Bomb bomb;

    public Ship(){
        super(Settings.tileSizeX, Settings.tileSizeY);

        gc = getGraphicsContext2D();

        t_move = new Timeline(new KeyFrame(Duration.millis(1), e->move()));
        t_move.setCycleCount(Animation.INDEFINITE);
        t_move.play();

        t_animation = new Timeline(new KeyFrame(Duration.millis(1000 / 14), e->animation() ));
        t_animation.setCycleCount(Animation.INDEFINITE);
        t_animation.play();

        setFocusTraversable(true);
        requestFocus();

        setLayoutY(Settings.scene.getHeight() * 0.15);

        Settings.group.getChildren().add(this);

        setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.SPACE)
                deployBomb();
        });
    }

    void deployBomb(){
        if(bomb == null && !isDead)
            bomb = new Bomb();
    }

    void move(){
        if(isDead)
            return;

        double speed = Settings.shipSpeed + ((double)Settings.score / 1000);
        setLayoutX(getLayoutX() + speed);

        if(getLayoutX() > Settings.scene.getWidth()){
            setLayoutX(0 - getWidth());
            setLayoutY(getLayoutY() + Settings.tileSizeY);
        }
    }

    void animation(){
        gc.clearRect(0,0, getWidth(), getHeight());

        if(!isDead)
            gc.drawImage(Settings.spaceShip_idle.get(animationFrame), 0, 0, getWidth(), getHeight());
        else
            gc.drawImage(Settings.spaceShip_destroy.get(animationFrame), 0, 0, getWidth(), getHeight());

        animationFrame++;
        if(!isDead && animationFrame > 13)
            animationFrame = 0;

        if(isDead && animationFrame > 21)
            destroy();
    }

    public void destroy(){
        t_animation.stop();
        t_move.stop();
        GameHandler.restart();
        Settings.group.getChildren().remove(this);
    }

}
