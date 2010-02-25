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

public class Hovel extends Structure {

	private int inhabitants;
	private int maxInhabitants;
	
	public Hovel() {
		super("Hovel", 500, 30, 10,
				Config.getTextureManager().getTextureByKey("house_construction"),
				Config.getTextureManager().getTextureByKey("house_active"),
				Config.getTextureManager().getTextureByKey("house_destroyed"));
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());
		
		this.inhabitants = 0;
		this.maxInhabitants = 250;
	}

	public StructureType getType() {
		return StructureType.HOVEL;
	}
	
	public void setInhabitants(int inhabitants) {
		if(inhabitants <= maxInhabitants) {
			this.inhabitants = inhabitants;	
		}		
	}
	
	public int getInhabitants() {
		return inhabitants;
	}
	
	public int getMaxInhabitants() {
		return maxInhabitants;
	}
	
}
