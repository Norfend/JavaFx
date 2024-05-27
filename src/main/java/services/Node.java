package services;

public class Node {
    private final int x;
    private final int y;
    private final boolean walkable;
    private Node parent;
    private double givenCost, heuristicCost, finalCost;

    public Node(int x, int y, boolean walkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.givenCost = Double.MAX_VALUE;
        this.heuristicCost = Double.MAX_VALUE;
        this.finalCost = Double.MAX_VALUE;
        this.parent = null;
    }

    /**
     * Calculates the heuristic and final costs for this node.
     * <p>
     * The heuristic cost is calculated using the Manhattan distance to the specified
     * end node. The final cost is the sum of the given cost and the heuristic cost.
     * </p>
     *
     * @param endNode the destination node used to calculate the heuristic cost
     */
    public void calculateCosts(Node endNode) {
        this.heuristicCost = Math.abs(this.x - endNode.x) + Math.abs(this.y - endNode.y);
        this.finalCost = this.givenCost + this.heuristicCost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    public double getGivenCost() {
        return givenCost;
    }

    public void setGivenCost(double givenCost) {
        this.givenCost = givenCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getFinalCost() {
        return finalCost;
    }
}
