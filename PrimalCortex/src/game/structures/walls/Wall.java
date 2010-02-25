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

package game.structures.walls;

import game.structures.ConstructionState;
import game.structures.Structure;
import game.structures.StructureType;

import org.newdawn.slick.Image;

public abstract class Wall extends Structure {		
	
	public Wall(Image construction, Image active, Image destroyed) {
		super("Wall", 100, 1, 10, 
				construction,
				active,
				destroyed);					
		
		setState(new ConstructionState(this));			
		build(getTimeToBuild());

	}
	
	public StructureType getType() {
		return StructureType.WALL;
	}
	
	public abstract WallType getWallType();
}