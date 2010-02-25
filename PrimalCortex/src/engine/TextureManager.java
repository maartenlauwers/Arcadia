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

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureManager {

	private HashMap textureMap;
	
	public TextureManager() {
		textureMap = new HashMap();
			
		try {
			Image grass = new Image("grass.png");
			Image dirt = new Image("dirt.png");
			Image water = new Image("water.png");
			Image forest = new Image("forest.png");
			Image transparent = new Image("transparent.png");
			
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
	    	
	    	// Walls
	    	Image wall_vertical_construction = new Image("wall_vertical_construction.png");
	    	Image wall_vertical_active = new Image("wall_vertical.png");
	    	Image wall_horizontal_construction = new Image("wall_horizontal_construction.png");
	    	Image wall_horizontal_active = new Image("wall_horizontal.png");
	    	// Wall corners
	    	Image wall_topleft_active = new Image("wall_topleft.png");
	    	Image wall_topleft_construction = new Image("wall_topleft_construction.png");
	    	Image wall_topright_active = new Image("wall_topright.png");
	    	Image wall_topright_construction = new Image("wall_topright_construction.png");
	    	Image wall_bottomright_active = new Image("wall_bottomright.png");
	    	Image wall_bottomright_construction = new Image("wall_bottomright_construction.png");
	    	Image wall_bottomleft_active = new Image("wall_bottomleft.png");
	    	Image wall_bottomleft_construction = new Image("wall_bottomleft_construction.png");
	    	// Wall T shapes
	    	Image wall_T_right_active = new Image("wall_T_right.png");
	    	Image wall_T_right_construction = new Image("wall_T_right_construction.png");
	    	Image wall_T_bottom_active = new Image("wall_T_bottom.png");
	    	Image wall_T_bottom_construction = new Image("wall_T_bottom_construction.png");
	    	Image wall_T_left_active = new Image("wall_T_left.png");
	    	Image wall_T_left_construction = new Image("wall_T_left_construction.png");
	    	Image wall_T_top_active = new Image("wall_T_top.png");
	    	Image wall_T_top_construction = new Image("wall_T_top_construction.png");
	    	// Wall cross
	    	Image wall_cross_construction = new Image("wall_cross_construction.png");
	    	Image wall_cross_active = new Image("wall_cross.png");	    	
	    	
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
	    	Image gold_icon = new Image("gold_icon.png");
	    	Image sword_and_shield = new Image("sword_and_shield.png");
	    	Image sorceress = new Image("sorceress.gif");
	    	
	    	addTextureByKey("grass", grass);
	    	addTextureByKey("dirt", dirt);
	    	addTextureByKey("water", water);
	    	addTextureByKey("forest", forest);
	    	addTextureByKey("transparent", transparent);
	    	
	    	addTextureByKey("farm_construction", farm_construction);
	    	addTextureByKey("farm_active", farm_active);
	    	addTextureByKey("market_construction", market_construction);
	    	addTextureByKey("market_active", market_active);
	    	addTextureByKey("house_construction", house_construction);
	    	addTextureByKey("house_active", house_active);
	    	addTextureByKey("barracks_construction", barracks_construction);
	    	addTextureByKey("barracks_active", barracks_active);
	    	
	    	// Walls
	    	addTextureByKey("wall_vertical_construction", wall_vertical_construction);
	    	addTextureByKey("wall_vertical_active", wall_vertical_active);
	    	addTextureByKey("wall_horizontal_construction", wall_horizontal_construction);
	    	addTextureByKey("wall_horizontal_active", wall_horizontal_active);
	    	// Walls corners
	    	addTextureByKey("wall_topleft_active", wall_topleft_active);
	    	addTextureByKey("wall_topleft_construction", wall_topleft_construction);
	    	addTextureByKey("wall_topright_active", wall_topright_active);
	    	addTextureByKey("wall_topright_construction", wall_topright_construction);
	    	addTextureByKey("wall_bottomright_active", wall_bottomright_active);
	    	addTextureByKey("wall_bottomright_construction", wall_bottomright_construction);
	    	addTextureByKey("wall_bottomleft_active", wall_bottomleft_active);
	    	addTextureByKey("wall_bottomleft_construction", wall_bottomleft_construction);
	    	// Walls T shapes
	    	addTextureByKey("wall_T_right_construction", wall_T_right_construction);
	    	addTextureByKey("wall_T_right_active", wall_T_right_active);
	    	addTextureByKey("wall_T_bottom_construction", wall_T_bottom_construction);
	    	addTextureByKey("wall_T_bottom_active", wall_T_bottom_active);
	    	addTextureByKey("wall_T_left_construction", wall_T_left_construction);
	    	addTextureByKey("wall_T_left_active", wall_T_left_active);
	    	addTextureByKey("wall_T_top_construction", wall_T_top_construction);
	    	addTextureByKey("wall_T_top_active", wall_T_top_active);	    	
	    	// Walls cross
	    	addTextureByKey("wall_cross_construction", wall_cross_construction);
	    	addTextureByKey("wall_cross_active", wall_cross_active);
	    	
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
	    	addTextureByKey("gold_icon", gold_icon);
	    	addTextureByKey("sword_and_shield", sword_and_shield);
	    	addTextureByKey("sorceress", sorceress);
	    	
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
