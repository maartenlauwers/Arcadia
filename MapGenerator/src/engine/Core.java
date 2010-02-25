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

package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.Game;
import game.Tile;

import javax.swing.Timer;
 
public class Core extends BasicGame implements ActionListener {
 		
			
	private boolean isLeftMouseDown;
	private boolean isRightMouseDown;
	private boolean isKeyLeftDown;
	private boolean isKeyRightDown;
	private boolean isKeyUpDown;
	private boolean isKeyDownDown;
		
	private int oldMouseX;
	private int oldMouseY;
	private Timer dragTimer;
	private boolean dragAllowed;
	
	private Game game;	
	
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
    	
    	gc.setShowFPS(false);
    	dragTimer = new Timer(40, this);
    	dragTimer.stop();
    	dragAllowed = false;
    	
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	 
    	Input input = gc.getInput();
    	 
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();
    	
    	
    	if (input.isMouseButtonDown(1)) {    		
    			
    			if(isRightMouseDown == true && dragAllowed) {
    				    				        		
        			
        			if(mouseX > (oldMouseX + 15)) {
        				game.getWorldMap().moveMapLeft();
        			}
        			
        			if(mouseX < (oldMouseX - 15)) {
        				game.getWorldMap().moveMapRight();
        			}
        			
        			if(mouseY > (oldMouseY + 15)) {
        				game.getWorldMap().moveMapUp();
        			}
        			
        			if(mouseY < (oldMouseY - 15)) {
        				game.getWorldMap().moveMapDown();
        			}
        			
        			oldMouseX = mouseX;
        			oldMouseY = mouseY;   
        			dragAllowed = false;
        			dragTimer.restart();
        			    			
    			}
    			
    			if(isRightMouseDown == false) {
    				isRightMouseDown = true;
    				dragAllowed = false;
    				dragTimer.restart();
    				
    			}
    			    					
    	}
    	
       	if(!input.isMouseButtonDown(1)) {
    		isRightMouseDown = false;
    		dragAllowed = false;
    		dragTimer.stop();
    	}
    	
    	
    	// KEYBOARD INPUT
    	if (input.isKeyDown(input.KEY_LEFT)) {
        	if(isKeyLeftDown == false) {
        		game.getWorldMap().moveMapLeft();
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
        		game.getWorldMap().moveMapDown();
        	}
        	isKeyDownDown = true;
        }
        	
        if (!input.isKeyDown(input.KEY_DOWN)) {
        	isKeyDownDown = false;
        }
    	    	    	    
    	
    	game.checkGUIHover(mouseX, mouseY);
    	if (input.isMouseButtonDown(0)) {
    		if(isLeftMouseDown == false) {
    			isLeftMouseDown = true;
    			
    			boolean guiClicked = game.checkGUIClicked(mouseX, mouseY);    			
    			
    			// If the GUI wasn't clicked, it could have been one of the tiles
    			if(!guiClicked) {
    				for(Tile t : game.getWorldMap().getOnScreenTileList()) {
    	    			
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
    	    	
    	game.getWorldMap().draw();
    		
    	for(Tile t: game.getWorldMap().getOnScreenTileList()) {
        	if(t.isSelected()) {    			    			
        		g.drawRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		dragAllowed = true;		
		System.out.println("Dragallowed true");
		
	}
}