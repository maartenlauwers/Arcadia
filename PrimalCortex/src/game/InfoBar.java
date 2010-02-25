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

package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import engine.Config;
import engine.gui.EmptyWindow;
import engine.gui.Window;
import engine.gui.widgets.Border;
import engine.gui.widgets.Button;
import engine.gui.widgets.Panel;
import engine.gui.widgets.ProgressBar;

public class InfoBar {
		
	private Window window;
	private Panel panel;
	
	private ProgressBar structureBar;	
	private Button mapButton;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Kingdom kingdom;
	
	public InfoBar(Game game, Kingdom kingdom) {
		this.x = 0;
		this.y = Config.getScreenHeight() - 30;
		this.width = Config.getScreenWidth();
		this.height = 30;	
		this.kingdom = kingdom;
				
		window = new EmptyWindow("InfoBar", x, y, width, height);
		panel = new Panel("InfoBar", 0, 0, window.getWidth(), window.getHeight(), false);
		
		
		TrueTypeFont ttf = Config.getFont1();						
		structureBar = new ProgressBar("InfoBar", "", 10, 5, 300, 20, Config.getTextureManager().getTextureByKey("progressbar"), 0);				

		mapButton = new Button("InfoBar", Config.getScreenWidth() - 240, 5, "World map", "WorldMap");
		mapButton.addGuiListener(game);
		
		Button infoButton = new Button("InfoBar", Config.getScreenWidth() - 120, 5, "Town info", "TownInfo");
		infoButton.addGuiListener(game);
		
		
		//top border
		Border border = new Border("InfoBar", 0, -5, width, 5, true);
		
		window.addWidget(panel);		
		window.addWidget(structureBar);
		window.addWidget(mapButton);
		window.addWidget(infoButton);
		window.addWidget(border);
	}	
	
	public Window getWindow() {
		return window;
	}
		
	public void draw(Graphics g) {
		window.draw(g);		
	}
	
	public void isMouseOver(int mouseX, int mouseY) {
		window.isMouseOver(mouseX, mouseY);				
	}
	
	public boolean isMouseClicked(int mouseX, int mouseY) {
		return window.isMouseClicked(mouseX, mouseY);
	}
	
	public void updateMapButton(boolean onLocalMap) {
		if(onLocalMap) {
			mapButton.setText("World map");
		} else {
			mapButton.setText("Town view");
		}
	}
	
	public void update(int structureProgress, String structureText) {
		
		TrueTypeFont ttf = Config.getFont1();
		
		structureBar.setProgress(structureProgress);
		structureBar.setText(structureText);
	}

}