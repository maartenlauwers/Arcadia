package game.structures;

import org.newdawn.slick.Image;

import engine.Config;

public class EmptyStructure extends Structure {

	
	public EmptyStructure() {
		super("Grass", 0, 0, 
				Config.getTextureManager().getTextureByKey("grass"),
				Config.getTextureManager().getTextureByKey("grass"),
				Config.getTextureManager().getTextureByKey("grass"));	
		
		setState(new ConstructionState(this));			
	}

	public StructureType getType() {
		return StructureType.GRASS;
	}
}