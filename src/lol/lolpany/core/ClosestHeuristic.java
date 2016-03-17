package lol.lolpany.core;

public class ClosestHeuristic implements Heuristic {

	public double getCost (Grid grid, Tile from, Tile to) {
		double dx = to.getX() - from.getX();
		double dy = to.getY() - from.getY();

		return Math.sqrt ((dx * dx) + (dy * dy));
	}

}