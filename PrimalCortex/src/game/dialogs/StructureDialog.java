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

package game.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import engine.Config;
import engine.gui.Window;
import engine.gui.WindowStatus;
import engine.gui.widgets.Button;
import engine.gui.widgets.Label;
import engine.gui.widgets.Panel;
import engine.gui.widgets.PictureBox;
import engine.gui.widgets.Widget;
import engine.gui.widgets.WidgetType;
import game.Game;

public class StructureDialog implements Window {
	
	private WindowStatus status;
	
	private String id;
	private String title;	
	private String message;
	private int x;
	private int y;
	private int width;
	private int height;
	private int picHeight;
	private int picWidth;
	private Image picture;
	
	private List<Widget> widgetList;
	
	public StructureDialog(String id, String title, String message, Image picture, int picWidth, int picHeight, boolean upgradeButton) {
		
		TrueTypeFont ttf = Config.getFont1();
		
		this.status = WindowStatus.ACTIVE;
		this.id = id;
		this.title = title;
		this.message = message;
		this.width = ttf.getWidth(message) + 40;
		this.height = ttf.getHeight() + 60;
		this.x = (Config.getScreenWidth() - width)/2;
		this.y = (Config.getScreenHeight() - height)/2;
		this.picWidth = picWidth;
		this.picHeight = picHeight;
		this.picture = picture;
		
		widgetList = new ArrayList<Widget>();	
		
		
		//TODO: Add title to the dialog window
		addWidget(new Panel(id, 0, 0, width, height, true));		
		
		addWidget(new PictureBox(id, 10, 10, picWidth, picHeight, picture));
		addWidget(new Label(id, width/2 - ttf.getWidth(message)/2 + 10 + picWidth + 10, 15, message));
		
		if(upgradeButton) {
			addWidget(new Button(id, width/2 - 115, height - 30, "Upgrade", "Upgrade"));
			addWidget(new Button(id, width/2 + 7, height - 30, "Close", "Close"));	
		} else {			
			addWidget(new Button(id, width/2 - 54, height - 30, "Close", "Close"));
		}
			
	}
	
	public StructureDialog(String id, String title, String message, int width, int height, Image picture, int picWidth, int picHeight, boolean upgradeButton) {
		this.status = WindowStatus.ACTIVE;
		this.id = id;
		this.title = title;
		this.message = message;
		this.width = width;
		this.height = height;
		this.x = (Config.getScreenWidth() - width)/2;
		this.y = (Config.getScreenHeight() - height)/2;
		this.picWidth = picWidth;
		this.picHeight = picHeight;
		this.picture = picture;
		
		widgetList = new ArrayList<Widget>();	
		
		
		//TODO: Add title to the dialog window
		addWidget(new Panel(id, 0, 0, width, height, true));				
		addWidget(new PictureBox(id, 10, 10, picWidth, picHeight, picture));		
		TrueTypeFont ttf = Config.getFont1();
				
		
		ArrayList<Integer> breaks = new ArrayList<Integer>();
		
		// Parse the text
		for(int i=0; i<message.length(); i++) {
			if(message.charAt(i) == '/') {
				if(message.charAt(i+1) == 'n') {
					breaks.add(i);					
				}
			}
		}
		
		ArrayList<String> lines = new ArrayList<String>();
		int lastIndex = 0;
		for(Integer breakIndex : breaks) {
			lines.add(message.substring(lastIndex, breakIndex));
			lastIndex = breakIndex + 2;
		}
		lines.add(message.substring(lastIndex));
		
		int top = 10;
		for(String line : lines) {
			addWidget(new Label(id, 20 + picWidth, top, line));
			top += 18;
		}
					
		if(upgradeButton) {
			addWidget(new Button(id, width/2 - 115, height - 30, "Upgrade", "Upgrade"));
			addWidget(new Button(id, width/2 + 7, height - 30, "Close", "Close"));	
		} else {			
			addWidget(new Button(id, width/2 - 54, height - 30, "Close", "Close"));
		}	
	}	
	
	public void addWidget(Widget w) {
		// We assume the coordinates of the widget have to be interpreted as relative to the window coordinates.
		w.setX(w.getX() + x);
		w.setY(w.getY() + y);
		w.setId(id);
		widgetList.add(w);
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		
		// Draw the widgets
		for(Widget w : widgetList) {
			w.draw(g);
		}					
	}	
	
}