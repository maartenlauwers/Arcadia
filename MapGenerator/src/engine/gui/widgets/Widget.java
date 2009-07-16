package engine.gui.widgets;

import org.newdawn.slick.Graphics;

/**
 * Defines required methods for 'widget' elements, like buttons, labels, panels ...
 * 
 * @author Maarten Lauwers
 *
 */
public interface Widget {
	
	public void draw(Graphics g);
	public void setId(String id);
	public void setX(int x);
	public int getX();
	public void setY(int y);
	public int getY();
	
	public WidgetType getType();
}
