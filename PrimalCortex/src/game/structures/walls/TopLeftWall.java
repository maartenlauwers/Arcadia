package game.structures.walls;

import engine.Config;

public class TopLeftWall extends Wall {

	public TopLeftWall() {
		super(Config.getTextureManager().getTextureByKey("wall_topleft_construction"),
				Config.getTextureManager().getTextureByKey("wall_topleft_active"),
				Config.getTextureManager().getTextureByKey("wall_topleft_destroyed"));			
	}
}