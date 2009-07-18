package game.map;

import java.util.ArrayList;
import java.util.List;

import engine.Config;
import game.Tile;

public class WorldMap extends Map {

	private int[][] map;	
	private int tileWidth;
	private int tileHeight;
	private int nrHorizontalTilesOnScreen;
	private int nrVerticalTilesOnScreen;
	private int offsetX;
	private int offsetY;
	private List<Tile> tileListCopy;
	
	public WorldMap(int pixelWidth, int pixelHeight, int nrTilesHorizontal, int nrTilesVertical, int tileWidth, int tileHeight) {
		super(pixelWidth, pixelHeight, nrTilesHorizontal, nrTilesVertical);			
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.nrHorizontalTilesOnScreen = Config.getScreenWidth()/tileWidth + 1;
		this.nrVerticalTilesOnScreen = Config.getScreenHeight()/tileHeight + 1;
		this.offsetX = 0;
		this.offsetY = 0;
		tileListCopy = new ArrayList<Tile>();
		
		map = new int[nrTilesHorizontal][nrTilesVertical];
		
		for(int i=0; i<nrTilesHorizontal; i++) {
			for(int j=0; j<nrTilesVertical; j++) {
				map[i][j] = 0;
				if(i < nrHorizontalTilesOnScreen) {
					if(j < nrVerticalTilesOnScreen) {
						map[i][j] = 1;
					}
				}
			}
		}
		
		//printMap();
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}	
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}
	
	public void moveMapRight() {
		if(offsetX < super.getNrTilesHorizontal() - nrHorizontalTilesOnScreen) {
			offsetX++;
		}
		
	}
	
	public void moveMapLeft() {
		if(offsetX > 0) {			
			offsetX--;	
		}
		
	}
	
	public void moveMapUp() {
		if(offsetY > 0) {			
			offsetY--;
		}
		
	}
	
	public void moveMapDown() {		
		if(offsetY < super.getNrTilesVertical() - nrVerticalTilesOnScreen) {
			offsetY++;
		}
		
	}	
	
	public List<Tile> getOnScreenTileList() {
		return tileListCopy;
	}
	
	public void draw() {
		
		
		int[] requiredIndices = new int[nrHorizontalTilesOnScreen * nrVerticalTilesOnScreen];
		
		int nrTilesHorizontal = super.getNrTilesHorizontal();		
		int lengthToNextRow = nrTilesHorizontal;	
		
		List<Tile> tileList = super.getTileList();						
		tileListCopy = new ArrayList<Tile>();
		
		int startIndex = offsetX + (offsetY * nrTilesHorizontal);				
					
		int index = 0;				
		int counter = 0;
		int i = startIndex;
		while(counter < nrVerticalTilesOnScreen) {
			for(int j=i; j<i+nrHorizontalTilesOnScreen; j++) {
				requiredIndices[index] = j;				
				index++;				
			}
			i += lengthToNextRow;
			counter++;
		}				
		
		
		for(int a=0; a<requiredIndices.length; a++) {				
			Tile t = tileList.get(requiredIndices[a]);
			tileListCopy.add(t);
		}		
		
		
		int horTilesChecked = 0;
		int verTilesChecked = 0;
		for(Tile t : tileListCopy) {
			t.setX(horTilesChecked*tileWidth);
			t.setY(verTilesChecked*tileHeight + 30);
			
			horTilesChecked++;
			if(horTilesChecked == nrHorizontalTilesOnScreen) {
				horTilesChecked = 0;
				verTilesChecked++;
			}
			System.out.println("X: " + t.getX() + ", Y: " + t.getY());
			t.draw();
		}
					
	}
}


