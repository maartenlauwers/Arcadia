package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import game.structures.Farm;
import game.structures.Hovel;
import game.structures.Structure;
import game.structures.StructureType;

public class Game implements GuiListener, ActionListener {

	private Core engine;	
	private TextureManager textureManager;
	
	private Kingdom kingdom;
	
	private Map localMap;
	private Map worldMap;
	private boolean localMapActive;
	
	private InputController inputController;
	private List<Structure> structureList;
	
	private StatusBar statusBar;
	private InfoBar infoBar;
	private TownInfoView townInfoView;
	
	private boolean underConstruction;
	private int hovelAmount;
	private final double taxRate = 0.1;
	private int lastTaxIncome;
	private int lastFoodProduction;
	
	private Timer gameTimer;
	private DualMap windowList;
	
	public Game(Core engine) {
		this.engine = engine;		
		this.textureManager = Config.getTextureManager();
		
		this.kingdom = new Kingdom("Northrend");
		kingdom.setFood(10000);
		kingdom.setWealth(10000);
	
		windowList = new DualMap();
		
		// Init the local map
    	localMap = new LocalMap(Config.getScreenWidth(), Config.getScreenHeight(), Config.getScreenWidth()/64 + 1, (Config.getScreenHeight() - 60)/64 + 1);
    	localMapActive = true;
    	
    	for(int i=0; i<localMap.getNrTilesHorizontal()*64; i += 64) {
    		for (int j=30; j<localMap.getNrTilesVertical()*64; j += 64) {
    			localMap.addTile(new Tile(textureManager.getTextureByKey("grass"), i, j, 64, 64));   //TODO: hardcoded tile width and height 			
    		}
    	}
    	
    	worldMap = new WorldMap(Config.getScreenWidth(), Config.getScreenHeight(), 100, 100, 64, 64);
    	
    	for(int i=0; i<worldMap.getNrTilesVertical(); i++) {
    		for (int j=0; j<worldMap.getNrTilesHorizontal(); j++) {
    			//worldMap.addTile(new Tile(textureManager.getTextureByKey("grass"), i, j, 64, 64));   //TODO: hardcoded tile width and height
    			
    			if(i%2 == 0 && j%2 == 0) {
    				worldMap.addTile(new Tile(textureManager.getTextureByKey("market_active"), j, i, 64, 64));   //TODO: hardcoded tile width and height
    			} else {
    				worldMap.addTile(new Tile(textureManager.getTextureByKey("dirt"), j, i, 64, 64));   //TODO: hardcoded tile width and height
    			}
    		} 
    	}
    	
    	inputController = new InputController(this);      	
    	structureList = new ArrayList<Structure>();
    	
    	underConstruction = false;
    	
		gameTimer = new Timer(1000, this);
		gameTimer.start();
		
		createWelcomeDialog();
	}
		
	public LocalMap getLocalMap() {
		return (LocalMap)localMap;
	}
	
	public WorldMap getWorldMap() {
		return (WorldMap)worldMap;
	}
	
	public boolean onLocalMap() {
		return localMapActive;
	}
	
	public Kingdom getKingdom() {
		return kingdom;
	}
	
	public void addStructure(Structure s) {
		structureList.add(s);
	}
	
	public void removeStructure(Structure s) {
		structureList.remove(s);
	}
	
	public List getStructures() {
		return structureList;
	}
	
	public void createStatusBar() {
		this.statusBar = new StatusBar(this, kingdom);		
	}
	
	public void updateStatusBar() {
		// Update the statusbar
		statusBar.update("" + kingdom.getPopulation(), "Gold: " + kingdom.getWealth(), "" + kingdom.getFood(), "");
	}
	
	public void createInfoBar() {
		this.infoBar = new InfoBar(this, kingdom);
	}
	
	public void createTownInfoView() {
		this.townInfoView = new TownInfoView(this, kingdom);
	}
	
	public void updateTownInfoView() {
		// Update the townInfoView (if it exists)
		if(townInfoView != null) {
			townInfoView.update(kingdom.getPopulation(), kingdom.getWealth(), lastTaxIncome, kingdom.getFood(), lastFoodProduction, kingdom.getPopulation() * 3);	
		}
	}
	
