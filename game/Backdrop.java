package at.sideras.game;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Backdrop {
    public GridPane getRoot() throws FileNotFoundException {
        GridPane root = new GridPane();
        //root.setGridLinesVisible(true);

        int u = 0;
        for (int i = 1; i<=144;i++) {
            Image grass = new Image(new FileInputStream("src/main/java/at/sideras/game/img/grass.png"));
            ImageView grassView = new ImageView(grass);

            Pane p = new Pane();
            p.setId(""+i);
            //p.getChildren().add(new Label(""+i));
            p.getChildren().add(grassView);

            root.addRow(u, p);
            if (i%12==0) {
                u++;
            }
        }

        root.getColumnConstraints().addAll(
                new ColumnConstraints(60), new ColumnConstraints(60), new ColumnConstraints(60),
                new ColumnConstraints(60), new ColumnConstraints(60), new ColumnConstraints(60),
                new ColumnConstraints(60), new ColumnConstraints(60), new ColumnConstraints(60),
                new ColumnConstraints(60), new ColumnConstraints(60), new ColumnConstraints(60));

        root.getRowConstraints().addAll(
                new RowConstraints(60), new RowConstraints(60), new RowConstraints(60),
                new RowConstraints(60), new RowConstraints(60), new RowConstraints(60),
                new RowConstraints(60), new RowConstraints(60), new RowConstraints(60),
                new RowConstraints(60), new RowConstraints(60), new RowConstraints(60));

        return root;
    }
}
