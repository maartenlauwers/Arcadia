package game;

import game.roles.Role;

public abstract class TileState {

	public abstract void setRole(Tile t, Role role);
	public abstract void goNext(Tile t);
}
