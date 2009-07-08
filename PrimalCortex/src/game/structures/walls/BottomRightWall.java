package game.structures.walls;

import engine.Config;

public class BottomRightWall extends Wall {
		
	public BottomRightWall() {
		super(Config.getTextureManager().getTextureByKey("wall_bottomright_construction"),
				Config.getTextureManager().getTextureByKey("wall_bottomright_active"),
				Config.getTextureManager().getTextureByKey("wall_bottomright_destroyed"));		
	}
}