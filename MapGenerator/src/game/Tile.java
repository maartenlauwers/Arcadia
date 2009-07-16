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
	private Image texture;
	//private Role role;	
	private TileState currentState;
	
	private boolean selected;				
	
	public Tile(Image texture, int x, int y, int width, int height) {
		this.texture = texture;
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
	
	public void setTexture(Image texture) {
		this.texture = texture;
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
		
		if(mouseX >= x && mouseX < (x + 64)) {
			if(mouseY >= y && mouseY < (y + 64)) {
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
		texture.draw(x, y);		
	}
}
