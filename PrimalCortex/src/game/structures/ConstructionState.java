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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ConstructionState extends StructureState implements ActionListener {
	
	private int originalTimeLeft;
	private int timeLeft;
	private Timer timer;
	private int progress;
	private Structure structure;
	
	public ConstructionState(Structure structure) {
		this.structure = structure;	
		this.progress = 0;
	}
	
	public void build(int timeToBuild) {
		this.originalTimeLeft = timeToBuild;
		this.timeLeft = timeToBuild;
		
		timer = new Timer(1000, this);
		timer.start(); 		
	}
	
	public void destroy() {
		timer.stop();
		structure.setState(new DestroyedState(structure));
	}
	
	public void repair(int timeToRepair) {
		this.originalTimeLeft = timeToRepair;
		this.timeLeft = timeToRepair;
		
		timer = new Timer(1000, this);		
		timer.start(); 
	}
	
	public int getProgress() {
		return progress;
	}
	
	public boolean isConstructed() {
		return true;
	}
	
	public boolean isActive() {
		return false;
	}
	
	public boolean isDestroyed() {
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {		
		if(timeLeft > 0) {
			timeLeft -= 1;
							
			float dif = originalTimeLeft - timeLeft;
			if(dif > 0) {
				progress = (int)((dif/originalTimeLeft)*100);	
			} else {
				progress = 100;
			}
						
			timer.restart();
		} else {
			
			// Lets use the construction state as the upgrade state because both states share the same characteristics.
			// Every time we reconstruct, we upgrade the level of the building.
			// The destroyed state is responsible for resetting the level of the building.
			
			// We assume that it's possible to advance another level. If the building was just erected, then its current level
			// is zero and thus an upgrade is possible. If it was already existing, then we had to execute the 'upgrade()' method
			// from the Structure class which checked whether a further upgrade was possible or not. Either way, we only get here
			// if a further upgrade is possible.
			structure.setCurrentLevel(structure.getCurrentLevel() + 1);
			
			// Advance to the active state
			structure.setState(new ActiveState(structure));					
			
			timer.stop();
		}
	}
	
}
