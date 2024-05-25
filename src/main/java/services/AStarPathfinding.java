package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AStarPathfinding {

    public static List<Node> findPath(Node startNode, Node endNode, Node[][] grid) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        startNode.setGivenCost(0);
        startNode.calculateCosts(endNode);
        openList.add(startNode);
        while (!openList.isEmpty()) {
            Node currentNode = openList.stream().min(Comparator.comparingDouble(Node::getFinalCost)).orElse(null);
            if (currentNode == null) break;
            if (currentNode.equals(endNode)) {
                return reconstructPath(currentNode);
            }
            openList.remove(currentNode);
            closedList.add(currentNode);
            for (Node neighbor : getNeighbors(currentNode, grid)) {
                if (closedList.contains(neighbor) || !neighbor.isWalkable()) continue;
                double tentativeGCost = currentNode.getGivenCost() + 1;
                if (tentativeGCost < neighbor.getGivenCost()) {
                    neighbor.setParent(currentNode);
                    neighbor.setGivenCost(tentativeGCost);
                    neighbor.calculateCosts(endNode);
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    private static List<Node> reconstructPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    private static List<Node> getNeighbors(Node node, Node[][] grid) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int x = node.getX() + direction[0];
            int y = node.getY() + direction[1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) {
                neighbors.add(grid[x][y]);
            }
        }
        return neighbors;
    }
}