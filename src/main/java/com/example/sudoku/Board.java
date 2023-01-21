package com.example.sudoku;

public class Board {
    private char[][] board;

    public Board() {
        board = new char[9][9];
    }

    public Board(Board forCopy) {
        board = new char[9][9];

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = forCopy.get(i, j);
    }

    public char[][] get() {
        return board;
    }

    public char[] get(int i) {
        return board[i];
    }

    public char get(int i, int j) {
        return board[i][j];
    }

    public void set(int i, int j) {
        board[i][j] = '.';
    }

    public void set(int i, int j, char c) {
        board[i][j] = Character.isDigit(c) && c != '0' ? c : '.';
    }

    public int length() {
        return board.length;
    }
}
