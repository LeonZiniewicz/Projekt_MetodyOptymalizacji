import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MazePanel extends JPanel {
    private MazeGenerator maze;
    private List<Point> path = null;
    private final int cellSize = 20;
    private final int rows, cols;

    public MazePanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        regenerate();
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    public void regenerate() {
        maze = new MazeGenerator(rows, cols);
        path = null;
        repaint();
    }

    public void solveMaze() {
        MazeSolverAStar solver = new MazeSolverAStar(maze);
        path = solver.path;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rysuj labirynt
        for (int r = 0; r < maze.visited.length; r++) {
            for (int c = 0; c < maze.visited[0].length; c++) {
                int x = c * cellSize;
                int y = r * cellSize;
                if (maze.walls[r][c][0]) g.drawLine(x, y, x + cellSize, y); // top
                if (maze.walls[r][c][1]) g.drawLine(x + cellSize, y, x + cellSize, y + cellSize); // right
                if (maze.walls[r][c][2]) g.drawLine(x, y + cellSize, x + cellSize, y + cellSize); // bottom
                if (maze.walls[r][c][3]) g.drawLine(x, y, x, y + cellSize); // left
            }
        }

        // Rysuj ścieżkę
        if (path != null) {
            g.setColor(Color.RED);
            for (Point p : path) {
                int x = p.y * cellSize + cellSize / 4;
                int y = p.x * cellSize + cellSize / 4;
                g.fillOval(x, y, cellSize / 2, cellSize / 2);
            }
        }
    }
}
