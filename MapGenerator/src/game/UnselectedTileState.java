package game;

public class UnselectedTileState extends TileState {
	
	
	public UnselectedTileState() {

	}	
	
	public void goNext(Tile t) {
		t.setState(new SelectedTileState());
	}
}
