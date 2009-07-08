package game.structures;

import engine.Config;

public class BottomRightWall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public BottomRightWall() {
		super("Wall", 500, 5, 
				Config.getTextureManager().getTextureByKey("wall_bottomright_construction"),
				Config.getTextureManager().getTextureByKey("wall_bottomright_active"),
				Config.getTextureManager().getTextureByKey("wall_bottomright_destroyed"));		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}