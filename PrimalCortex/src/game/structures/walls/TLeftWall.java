package game.structures.walls;

import engine.Config;

public class TLeftWall extends Wall {
	
	public TLeftWall() {
		super( Config.getTextureManager().getTextureByKey("wall_T_left_construction"),
				Config.getTextureManager().getTextureByKey("wall_T_left_active"),
				Config.getTextureManager().getTextureByKey("wall_T_left_destroyed"));				
	}
	
}