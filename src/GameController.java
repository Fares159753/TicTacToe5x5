import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The main controller class for the Tic Tac Toe game
public class GameController implements ActionListener {
    private JFrame frame;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton restartButton;
    private boolean player1Turn;
    private GameBoard gameBoard;

    // Constructor to initialize the UI
    public GameController() {
        initializeUI();
    }

    // Method to initialize the user interface
    private void initializeUI() {
        frame = new JFrame("Tic Tac Toe 5x5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titleLabel = new JLabel("Player X's turn");
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Initialize the game board
        gameBoard = new GameBoard(this);
        frame.add(gameBoard.getButtonPanel(), BorderLayout.CENTER);

        // Initialize the restart button
        initializeRestartButton();

        frame.setVisible(true);
    }

    // Method to initialize the restart button
    private void initializeRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        titlePanel.add(restartButton);
    }

    // ActionListener method for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Check if the clicked button is empty
        if (clickedButton.getText().equals("")) {
            performPlayerTurn(clickedButton);

            // Check for a win
            if (checkForWin()) {
                String winner = player1Turn ? "Player X" : "Player O";
                handleGameEnd(winner);
                disableButtons();
            }
            // Check for a tie
            else if (checkForTie()) {
                handleGameEnd("It's a tie!");
                disableButtons();
            } else {
                // Switch turns
                player1Turn = !player1Turn;
            }
        }
    }

    // Method to handle a player's turn
    private void performPlayerTurn(JButton clickedButton) {
        if (player1Turn) {
            clickedButton.setText("X");
            clickedButton.setForeground(Color.RED);
            titleLabel.setText("Player O's turn");
        } else {
            clickedButton.setText("O");
            clickedButton.setForeground(Color.BLUE);
            titleLabel.setText("Player X's turn");
        }
    }

    // Method to check for a win
    private boolean checkForWin() {
        // Check rows, columns, and diagonals...
        // (Same as in the original code)
        return false;
    }

    // Method to check for a tie
    private boolean checkForTie() {
        // Check for a tie...
        // (Same as in the original code)
        return false;
    }

    // Method to restart the game
    private void restartGame() {
        JButton[][] buttons = gameBoard.getButtons();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }

        titleLabel.setText("Player X's turn");
        player1Turn = true;
    }

    // Method to handle the end of the game
    private void handleGameEnd(String result) {
        titleLabel.setText(result);
        JOptionPane.showMessageDialog(frame, result, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to disable all buttons on the game board
    private void disableButtons() {
        JButton[][] buttons = gameBoard.getButtons();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
