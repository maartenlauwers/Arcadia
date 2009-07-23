package game.structures;

import engine.Config;

public class Hovel extends Structure {

	private int inhabitants;
	private int maxInhabitants;
	
	public Hovel() {
		super("Hovel", 500, 30, 10,
				Config.getTextureManager().getTextureByKey("house_construction"),
				Config.getTextureManager().getTextureByKey("house_active"),
				Config.getTextureManager().getTextureByKey("house_destroyed"));
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());
		
		this.inhabitants = 0;
		this.maxInhabitants = 250;
	}

	public StructureType getType() {
		return StructureType.HOVEL;
	}
	
	public void setInhabitants(int inhabitants) {
		if(inhabitants <= maxInhabitants) {
			this.inhabitants = inhabitants;	
		}		
	}
	
	public int getInhabitants() {
		return inhabitants;
	}
	
	public int getMaxInhabitants() {
		return maxInhabitants;
	}
	
}
