package game.structures.walls;

import game.structures.ConstructionState;
import game.structures.Structure;
import game.structures.StructureType;

import org.newdawn.slick.Image;

public abstract class Wall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public Wall(Image construction, Image active, Image destroyed) {
		super("Wall", 100, 1, 
				construction,
				active,
				destroyed);		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}