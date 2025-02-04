import java.util.Scanner;

public class SudokuSolver {

    public static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle row by row (9 rows).");
        System.out.println("Use space-separated numbers, and use 0 for empty cells:");
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                board[row][col] = scanner.nextInt();
            }
        }
        scanner.close();

        System.out.println("\nInput Sudoku puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSudoku solved successfully!");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku puzzle.");
        }
    }

    public static boolean isSafe(int[][] board, int row, int col, int digit) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == digit || board[i][col] == digit) {
                return false;
            }
        }

        int subRowStart = row - row % 3;
        int subColStart = col - col % 3;

        for (int i = subRowStart; i < subRowStart + 3; i++) {
            for (int j = subColStart; j < subColStart + 3; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {

                    for (int digit = 1; digit <= GRID_SIZE; digit++) {
                        if (isSafe(board, row, col, digit)) {
                            board[row][col] = digit; 

                            if (solveSudoku(board)) {
                                return true;
                            }


                            board[row][col] = 0;
                        }
                    }
  
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print((board[row][col] == 0 ? ". " : board[row][col] + " "));
            }
            System.out.println();
        }
    }
}
