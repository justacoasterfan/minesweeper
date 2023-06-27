package com.triagetrio.minesweeper;

import java.io.IOException;
import javafx.fxml.FXML;

public class WonGUIController {

    @FXML
    private void switchToPrimary() throws IOException {
        GameController.resetGame();
        App.setRoot("/game.fxml");
    }
}