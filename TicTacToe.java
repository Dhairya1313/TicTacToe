import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JFrame frame = new JFrame("Tic-Tac-Toe");
    private JButton[][] board = new JButton[3][3];
    private JLabel textLabel = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
    private String currentPlayer = "X";
    private int turns = 0;

    public TicTacToe() {
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.darkGray);
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.addActionListener(e -> {
                    if (tile.getText().isEmpty() && !gameOver()) {
                        tile.setText(currentPlayer);
                        turns++;
                        if (!gameOver()) {
                            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                            textLabel.setText(currentPlayer + "'s turn.");
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    private boolean gameOver() {
        return checkWinner() || turns == 9;
    }

    private boolean checkWinner() {
        String[][] boardState = new String[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                boardState[r][c] = board[r][c].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!boardState[i][0].isEmpty() && boardState[i][0].equals(boardState[i][1]) && boardState[i][1].equals(boardState[i][2])) {
                setWinner(board[i][0], board[i][1], board[i][2]);
                return true;
            }
            if (!boardState[0][i].isEmpty() && boardState[0][i].equals(boardState[1][i]) && boardState[1][i].equals(boardState[2][i])) {
                setWinner(board[0][i], board[1][i], board[2][i]);
                return true;
            }
        }

        if (!boardState[0][0].isEmpty() && boardState[0][0].equals(boardState[1][1]) && boardState[1][1].equals(boardState[2][2])) {
            setWinner(board[0][0], board[1][1], board[2][2]);
            return true;
        }

        if (!boardState[0][2].isEmpty() && boardState[0][2].equals(boardState[1][1]) && boardState[1][1].equals(boardState[2][0])) {
            setWinner(board[0][2], board[1][1], board[2][0]);
            return true;
        }

        return false;
    }

    private void setWinner(JButton... tiles) {
        for (JButton tile : tiles) {
            tile.setForeground(Color.green);
            tile.setBackground(Color.black);
        }
        textLabel.setText(currentPlayer + " is the winner!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
