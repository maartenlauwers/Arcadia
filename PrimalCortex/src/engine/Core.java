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
import engine.gui.EventActionType;
import engine.gui.GuiEvent;
import engine.gui.Window;
import engine.gui.WindowStatus;
import engine.gui.widgets.Button;
import engine.gui.widgets.Label;
import engine.gui.widgets.Panel;

import game.Game;
import game.InputController;
import game.Tile;
import game.map.LocalMap;
import game.map.Map;
 
public class Core extends BasicGame {
 	
	private InputController inputController;	
		
	private boolean isLeftMouseDown;
	private boolean isRightMouseDown;
	private boolean isKeyDown;
		
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
        		if(isKeyDown == false) {
        			game.getWorldMap().moveMapLeft();
        		}
        		isKeyDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_LEFT)) {
        		isKeyDown = false;
        	}
        	
        	if (input.isKeyDown(input.KEY_RIGHT)) {
        		if(isKeyDown == false) {
        			game.getWorldMap().moveMapRight();
        		}
        		isKeyDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_RIGHT)) {
        		isKeyDown = false;
        	}
        	
        	if (input.isKeyDown(input.KEY_UP)) {
        		if(isKeyDown == false) {
        			game.getWorldMap().moveMapUp();
        		}
        		isKeyDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_UP)) {
        		isKeyDown = false;
        	}
        	
        	if (input.isKeyDown(input.KEY_DOWN)) {
        		if(isKeyDown == false) {
        			game.getWorldMap().moveMapUp();
        		}
        		isKeyDown = true;
        	}
        	
        	if (!input.isKeyDown(input.KEY_DOWN)) {
        		isKeyDown = false;
        	}
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	if (input.isKeyDown(input.KEY_F)) {
    		if(isKeyDown == false) {
    			inputController.Key_F();
    		}
    		isKeyDown = true;
    	}    	    	
    	
    	if (!input.isKeyDown(input.KEY_F)) {
    		isKeyDown = false;
    	}
    	
    	if (input.isKeyDown(input.KEY_M)) {
    		if(isKeyDown == false) {
    			inputController.Key_M();
    		}
    		isKeyDown = true;
    	}
    	
    	if (!input.isKeyDown(input.KEY_M)) {
    		isKeyDown = false;
    	}
    	    	    
    	
    	game.checkGUIHover(mouseX, mouseY);
    	if (input.isMouseButtonDown(0)) {
    		if(isLeftMouseDown == false) {
    			isLeftMouseDown = true;
    			
    			boolean guiClicked = game.checkGUIClicked(mouseX, mouseY);    			
    			
    			// If the GUI wasn't clicked, it could have been one of the tiles
    			if(!guiClicked) {
    				for(Tile t : game.getLocalMap().getTileList()) {
    	    			
    		    		t.setSelected(false);
    		    		if (t.isClicked(mouseX, mouseY)) {
    		    			t.setSelected(true);    		    			
    		    		}
    		    	}	
    			}    			
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
    		game.getLocalMap().draw();
    		
    		for(Tile t: game.getLocalMap().getTileList()) {
        		if(t.isSelected()) {    			    			
        			g.drawRect(t.getX(), t.getY(), 64, 64);
        		}
        	}
    	} else {
    		game.getWorldMap().draw();
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