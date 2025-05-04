import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeSolverAStar {
    private final MazeGenerator maze;
    private final int rows, cols;
    public List<Point> path = new ArrayList<>();

    private record Node(Point point, int g, int h, Node parent) {
        int f() { return g + h; }
    }

    public MazeSolverAStar(MazeGenerator maze) {
        this.maze = maze;
        this.rows = maze.visited.length;
        this.cols = maze.visited[0].length;
        solve();
    }

    private void solve() {
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        Set<Point> closed = new HashSet<>();

        Point start = new Point(0, 0);
        Point goal = new Point(rows - 1, cols - 1);

        open.add(new Node(start, 0, heuristic(start, goal), null));

        while (!open.isEmpty()) {
            Node current = open.poll();
            Point p = current.point;

            if (p.equals(goal)) {
                reconstructPath(current);
                return;
            }

            closed.add(p);

            for (int dir = 0; dir < 4; dir++) {
                if (!maze.walls[p.x][p.y][dir]) {
                    int nx = p.x, ny = p.y;
                    switch (dir) {
                        case 0 -> nx--;
                        case 1 -> ny++;
                        case 2 -> nx++;
                        case 3 -> ny--;
                    }

                    Point neighbor = new Point(nx, ny);
                    if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || closed.contains(neighbor))
                        continue;

                    int g = current.g + 1;
                    int h = heuristic(neighbor, goal);
                    open.add(new Node(neighbor, g, h, current));
                }
            }
        }
    }

    private void reconstructPath(Node node) {
        while (node != null) {
            path.add(0, node.point);
            node = node.parent;
        }
    }

    private int heuristic(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan distance
    }
}
