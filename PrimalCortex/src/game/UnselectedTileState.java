package game;

import game.roles.Role;

public class UnselectedTileState extends TileState {
	
	
	public UnselectedTileState() {

	}
	
	public void setRole(Tile t, Role role) {
		System.out.println("Can't change the role of this tile (unselected)");
	}
	
	public void goNext(Tile t) {
		t.setState(new SelectedTileState());
	}
}
