import org.junit.jupiter.api.Test;
import services.AStarPathfinding;
import services.Node;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PathfindingTest {

    @Test
    void testFindPath() {
        Node[][] grid = new Node[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Node(i, j, true);
            }
        }
        grid[1][2] = new Node(1, 2, false);
        grid[2][2] = new Node(2, 2, false);
        Node startNode = grid[0][0];
        Node endNode = grid[4][4];
        List<Node> path = AStarPathfinding.findPath(startNode, endNode, grid);
        assertNotNull(path);
        assertTrue(path.size() >= 2);
        assertEquals(startNode, path.getFirst());
        assertEquals(endNode, path.getLast());
        for (Node node : path) {
            assertTrue(node.isWalkable());
        }
    }
}