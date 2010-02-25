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

package engine.gui.widgets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import engine.Config;

public class Border implements Widget {

	private String id;
	private int x;
	private int y;
	private int width;
	private int height; 
	private boolean isHorizontal;
	
	public Border(String id, int x, int y, int width, int height, boolean isHorizontal) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isHorizontal = isHorizontal;				
	}	

	@Override
	public WidgetType getType() {
		return WidgetType.BORDER;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setId(String id) {
		this.id = id;		
	}

	@Override
	public void setX(int x) {
		this.x = x;		
	}

	@Override
	public void setY(int y) {
		this.y = y;
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(isHorizontal) {
			Image texture_border_horizontal = Config.getTextureManager().getTextureByKey("border_horizontal"); 
			int horizontalWidth = texture_border_horizontal.getWidth();
			int horizontalHeight = texture_border_horizontal.getHeight();
			
			int i = 0;
			for(i = 0; i< width - horizontalWidth; i += horizontalWidth) {				
				texture_border_horizontal.draw(i, y, horizontalWidth, horizontalHeight);
			}			
			int gap = x + width - i;	
			texture_border_horizontal.draw(i, y, gap, horizontalHeight);			
		} else {
			Image texture_border_vertical = Config.getTextureManager().getTextureByKey("border_vertical"); 
			int horizontalWidth = texture_border_vertical.getWidth();
			int horizontalHeight = texture_border_vertical.getHeight();
			
			int i = 0;
			for(i = 0; i< height - horizontalHeight; i += horizontalHeight) {				
				texture_border_vertical.draw(x, i, horizontalWidth, horizontalHeight);
			}			
			int gap = x + width - i;	
			texture_border_vertical.draw(x, i, horizontalWidth, gap);	
		}
		
		
	}
}