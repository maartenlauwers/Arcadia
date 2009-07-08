package game.structures.walls;

import engine.Config;

public class HorizontalWall extends Wall {
		
	public HorizontalWall() {
		super(Config.getTextureManager().getTextureByKey("wall_horizontal_construction"),
				Config.getTextureManager().getTextureByKey("wall_horizontal_active"),
				Config.getTextureManager().getTextureByKey("wall_horizontal_destroyed"));		
		
	}
	
	public WallType getWallType() {
		return WallType.HORIZONTAL;
	}
}