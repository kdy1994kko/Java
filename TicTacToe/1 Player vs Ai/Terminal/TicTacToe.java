// make a tic tac toe game in java vs the ai 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private char[] board;
    private char player;
    private char ai;

    // Available positions on the board
    private List<Integer> availableMoves;

    // Winning combinations
    private final int[][] WINNING_COMBINATIONS = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}
    };

    public TicTacToe() {
        board = new char[10];
        Arrays.fill(board, ' ');
        player = 'X';
        ai = 'O';
        availableMoves = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            availableMoves.add(i);
        }
    }

    private void displayBoard() {
        System.out.println("-------------");
        for (int i = 1; i <= 9; i += 3) {
            System.out.printf("| %c | %c | %c |\n", board[i], board[i + 1], board[i + 2]);
            System.out.println("-------------");
        }
    }

    private boolean isBoardFull() {
        return availableMoves.isEmpty();
    }

    private boolean hasPlayerWon(char player) {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (board[combination[0]] == player && board[combination[1]] == player && board[combination[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private void makeMove(int move, char player) {
        board[move] = player;
        availableMoves.remove(Integer.valueOf(move));
    }

    private boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && availableMoves.contains(move);
    }

    private int getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your move (1-9): ");
        int move = scanner.nextInt();
        while (!isValidMove(move)) {
            System.out.println("Invalid move. Please try again.");
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt();
        }
        return move;
    }

    private int getAIMove() {
        Random random = new Random();
        int index = random.nextInt(availableMoves.size());
        return availableMoves.get(index);
    }

    private void play() {
        System.out.println("--- Tic Tac Toe ---");
        System.out.println("You are X, and the AI is O.");
        System.out.println("Enter a number to make your move on the corresponding cell.");
        displayBoard();

        while (true) {
            // Player's turn
            int playerMove = getPlayerMove();
            makeMove(playerMove, player);
            displayBoard();
            if (hasPlayerWon(player)) {
                System.out.println("Congratulations! You won!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }

            // AI's turn
            System.out.println("AI's turn...");
            int aiMove = getAIMove();
            makeMove(aiMove, ai);
            displayBoard();
            if (hasPlayerWon(ai)) {
                System.out.println("AI won! Better luck next time!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }
        }

        System.out.println("--- Game Over ---");
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}