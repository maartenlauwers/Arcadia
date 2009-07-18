 	
package game;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import game.map.Map;
import game.map.WorldMap;

public class Game implements GuiListener {

	private Core engine;	
	private TextureManager textureManager;
			
	private Map worldMap;		
	
	private StatusBar statusBar;	
				
	private DualMap windowList;
	
	public Game(Core engine) {
		this.engine = engine;		
		this.textureManager = Config.getTextureManager();
				
		windowList = new DualMap();
				    	
    	generateMap(100, 100, 32, 32);    	    	      	        	    		
	}		
	
	public void generateMap(int tilesHorizontal, int tilesVertical, int tileWidth, int tileHeight) {
		Random random = new Random();
    	worldMap = new WorldMap(Config.getScreenWidth(), Config.getScreenHeight(), tilesHorizontal, tilesVertical, tileWidth, tileHeight);    	
    	
    	// Aim = 30% water, 20% resources, 50% build space
    	// Note, the algorithm below doesn't guarantee this.
    	for(int i=0; i<worldMap.getNrTilesVertical(); i++) {
    		for (int j=0; j<worldMap.getNrTilesHorizontal(); j++) {    			
    			
    			int r = random.nextInt(10);    			
    			if(r == 0 || r == 1 || r == 2) {
    				// water
    				worldMap.addTile(new Tile(textureManager.getTextureByKey("water"), TileType.WATER, j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    			} else if(r == 3 || r == 4) {
    				// resources
    				int resource = random.nextInt(2);    				
    				if(resource == 0) {
    					//goldmine
    					worldMap.addTile(new Tile(textureManager.getTextureByKey("dirt"), TileType.DIRT, j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    				} else {
    					//farm (forest?)
    					worldMap.addTile(new Tile(textureManager.getTextureByKey("farm_active"), TileType.FOREST, j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    				}
    				
    			} else {
    				// build space
    				worldMap.addTile(new Tile(textureManager.getTextureByKey("grass"), TileType.GRASS, j, i, tileWidth, tileHeight));   //TODO: hardcoded tile width and height
    			}
    		} 
    	}
	}
	
	public void loadMap() {			
		
		try {
			 
			 BufferedReader in = new BufferedReader(new FileReader("map.kac"));
			 			 		
			 int nrHorizontalTiles = new Integer(in.readLine()).intValue();
			 System.out.println("Horizontal tiles: " + nrHorizontalTiles);
			 int nrVerticalTiles = new Integer(in.readLine()).intValue();
			 System.out.println("Vertical tiles: " + nrVerticalTiles);
			 int tileWidth = new Integer(in.readLine()).intValue();
			 System.out.println("Horizontal tiles: " + tileWidth);
			 int tileHeight = new Integer(in.readLine()).intValue();
			 System.out.println("Horizontal tiles: " + tileHeight);
			 
			 WorldMap newWorldMap = new WorldMap(Config.getScreenWidth(), Config.getScreenHeight(), nrHorizontalTiles, nrVerticalTiles, tileWidth, tileHeight);
			 
			 char[][] tokens = new char[nrHorizontalTiles][nrVerticalTiles];			 
			 for(int i=0; i<nrVerticalTiles; i++) {
				 
				 StringBuffer test = new StringBuffer();
				 String line = in.readLine();
				 if(line != null) {
					 for (int j=0; j<nrHorizontalTiles; j++) {    			
						 tokens[i][j] = line.charAt(j);
						 test.append(line.charAt(j));
					 }	 
				 }		
				 System.out.println(test.toString());
			 }

			 
		    for(int i=0; i<nrVerticalTiles; i++) {
		    	for (int j=0; j<nrHorizontalTiles; j++) {    					    					    			 
					 
		    		char token = tokens[j][i];
		    		
					if(token == '1') {
						newWorldMap.addTile(new Tile(textureManager.getTextureByKey("grass"), TileType.GRASS, j, i, tileWidth, tileHeight));
					} else if(token == '2') {
						newWorldMap.addTile(new Tile(textureManager.getTextureByKey("dirt"), TileType.DIRT, j, i, tileWidth, tileHeight));
					} else if(token == '3') {
						newWorldMap.addTile(new Tile(textureManager.getTextureByKey("water"), TileType.WATER, j, i, tileWidth, tileHeight));
					} else if(token == '4') {
						newWorldMap.addTile(new Tile(textureManager.getTextureByKey("farm_active"), TileType.FOREST, j, i, tileWidth, tileHeight));
					} else if(token == '5') {
						newWorldMap.addTile(new Tile(textureManager.getTextureByKey("grass"), TileType.MINE, j, i, tileWidth, tileHeight));
					}						 
		    	} 
		    }
		    	
		    worldMap = newWorldMap;
		 
		    in.close();
		     		     
		    DialogWindow dw = new DialogWindow("LoadDialog", "Loaded", "The map was successfully loaded.");
			dw.addGuiListener(this);
			windowList.add("LoadDialog", dw);				
				
		 } catch (IOException e) {
			System.out.println(e.toString());
			 
			DialogWindow dw = new DialogWindow("LoadDialog", "Error", "An error occured while loading the map.");
			dw.addGuiListener(this);
			windowList.add("LoadDialog", dw);		
		 }
	}
	
	public void saveMap() {
		 try {
			 
			 WorldMap wm = (WorldMap)worldMap;
			 BufferedWriter out = new BufferedWriter(new FileWriter("map.kac"));			 
			 
			 
			 int nrHorizontalTiles = wm.getNrTilesHorizontal();
			 int nrVerticalTiles = wm.getNrTilesVertical();
			 int tileWidth = wm.getTileWidth();	//TODO: The tile width and height should be a property of the superclass Map.
			 int tileHeight = wm.getTileHeight();
			 
			 out.write("" + nrHorizontalTiles + "\n");
			 out.write("" + nrVerticalTiles + "\n");
			 out.write("" + tileWidth + "\n");
			 out.write("" + tileHeight + "\n");
			 
			 int index = 0;
			 for(Tile t : wm.getTileList()) {
				 if(t.getTileType().equals(TileType.GRASS)) {
					 out.write("1");
				 } else if(t.getTileType().equals(TileType.DIRT)) {
					 out.write("2");
				 } else if(t.getTileType().equals(TileType.WATER)){
					 out.write("3");
				 } else if(t.getTileType().equals(TileType.FOREST)) {
					 out.write("4");
				 } else if(t.getTileType().equals(TileType.MINE)){
					 out.write("5");
				 }
				 	 
				 index++;
				 if(index >= nrHorizontalTiles) {
					 index = 0;
					 out.write("\n");
				 }
			 }
			 		     
		     out.close();
		     		     
		     DialogWindow dw = new DialogWindow("SaveDialog", "Saved", "The map was successfully saved.");
			 dw.addGuiListener(this);
			 windowList.add("SaveDialog", dw);				
				
		 } catch (IOException e) {
			 System.out.println(e.toString());
			 
			 DialogWindow dw = new DialogWindow("SaveDialog", "Error", "An error occured while saving the map.");
			 dw.addGuiListener(this);
			 windowList.add("SaveDialog", dw);		
		 }
	}
	
	public WorldMap getWorldMap() {
		return (WorldMap)worldMap;
	}	
		
	public void createStatusBar() {
		this.statusBar = new StatusBar(this);		
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
		
		
		if(winId.equals("SaveDialog")) {
			if(event.getObjectId().equals("Ok")) {
				windowList.remove("SaveDialog");
			}
		}
		
		if(winId.equals("LoadDialog")) {
			if(event.getObjectId().equals("Ok")) {
				windowList.remove("LoadDialog");
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
				
				generateMap(100, 100, 32, 32);
			}
			
			if(event.getObjectId().equals("Save")) {
				
				saveMap();
			}
			
			if(event.getObjectId().equals("Load")) {
				
				loadMap();
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
					for(Tile t : worldMap.getTileList()) {
    	    			if(t.isSelected()) {
    	    				t.setTexture(textureManager.getTextureByKey("grass"), TileType.GRASS);
    	    			}    		    		
    		    	}												
				}
				
				if(event.getObjectId().equals("BuildDirt")) {				
					for(Tile t : worldMap.getTileList()) {
    	    			if(t.isSelected()) {
    	    				t.setTexture(textureManager.getTextureByKey("dirt"), TileType.DIRT);
    	    			}    		    		
    		    	}																								
				}
				
				if(event.getObjectId().equals("BuildWater")) {
					for(Tile t : worldMap.getTileList()) {
    	    			if(t.isSelected()) {
    	    				t.setTexture(textureManager.getTextureByKey("water"), TileType.WATER);
    	    			}    		    		
    		    	}																	
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
												
			for(int i=windowList.size()-1; i>=0; i--) {
				Window window = (Window)windowList.get(i);
					if(window.isMouseClicked(mouseX, mouseY)) {
					clicked = true;
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
		
		for(int i=0; i<windowList.size(); i++) {			
			Window window = (Window)windowList.get(i);
			window.draw(g);
		}		
		
		//Update statusbar
		statusBar.updateStatusBar(this);
	}
}
