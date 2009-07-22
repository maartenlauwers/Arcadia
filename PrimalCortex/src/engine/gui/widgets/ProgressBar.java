package engine.gui.widgets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ProgressBar implements Widget {

	private String id;
	private String text;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	
	private int progress;
	
	public ProgressBar(String id, String text, int x, int y, int width, int height, Image image, int progress) {
		this.id = id;
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		
		this.progress = progress;
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
	
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setText(String text) {
		this.text = text;		
	}
	
	public String getText() {
		return text;
	}
	
	public WidgetType getType() {
		return WidgetType.LABEL;
	}
	
	public void draw(Graphics g) {		
		
		// Align the text to the left of the bar
		g.drawRect(x, y, width, height);
		if(progress > 0) {
			image.draw(x+2, y+2, width/100 * progress - 3, height - 3);
			g.drawString(text, x + 5, y + 2);
		} else {
			image.draw(x+2, y+2, width/100 * progress, height - 3);
			g.drawString(text, x + 5, y + 2);
		}
		
		
	}
}
