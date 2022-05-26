package at.sideras.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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
        Backdrop background = new Backdrop();

        // create canvas for player to render
        Canvas canvas = new Canvas(720,720);
        // add background to scene
        root.getChildren().add(background.getRoot());
        // add canvas on top of background
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();




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
        gc.drawImage(defaultPlayer,320,320);


        // GAMELOOP
        AnimationTimer timer = new AnimationTimer() {

            // position
            int dx = 320, dy = 320;

            // animation frame
            int u=0,d=0,r=0,l=0;


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
                    u++;
                    if(3<u) u=0;

                    gc.clearRect(dx,dy,64,64);
                    dy -= 10;
                    gc.drawImage(player.getAnimationUp(u), dx,dy);
                }
                if (goDOWN){
                    d++;
                    if(3<d) d=0;

                    gc.clearRect(dx,dy,64,64);
                    dy += 10;
                    gc.drawImage(player.getAnimationDown(d), dx,dy);
                }
                if (goRIGHT){
                    r++;
                    if(3<r) r=0;

                    gc.clearRect(dx,dy,64,64);
                    dx += 10;
                    gc.drawImage(player.getAnimationRight(r), dx,dy);
                }
                if (goLEFT){
                    l++;
                    if(3<l) l=0;

                    gc.clearRect(dx,dy,64,64);
                    dx -= 10;
                    gc.drawImage(player.getAnimationLeft(l), dx,dy);
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
