package lol.lolpany.core;

public interface Heuristic {
	public double getCost (Grid grid, Tile from, Tile to);
}