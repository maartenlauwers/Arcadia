package game.structures;

import org.newdawn.slick.Image;

import engine.Config;

public class Market extends Structure {

	public Market() {
		super("Market", 1000, 120, 10,
				Config.getTextureManager().getTextureByKey("market_construction"),
				Config.getTextureManager().getTextureByKey("market_active"),
				Config.getTextureManager().getTextureByKey("market_destroyed"));
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());
	}

	public StructureType getType() {
		return StructureType.MARKET;
	}
}
