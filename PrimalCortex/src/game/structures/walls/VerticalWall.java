package game.structures.walls;


import engine.Config;

public class VerticalWall extends Wall {
	
	public VerticalWall() {
		super(Config.getTextureManager().getTextureByKey("wall_vertical_construction"),
				Config.getTextureManager().getTextureByKey("wall_vertical_active"),
				Config.getTextureManager().getTextureByKey("wall_vertical_destroyed"));					

	}	
}