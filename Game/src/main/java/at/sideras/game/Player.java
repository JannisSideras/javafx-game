package at.sideras.game;

import javafx.scene.canvas.GraphicsContext;
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

    int u=0,d=0,r=0,l=0;
    int dx = 320, dy = 320;

    int size = 64;
    public void movePlayerUp(GraphicsContext gc) {
        u++;
        if(3<u) u=0;

        gc.clearRect(dx,dy,size,size);
        dy -= 10;
        gc.drawImage(getAnimationUp(u), dx,dy);
    }
    public void movePlayerDown(GraphicsContext gc) {
        d++;
        if(3<d) d=0;

        gc.clearRect(dx,dy,size,size);
        dy += 10;
        gc.drawImage(getAnimationDown(d), dx,dy);
    }
    public void movePlayerLeft(GraphicsContext gc) {
        l++;
        if(3<l) l=0;

        gc.clearRect(dx,dy,size,size);
        dx -= 10;
        gc.drawImage(getAnimationLeft(l), dx,dy);
    }
    public void movePlayerRight(GraphicsContext gc) {
        r++;
        if(3<r) r=0;

        gc.clearRect(dx,dy,size,size);
        dx += 10;
        gc.drawImage(getAnimationRight(r), dx,dy);
    }
}
