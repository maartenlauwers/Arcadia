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
