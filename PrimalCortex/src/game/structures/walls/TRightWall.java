package game.structures.walls;

import engine.Config;

public class TRightWall extends Wall {	
	
	public TRightWall() {
		super(Config.getTextureManager().getTextureByKey("wall_T_right_construction"),
				Config.getTextureManager().getTextureByKey("wall_T_right_active"),
				Config.getTextureManager().getTextureByKey("wall_T_right_destroyed"));				

	}	
}