/*
 *   Copyright (C) 2010 Maarten Lauwers
 *
 *   This program is free software; you can redistribute it and/or modify it under the terms of the 
 *   GNU General Public License as published by the Free Software Foundation; either version 2 of the License, 
 *   or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 *   without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *   See the GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along with this program; 
 *   if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 *	 Email: maartenlauwers007@gmail.com 
 */

package game.map;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import engine.Config;
import game.tile.Tile;

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
	
	public void draw(Graphics g) {
		
		
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
			t.draw(g);
		}
					
	}
}


