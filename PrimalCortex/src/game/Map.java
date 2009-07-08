package game;

import java.util.ArrayList;
import java.util.List;

public abstract class Map {

	private List<Tile> tileList;
	private int width;
	private int height;	
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;		
		
		tileList = new ArrayList<Tile>();
	}
	
	/**
	 * Returns the height (in pixels, NOT the vertical number of tiles) of the map
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns the width (in pixels, NOT the horizontal number of tiles) of the map
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Adds a new tile to the map
	 * 
	 * @param t the new tile
	 */
	public void addTile(Tile t) {
		tileList.add(t);
	}
	
	/**
	 * Returns a list of all tiles in the map
	 * 
	 * @return a list of tiles
	 */
	public List<Tile> getTileList() {
		return tileList;
	}
	
	/**
	 * Checks if a tile was clicked
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return true if a tile was clicked
	 */
	public boolean checkClicked(int mouseX, int mouseY) {		
		
		for(Tile t : tileList) {	
			if(t.isClicked(mouseX, mouseY)) {
				return true;
			}		
		}		
		
		return false;
	}
	
	/**
	 * Returns the currently selected tile
	 * 
	 * @return the selected tile
	 */
	public Tile getSelectedTile() {
		for(Tile t : tileList) {		
			if(t.isSelected()) {
				return t;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the tile north of the currently selected tile
	 * 
	 * @return tile
	 */
	public Tile getTileNorthOf(Tile tile) {
		
		int x = tile.getX();
		int y = tile.getY();		
		int height = tile.getHeight();
		
		for(Tile t : tileList) {
			if(t.getX() == x) {
				if(t.getY() == (y - height)) {
					return t;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the tile south of the currently selected tile
	 * 
	 * @return tile
	 */
	public Tile getTileSouthOf(Tile tile) {
		
		int x = tile.getX();
		int y = tile.getY();		
		int height = tile.getHeight();
		
		for(Tile t : tileList) {
			if(t.getX() == x) {
				if(t.getY() == (y + height)) {
					return t;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the tile west of the currently selected tile
	 * 
	 * @return tile
	 */
	public Tile getTileWestOf(Tile tile) {
		
		int x = tile.getX();
		int y = tile.getY();
		int width = tile.getWidth();		
		
		for(Tile t : tileList) {
			if(t.getY() == y) {
				if(t.getX() == (x - width)) {
					return t;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the tile east of the currently selected tile
	 * 
	 * @return tile
	 */
	public Tile getTileEastOf(Tile tile) {
		
		int x = tile.getX();
		int y = tile.getY();
		int width = tile.getWidth();		
		
		for(Tile t : tileList) {
			if(t.getY() == y) {
				if(t.getX() == (x + width)) {
					return t;
				}
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * Draws every tile on the map
	 */
	public void draw() {
		for(Tile t : tileList) {
			t.draw();
		}
	}
}
