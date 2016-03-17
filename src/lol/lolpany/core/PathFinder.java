package lol.lolpany.core;

import java.util.List;

public interface PathFinder {
	public List<Tile> findPath (Tile from, Tile to);
}