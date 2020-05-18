package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BuildingBlock extends Button {

    Timeline collision;

    ImageView explosion;

    int animationFrame = 0;

    int removeFromBombPower = 1;

    public BuildingBlock(){
        collision = new Timeline(new KeyFrame(Duration.millis(1), e->collisionCheck()));
        collision.setCycleCount(Animation.INDEFINITE);
        collision.play();

        explosion = new ImageView(Settings.explosion.get(animationFrame));
        explosion.setFitWidth(Settings.tileSizeX);
        explosion.setFitHeight(Settings.tileSizeY);
    }

    void collisionCheck(){

        // <editor-fold desc="Collision with ship">

        if(GameHandler.ship.getBoundsInParent().intersects(
                getBoundsInParent().getMinX(), getBoundsInParent().getMinY(),
                Settings.tileSizeX, Settings.tileSizeY
        )){
            collision.stop();
            GameHandler.ship.isDead = true;

            setId("dead");
            destroy();
        }

        // </editor-fold>

        // <editor-fold desc="Collision with bomb">

        if(GameHandler.ship.bomb != null)
            if(GameHandler.ship.bomb.getBoundsInParent().intersects(
                    getBoundsInParent().getMinX(), getBoundsInParent().getMinY(),
                    Settings.tileSizeX, Settings.tileSizeY
            )){
                if(GameHandler.ship.bomb.power > 0){
                    collision.stop();
                    GameHandler.ship.bomb.power -= removeFromBombPower;
                    removeFromBombPower = 0;
                    Settings.score++;

                    setId("dead");
                    destroy();
                }
            }

        // </editor-fold>

    }

    void destroy(){
        GameHandler.map.map.getChildren().remove(this);

        if(GameHandler.map.map.getChildren().size() <= 0){
            GameHandler.restart();
        }
    }

}
