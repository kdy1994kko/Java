// make a 2 player tic tac toe game in java where you Enter a number to make your move on the corresponding cell.

import java.util.*;

public class TicTacToe {
    // Game board representation
    private static char[][] board = new char[3][3];
    // Current player 'X' or 'O'
    private static char currentPlayer;
    // Number of moves played
    private static int movesPlayed;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the game
        initializeBoard();

        // Game loop
        while (true) {
            printBoard();

            // Get move from the current player
            int move;
            do {
                System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
                move = scanner.nextInt();
            } while (!isValidMove(move));

            // Update the game board
            updateBoard(move);

            // Check if the current player wins
            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            // Check if it's a draw
            if (movesPlayed == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // Switch to the next player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Initialize the game board
    private static void initializeBoard() {
        currentPlayer = 'X';
        movesPlayed = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the game board
    private static void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check if the move is valid
    private static boolean isValidMove(int move) {
        if (move < 1 || move > 9) {
            System.out.println("Invalid move. Enter a number from 1 to 9.");
            return false;
        }

        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        if (board[row][col] != '-') {
            System.out.println("Cell already occupied. Choose another cell.");
            return false;
        }

        return true;
    }

    // Update the game board with the current player's move
    private static void updateBoard(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        board[row][col] = currentPlayer;
        movesPlayed++;
    }

    // Check if the current player wins
    private static boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }
}