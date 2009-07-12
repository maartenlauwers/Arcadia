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
	
	public WorldMap(int pixelWidth, int pixelHeight, int nrTilesHorizontal, int nrTilesVertical, int tileWidth, int tileHeight) {
		super(pixelWidth, pixelHeight, nrTilesHorizontal, nrTilesVertical);			
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.nrHorizontalTilesOnScreen = Config.getScreenWidth()/tileWidth + 1;
		this.nrVerticalTilesOnScreen = Config.getScreenHeight()/tileHeight + 1;
		this.offsetX = 0;
		this.offsetY = 0;
		
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
		if(offsetX < super.getNrTilesHorizontal() - (Config.getScreenWidth()/tileWidth) + 1) {
			/*
			for(int y = offsetY; y < offsetY + (Config.getScreenHeight()/tileHeight); y++) {
				map[offsetX][y] = 0;
				map[offsetX + (Config.getScreenWidth()/tileWidth)][y] = 1;
			}
			*/
			offsetX++;
		}
		
	}
	
	public void moveMapLeft() {
		if(offsetX > 0) {
			/*
			for(int y = offsetY; y < offsetY + (Config.getScreenHeight()/tileHeight); y++) {
				map[offsetX + ((Config.getScreenWidth()/tileWidth) - 1)][y] = 0;
				map[offsetX - 1][y] = 1;
			}
			*/
			offsetX--;	
		}
		
	}
	
	public void moveMapUp() {
		if(offsetY > 0) {
			/*
			for(int x = offsetX; x < offsetX + (Config.getScreenWidth()/tileWidth); x++) {
				map[x][offsetY + ((Config.getScreenHeight()/tileHeight) - 1)] = 0;
				map[x][offsetY - 1] = 1;
			}
			*/
			offsetY--;
		}
		
	}
	
	public void moveMapDown() {		
		if(offsetY < super.getNrTilesVertical() - (Config.getScreenHeight()/tileHeight) + 1) {
			/*
			for(int x = offsetX; x < offsetX + (Config.getScreenWidth()/tileWidth); x++) {
				map[x][offsetY] = 0;
				map[x][offsetY + (Config.getScreenHeight()/tileHeight)] = 1;
			}
			*/
			offsetY++;
		}
		
	}
	
	public void printMap() {
		
		StringBuffer sb = new StringBuffer();
		for(int j=0; j<100; j++) {
			for(int i=0; i<100; i++) {
				sb.append(map[i][j] + ", ");				
			}
			
			System.out.println(sb.toString());
			sb = new StringBuffer();
		}
	}
	
	public void draw() {
		
		int nrTilesHorizontal = super.getNrTilesHorizontal();
		List<Tile> tileList = super.getTileList();		
		List<Tile> tileListCopy = new ArrayList<Tile>();		
		
		int[] requiredIndices = new int[nrHorizontalTilesOnScreen * nrVerticalTilesOnScreen];
		int index = 0;
		
		// Fetch and draw only the visible tiles					
		int firstIndex = (nrTilesHorizontal * offsetY) + offsetX;					
		int lengthToNextRow = nrTilesHorizontal;												
		
		for(int i=firstIndex; i<nrVerticalTilesOnScreen*lengthToNextRow; i += lengthToNextRow) {			
			for(int j=i; j<(i + nrHorizontalTilesOnScreen); j++) {				
				requiredIndices[index] = j;
				index++;
			}	
		}
								
		for(int a=0; a<requiredIndices.length; a++) {				
			Tile t = tileList.get(requiredIndices[a]);
			tileListCopy.add(t);
		}
		
		
		int horizontalTilesChecked = 0;
		int verticalTilesChecked = 0;
		for(int k=0; k<tileListCopy.size(); k++) {
			
			Tile t = tileListCopy.get(k);
			t.setX(horizontalTilesChecked*64);
			t.setY(verticalTilesChecked*64);
			
			horizontalTilesChecked++;
			if(horizontalTilesChecked > nrHorizontalTilesOnScreen) {
				horizontalTilesChecked = 0;
				verticalTilesChecked++;
			}						
		}		
		
		for(Tile t : tileListCopy) {
			t.draw();
		}						
	}
}


