package engine;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

import engine.gui.EmptyWindow;
import engine.gui.GuiEvent;
import engine.gui.Window;
import engine.gui.WindowStatus;
import engine.gui.widgets.Button;
import engine.gui.widgets.Label;
import engine.gui.widgets.Panel;

import game.Game;
import game.InputController;
import game.map.LocalMap;
import game.map.Map;
import game.tile.Tile;
 
public class Core extends BasicGame {
 	
	private InputController inputController;	
		
	private boolean isLeftMouseDown;
	private boolean isRightMouseDown;
	private boolean isKeyDown;
	private boolean isKeyLeftDown;
	private boolean isKeyRightDown;
	private boolean isKeyUpDown;
	private boolean isKeyDownDown;
	
	private Game game;
	private Camera camera;
	
    public Core()
    {
        super("Kings & Castles");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
     	
    	TextureManager textureManager = new TextureManager();
    	
    	new Config(1024, 768, textureManager);   
    	      	    	  
    	game = new Game(this);
    	game.createStatusBar();
    	game.createInfoBar();
    	
    	this.isLeftMouseDown = false;
    	this.isRightMouseDown = false;
    	this.isKeyDown = false;
    	
    	gc.setShowFPS(false);
    	
    	camera = new Camera(Config.getScreenWidth(), Config.getScreenHeight());    	
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	 
    	Input input = gc.getInput();
    	 
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();
    	
    	
    	// Check map movement
    	if (game.onLocalMap() == false) {
    		/*
    		if(mouseX < 10) {
    			game.getWorldMap().moveMapLeft();
    			//camera.moveCameraLeft(1);
    		}
    		if(mouseX > camera.getCameraScreenWidth() - 10) {
    			//camera.moveCameraRight(1);
    			game.getWorldMap().moveMapRight();
    		}
    		if(mouseY < 10) {
    			//camera.moveCameraUp(1);
    			game.getWorldMap().moveMapUp();
    		}
    		if(mouseY > camera.getCameraScreenHeight() - 10) {
    			//camera.moveCameraDown(1);
    			game.getWorldMap().moveMapDown();
    		}
    		*/
    		
    		if (input.isKeyDown(input.KEY_LEFT)) {
        		if(isKeyLeftDown == false) {
        			game.getWorldMap().moveMapLeft();
        		}
        		isKeyLeftDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_LEFT)) {
        		isKeyLeftDown = false;
        	}
        	
        	if (input.isKeyDown(input.KEY_RIGHT)) {
        		if(isKeyRightDown == false) {
        			game.getWorldMap().moveMapRight();
        		}
        		isKeyRightDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_RIGHT)) {
        		isKeyRightDown = false;
        	}
        	
        	if (input.isKeyDown(input.KEY_UP)) {
        		if(isKeyUpDown == false) {
        			game.getWorldMap().moveMapUp();
        		}
        		isKeyUpDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_UP)) {
        		isKeyUpDown = false;
        	}
        	
        	if (input.isKeyDown(input.KEY_DOWN)) {
        		if(isKeyDownDown == false) {
        			game.getWorldMap().moveMapDown();
        		}
        		isKeyDownDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_DOWN)) {
        		isKeyDownDown = false;
        	}
    	}    	    	    	    	    	    	    	    	    	    	   
    	
    	game.checkGUIHover(mouseX, mouseY);
    	if (input.isMouseButtonDown(0)) {
    		if(isLeftMouseDown == false) {
    			isLeftMouseDown = true;
    			    			
    			game.handleLeftMouseClick(mouseX, mouseY);    			
    		}    		    		
    	}
    	
    	if(!input.isMouseButtonDown(0)) {
    		isLeftMouseDown = false;
    	}
    
    
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	
    	if(game.onLocalMap()) {
    		// Draw the local map
    		game.getLocalMap().draw();
    		
    		for(Tile t: game.getLocalMap().getTileList()) {
        		if(t.isSelected()) {    			    			
        			g.drawRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
        		}
        	}
    	} else {
    		// Draw the world map
    		game.getWorldMap().draw();
    		
    		for(Tile t: game.getWorldMap().getOnScreenTileList()) {
            	if(t.isSelected()) {    			    			
            		g.drawRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
            	}
            }    	  
    	}    	    	    	   
    	
    	game.drawGUIElements(g);
    }           
    
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new Core());
 
         app.setDisplayMode(1024, 768, false);
         app.start();
    }
}