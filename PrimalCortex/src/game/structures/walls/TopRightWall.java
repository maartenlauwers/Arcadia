package game.structures.walls;

import engine.Config;

public class TopRightWall extends Wall {
	
	public TopRightWall() {
		super(Config.getTextureManager().getTextureByKey("wall_topright_construction"),
				Config.getTextureManager().getTextureByKey("wall_topright_active"),
				Config.getTextureManager().getTextureByKey("wall_topright_destroyed"));				

	}
}