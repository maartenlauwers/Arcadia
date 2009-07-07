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
	 * Returns the height of the map
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns the width of the map
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
	 * Draws every tile on the map
	 */
	public void draw() {
		for(Tile t : tileList) {
			t.draw();
		}
	}
}
