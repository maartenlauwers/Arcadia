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
