package game.structures;

import engine.Config;

public class TopRightWall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public TopRightWall() {
		super("Wall", 500, 5, 
				Config.getTextureManager().getTextureByKey("wall_topright_construction"),
				Config.getTextureManager().getTextureByKey("wall_topright_active"),
				Config.getTextureManager().getTextureByKey("wall_topright_destroyed"));		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}