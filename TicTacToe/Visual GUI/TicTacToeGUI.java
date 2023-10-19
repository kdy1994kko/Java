// say player o's turn when its their turn

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {

    private JButton[][] board;
    private int currentPlayer;
    private boolean gameEnded;
    private JLabel messageLabel;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        board = new JButton[3][3];
        currentPlayer = 1;
        gameEnded = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton();
                board[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 56));
                final int finalI = i;
                final int finalJ = j;
                board[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!gameEnded && board[finalI][finalJ].getText().isEmpty()) {
                            board[finalI][finalJ].setText(currentPlayer == 1 ? "X" : "O");
                            board[finalI][finalJ].setEnabled(false);
                            if (checkWin(finalI, finalJ)) {
                                messageLabel.setText("Player " + (currentPlayer == 1 ? "X" : "O") + " wins!");
                                gameEnded = true;
                            } else if (checkDraw()) {
                                messageLabel.setText("It's a draw!");
                                gameEnded = true;
                            } else {
                                currentPlayer = currentPlayer == 1 ? 2 : 1;
                                messageLabel.setText("Player " + (currentPlayer == 1 ? "X" : "O") + "'s turn");
                            }
                        }
                    }
                });
                boardPanel.add(board[i][j]);
            }
        }

        messageLabel = new JLabel("Player X's turn");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(boardPanel, BorderLayout.CENTER);
        mainContainer.add(messageLabel, BorderLayout.SOUTH);
    }

    private boolean checkWin(int row, int col) {
        String currentSymbol = board[row][col].getText();
        return (board[row][(col + 1) % 3].getText().equals(currentSymbol) &&
                board[row][(col + 2) % 3].getText().equals(currentSymbol)) ||
                (board[(row + 1) % 3][col].getText().equals(currentSymbol) &&
                        board[(row + 2) % 3][col].getText().equals(currentSymbol)) ||
                (row == col && board[(row + 1) % 3][(col + 1) % 3].getText().equals(currentSymbol) &&
                        board[(row + 2) % 3][(col + 2) % 3].getText().equals(currentSymbol)) ||
                (row + col == 2 && board[(row + 1) % 3][(col + 2) % 3].getText().equals(currentSymbol) &&
                        board[(row + 2) % 3][(col + 1) % 3].getText().equals(currentSymbol));
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicTacToeGUI game = new TicTacToeGUI();
                game.setVisible(true);
            }
        });
    }
}