package game.structures;


import engine.Config;

public class Farm extends Structure {
	
	private final int baseProduction = 1;	
	private int currentLevel;
		
	private int generatedFood;
	
	public Farm() {
		super("Farm", 500, 60, 5,
				Config.getTextureManager().getTextureByKey("farm_construction"),
				Config.getTextureManager().getTextureByKey("farm_active"),
				Config.getTextureManager().getTextureByKey("farm_destroyed"));		
		
		currentLevel = 1;
		generatedFood = 0;			
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.FARM;
	}

	public int getGeneratedFood() {
		if(super.isActive()) {
			generatedFood += currentLevel * baseProduction;
			return currentLevel * baseProduction;	
		}
		
		return 0;
	}
}
