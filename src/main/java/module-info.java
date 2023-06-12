module com.triagetrio.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.triagetrio.minesweeper to javafx.fxml;
    exports com.triagetrio.minesweeper;
}
