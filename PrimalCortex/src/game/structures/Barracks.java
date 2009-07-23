package game.structures;

import engine.Config;

public class Barracks extends Structure {	
	
	public Barracks() {
		super("Barracks", 2000, 300, 10,
				Config.getTextureManager().getTextureByKey("barracks_construction"),
				Config.getTextureManager().getTextureByKey("barracks_active"),
				Config.getTextureManager().getTextureByKey("barracks_destroyed"));				
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.BARRACKS;
	}
}
