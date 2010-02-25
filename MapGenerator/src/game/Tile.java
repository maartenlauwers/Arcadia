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

/**
 * Represents a tile on a map. The tile has info about its (absolute) position, its texture and knows
 * whether it is selected or not.
 * 
 * @author Maarten Lauwers
 */


package game;

import org.newdawn.slick.Image;


public class Tile {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private TileType tileType;
	private Image texture;	
	private TileState currentState;
	
	private boolean selected;				
	
	public Tile(Image texture, TileType tileType, int x, int y, int width, int height) {
		this.texture = texture;
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.selected = false;				
		
		setState(new UnselectedTileState());	
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setTexture(Image texture, TileType tileType) {
		this.texture = texture;
		this.tileType = tileType;
	}
	
	public TileType getTileType() {
		return tileType;
	}
	
	public void setState(TileState tileState) {
		this.currentState = tileState;
	}
	/**
	 * Checks if the current tile was clicked.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return true if clicked
	 */
	public boolean isClicked(int mouseX, int mouseY) {				
		
		if(mouseX >= x && mouseX < (x + width)) {
			if(mouseY >= y && mouseY < (y + height)) {
				System.out.println("Clicked tile at " + x + ", " + y);
				return true;
			}
		}
		
		return false;
	}	
	
	/**
	 * Checks whether the current tile is selected
	 * 
	 * @return true if selected
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Sets the selected status of this tile
	 * 
	 * @param select
	 */
	public void setSelected(boolean selected) {				
		this.selected = selected;						
	}

	/**
	 * Draws the texture of the current tile on the screen
	 */
	public void draw() {		
		texture.draw(x, y, width, height);		
	}
}
