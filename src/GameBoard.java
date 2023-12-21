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
        buttonPanel.setLayout(new GridLayout(5, 5));
        buttons = new JButton[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(controller);
                buttonPanel.add(buttons[i][j]);
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }
}
