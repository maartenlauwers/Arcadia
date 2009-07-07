package engine;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureManager {

	private HashMap textureMap;
	
	public TextureManager() {
		textureMap = new HashMap();
			
		try {
			Image grass = new Image("grass.png");
			Image farm_construction = new Image("farm_construction2.png");
	    	Image farm_active = new Image("farm_active2.png");
	    	Image market_construction = new Image("market_construction.png");
	    	Image market_active = new Image("market_active.png");
	    	Image house_construction = new Image("house_construction2.png");
	    	Image house_active = new Image("house_active2.png");
	    	//Image barracks_construction = new Image("barracks_construction.png");
	    	//Image barracks_active = new Image("barracks_active.png");
	    	Image barracks_construction = new Image("market_active.png");
	    	Image barracks_active = new Image("market_active.png");
	    	Image wall_vertical_construction = new Image("wall_vertical_construction.png");
	    	Image wall_vertical_active = new Image("wall_vertical.png");
	    	Image wall_horizontal_construction = new Image("wall_horizontal_construction.png");
	    	Image wall_horizontal_active = new Image("wall_horizontal.png");
	    	
	    	Image button = new Image("button.png");
	    	Image buttonActive = new Image("button-active.png");
	    	Image buttonPressed = new Image("button-pressed.png");
	    	Image buttonBackground = new Image("button_background.png");
	    	Image panel = new Image("panel.png");
	    	Image parchment = new Image("parchment.png");
	    	Image border_vertical = new Image("bordertest.png");
	    	Image border_horizontal = new Image("bordertest_horizontal.png");
	    	Image progressbar = new Image("progressbar.png");
	    	
	    	Image population_icon = new Image("population_icon.png");
	    	Image food_icon = new Image("food_icon.png");
	    	Image sword_and_shield = new Image("sword_and_shield.png");
	    	
	    	addTextureByKey("grass", grass);
	    	addTextureByKey("farm_construction", farm_construction);
	    	addTextureByKey("farm_active", farm_active);
	    	addTextureByKey("market_construction", market_construction);
	    	addTextureByKey("market_active", market_active);
	    	addTextureByKey("house_construction", house_construction);
	    	addTextureByKey("house_active", house_active);
	    	addTextureByKey("barracks_construction", barracks_construction);
	    	addTextureByKey("barracks_active", barracks_active);
	    	addTextureByKey("wall_vertical_construction", wall_vertical_construction);
	    	addTextureByKey("wall_vertical_active", wall_vertical_active);
	    	addTextureByKey("wall_horizontal_construction", wall_horizontal_construction);
	    	addTextureByKey("wall_horizontal_active", wall_horizontal_active);
	    	
	    	addTextureByKey("texButton", button);
	    	addTextureByKey("texButtonActive", buttonActive);
	    	addTextureByKey("texButtonPressed", buttonPressed);
	    	addTextureByKey("texButtonBackground", buttonBackground);
	    	addTextureByKey("texPanel", panel);
	    	addTextureByKey("parchment", parchment);
	    	addTextureByKey("border_vertical", border_vertical);
	    	addTextureByKey("border_horizontal", border_horizontal);
	    	addTextureByKey("progressbar", progressbar);
	    	addTextureByKey("population_icon", population_icon);
	    	addTextureByKey("food_icon", food_icon);
	    	addTextureByKey("sword_and_shield", sword_and_shield);
	    	
		} catch (SlickException e) {			
			e.printStackTrace();
		}    	    	    

	}
	
	public Image getTextureByKey(String key) {
		return (Image)textureMap.get(key);
	}
	
	public void addTextureByKey(String key, Image texture) {
		textureMap.put(key, texture);
	}
}
