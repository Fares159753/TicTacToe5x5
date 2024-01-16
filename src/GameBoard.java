import javax.swing.*;
import java.awt.*;

public class GameBoard {
    private JButton[][] buttons;
    private JPanel buttonPanel;

    public GameBoard(GameController controller) {
        initializeButtonPanel(controller);
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    private void initializeButtonPanel(GameController controller) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 10)); // Change grid size to 10x10
        buttons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 20)); // Adjust font size if needed
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(controller);
                buttonPanel.add(buttons[i][j]);
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    // Additional method to set the text of a button at a specific position
    public void setButtonText(int row, int col, String text) {
        buttons[row][col].setText(text);
    }

    // Additional method to enable a button at a specific position
    public void enableButton(int row, int col) {
        buttons[row][col].setEnabled(true);
    }

    // Additional method to disable a button at a specific position
    public void disableButton(int row, int col) {
        buttons[row][col].setEnabled(false);
    }

 // Helper method to check a row for a win (modified for 5 symbols)
    public boolean checkRowForWin(int row) {
        for (int j = 0; j <= 10 - 5; j++) {
            String symbol = buttons[row][j].getText();
            if (!symbol.isEmpty()) {
                boolean win = true;
                for (int k = 1; k < 5; k++) {
                    if (!buttons[row][j + k].getText().equals(symbol)) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }
 // Helper method to check a column for a win (modified for 5 symbols)
    public boolean checkColumnForWin(int col) {
        for (int i = 0; i <= 10 - 5; i++) {
            String symbol = buttons[i][col].getText();
            if (!symbol.isEmpty()) {
                boolean win = true;
                for (int k = 1; k < 5; k++) {
                    if (!buttons[i + k][col].getText().equals(symbol)) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }

 // Helper method to check the main diagonal for a win (modified for 5 symbols)
    public boolean checkDiagonalForWin() {
        for (int i = 0; i <= 10 - 5; i++) {
            for (int j = 0; j <= 10 - 5; j++) {
                String symbol = buttons[i][j].getText();
                if (!symbol.isEmpty()) {
                    boolean win = true;
                    for (int k = 1; k < 5; k++) {
                        if (!buttons[i + k][j + k].getText().equals(symbol)) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


 // Helper method to check the reverse diagonal for a win (modified for 5 symbols)
    public boolean checkReverseDiagonalForWin() {
        for (int i = 0; i <= 10 - 5; i++) {
            for (int j = 4; j < 10; j++) {
                String symbol = buttons[i][j].getText();
                if (!symbol.isEmpty()) {
                    boolean win = true;
                    for (int k = 1; k < 5; k++) {
                        if (!buttons[i + k][j - k].getText().equals(symbol)) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
