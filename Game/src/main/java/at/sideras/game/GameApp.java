package at.sideras.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameApp extends Application {
    boolean goUP, goDOWN, goRIGHT, goLEFT;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // SETTING UP THE STAGE
        Group root = new Group();
        Scene scene = new Scene(root);
        Player player = new Player();

        // create canvas for player to render
        Canvas canvas = new Canvas(720,720);
        Canvas background = new Canvas(720,720);

        // add canvas on top of background
        root.getChildren().add(background);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gp = background.getGraphicsContext2D();

        TileManager tm = new TileManager(gp);

        // ADD USER INPUT
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: goUP = true; break;
                    case A: goLEFT = true; break;
                    case S: goDOWN = true; break;
                    case D: goRIGHT  = true; break;
                    default: break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: goUP = false; break;
                    case A: goLEFT = false; break;
                    case S: goDOWN = false; break;
                    case D: goRIGHT  = false; break;
                    default: break;
                }
            }
        });

        // add default player to scene
        Image defaultPlayer = new Image(new FileInputStream("src/main/java/at/sideras/game/img/player_Sprite/0down.png"));

        // draw player on canvas and give position
        tm.draw();
        gc.drawImage(defaultPlayer, player.dx, player.dy);

        // GAMELOOP
        AnimationTimer timer = new AnimationTimer() {
            // animation speed of canvas
            private long sleepNs = 80000000;
            long prevTime = 0;

            @Override
            public void handle(long now) {
                // animation speed of canvas
                if ((now - prevTime) < sleepNs) {
                    return;
                }
                prevTime = now;
                // animate frames of player
                if (goUP){
                    player.movePlayerUp(gc);
                }
                if (goDOWN){
                    player.movePlayerDown(gc);
                }
                if (goRIGHT){
                    player.movePlayerRight(gc);
                }
                if (goLEFT){
                    player.movePlayerLeft(gc);
                }
            }
        };
        // start gameloop
        timer.start();

        stage.setTitle("game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
