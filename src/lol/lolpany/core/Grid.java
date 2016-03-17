package lol.lolpany.core;

import java.io.File;

public class Grid {
	private static final String CONFIGURATION_SEPARATOR = System.getProperty("line.separator");
	private static final int WIDTH_LINE_NUMBER = 0;
	private static final int HEIGHT_LINE_NUMBER = 1;
	private static final int TILES_START_LINE_NUMBER = 2;
	private static final char FREE_TILE_SYMBOL = '0';
	private static final char BLOCKED_TILE_SYMBOL = 'x';
	
	private boolean[][] blocked;
	
	
	public Grid(String configuration) {
		String[] configurationParts = configuration.split(CONFIGURATION_SEPARATOR);
		int width = Integer.parseInt(configurationParts[WIDTH_LINE_NUMBER]);
		int height = Integer.parseInt(configurationParts[HEIGHT_LINE_NUMBER]);
		blocked = new boolean[width][height];
		int i = 0;
		int j;
		char tileSymbol;
		while (i < height) {
			j = 0;
			String row = i + TILES_START_LINE_NUMBER >= configurationParts.length ? "" : configurationParts [i + TILES_START_LINE_NUMBER]; 
			while (j < width) {
				if (j < row.length()) {
					tileSymbol = row.charAt(j);
				} else {
					tileSymbol = FREE_TILE_SYMBOL;
				}
				if (tileSymbol == BLOCKED_TILE_SYMBOL) {
					blocked[j][i] = true;
				}
				j++;
			}
			i++;
		}
	}

	public int getWidth () {
		return blocked.length;
	}
	
	public int getHeight () {
		return blocked == null ? 0 : blocked[0].length;
	}
	
	public boolean inGrid(Tile tile) {
		return tile.getX() >= 0 && tile.getX() < getWidth() && tile.getY() >=0 && tile.getY() < getHeight();
	}
	
	public boolean isBlocked (Tile tile) {
		return blocked[tile.getX()][tile.getY()];	
	}
}