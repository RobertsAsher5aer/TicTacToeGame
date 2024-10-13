import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TicTacToeFrame class creates the GUI for the Tic Tac Toe game.
 */
public class TicTacToeFrame extends JFrame implements ActionListener {
    private final TicTacToeButton[][] buttons = new TicTacToeButton[3][3];
    private char currentPlayer = 'X'; // Player starts with 'X'
    private int movesCount = 0;

    /**
     * Constructor to set up the game frame.
     */
    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        // Initialize buttons and add to panel
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TicTacToeButton(row, col);
                buttons[row][col].addActionListener(this);
                buttonPanel.add(buttons[row][col]);
            }
        }

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    /**
     * Action event handler for button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof TicTacToeButton) {
            TicTacToeButton button = (TicTacToeButton) e.getSource();
            if (!button.isOccupied()) {
                button.setPlayer(currentPlayer);
                movesCount++;

                if (checkForWin(button.getRow(), button.getCol())) {
                    showMessage("Player " + currentPlayer + " wins!");
                    promptPlayAgain();
                } else if (movesCount >= 9) {
                    showMessage("It's a tie!");
                    promptPlayAgain();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch players
                }
            } else {
                // Show illegal move message
                showMessage("Illegal move! The square is already occupied. Try again.");
            }
        }
    }

    /**
     * Displays a message dialog with custom size.
     *
     * @param message The message to display.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Prompts the user to play again or quit.
     */
    private void promptPlayAgain() {
        int option = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * Checks if the current player has won.
     *
     * @param row The row of the last move.
     * @param col The column of the last move.
     * @return True if the current player has won.
     */
    private boolean checkForWin(int row, int col) {
        return (checkRow(row) || checkCol(col) || (row == col && checkDiagonal()) || (row + col == 2 && checkAntiDiagonal()));
    }

    private boolean checkRow(int row) {
        return buttons[row][0].getPlayer() == currentPlayer && buttons[row][1].getPlayer() == currentPlayer && buttons[row][2].getPlayer() == currentPlayer;
    }

    private boolean checkCol(int col) {
        return buttons[0][col].getPlayer() == currentPlayer && buttons[1][col].getPlayer() == currentPlayer && buttons[2][col].getPlayer() == currentPlayer;
    }

    private boolean checkDiagonal() {
        return buttons[0][0].getPlayer() == currentPlayer && buttons[1][1].getPlayer() == currentPlayer && buttons[2][2].getPlayer() == currentPlayer;
    }

    private boolean checkAntiDiagonal() {
        return buttons[0][2].getPlayer() == currentPlayer && buttons[1][1].getPlayer() == currentPlayer && buttons[2][0].getPlayer() == currentPlayer;
    }

    /**
     * Resets the game for a new round.
     */
    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].reset();
            }
        }
        currentPlayer = 'X'; // Reset to player 'X'
        movesCount = 0;
    }
}
