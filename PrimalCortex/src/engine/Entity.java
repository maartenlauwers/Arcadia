package engine;

/**
 * Represents everything that can be drawn on screen
 * 
 * @author Maarten Lauwers
 *
 */

import org.newdawn.slick.Image;

public abstract class Entity {

	private Image texture;
	
	public Image getTexture() {
		return texture;
	}
}
