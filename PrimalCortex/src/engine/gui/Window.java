package engine.gui;

import org.newdawn.slick.Graphics;

import engine.gui.widgets.Widget;

/**
 * Defines required methods for a gui window (which in turn contains widgets)
 * 
 * @author Maarten Lauwers
 *
 */
public interface Window {

	public int getX();
	public void setX(int x);
	public int getY();
	public void setY(int y);
	public int getWidth();
	public void setWidth(int width);
	public int getHeight();
	public void setHeight(int height);
	
	
	public void draw(Graphics g);
	public void addWidget(Widget w);
	public WindowStatus getStatus();
	public void setStatus(WindowStatus status);
	public void isMouseOver(int mouseX, int mouseY);
	public boolean isMouseClicked(int mouseX, int mouseY);
	public String getId();
	
}
