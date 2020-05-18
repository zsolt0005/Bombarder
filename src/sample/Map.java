package sample;

import javafx.scene.Group;

import java.util.concurrent.ThreadLocalRandom;

public class Map {

    Group map;

    public Map(){
        // Init group
        double prefHeight = Settings.scene.getHeight();
        map = new Group();
        map.prefWidth(Settings.scene.getWidth());
        map.prefHeight(prefHeight);

        // Get X tile count
        int xTileCount = Settings.width / Settings.tileSizeX;

        // Array
        int[] yTiles = new int[xTileCount];

        // Populate array
        for(int i = 0; i < xTileCount; i++)
            yTiles[i] = ThreadLocalRandom.current().nextInt(Settings.minBuildingHeight, Settings.maxBuildingHeight);

        // Draw map
        for(int x = 0; x < xTileCount; x++)
            for(int y = 0; y < yTiles[x]; y++){
                BuildingBlock b = new BuildingBlock();
                b.setId("block");
                b.setPrefWidth(Settings.tileSizeX);
                b.setPrefHeight(Settings.tileSizeY);
                b.setFocusTraversable(false);

                b.setLayoutX(x * Settings.tileSizeX);
                b.setLayoutY(prefHeight - (y * Settings.tileSizeY) - Settings.tileSizeY);

                map.getChildren().add(b);
            }

        Settings.group.getChildren().add(map);
    }

}
