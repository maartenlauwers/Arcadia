package game.structures.walls;

import engine.Config;

public class TBottomWall extends Wall {
	
	public TBottomWall() {
		super(Config.getTextureManager().getTextureByKey("wall_T_bottom_construction"),
				Config.getTextureManager().getTextureByKey("wall_T_bottom_active"),
				Config.getTextureManager().getTextureByKey("wall_T_bottom_destroyed"));		
	
	}
	
	public WallType getWallType() {
		return WallType.T_BOTTOM;
	}
}