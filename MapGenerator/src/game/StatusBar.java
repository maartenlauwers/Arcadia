package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import engine.Config;
import engine.gui.EmptyWindow;
import engine.gui.EventActionType;
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
	
	private Button btnGenerate;
	private Button btnSave;
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
		
		btnGenerate = new Button("Statusbar", EventActionType.OTHER, window.getWidth() - 480, 5, "Generate", "Generate");
		btnGenerate.addGuiListener(game);
		btnSave = new Button("Statusbar", EventActionType.OTHER, window.getWidth() - 360, 5, "Save", "Save");
		btnSave.addGuiListener(game);
		btnBuild = new Button("Statusbar", EventActionType.OTHER, window.getWidth() - 240, 5, "Build", "Build");
		btnBuild.addGuiListener(game);
		btnQuit = new Button("Statusbar", EventActionType.OTHER, window.getWidth() - 120, 5, "Quit", "Quit");
		btnQuit.addGuiListener(game);			
				
		//bottom border
		Border border = new Border("Statusbar", 0, height, width, 5, true);
		
		window.addWidget(panel);
		window.addWidget(btnGenerate);
		window.addWidget(btnSave);
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
}
