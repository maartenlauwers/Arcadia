package game;

import game.roles.Role;

public class SelectedTileState extends TileState {
	
	
	public SelectedTileState() {		
	}
	
	public void setRole(Tile t, Role role) {
		//t.setRole(role);
	}
	
	public void goNext(Tile t) {
		t.setState(new SelectedTileState());
	}
}
