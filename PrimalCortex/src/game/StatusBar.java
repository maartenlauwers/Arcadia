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
	private Label kingdomLabel;
	private Label goldLabel;
	private Label foodLabel;
	private Label trainLabel;
	private PictureBox populationPicture;
	private PictureBox foodPicture;
	private Label populationLabel;
	private Button btnBuild;
	private Button btnHelp;
	private Button btnQuit;		
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Kingdom kingdom;
	
	public StatusBar(Game game, Kingdom kingdom) {
		this.x = 0;
		this.y = 0;
		this.width = Config.getScreenWidth();
		this.height = 30;	
		this.kingdom = kingdom;
				
		window = new EmptyWindow("Statusbar", x, y, width, height);
		panel = new Panel("Statusbar", 0, 0, window.getWidth(), window.getHeight(), false);
		
		
		TrueTypeFont ttf = Config.getCurrentFont();
		
		kingdomLabel = new Label("Statusbar", 10, 5, kingdom.getName());				
		
		int offset = 10 + ttf.getWidth(kingdom.getName()) + 25;
		populationPicture = new PictureBox("Statusbar", offset, 5, 16, 16, Config.getTextureManager().getTextureByKey("population_icon"));
		populationLabel = new Label("Statusbar", offset + 20, 5, "" + kingdom.getPopulation());
		
		offset = offset + ttf.getWidth(populationLabel.getText()) + 50;
		goldLabel = new Label("Statusbar", offset , 5, "Gold: " + kingdom.getWealth());
		
		offset = offset + ttf.getWidth(goldLabel.getText()) + 25;
		foodPicture = new PictureBox("Statusbar", offset, 5, 32, 24, Config.getTextureManager().getTextureByKey("food_icon"));
		foodLabel = new Label("Statusbar", offset + 36, 5, "" + kingdom.getFood());
		
		offset = offset + ttf.getWidth(foodLabel.getText()) + 25;
		trainLabel = new Label("Statusbar", offset , 5, "");
					
		btnBuild = new Button("Statusbar", EventActionType.OTHER, window.getWidth() - 240, 5, "Build", "Build");
		btnBuild.addGuiListener(game);
		btnQuit = new Button("Statusbar", EventActionType.OTHER, window.getWidth() - 120, 5, "Quit", "Quit");
		btnQuit.addGuiListener(game);			
		
		

		
		//bottom border
		Border border = new Border("Statusbar", 0, height, width, 5, true);
		
		window.addWidget(panel);
		window.addWidget(kingdomLabel);
		window.addWidget(populationPicture);
		window.addWidget(populationLabel);
		window.addWidget(goldLabel);
		window.addWidget(foodPicture);
		window.addWidget(foodLabel);
		window.addWidget(trainLabel);
		//window.addWidget(btnWorldMap);
		//window.addWidget(btnHelp);
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
	
	public void update(String messagePopulation, String messageGold, String messageFood, String messageTraining) {
		
		TrueTypeFont ttf = Config.getCurrentFont();
		
		int offset = 10 + ttf.getWidth(kingdom.getName()) + 25;
		populationLabel.setX(offset + 20);
		populationLabel.setText(messagePopulation);
		
		offset = offset + ttf.getWidth(populationLabel.getText()) + 50;
		goldLabel.setX(offset);
		goldLabel.setText(messageGold);
		
		offset = offset + ttf.getWidth(goldLabel.getText()) + 25;
		foodLabel.setX(offset + 36);
		foodLabel.setText(messageFood);
		
		offset = offset + ttf.getWidth(foodLabel.getText()) + 25;
		trainLabel.setX(offset);
		trainLabel.setText(messageTraining);				
	}

}
