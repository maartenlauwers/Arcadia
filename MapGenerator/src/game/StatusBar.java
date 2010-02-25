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
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import engine.Config;
import engine.gui.EmptyWindow;
import engine.gui.Window;
import engine.gui.WindowStatus;
import engine.gui.widgets.Border;
import engine.gui.widgets.Button;
import engine.gui.widgets.Label;
import engine.gui.widgets.Panel;
import engine.gui.widgets.PictureBox;
import engine.gui.widgets.Widget;

public class StatusBar {
		
	private Window window;
	private Panel panel;
	
	private Label lblViewport;
	private Label lblX;
	private Label lblY;
	
	private Button btnGenerate;
	private Button btnSave;
	private Button btnLoad;
	private Button btnBuild;	
	private Button btnQuit;		
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Kingdom kingdom;
	
	public StatusBar(Game game) {
		this.x = 0;
		this.y = 0;
		this.width = Config.getScreenWidth();
		this.height = 30;			
				
		window = new EmptyWindow("Statusbar", x, y, width, height);
		panel = new Panel("Statusbar", 0, 0, window.getWidth(), window.getHeight(), false);
		
		
		TrueTypeFont ttf = Config.getCurrentFont();				
		
		int offset = 10;
		lblViewport = new Label("Statusbar", offset, 5, "Viewport coördinates: ");
		
		offset += ttf.getWidth("Viewport coördinates") + 25;
		lblX = new Label("Statusbar", offset, 5, "X: " + game.getWorldMap().getOffsetX());
		
		offset += ttf.getWidth("X: " + game.getWorldMap().getOffsetX()) + 25;
		lblY = new Label("Statusbar", offset, 5, "Y: " + game.getWorldMap().getOffsetY());
		
		btnGenerate = new Button("Statusbar", window.getWidth() - 600, 5, "Generate", "Generate");
		btnGenerate.addGuiListener(game);
		btnSave = new Button("Statusbar", window.getWidth() - 480, 5, "Save", "Save");
		btnSave.addGuiListener(game);
		btnLoad = new Button("Statusbar", window.getWidth() - 360, 5, "Load", "Load");
		btnLoad.addGuiListener(game);
		btnBuild = new Button("Statusbar", window.getWidth() - 240, 5, "Build", "Build");
		btnBuild.addGuiListener(game);
		btnQuit = new Button("Statusbar", window.getWidth() - 120, 5, "Quit", "Quit");
		btnQuit.addGuiListener(game);			
				
		//bottom border
		Border border = new Border("Statusbar", 0, height, width, 5, true);
		
		window.addWidget(panel);
		window.addWidget(lblViewport);
		window.addWidget(lblX);
		window.addWidget(lblY);
		window.addWidget(btnGenerate);
		window.addWidget(btnSave);
		window.addWidget(btnLoad);
		window.addWidget(btnBuild);
		window.addWidget(btnQuit);
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
	
	public void updateStatusBar(Game game) {
		
		TrueTypeFont ttf = Config.getCurrentFont();		
		
		int offset = 10;
		lblViewport = new Label("Statusbar", offset, 5, "Viewport coördinates: ");
		
		offset += ttf.getWidth("Viewport coördinates") + 25;
		lblX.setX(offset);
		lblX.setText("X: " + game.getWorldMap().getOffsetX());		
		
		offset += ttf.getWidth("X: " + game.getWorldMap().getOffsetX()) + 25;
		lblY.setX(offset);
		lblY.setText("Y: " + game.getWorldMap().getOffsetY());
	}
}
