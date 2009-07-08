package game;

import game.structures.Barracks;
import game.structures.EmptyStructure;
import game.structures.Farm;
import game.structures.Hovel;
import game.structures.Market;
import game.structures.StructureType;
import game.structures.walls.BottomLeftWall;
import game.structures.walls.BottomRightWall;
import game.structures.walls.CrossWall;
import game.structures.walls.HorizontalWall;
import game.structures.walls.TBottomWall;
import game.structures.walls.TLeftWall;
import game.structures.walls.TRightWall;
import game.structures.walls.TTopWall;
import game.structures.walls.TopLeftWall;
import game.structures.walls.TopRightWall;
import game.structures.walls.VerticalWall;
import game.structures.walls.Wall;
import game.structures.walls.WallType;

public class InputController {

	private Game game;
	private Map localMap;
	private Kingdom kingdom;
	
	public InputController(Game game) {
		this.game = game;
		this.localMap = game.getLocalMap();
		this.kingdom = game.getKingdom();
	}
	
	/**
	 * Build farm
	 */
	public void Key_F() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				t.setStructure(new Farm());
			}
		}
	}
	
	public void Key_M() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				t.setStructure(new Market());
			}
		}
	}
	public void Key_D() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				t.setStructure(new EmptyStructure());
			}
		}
	}
	
	public void buildFarm() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				Farm f = new Farm();
				t.setStructure(f);
				game.addStructure(f);
				kingdom.setWealth(kingdom.getWealth() - f.getCost());
				game.updateGUIElements();
			}
		}
	}
	
	public void buildMarket() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				Market m = new Market();				
				t.setStructure(m);
				game.addStructure(m);
				kingdom.setWealth(kingdom.getWealth() - m.getCost());
				game.updateGUIElements();
			}
		}
	}

	public void buildHovel() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				Hovel m = new Hovel();				
				t.setStructure(m);
				game.addStructure(m);
				kingdom.setWealth(kingdom.getWealth() - m.getCost());
				game.updateGUIElements();
			}
		}		
	}

	public void buildBarracks() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				Barracks b = new Barracks();				
				t.setStructure(b);
				game.addStructure(b);
				kingdom.setWealth(kingdom.getWealth() - b.getCost());
				game.updateGUIElements();
			}
		}		
		
	}

	public void buildWallVertical() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				VerticalWall w = new VerticalWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();								
			}
		}	
		
	}
	
	public void buildWallHorizontal() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				HorizontalWall w = new HorizontalWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();				
			}
		}	
		
	}
	
	
	
	// corner walls
	
	public void buildWallTopLeft() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				TopLeftWall w = new TopLeftWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();		
			}
		}			
	}
	
	public void buildWallTopRight() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				TopRightWall w = new TopRightWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();	
			}
		}			
	}
	
	
	public void buildWallBottomRight() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				BottomRightWall w = new BottomRightWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}
	
	public void buildWallBottomLeft() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				BottomLeftWall w = new BottomLeftWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}
	
	
	// T shape walls
	
	public void buildWallTTop() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				TTopWall w = new TTopWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}
	
	public void buildWallTRight() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				TRightWall w = new TRightWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}
	
	public void buildWallTBottom() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				TBottomWall w = new TBottomWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}

	public void buildWallTLeft() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				TLeftWall w = new TLeftWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}
	
	// Cross wall
	
	public void buildWallCross() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {				
				CrossWall w = new CrossWall();				
				t.setStructure(w);
				game.addStructure(w);
				kingdom.setWealth(kingdom.getWealth() - w.getCost());
				game.updateGUIElements();
			}
		}			
	}	
		
}
