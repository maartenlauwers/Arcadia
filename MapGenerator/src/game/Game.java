package game;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import org.newdawn.slick.Graphics;

import engine.Config;
import engine.Core;
import engine.DualMap;
import engine.TextureManager;
import engine.gui.DialogWindow;
import engine.gui.EmptyWindow;
import engine.gui.EventActionType;
import engine.gui.GuiEvent;
import engine.gui.GuiListener;
import engine.gui.PictureDialogWindow;
import engine.gui.QuestionWindow;
import engine.gui.Window;
import engine.gui.widgets.Button;
import engine.gui.widgets.Label;
import engine.gui.widgets.Panel;
import engine.gui.widgets.PictureBox;
import game.map.LocalMap;
import game.map.Map;
import game.map.WorldMap;

public class Game implements GuiListener {

	private Core engine;	
	private TextureManager textureManager;
			
	private Map worldMap;		
	
	private StatusBar statusBar;
	private InfoBar infoBar;	
				
	private DualMap windowList;
	
	public Game(Core engine) {
		this.engine = engine;		
		this.textureManager = Config.getTextureManager();
				
		windowList = new DualMap();
				    	
    	generateMap(100, 100, 64, 64);    	    	      	        	    		
	}		
	
	public void generateMap(int tilesHorizontal, int tilesVertical, int tileWidth, int tileHeight) {
		Random random = new Random();
    	worldMap = new WorldMap(Config.getScreenWidth(), Config.getScreenHeight(), tilesHorizontal, tilesVertical, tileWidth, tileHeight);    	
    	
    	// Aim = 30% water, 20% resources, 50% build space
    	// Note, the algorithm below doesn't guarantee this.
    	for(int i=0; i<worldMap.getNrTilesVertical(); i++) {
    		for (int j=0; j<worldMap.getNrTilesHorizontal(); j++) {    			
    			
    			int r = random.nextInt(10);
    			System.out.println("random: " + r);
    			if(r == 0 || r == 1 || r == 2) {
    				// water
    				worldMap.addTile(new Tile(textureManager.getTextureByKey("water"), j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    			} else if(r == 3 || r == 4) {
    				// resources
    				int resource = random.nextInt(2);
    				System.out.println("resource: " + resource);
    				if(resource == 0) {
    					//goldmine
    					worldMap.addTile(new Tile(textureManager.getTextureByKey("dirt"), j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    				} else {
    					//farm (forest?)
    					worldMap.addTile(new Tile(textureManager.getTextureByKey("farm_active"), j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    				}
    				
    			} else {
    				// build space
    				worldMap.addTile(new Tile(textureManager.getTextureByKey("grass"), j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    			}
    		} 
    	}
	}
	
	public void saveMap() {
		
	}
	
	public WorldMap getWorldMap() {
		return (WorldMap)worldMap;
	}	
		
	public void createStatusBar() {
		this.statusBar = new StatusBar(this);		
	}
	
	public void createInfoBar() {
		this.infoBar = new InfoBar(this);
	}

	/**
	 * Creates the build menu
	 */
	public void createBuildMenu() {
		Window newWindow = new EmptyWindow("BuildMenu", Config.getScreenWidth() - 300, 30, 300, 400);
		newWindow.addWidget(new Panel("BuildMenu", 0, 0, 300, 500, true));
		
		newWindow.addWidget(new Label("BuildMenu", 10, 10, "Available tiles"));
			
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, 40, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		int heightOffset = 50;
		
		// Grass
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("grass")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Grass"));		
		Button buildGrassButton = new Button("BuildMenu", EventActionType.OTHER, 80, heightOffset + 25, "Create", "BuildGrass");
		buildGrassButton.addGuiListener(this);
		newWindow.addWidget(buildGrassButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		
		// Dirt
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("dirt")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Dirt"));		
		Button buildDirtButton = new Button("BuildMenu", EventActionType.OTHER, 80, heightOffset + 25, "Create", "BuildDirt");
		buildDirtButton.addGuiListener(this);
		newWindow.addWidget(buildDirtButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
					
		// Water
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("water")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Water"));		
		Button buildWaterButton = new Button("BuildMenu", EventActionType.OTHER, 80, heightOffset + 25, "Create", "BuildWater");
		buildWaterButton.addGuiListener(this);
		newWindow.addWidget(buildWaterButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
				
		// Forest
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("grass")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Forest"));				
		Button buildForestButton = new Button("BuildMenu", EventActionType.CLOSE, 80, heightOffset + 25, "Create", "BuildForest");
		buildForestButton.addGuiListener(this);
		newWindow.addWidget(buildForestButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		// Mine
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("grass")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Mine"));	
		Button buildMineButton = new Button("BuildMenu", EventActionType.CLOSE, 80, heightOffset + 25, "Create", "BuildMine");
		buildMineButton.addGuiListener(this);
		newWindow.addWidget(buildMineButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));			
		
		
		Button closeButton = new Button("BuildMenu", EventActionType.CLOSE, 10, 470, "Close", "CloseBuildMenu");
		closeButton.addGuiListener(this);
		newWindow.addWidget(closeButton);
		
		windowList.add("BuildMenu", newWindow);
	}		
	
	/**
	 * Handles all received GUI events
	 */
	public void eventReceived(GuiEvent event) { 		
		String winId = event.getParentId();				
		
		
		if(winId.equals("IntroDialog")) {
			if(event.getObjectId().equals("Ok")) {
				windowList.remove("IntroDialog");
			}
		}
		/*
		 * Statusbar events
		 */
		if(winId.equals("Statusbar")) {
			if(event.getObjectId().equals("Quit")) {
				
				QuestionWindow dw = new QuestionWindow("QuitDialog", "Are you sure?", "Do you really want to quit?");
				dw.addGuiListener(this);
				windowList.add("QuitDialog", dw);				
			}
			
			if(event.getObjectId().equals("Build")) {
				
				createBuildMenu();						
			}
			
			if(event.getObjectId().equals("Generate")) {
				
				generateMap(100, 100, 64, 64);
			}
			
			if(event.getObjectId().equals("Save")) {
				
				saveMap();
			}
		}
		
		if(winId.equals("InfoBar")) {
			/*
			if(event.getObjectId().equals("TownInfo")) {
								
			}
			if(event.getObjectId().equals("WorldMap")) {
				if(localMapActive) {
					localMapActive = false;	
				} else {
					localMapActive = true;
				}										
			}
			*/
		}
		
		if(winId.equals("BuildMenu")) {
			if(event.getObjectId().equals("CloseBuildMenu")) {				
				windowList.remove("BuildMenu");				
			} else {			
				if(event.getObjectId().equals("BuildGrass")) {				
					//inputController.buildHovel();											
				}
				
				if(event.getObjectId().equals("BuildDirt")) {				
					//inputController.buildFarm();												
				}
				
				if(event.getObjectId().equals("BuildWater")) {
					//inputController.buildBarracks();					
				}
				
				if(event.getObjectId().equals("BuildForest")) {				
					//inputController.buildMarket();								
				}
					
				if(event.getObjectId().equals("BuildMine")) {
					//createWallBuildMenu();						
				}									
					
			}		
		}
						
		if(winId.equals("QuitDialog")) {
			if(event.getObjectId().equals("Yes")) {
				System.exit(0);
			}
			
			if(event.getObjectId().equals("No")) {
				windowList.remove("QuitDialog");
			}
		}	
		
		if(winId.equals("InfoDialog")) {
			if(event.getObjectId().equals("Ok")) {
				windowList.remove("InfoDialog");
			}
		}
		
	}	
	
	/**
	 * Checks all GUI elements for mouse hover
	 * 
	 * @param mouseX x coordinate of the mouse
	 * @param mouseY y coordinate of the mouse
	 */
	public void checkGUIHover(int mouseX, int mouseY) {
		statusBar.isMouseOver(mouseX, mouseY);
		infoBar.isMouseOver(mouseX, mouseY);		
		
		for(int i=windowList.size()-1; i>=0; i--) {
			Window window = (Window)windowList.get(i);
			window.isMouseOver(mouseX, mouseY);	
		}			
	}
	
	/**
	 * Checks all GUI elements for mouse clicks
	 * 
	 * @param mouseX x coordinate of the mouse
	 * @param mouseY y coordinate of the mouse
	 * @return true if a gui element was clicked
	 */
	public boolean checkGUIClicked(int mouseX, int mouseY) {
		boolean clicked = false;
		
		if(statusBar.isMouseClicked(mouseX, mouseY)) {
			clicked = true;
		}		
		
		if(clicked == false) {			
			if(infoBar.isMouseClicked(mouseX, mouseY)) {
				clicked = true;
			}
			
			if(clicked == false) {				
										
				for(int i=windowList.size()-1; i>=0; i--) {
					Window window = (Window)windowList.get(i);
					if(window.isMouseClicked(mouseX, mouseY)) {
						clicked = true;
					}
				}	
									
			}				
		}
		
		
		return clicked;
	}

	/**
	 * Draws all GUI elements on the screen. The elements are drawn in the order they were added.
	 * 
	 * @param g Graphics
	 */
	public void drawGUIElements(Graphics g) {
		statusBar.draw(g);
		infoBar.draw(g);
		
		for(int i=0; i<windowList.size(); i++) {			
			Window window = (Window)windowList.get(i);
			window.draw(g);
		}		
	}
}
