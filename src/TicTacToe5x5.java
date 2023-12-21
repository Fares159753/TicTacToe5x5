import javax.swing.SwingUtilities;

public class TicTacToe5x5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameController();
        });
    }
}
