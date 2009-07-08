package game.structures.walls;

import engine.Config;

public class CrossWall extends Wall {
	
	public CrossWall() {
		super(Config.getTextureManager().getTextureByKey("wall_cross_construction"),
				Config.getTextureManager().getTextureByKey("wall_cross_active"),
				Config.getTextureManager().getTextureByKey("wall_cross_destroyed"));		
	}
	
	public WallType getWallType() {
		return WallType.CROSS;
	}
}