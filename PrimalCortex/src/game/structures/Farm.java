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

package game.structures;


import engine.Config;

public class Farm extends Structure {
	
	private final int baseProduction = 1;	
	private int currentLevel;
		
	private int generatedFood;
	
	public Farm() {
		super("Farm", 500, 60, 5,
				Config.getTextureManager().getTextureByKey("farm_construction"),
				Config.getTextureManager().getTextureByKey("farm_active"),
				Config.getTextureManager().getTextureByKey("farm_destroyed"));		
		
		currentLevel = 1;
		generatedFood = 0;			
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.FARM;
	}

	public int getGeneratedFood() {
		if(super.isActive()) {
			generatedFood += currentLevel * baseProduction;
			return currentLevel * baseProduction;	
		}
		
		return 0;
	}
}
