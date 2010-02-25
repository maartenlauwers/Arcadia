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


public class ActiveState extends StructureState {

	private Structure structure;
	
	public ActiveState(Structure structure) {
		this.structure = structure;		
	}
	
	public void build(int timeToBuild) {
		System.out.println("Cannot build now (activeState)");
	}
	
	public void destroy() {
		structure.setState(new DestroyedState(structure));
	}
	
	public void repair(int timeToRepair) {
		System.out.println("Nothing to repair (activeState)");
	}
	
	public int getProgress() {
		return -1;
	}
	
	public boolean isConstructed() {
		return false;
	}
	
	public boolean isActive() {
		return true;
	}
	
	public boolean isDestroyed() {
		return false;
	}
}
