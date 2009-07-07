package engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class PictureBox implements Widget {

	private String id;
	private String text;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	
	public PictureBox(String id, int x, int y, int width, int height, Image image) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;		
	}
	
	public void setId(String id) {
		this.id = id;
	}	
	
	public void setX(int x) {
		this.x = x ;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public WidgetType getType() {
		return WidgetType.LABEL;
	}
	
	public void draw(Graphics g) {
		image.draw(x, y, width, height);
	}

}
