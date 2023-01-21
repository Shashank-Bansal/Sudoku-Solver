package com.example.sudoku;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SudokuApplication extends Application {

    private TextField[][] grid = new TextField[9][9];

    @Override
    public void start(Stage stage) throws IOException {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(1);
        root.setVgap(1);
        root.setPadding(new Insets(25, 25, 25, 25));
//        root.setGridLinesVisible(true);

        resetBoard(root);

        Button clear = new Button("Clear");
        HBox hb1 = new HBox(9);
        hb1.getChildren().add(clear);
        hb1.setAlignment(Pos.BOTTOM_LEFT);
        hb1.setPadding(new Insets(20));
        root.add(hb1, 0, 10, 3, 1);

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++)
                        grid[j][i].setText("");
            }

        });

        Button solve = new Button("Solve");
        HBox hb2 = new HBox(9);
        hb2.getChildren().add(solve);
        hb2.setAlignment(Pos.BOTTOM_RIGHT);
        hb2.setPadding(new Insets(20));
        root.add(hb2, 6, 10, 3, 1);

        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Board board = createBoard();

                if (checkAndAlert(board)) {
                    Solver s = new Solver();
                    s.solveSudoku(board);

                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            grid[j][i].setText(board.get(i, j) + "");
                        }
                    }
                }
            }
        });

        Button check = new Button("Check");
        HBox hb3 = new HBox(9);
        hb3.getChildren().add(check);
        hb3.setAlignment(Pos.BOTTOM_CENTER);
        hb3.setPadding(new Insets(20));
        root.add(hb3, 3, 10, 3, 1);

        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (checkAndAlert(createBoard())) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Checking Sudoku is Valid or Not");
                    a.setHeaderText("The Sudoku is Valid.");
                    a.show();
                }
            }
        });

        Scene scene = new Scene(root, 415, 420);
        stage.setMinHeight(457.6000061035156);
        stage.setMinWidth(429.6000061035156);
        stage.setResizable(false);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
    }

    private Board createBoard() {
        Board board = new Board();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String s = grid[i][j].getText().trim();
                if (s.length() != 1)
                    board.set(i, j);
                else
                    board.set(i, j, s.charAt(0));
            }
        }

        return board;
    }

    private boolean checkAndAlert(Board board) {
        Valid v = new Valid(board);

        if (v.getIsValid())
            return true;

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Checking Sudoku is Valid or Not");
        a.setHeaderText("The Sudoku is not Valid.");
        a.setContentText("Press \"Clear\" Button and Try again.");
        a.show();
        return false;
    }

    private void resetBoard(GridPane root) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField t = new TextField();
                t.setAlignment(Pos.CENTER);
                t.setStyle("-fx-border-style: solid;");
                t.setPadding(new Insets(5));
                grid[j][i] = t;
                root.add(t, j, i);

                if ((i < 3 && j < 3) || (i >= 6 && j >= 6) || (i < 3 && j >= 6) || (i >= 6 && j < 3) || (i > 2 && j > 2 && i < 6 && j < 6))
                    t.setStyle("-fx-control-inner-background: #DAF7A6; -fx-border-style: solid;");
                else
                    t.setStyle("-fx-control-inner-background: #FFFFE0; -fx-border-style: solid;");

                // to make the text bold use this: -fx-font-weight: bold;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}