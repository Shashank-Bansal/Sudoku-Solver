module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.sudoku to javafx.fxml;
    exports com.example.sudoku;
}