package game;


public class SelectedTileState extends TileState {
	
	
	public SelectedTileState() {		
	}
	
	public void goNext(Tile t) {
		t.setState(new SelectedTileState());
	}
}
