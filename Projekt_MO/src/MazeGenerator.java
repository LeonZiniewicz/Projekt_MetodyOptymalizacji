import java.util.*;

public class MazeGenerator {
    private final int rows, cols;
    public boolean[][] visited;
    public boolean[][][] walls; // 0=top, 1=right, 2=bottom, 3=left

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        visited = new boolean[rows][cols];
        walls = new boolean[rows][cols][4]; // wszystkie ściany obecne na start
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                Arrays.fill(walls[r][c], true);
        generate(0, 0); // zacznij od komórki [0][0]
    }

    private void generate(int r, int c) {
        visited[r][c] = true;
        Integer[] dirs = {0, 1, 2, 3}; // góra, prawo, dół, lewo
        Collections.shuffle(Arrays.asList(dirs));
        for (int dir : dirs) {
            int nr = r, nc = c;
            switch (dir) {
                case 0: nr--; break;
                case 1: nc++; break;
                case 2: nr++; break;
                case 3: nc--; break;
            }
            if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && !visited[nr][nc]) {
                walls[r][c][dir] = false;
                walls[nr][nc][(dir + 2) % 4] = false; // usuń przeciwną ścianę
                generate(nr, nc);
            }
        }
    }
}
