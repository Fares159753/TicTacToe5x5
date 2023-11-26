import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe5x5 implements ActionListener {

    JFrame frame;
    JPanel titlePanel, buttonPanel;
    JLabel titleLabel;
    JButton[][] buttons;
    JButton restartButton;
    boolean player1Turn;

    // Constructor
    TicTacToe5x5() {
        initializeUI();
    }

    // UI Initialization
    private void initializeUI() {
        frame = new JFrame("Tic Tac Toe 5x5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        // Title Panel
        titlePanel = new JPanel();
        titleLabel = new JLabel("Player X's turn");
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Button Panel
        initializeButtonPanel();

        // Restart Button
        initializeRestartButton();

        // Add panels to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Button Panel Initialization
    private void initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5));
        buttons = new JButton[5][5];
        player1Turn = true;

        // Create buttons and add them to the panel
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }
    }

    // Restart Button Initialization
    private void initializeRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        titlePanel.add(restartButton);
    }

    // Main method
    public static void main(String[] args) {
        new TicTacToe5x5();
    }
}
