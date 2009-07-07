package game;

import game.structures.Barracks;
import game.structures.EmptyStructure;
import game.structures.Farm;
import game.structures.HorizontalWall;
import game.structures.Hovel;
import game.structures.Market;
import game.structures.VerticalWall;

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
		
}
