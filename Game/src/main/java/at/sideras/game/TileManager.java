package at.sideras.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;

public class TileManager {

    public final int maxRow = 16;
    public final int maxCol = 16;
    public final int screenSize = 720;

    public final int tileSize = screenSize / maxRow;
    GraphicsContext gp;
    Tile[] tile;

    int mapTileNum[][];

    public TileManager(GraphicsContext gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[maxRow][maxCol];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = new Image(new FileInputStream("src/main/java/at/sideras/game/img/grass.png"));
            tile[1] = new Tile();
            tile[1].image = new Image(new FileInputStream("src/main/java/at/sideras/game/img/dirt.png"));
            tile[2] = new Tile();
            tile[2].image = new Image(new FileInputStream("src/main/java/at/sideras/game/img/water.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/at/sideras/game/maps/map01.txt"));

            int col = 0;
            int row = 0;

            while(col < maxCol && row < maxRow) {
                String line = br.readLine();
                while (col < maxCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == maxCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw() {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < maxRow && row < maxRow) {
            int tileNum = mapTileNum[col][row];

            gp.drawImage(tile[tileNum].image, x,y,tileSize,tileSize);
            col++;
            x+=tileSize;
            if (col == maxCol){
                col=0;
                x=0;
                row++;
                y+=tileSize;
            }
        }
    }
}
