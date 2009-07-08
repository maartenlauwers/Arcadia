package game.structures;

import engine.Config;

public class TopLeftWall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public TopLeftWall() {
		super("Wall", 500, 5, 
				Config.getTextureManager().getTextureByKey("wall_topleft_construction"),
				Config.getTextureManager().getTextureByKey("wall_topleft_active"),
				Config.getTextureManager().getTextureByKey("wall_topleft_destroyed"));		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}