	public void createWelcomeDialog() {		
		PictureDialogWindow pdw = new PictureDialogWindow("IntroDialog", "Intro", "Greetings. I am Ellune, goddess of Arcadia. /n" +
															"I am here to guide you in your task to /nrule the land.", 400, 100, textureManager.getTextureByKey("sorceress"), 75, 75);
		pdw.addGuiListener(this);
		windowList.add("IntroDialog", pdw);	
	}
	/**
	 * Creates the build menu
	 */
	public void createBuildMenu() {
		Window newWindow = new EmptyWindow("BuildMenu", Config.getScreenWidth() - 300, 30, 300, 400);
		newWindow.addWidget(new Panel("BuildMenu", 0, 0, 300, 500, true));
		
		newWindow.addWidget(new Label("BuildMenu", 10, 10, "Available structures"));
			
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, 40, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		int heightOffset = 50;
		
		// Hovel
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("house_active")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Hovel"));
		newWindow.addWidget(new Label("BuildMenu", 160, heightOffset, "Cost: 500"));
		Button buildHouseButton = new Button("BuildMenu", EventActionType.OTHER, 80, heightOffset + 25, "Build", "BuildHovel");
		buildHouseButton.addGuiListener(this);
		newWindow.addWidget(buildHouseButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		
		// Farm
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("farm_active")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Farm"));
		newWindow.addWidget(new Label("BuildMenu", 160, heightOffset, "Cost: 1000"));
		Button buildFarmButton = new Button("BuildMenu", EventActionType.OTHER, 80, heightOffset + 25, "Build", "BuildFarm");
		buildFarmButton.addGuiListener(this);
		newWindow.addWidget(buildFarmButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
					
		// Barracks
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("barracks_active")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Barracks"));
		newWindow.addWidget(new Label("BuildMenu", 160, heightOffset, "Cost: 2000"));
		Button buildBarracksButton = new Button("BuildMenu", EventActionType.OTHER, 80, heightOffset + 25, "Build", "BuildBarracks");
		buildBarracksButton.addGuiListener(this);
		newWindow.addWidget(buildBarracksButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
				
		// Market
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("market_active")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Market"));
		newWindow.addWidget(new Label("BuildMenu", 160, heightOffset, "Cost: 2000"));			
		Button buildMarketButton = new Button("BuildMenu", EventActionType.CLOSE, 80, heightOffset + 25, "Build", "BuildMarket");
		buildMarketButton.addGuiListener(this);
		newWindow.addWidget(buildMarketButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		// Wall_vertical
		heightOffset += 70;
		newWindow.addWidget(new PictureBox("BuildMenu", 10, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_horizontal_active")));
		newWindow.addWidget(new Label("BuildMenu", 80, heightOffset, "Wall"));
		newWindow.addWidget(new Label("BuildMenu", 160, heightOffset, "Cost: 500"));			
		Button buildWallButton = new Button("BuildMenu", EventActionType.CLOSE, 80, heightOffset + 25, "Build", "BuildWall");
		buildWallButton.addGuiListener(this);
		newWindow.addWidget(buildWallButton);
		
		// border
		newWindow.addWidget(new PictureBox("BuildMenu", 0, heightOffset + 60, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));			
		
		
		Button closeButton = new Button("BuildMenu", EventActionType.CLOSE, 10, 470, "Close", "CloseBuildMenu");
		closeButton.addGuiListener(this);
		newWindow.addWidget(closeButton);
		
		windowList.add("BuildMenu", newWindow);
	}	
	
	public void createWallBuildMenu() {
		Window newWindow = new EmptyWindow("WallBuildMenu", Config.getScreenWidth()/2 - 250, Config.getScreenHeight()/2 - 120, 500, 240);
		newWindow.addWidget(new Panel("WallBuildMenu", 0, 0, 500, 240, true));
		
		//newWindow.addWidget(new Label("WallBuildMenu", 10, 10, "));
			
					
		// border
		//newWindow.addWidget(new PictureBox("BuildMenu", 0, 40, 300, 1, Config.getTextureManager().getTextureByKey("border_horizontal")));
		
		int heightOffset = 20;
		int widthOffset = 20;
		
		// Horizontal wall
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_horizontal_active")));		
		Button buildWallHorizontalButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildHorizontal");
		buildWallHorizontalButton.addGuiListener(this);
		newWindow.addWidget(buildWallHorizontalButton);
		
		// Vertical wall
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_vertical_active")));		
		Button buildWallVerticalButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildVertical");
		buildWallVerticalButton.addGuiListener(this);
		newWindow.addWidget(buildWallVerticalButton);
		
		// Top left corner wall
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_topleft_active")));		
		Button buildWallTopLeftButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildTopLeft");
		buildWallTopLeftButton.addGuiListener(this);
		newWindow.addWidget(buildWallTopLeftButton);
		
		// Top right corner wall
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_topright_active")));		
		Button buildWallTopRightButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildTopRight");
		buildWallTopRightButton.addGuiListener(this);
		newWindow.addWidget(buildWallTopRightButton);
		
		// Bottom right corner wall
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_bottomright_active")));		
		Button buildWallBottomRightButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildBottomRight");
		buildWallBottomRightButton.addGuiListener(this);
		newWindow.addWidget(buildWallBottomRightButton);
		
		// Bottom left corner wall
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_bottomleft_active")));		
		Button buildWallBottomLeftButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildBottomLeft");
		buildWallBottomLeftButton.addGuiListener(this);
		newWindow.addWidget(buildWallBottomLeftButton);
		
		
		heightOffset += 100;
		widthOffset = 20;
		
		// T Top		
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_T_top_active")));		
		Button buildWallTTopButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildTTop");
		buildWallTTopButton.addGuiListener(this);
		newWindow.addWidget(buildWallTTopButton);			
		
		// T Bottom
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_T_bottom_active")));		
		Button buildWallTBottomButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildTBottom");
		buildWallTBottomButton.addGuiListener(this);
		newWindow.addWidget(buildWallTBottomButton);	
		
		// T Left
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_T_left_active")));		
		Button buildWallTLeftButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildTLeft");
		buildWallTLeftButton.addGuiListener(this);
		newWindow.addWidget(buildWallTLeftButton);		
		
		// T Right
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_T_right_active")));		
		Button buildWallTRightButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildTRight");
		buildWallTRightButton.addGuiListener(this);
		newWindow.addWidget(buildWallTRightButton);	
		
		// Cross
		widthOffset += 80;
		newWindow.addWidget(new PictureBox("WallBuildMenu", widthOffset, heightOffset, 48, 48, Config.getTextureManager().getTextureByKey("wall_cross_active")));		
		Button buildWallCrossButton = new Button("WallBuildMenu", EventActionType.OTHER, widthOffset - 10, heightOffset + 50, 70, 20, "Build", "WallBuildCross");
		buildWallCrossButton.addGuiListener(this);
		newWindow.addWidget(buildWallCrossButton);		
		
		Button closeButton = new Button("BuildMenu", EventActionType.CLOSE, 10, 210, "Close", "CloseWallBuildMenu");
		closeButton.addGuiListener(this);
		newWindow.addWidget(closeButton);
		
		windowList.add("WallBuildMenu", newWindow);
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
			
			if(event.getObjectId().equals("Destroy")) {
				
				inputController.destroyStructure();
			}
		}
		
		if(winId.equals("InfoBar")) {
			if(event.getObjectId().equals("TownInfo")) {
				
				createTownInfoView();
			}
			if(event.getObjectId().equals("WorldMap")) {
				if(localMapActive) {
					localMapActive = false;	
				} else {
					localMapActive = true;
				}
								
				
			}
		}
		
		if(winId.equals("BuildMenu")) {
			if(event.getObjectId().equals("CloseBuildMenu")) {				
				windowList.remove("BuildMenu");				
			} else {
				if(!underConstruction) {
					if(event.getObjectId().equals("BuildHovel")) {				
						inputController.buildHovel();
						underConstruction = true;									
					}
					
					if(event.getObjectId().equals("BuildFarm")) {				
						inputController.buildFarm();
						underConstruction = true;									
					}
					
					if(event.getObjectId().equals("BuildBarracks")) {
						inputController.buildBarracks();
						underConstruction = true;
					}
					
					if(event.getObjectId().equals("BuildMarket")) {				
						inputController.buildMarket();
						underConstruction = true;					
					}
					
					if(event.getObjectId().equals("BuildWall")) {
						createWallBuildMenu();						
					}									
					
				} else {
					DialogWindow dw = new DialogWindow("InfoDialog", "", "Another building is already under construction.");
					dw.addGuiListener(this);
					windowList.add("InfoDialog", dw);	
				}
			}				
		}
		
		if(winId.equals("WallBuildMenu")) {
			if(event.getObjectId().equals("CloseWallBuildMenu")) {
				windowList.remove("WallBuildMenu");
			} else {
				if(!underConstruction) {
					if(event.getObjectId().equals("WallBuildHorizontal")) {
						inputController.buildWallHorizontal();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildVertical")) {
						System.out.println("Vertical wall requested");
						inputController.buildWallVertical();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildTopLeft")) {
						inputController.buildWallTopLeft();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildTopRight")) {
						inputController.buildWallTopRight();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildBottomRight")) {
						inputController.buildWallBottomRight();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildBottomLeft")) {
						inputController.buildWallBottomLeft();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildTTop")) {
						inputController.buildWallTTop();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildTBottom")) {
						inputController.buildWallTBottom();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildTLeft")) {
						inputController.buildWallTLeft();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildTRight")) {
						inputController.buildWallTRight();
						underConstruction = true;
					}
					if(event.getObjectId().equals("WallBuildCross")) {
						inputController.buildWallCross();
						underConstruction = true;
					}
					
					// We assume that a build button was clicked, in which case the window may be closed
					windowList.remove("WallBuildMenu");
				}
			}
		}
		
		if(winId.equals("TownInfo")) {
			if(event.getObjectId().equals("CloseTownInfo")) {
				townInfoView = null;				
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
	 * Updates all GUI elements (the contents of the update are defined by the element itself)
	 */	
	public void updateGUIElements() {
		updateStatusBar();
		updateTownInfoView();		
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
		if(townInfoView != null) {
			townInfoView.isMouseOver(mouseX, mouseY);
		}
		
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
				
				if(townInfoView != null) {
					if(townInfoView.isMouseClicked(mouseX, mouseY)) {
						clicked = true;
					}	
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
		
		if(townInfoView != null) {
			townInfoView.draw(g);
		}
		
		for(int i=0; i<windowList.size(); i++) {			
			Window window = (Window)windowList.get(i);
			window.draw(g);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(gameTimer)) {
			
			this.lastTaxIncome = 0;
			this.lastFoodProduction = 0;
			
			// Update the food resource and population 
			int food = kingdom.getFood();						
			int population = kingdom.getPopulation();
			
			boolean isSomethingUnderConstruction = false;
			for(Structure s : structureList) {
				if(s.getType().equals(StructureType.FARM)) {
					Farm f = (Farm)s;
					if (f.isActive()) {
						food += f.getGeneratedFood();
						lastFoodProduction += f.getGeneratedFood();
					}
				}							
				
				
				//TODO: This really isn't the best way to deal with static population increase, but maybe it's a convenient way
				// to work with dynamic populations ?
				//
				// Possibilities: 
				//		- hovel's activestate informs the kingdom about it's population (requires knowledge of the kingdom or game 
				//		  in the state).
				//		- Periodically ask the hovel about its status and update the population dynamically, taking into account
				//	      other factors such as economics, ...
				
				if(s.getType().equals(StructureType.HOVEL)) {
					Hovel h = (Hovel)s;
					if(h.isActive()) {
						if(h.getInhabitants() < h.getMaxInhabitants()) {
							h.setInhabitants(h.getMaxInhabitants());
							population += h.getMaxInhabitants();
						}	
					}				
				}
				
				if(s.isConstructed()) {
					infoBar.update(s.getBuildProgress(), s.getName() + " (" + s.getBuildProgress() + "%)");
					isSomethingUnderConstruction = true;
				}
			}
			
			if(! isSomethingUnderConstruction) {
				underConstruction = false;
				infoBar.update(0, "");
			}
			
			kingdom.setFood(food);
			kingdom.setPopulation(population);
			
			// Update the gold resource
			double gold = kingdom.getWealth();			
			double g = 0;
			g = 0.01 * kingdom.getPopulation();
			this.lastTaxIncome = (int)g;
			gold += g;
			kingdom.setWealth((int)Math.round(gold));
			

			updateStatusBar();
			updateTownInfoView();
			
			
			// Restart the gametimer
			gameTimer.restart();
		}
		
	}
}
