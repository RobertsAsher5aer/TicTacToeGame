import javax.swing.*;
import java.awt.*;

/**
 * TicTacToeButton class represents a button on the Tic Tac Toe board.
 */
public class TicTacToeButton extends JButton {
    private char player = ' ';
    private final int row;
    private final int col;

    /**
     * Constructor to initialize the button.
     *
     * @param row The row of the button.
     * @param col The column of the button.
     */
    public TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
        setFont(new Font("Arial", Font.PLAIN, 60)); // Increase font size
    }

    /**
     * Sets the player for this button and updates the button text.
     *
     * @param player The player ('X' or 'O').
     */
    public void setPlayer(char player) {
        this.player = player;
        setText(String.valueOf(player));
        setEnabled(false); // Disable button after it has been played
    }

    /**
     * Checks if the button is occupied.
     *
     * @return True if the button is occupied.
     */
    public boolean isOccupied() {
        return player != ' ';
    }

    /**
     * Gets the player for this button.
     *
     * @return The player character ('X' or 'O').
     */
    public char getPlayer() {
        return player;
    }

    /**
     * Resets the button for a new game.
     */
    public void reset() {
        player = ' ';
        setText("");
        setEnabled(true); // Re-enable button for new game
    }

    /**
     * Gets the row of the button.
     *
     * @return The row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column of the button.
     *
     * @return The column index.
     */
    public int getCol() {
        return col;
    }
}
