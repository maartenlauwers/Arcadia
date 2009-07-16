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
