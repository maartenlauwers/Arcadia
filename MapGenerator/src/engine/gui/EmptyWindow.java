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

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;

import engine.Core;
import engine.gui.widgets.Button;
import engine.gui.widgets.Widget;
import engine.gui.widgets.WidgetType;
import game.Game;

public class EmptyWindow implements Window {
	
	private WindowStatus status;
	
	private String id;	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private List<Widget> widgetList;
	
	public EmptyWindow(String id, int x, int y, int width, int height) {
		this.status = WindowStatus.ACTIVE;
		this.id = id;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		
		widgetList = new ArrayList<Widget>();						
	}
	
	public void addWidget(Widget w) {
		// We assume the coordinates of the widget have to be interpreted as relative to the window coordinates.
		w.setX(w.getX() + x);
		w.setY(w.getY() + y);
		w.setId(id);
		widgetList.add(w);
	}
	
	public List<Widget> getWidgets() {
		return widgetList;
	}
	
	public void addGuiListener(Game game) {
		for(Widget w : widgetList) {
			if (w.getType() == WidgetType.BUTTON) {
				Button b = (Button)w;
				b.addGuiListener(game);
			}
		}	
	}	
	
	public String getId() {
		return id;
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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public WindowStatus getStatus() {
		return status;
	}
	
	public void setStatus(WindowStatus status) {
		this.status = status;
	}	
	
	public boolean isMouseClicked(int mouseX, int mouseY) {
		
		boolean clicked = false;
		
		for(Widget w : widgetList) {
			if (w.getType() == WidgetType.BUTTON) {				
				Button b = (Button)w;
				if(b.gotClicked(mouseX, mouseY)) {
					clicked = true;
					break;
				}							
			}
		}
		
		return clicked;
	}
	
	public void isMouseOver(int mouseX, int mouseY) {
		for(Widget w : widgetList) {
			if (w.getType() == WidgetType.BUTTON) {
				Button b = (Button)w;
				b.isMouseOver(mouseX, mouseY);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		
		GL11.glClearColor(0.5f,0.1f,0.1f,0.5f);								
		
		// Draw the widgets
		for(Widget w : widgetList) {
			w.draw(g);
		}
		
		GL11.glClearColor(0.1f,0.1f,0.1f,0);	
	}
	
}