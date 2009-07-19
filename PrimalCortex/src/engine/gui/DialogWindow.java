package engine.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import engine.Config;
import engine.gui.widgets.Button;
import engine.gui.widgets.Label;
import engine.gui.widgets.Panel;
import engine.gui.widgets.Widget;
import engine.gui.widgets.WidgetType;
import game.Game;

public class DialogWindow implements Window {
	
	private WindowStatus status;
	
	private String id;
	private String title;	
	private String message;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private List<Widget> widgetList;
	
	public DialogWindow(String id, String title, String message) {
		
		TrueTypeFont ttf = Config.getCurrentFont();
		
		this.status = WindowStatus.ACTIVE;
		this.id = id;
		this.title = title;
		this.message = message;
		this.width = ttf.getWidth(message) + 40;
		this.height = ttf.getHeight() + 60;
		this.x = (Config.getScreenWidth() - width)/2;
		this.y = (Config.getScreenHeight() - height)/2;
		
		widgetList = new ArrayList<Widget>();	
		
		
		//TODO: Add title to the dialog window
		addWidget(new Panel(id, 0, 0, width, height, true));		
		
		
		addWidget(new Label(id, width/2 - ttf.getWidth(message)/2, 15, message));
		addWidget(new Button(id, (width - 100)/2, height - 30, "Ok", "Ok"));	
	}
	
	public DialogWindow(String id, String title, String message, int width, int height) {
		this.status = WindowStatus.ACTIVE;
		this.id = id;
		this.title = title;
		this.message = message;
		this.width = width;
		this.height = height;
		this.x = (Config.getScreenWidth() - width)/2;
		this.y = (Config.getScreenHeight() - height)/2;
		
		widgetList = new ArrayList<Widget>();	
		
		
		//TODO: Add title to the dialog window
		addWidget(new Panel(id, 0, 0, width, height, true));		
		
		TrueTypeFont ttf = Config.getCurrentFont();
		addWidget(new Label(id, width/2 - ttf.getWidth(message)/2, height/2 - ttf.getHeight()/2, message));
		addWidget(new Button(id, (width - 100)/2, height - 30, "Ok", "Ok"));	
	}
	
	public void addWidget(Widget w) {
		// We assume the coordinates of the widget have to be interpreted as relative to the window coordinates.
		w.setX(w.getX() + x);
		w.setY(w.getY() + y);
		w.setId(id);
		widgetList.add(w);
	}
	
	public void addGuiListener(Game game) {
		for(Widget w : widgetList) {
			if (w.getType() == WidgetType.BUTTON) {
				Button b = (Button)w;
				b.addGuiListener(game);
			}
		}	
	}	
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setX(int x) {
		this.x = x;
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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public WindowStatus getStatus() {
		return status;
	}
	
	public void setStatus(WindowStatus status) {
		this.status = status;
	}	
	
	public boolean isMouseClicked(int mouseX, int mouseY) {
		boolean clicked = false;
		
		for(Widget w : widgetList) {
			if (w.getType() == WidgetType.BUTTON) {				
				Button b = (Button)w;
				if(b.gotClicked(mouseX, mouseY)) {
					clicked = true;
					break;
				}							
			}
		}
		
		return clicked;
	}
	
	public void isMouseOver(int mouseX, int mouseY) {
		for(Widget w : widgetList) {
			if (w.getType() == WidgetType.BUTTON) {
				Button b = (Button)w;
				b.isMouseOver(mouseX, mouseY);
			}
		}
	}

	@Override
	public void draw(Graphics g) {						
		
		// Draw the widgets
		for(Widget w : widgetList) {
			w.draw(g);
		}					
	}	
	
}
