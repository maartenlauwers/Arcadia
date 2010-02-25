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

package engine;

/**
 * Defines a 'camera' for the engine that defines what part of the screen you're looking at. 
 * 
 * @author Maarten Lauwers
 *
 */
public class Camera {

	private int cameraX;
	private int cameraY;
	private int cameraScreenWidth;
	private int cameraScreenHeight;
	
	public Camera(int cameraScreenWidth, int cameraScreenHeight) {
		this.cameraX = 0;
		this.cameraY = 0;
		this.cameraScreenWidth = cameraScreenWidth;
		this.cameraScreenHeight = cameraScreenHeight;
	}
	
	public void setCameraX(int cameraX) {
		this.cameraX = cameraX;
	}
	
	public int getCameraX() {
		return cameraX;
	}
	
	public void setCameraY(int cameraY) {
		this.cameraY = cameraY;
	}
	
	public int getCameraY() {
		return cameraY;
	}
	
	public void moveCameraLeft(int offset) {
		this.cameraX -= offset;
	}
	
	public void moveCameraRight(int offset) {
		this.cameraX += offset;
	}
	
	public void moveCameraUp(int offset) {
		this.cameraY -= offset;
	}
	
	public void moveCameraDown(int offset) {
		this.cameraY += offset;
	}
	
	
	public void setCameraScreenWidth(int cameraScreenWidth) {
		this.cameraScreenWidth = cameraScreenWidth;
	}
	
	public int getCameraScreenWidth() {
		return cameraScreenWidth;
	}
	
	public void setCameraScreenHeight(int cameraScreenHeight) {
		this.cameraScreenHeight = cameraScreenHeight;
	}
	
	public int getCameraScreenHeight() {
		return cameraScreenHeight;
	}
		
}
