package com.example.sudoku;

public class Valid {
    private boolean isValid;

    Valid(Board board) {
        isValid = isValidSudoku(board);
    }

    public boolean getIsValid() {
        return isValid;
    }

    private static boolean isValidSudoku(Board board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == '.')
                    continue;

                for (int k = 0; k < 9; k++) {
                    if (i != k){
                        if (board.get(i, j) == board.get(k, j))
                            return false;
                    }

                    if (j != k){
                        if (board.get(i, j) == board.get(i, k))
                            return false;
                    }
                }

                for (int k = i - i % 3, m = k; k < m + 3; k++) {
                    for (int l = j - j % 3, n = l; l < n + 3; l++) {
                        if ((i == k && j == l) || (board.get(k, l) == '.'))
                            continue;

                        if (board.get(i, j) == board.get(k, l))
                            return false;
                    }
                }
            }
        }

        return true;
    }
}
