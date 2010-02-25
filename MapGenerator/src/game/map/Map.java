/*
 *   Copyright (C) 2010 Maarten Lauwers
 *
 *   This program is free software; you can redistribute it and/or modify it under the terms of the 
 *   GNU General Public License as published by the Free Software Foundation; either version 2 of the License, 
 *   or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 *   without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *   See the GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along with this program; 
 *   if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 *	 Email: maartenlauwers007@gmail.com 
 */

package game.map;

import game.Tile;

import java.util.ArrayList;
import java.util.List;

public abstract class Map {

	private List<Tile> tileList;
	private int pixelWidth;
	private int pixelHeight;	
	private int nrTilesHorizontal;
	private int nrTilesVertical;
	
	public Map(int pixelWidth, int pixelHeight, int nrTilesHorizontal, int nrTilesVertical) {
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;		
		this.nrTilesHorizontal = nrTilesHorizontal;
		this.nrTilesVertical = nrTilesVertical;
		
		tileList = new ArrayList<Tile>();
	}
	
	/**
	 * Returns the height (in pixels, NOT the vertical number of tiles) of the map
	 * 
	 * @return height
	 */
	public int getHeight() {
		return pixelHeight;
	}
	
	/**
	 * Returns the width (in pixels, NOT the horizontal number of tiles) of the map
	 * 
	 * @return width
	 */
	public int getWidth() {
		return pixelWidth;
	}
	
	/**
	 * Returns the amount of tiles in a single row
	 * 
	 * @return nr of tiles in a row
	 */
	public int getNrTilesHorizontal() {
		return nrTilesHorizontal;
	}
	
	/**
	 * Returns the amount of tiles in a single column
	 * 
	 * @return nr of tiles in a column
	 */
	public int getNrTilesVertical() {
		return nrTilesVertical;
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
	
	/**
	 * Draws only the tiles that are visible on the screen
	 */
	
	public void draw(int x, int y, int screenWidth, int screenHeight) {
		for(Tile t : tileList) {
			if(t.getX() >= x && t.getX() <= (x + screenWidth - t.getWidth()) ) {
				if(t.getY() >= y && t.getY() <= (y + screenHeight - t.getHeight()) ) {
					t.draw();
				}
				
			}
		}
	}
}
