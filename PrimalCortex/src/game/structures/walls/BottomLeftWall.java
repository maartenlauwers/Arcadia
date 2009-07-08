package game.structures.walls;

import engine.Config;

public class BottomLeftWall extends Wall {
		
	public BottomLeftWall() {
		super(Config.getTextureManager().getTextureByKey("wall_bottomleft_construction"),
				Config.getTextureManager().getTextureByKey("wall_bottomleft_active"),
				Config.getTextureManager().getTextureByKey("wall_bottomleft_destroyed"));		
		
	}
	
	public WallType getWallType() {
		return WallType.BOTTOM_LEFT;
	}
}