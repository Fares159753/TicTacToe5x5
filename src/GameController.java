import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    private JFrame frame;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton restartButton;
    private boolean player1Turn;
    private GameBoard gameBoard;
    private boolean gameOver;
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

 // Method to handle a player's turn (modified for 5 symbols)
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


    private boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 10; i++) {
            if (gameBoard.checkRowForWin(i)) {
                System.out.println("Row win: " + i);
                handleGameEnd(player1Turn ? "Player X wins!" : "Player O wins!");
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 10; j++) {
            if (gameBoard.checkColumnForWin(j)) {
                System.out.println("Column win: " + j);
                handleGameEnd(player1Turn ? "Player X wins!" : "Player O wins!");
                return true;
            }
        }

        // Check diagonals
        if (gameBoard.checkDiagonalForWin() || gameBoard.checkReverseDiagonalForWin()) {
            System.out.println("Diagonal win");
            handleGameEnd(player1Turn ? "Player X wins!" : "Player O wins!");
            return true;
        }

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
        // Reset button text and enable buttons using the GameBoard methods
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameBoard.setButtonText(i, j, ""); // Set the text to an empty string
                gameBoard.enableButton(i, j);
            }
        }

        // Reset title label and player turn
        titleLabel.setText("Player X's turn");
        player1Turn = true;
    }


    private void handleGameEnd(String result) {
        titleLabel.setText(result);
        JOptionPane.showMessageDialog(frame, result, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        // Disable all buttons when the game ends
        disableButtons();

        // Set the game over flag to true
        gameOver = true;
    }



 // Method to disable all buttons on the game board
    private void disableButtons() {
        // Disable buttons using the GameBoard method
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameBoard.disableButton(i, j);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameController();
        });
    }
}
