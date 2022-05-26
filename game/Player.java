package at.sideras.game;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player {

    public Image getAnimationUp(int u) {
        try {
            return new Image(new FileInputStream("src/main/java/at/sideras/game/img/player_Sprite/"+u+"up.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getAnimationDown(int u) {
        try {
            return new Image(new FileInputStream("src/main/java/at/sideras/game/img/player_Sprite/"+u+"down.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getAnimationLeft(int u) {
        try {
            return new Image(new FileInputStream("src/main/java/at/sideras/game/img/player_Sprite/"+u+"left.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getAnimationRight(int u) {
        try {
            return new Image(new FileInputStream("src/main/java/at/sideras/game/img/player_Sprite/"+u+"right.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
