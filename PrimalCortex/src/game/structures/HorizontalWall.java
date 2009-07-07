package game.structures;

import engine.Config;

public class HorizontalWall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public HorizontalWall() {
		super("Wall", 500, 10, 
				Config.getTextureManager().getTextureByKey("wall_horizontal_construction"),
				Config.getTextureManager().getTextureByKey("wall_horizontal_active"),
				Config.getTextureManager().getTextureByKey("wall_horizontal_destroyed"));		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}