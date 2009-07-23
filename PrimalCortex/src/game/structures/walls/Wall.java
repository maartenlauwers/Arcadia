package game.structures.walls;

import game.structures.ConstructionState;
import game.structures.Structure;
import game.structures.StructureType;

import org.newdawn.slick.Image;

public abstract class Wall extends Structure {		
	
	public Wall(Image construction, Image active, Image destroyed) {
		super("Wall", 100, 1, 10, 
				construction,
				active,
				destroyed);					
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
	
	public abstract WallType getWallType();
}