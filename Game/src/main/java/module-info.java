module at.sideras.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.sideras.game to javafx.fxml;
    exports at.sideras.game;
}