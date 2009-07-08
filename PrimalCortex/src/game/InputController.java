package game;

import game.structures.Barracks;
import game.structures.BottomLeftWall;
import game.structures.BottomRightWall;
import game.structures.EmptyStructure;
import game.structures.Farm;
import game.structures.HorizontalWall;
import game.structures.Hovel;
import game.structures.Market;
import game.structures.StructureType;
import game.structures.TopLeftWall;
import game.structures.TopRightWall;
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
				
				
				boolean north = false;
				boolean south = false;
				boolean west = false;
				boolean east = false;
				int neighbours = 0;
				
				// First, check whether this tile could be a corner tile
				Tile tileNorth = localMap.getTileNorthOf(t);
				Tile tileSouth = localMap.getTileSouthOf(t);
				Tile tileEast = localMap.getTileEastOf(t);
				Tile tileWest = localMap.getTileWestOf(t);
				
				if(tileNorth != null) {
					if(tileNorth.getStructure().getType().equals(StructureType.WALL)) {
						north = true;
						neighbours++;
					}
				}
				if(tileSouth != null) {
					if(tileSouth.getStructure().getType().equals(StructureType.WALL)) {
						south = true;
						neighbours++;
					}
				}
				if(tileEast != null) {
					if(tileEast.getStructure().getType().equals(StructureType.WALL)) {
						east = true;
						neighbours++;
					}
				}
				if(tileWest != null) {
					if(tileWest.getStructure().getType().equals(StructureType.WALL)) {
						west = true;
						neighbours++;
					}
				}
				
				if(neighbours < 2) {
					constructWallVertical(t);
				} else {
					if(north && south && east && west) {
						// Place a square wall
						// ...
					} else if(north && east) {
						constructWallBottomLeft(t);
					} else if(north && west) {
						constructWallBottomRight(t);
					} else if(south && east) {
						constructWallTopLeft(t);
					} else if(south && west) {
						constructWallTopRight(t);
					} else if(north && south) {
						constructWallVertical(t);	
					} else {
						// If all else fails, at least build the wall
						constructWallVertical(t);
					}
				}
				
			
			}
		}	
		
	}
	
	public void buildWallHorizontal() {
		for(Tile t : localMap.getTileList()) {
			if(t.isSelected()) {
				
				boolean north = false;
				boolean south = false;
				boolean west = false;
				boolean east = false;
				int neighbours = 0;
				
				// First, check whether this tile could be a corner tile
				Tile tileNorth = localMap.getTileNorthOf(t);
				Tile tileSouth = localMap.getTileSouthOf(t);
				Tile tileEast = localMap.getTileEastOf(t);
				Tile tileWest = localMap.getTileWestOf(t);
				
				if(tileNorth != null) {
					if(tileNorth.getStructure().getType().equals(StructureType.WALL)) {
						north = true;
						neighbours++;
					}
				}
				if(tileSouth != null) {
					if(tileSouth.getStructure().getType().equals(StructureType.WALL)) {
						south = true;
						neighbours++;
					}
				}
				if(tileEast != null) {
					if(tileEast.getStructure().getType().equals(StructureType.WALL)) {
						east = true;
						neighbours++;
					}
				}
				if(tileWest != null) {
					if(tileWest.getStructure().getType().equals(StructureType.WALL)) {
						west = true;
						neighbours++;
					}
				}
			
				//System.out.println("Neighbours: " + neighbours);
				//System.out.println("North: " + north + " South: " + south + " West: " + west + " East: " + east);
				
				if(neighbours < 2) {
					constructWallHorizontal(t);
				} else {
					if(north && south && east && west) {
						// Place a square wall
						// ...
					} else if(north && east) {
						constructWallBottomLeft(t);
					} else if(north && west) {
						constructWallBottomRight(t);
					} else if(south && east) {
						constructWallTopLeft(t);
					} else if(south && west) {
						constructWallTopRight(t);
					} else if(east && west) {
						constructWallHorizontal(t);	
					} else {
						// If all else fails, at least build the wall
						constructWallHorizontal(t);
					}
				}
				
			}
		}	
		
	}
	
	public void constructWallVertical(Tile t) {
		VerticalWall w = new VerticalWall();				
		t.setStructure(w);
		game.addStructure(w);
		kingdom.setWealth(kingdom.getWealth() - w.getCost());
		game.updateGUIElements();
	}
	
	public void constructWallHorizontal(Tile t) {
		HorizontalWall w = new HorizontalWall();				
		t.setStructure(w);
		game.addStructure(w);
		kingdom.setWealth(kingdom.getWealth() - w.getCost());
		game.updateGUIElements();
	}
	
	public void constructWallTopLeft(Tile t) {
		TopLeftWall w = new TopLeftWall();				
		t.setStructure(w);
		game.addStructure(w);
		kingdom.setWealth(kingdom.getWealth() - w.getCost());
		game.updateGUIElements();
	}
	
	public void constructWallTopRight(Tile t) {
		TopRightWall w = new TopRightWall();				
		t.setStructure(w);
		game.addStructure(w);
		kingdom.setWealth(kingdom.getWealth() - w.getCost());
		game.updateGUIElements();
	}
	
	public void constructWallBottomRight(Tile t) {
		BottomRightWall w = new BottomRightWall();				
		t.setStructure(w);
		game.addStructure(w);
		kingdom.setWealth(kingdom.getWealth() - w.getCost());
		game.updateGUIElements();
	}
	
	public void constructWallBottomLeft(Tile t) {
		BottomLeftWall w = new BottomLeftWall();				
		t.setStructure(w);
		game.addStructure(w);
		kingdom.setWealth(kingdom.getWealth() - w.getCost());
		game.updateGUIElements();
	}	
		
}
