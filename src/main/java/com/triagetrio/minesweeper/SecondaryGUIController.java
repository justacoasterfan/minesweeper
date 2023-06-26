package com.triagetrio.minesweeper;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryGUIController {

    @FXML
    private void switchToPrimary() throws IOException {
        GameController.resetGame();
        App.setRoot("/primary.fxml");
    }
}