/**
 * Represents a tile on a map. The tile has info about its (absolute) position, its texture and knows
 * whether it is selected or not.
 * 
 * @author Maarten Lauwers
 */


package game.tile;

import engine.Config;
import org.newdawn.slick.TrueTypeFont;

import game.structures.EmptyStructure;
import game.structures.Structure;
import game.structures.StructureType;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Tile {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Image texture;
	private TileType tileType;
	private Structure structure;
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
		//role = new EmptyRole();
		structure = new EmptyStructure();
		
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
	
	public void setTexture(Image texture) {
		this.texture = texture;
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
	
	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	
	public Structure getStructure() {
		return structure;
	}

	/**
	 * Draws the texture of the current tile on the screen
	 */
	public void draw(Graphics g) {		
		texture.draw(x, y, width, height);
		structure.getTexture().draw(x,y);
		
		// Show the structure's level
		if(! structure.getType().equals(StructureType.GRASS)) {
			
			TrueTypeFont ttf = Config.getFont2();			
			
			g.setColor(Color.orange);
			g.fillRect(x + (width - 16), y + (height - 12), 16, 12);
			
			g.setColor(Color.white);			
			
			// If the level text consists of 2 numbers, we have to edit its position
			if(structure.getCurrentLevel() >= 10) {
				ttf.drawString(x + (width - 15), y + (height - 13), "" + structure.getCurrentLevel());		
			} else {
				ttf.drawString(x + (width - 10), y + (height - 13), "" + structure.getCurrentLevel());
			}
					
		}
		
	}
}
