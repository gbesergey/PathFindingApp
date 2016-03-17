package lol.lolpany.core;

import java.util.*;

public class AStarPathFinder implements PathFinder {



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static final int NEIGHBOR_DISTANCE = 1;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Grid grid;
    private Tile to;
    private Heuristic heuristic;
    private OpenSet open;
    private Set<Node> closed;
    




























    public AStarPathFinder(Grid grid, Heuristic heuristic, Tile to) {
        this.heuristic = heuristic;
        this.grid = grid;
        this.to = to;
        this.open = new OpenSet();
        this.closed = new HashSet<>();
    }























    public List<Tile> findPath(Tile from, Tile to) {

        if (grid.isBlocked(from) || grid.isBlocked(to)) {
            return null;
        }
        
        
        closed.clear();
        open.clear();


        open.add(new Node(from.getX(), from.getY()));

        Node current = null;
        
        while (!open.isEmpty() && !to.equals(current)) {


            current = open.poll();
            
            closed.add(current);

            List<Node> neighbors = current.getNeighbors(grid);
            
            neighbors.removeAll(closed);
            
            open.addAll(neighbors);
     
        }
        
        return getPath(current);


//            // them as next steps
//
//            for (int x = -1; x < 2; x++) {
//                for (int y = -1; y < 2; y++) {
//                    // not a neighbour, its the current tile
//
//                    if ((x == 0) && (y == 0)) {
//                        continue;
//                    }
//
//                    // determine the location of the neighbour and evaluate it
//
//                    int xp = x + current.x;
//                    int yp = y + current.y;
//
//                    if (isValidLocation(mover, sx, sy, xp, yp)) {
//                        // the cost to get to this node is cost the current plus the movement
//
//                        // cost to reach this node. Note that the heursitic value is only used
//
//                        // in the sorted open list
//
//                        float nextStepCost = current.cost + getMovementCost(mover, current.x,
//                                current.y, xp, yp);
//                        Node neighbour = nodes[xp][yp];
//                        grid.pathFinderVisited(xp, yp);
//
//                        // if the new cost we've determined for this node is lower than
//
//                        // it has been previously makes sure the node hasn'e've
//                        // determined that there might have been a better path to get to
//
//                        // this node so it needs to be re-evaluated
//
//                        if (nextStepCost < neighbour.cost) {
//                            if (inOpenList(neighbour)) {
//                                removeFromOpen(neighbour);
//                            }
//                            if (inClosedList(neighbour)) {
//                                removeFromClosed(neighbour);
//                            }
//                        }
//
//                        // if the node hasn't already been processed and discarded then
//
//                        // reset it's cost to our current cost and add it as a next possible
//
//                        // step (i.e. to the open list)
//
//                        if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
//                            neighbour.cost = nextStepCost;
//                            neighbour.heuristic = getHeuristicCost(mover, xp, yp, tx, ty);
//                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
//                            addToOpen(neighbour);
//                        }
//                    }
//                }
//            }
        }

    private List<Tile> getPath(Node end) {
        List<Tile> result = new LinkedList<>();
        Node current = end;
        result.add(current);
        while (current.parent != null) {
            result.add(current.parent);
            current = current.parent;
        }
        Collections.reverse(result);
        return result;
    }
    






    private class OpenSet {
        private Queue<Node> open;
        private Map<Node, Double> costs;

        public OpenSet() {
            open = new PriorityQueue<>(new NodeComparator(grid, to));
            costs = new HashMap<>();
        }
        
        public void clear() {
            open.clear();
            costs.clear();
        }
        
        public void add(Node node) {
            open.remove(node);
            open.add(node);
            costs.put(node, node.cost);
        }
        
        public boolean isEmpty() {
            return open.isEmpty();
        }
        
        public Node peek() {
            return open.peek();
        }
        
        public Node poll() {
            Node result = open.poll();
//            costs.remove(result);
            return result;
        }
        
        public void addAll(List<Node> nodes) {
            for (Node node : nodes) {
                if (!open.contains(node) || node.cost < costs.get(node)) {
                    if (!open.contains(node)) {
                        add(node);
                    }
                }
//                if (costs.get(node) > node.cost) {
//                    open.add(node);
//                    costs.put(node, node.cost);
//                }
            }
        }
    }



















    private class Node extends Tile {
        
        private double cost;
        private Node parent;


        public Node(int x, int y) {
            super(x, y);
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
        
        private Node getNeighbor(Neighbor neighbor, Grid grid) {
            Node result = null;
            Node neighborNode = new Node(this.getX() + neighbor.getX(), this.getY() + neighbor.getY());
            if (grid.inGrid(neighborNode))
            {
                neighborNode.setCost(this.cost + NEIGHBOR_DISTANCE);
                neighborNode.setParent(this);
                result = neighborNode;
            }
            return result;
        }
        
        public List<Node> getNeighbors(Grid grid) {
            List<Node> result = new ArrayList<>(Neighbor.values().length);
            for (Neighbor neighbor :Neighbor.values()) {
                Node neighborNode = getNeighbor(neighbor, grid);
                if (neighborNode !=null && !grid.isBlocked(neighborNode)) {
                    result.add(neighborNode);
                }
            }
            return result;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        private Grid grid;
        private Tile to;

        public NodeComparator(Grid grid, Tile to) {
            this.grid = grid;
            this.to = to;
        }


        @Override
        public int compare(Node node, Node otherNode) {
            double estimation = node.cost + heuristic.getCost(grid, node, to);
            double otherEstimation = otherNode.cost + heuristic.getCost(grid, otherNode, to);

            if (estimation < otherEstimation)
            {
                return -1;
            } else if (estimation > otherEstimation)
            {
                return 1;
            } else
            {
                return 0;
            }
        }
    }

    private enum Neighbor
    {
        UPPER(0, -1),
        RIGHT(1, 0),
        BOTTOM(0, 1),
        LEFT(-1, 0);

        private int x;
        private int y;

        Neighbor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}