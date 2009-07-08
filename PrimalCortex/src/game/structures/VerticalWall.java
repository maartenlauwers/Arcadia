package game.structures;


import engine.Config;

public class VerticalWall extends Structure {
	
	private final int maxLevel = 10;
	private int currentLevel;
	
	public VerticalWall() {
		super("Wall", 500, 5, 
				Config.getTextureManager().getTextureByKey("wall_vertical_construction"),
				Config.getTextureManager().getTextureByKey("wall_vertical_active"),
				Config.getTextureManager().getTextureByKey("wall_vertical_destroyed"));		
		
		currentLevel = 1;		
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
}