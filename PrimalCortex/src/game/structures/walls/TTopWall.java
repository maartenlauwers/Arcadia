package game.structures.walls;

import engine.Config;

public class TTopWall extends Wall {
	
	public TTopWall() {
		super(Config.getTextureManager().getTextureByKey("wall_T_top_construction"),
				Config.getTextureManager().getTextureByKey("wall_T_top_active"),
				Config.getTextureManager().getTextureByKey("wall_T_top_destroyed"));										
	}
}