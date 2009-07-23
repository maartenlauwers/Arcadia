package engine.gui.widgets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Font;

import engine.Config;

public class Label implements Widget {

	private String id;
	private String text;
	private int x;
	private int y;		
	
	public Label(String id, int x, int y, String text) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.text = text;		
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
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
		g.setFont(Config.getFont1());
		g.drawString(text, x, y);
	}

}
