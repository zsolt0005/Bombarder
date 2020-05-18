package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    // Base
    public static String title = "Bombardér"; // Game title
    public static int width = 1360; // Game launch width
    public static int height = 700; // Game launch height
    public static Stage stage; // Primary stage
    public static Group group = new Group(); // Main group
    public static Scene scene = new Scene(group, width, height); // Main scene
    public static boolean canResize = false; // Can be resized ?
    public static boolean isPlayingSound = true; // Sound mute/unmute
    public static double musicVolume = 0.1; // Sound volume (1 = 100%)

    // Game
    public static int score = 0; // Score
    public static int maxBombDestroy = 3; // Max levels bomb can destroy. Min is always 1
    public static int tileSizeX = 68; // Building block size X (px) {1360}
    public static int tileSizeY = 28; // Building block size Y (px) {700 - Ui - Ship = 560}
    public static int minBuildingHeight = 10; // Min building height
    public static int maxBuildingHeight = 18; // Max building height
    public static double shipSpeed = 0.2; // Ship moving speed (0.2 ideal)

    // Cache
        // Images
    public static List<Image> background = new ArrayList<>(); // background elements
    public static List<Image> bomb = new ArrayList<>(); // Bomb elements
    public static List<Image> explosion = new ArrayList<>(); // Explosion elements
    public static List<Image> spaceShip_idle = new ArrayList<>(); // SpaceShip idle elements
    public static List<Image> spaceShip_destroy = new ArrayList<>(); // SpaceShip destroy elements
    public static List<Image> ui = new ArrayList<>(); // UI elements
    // Sounds
    public static MediaPlayer bg;
}
