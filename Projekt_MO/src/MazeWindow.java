import javax.swing.*;
import java.awt.*;

public class MazeWindow extends JFrame {
    private final MazePanel mazePanel;

    public MazeWindow(int rows, int cols) {
        super("Losowy Labirynt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mazePanel = new MazePanel(rows, cols);

        JButton regenerateButton = new JButton("Generuj nowy");
        regenerateButton.addActionListener(e -> mazePanel.regenerate());

        JButton solveButton = new JButton("Znajdź wyjście");
        solveButton.addActionListener(e -> mazePanel.solveMaze());

        JPanel controlPanel = new JPanel();
        controlPanel.add(regenerateButton);
        controlPanel.add(solveButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mazePanel, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
