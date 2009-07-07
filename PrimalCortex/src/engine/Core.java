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
import game.LocalMap;
import game.Map;
import game.Tile;
 
public class Core extends BasicGame {
 	
	private InputController inputController;	
		
	private boolean isLeftMouseDown;
	private boolean isRightMouseDown;
	private boolean isKeyDown;
		
	private Game game;
	
	
    public Core()
    {
        super("Slick2DPath2Glory - SimpleGame");
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
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	 
    	Input input = gc.getInput();
    	 
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();
    	
    	
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
    	   
    	for(Tile t : game.getLocalMap().getTileList()) {
    		t.draw();    		    	
    	}    	     
    	
    	for(Tile t: game.getLocalMap().getTileList()) {
    		if(t.isSelected()) {    			    			
    			g.drawRect(t.getX(), t.getY(), 64, 64);
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