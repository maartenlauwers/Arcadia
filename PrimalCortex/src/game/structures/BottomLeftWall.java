package game.structures;

import engine.Config;

public class BottomLeftWall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public BottomLeftWall() {
		super("Wall", 500, 5, 
				Config.getTextureManager().getTextureByKey("wall_bottomleft_construction"),
				Config.getTextureManager().getTextureByKey("wall_bottomleft_active"),
				Config.getTextureManager().getTextureByKey("wall_bottomleft_destroyed"));		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}