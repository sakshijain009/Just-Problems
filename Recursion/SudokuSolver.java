/**
 * Q: Sudoku Solver
 * ----------------
 * Given an incomplete Sudoku configuration in a 9x9 integer matrix `mat[][]`,
 * where `0` represents an empty cell, fill the board such that:
 *   1. Each row contains the digits 1–9 exactly once.
 *   2. Each column contains the digits 1–9 exactly once.
 *   3. Each of the nine 3x3 sub-boxes contains the digits 1–9 exactly once.
 *
 * It is guaranteed that the input Sudoku has exactly one solution.
 *
 * Approach:
 * ---------
 * We use backtracking:
 * - Traverse the board.
 * - For each empty cell (0), try placing numbers 1–9.
 * - Check if the number is valid (not already in row, column, or sub-box).
 * - If valid, place it and recursively try to solve the rest.
 * - If recursion fails, reset the cell (backtrack) and try the next number.
 * - If no number works, return false → triggers backtracking to previous cells.
 *
 * Time Complexity: O(9^(N)) worst case, but much faster in practice due to pruning.
 * Space Complexity: O(N^2) recursion depth at most 81.
 */

class Solution {

    // Function to solve Sudoku using backtracking
    static boolean solveSudoku(int[][] mat) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (mat[i][j] == 0) { // empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (canFill(num, mat, i, j)) {
                            mat[i][j] = num;

                            // recurse
                            if (solveSudoku(mat)) {
                                return true;
                            }

                            // backtrack
                            mat[i][j] = 0;
                        }
                    }
                    return false; // if no number fits, backtrack
                }
            }
        }
        return true; // board is solved
    }

    // Function to check if 'num' can be placed at (row, col)
    private static boolean canFill(int num, int[][] mat, int row, int col) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (mat[row][i] == num) return false;
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (mat[i][col] == num) return false;
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (mat[i][j] == num) return false;
            }
        }

        return true;
    }

    // Utility function to print board
    static void printBoard(int[][] mat) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Driver code for testing
    public static void main(String[] args) {
        int[][] mat = {
            {3,0,6,5,0,8,4,0,0},
            {5,2,0,0,0,0,0,0,0},
            {0,8,7,0,0,0,0,3,1},
            {0,0,3,0,1,0,0,8,0},
            {9,0,0,8,6,3,0,0,5},
            {0,5,0,0,9,0,6,0,0},
            {1,3,0,0,0,0,2,5,0},
            {0,0,0,0,0,0,0,7,4},
            {0,0,5,2,0,6,3,0,0}
        };

        if (solveSudoku(mat)) {
            printBoard(mat);
        } else {
            System.out.println("No solution exists");
        }
    }
}
