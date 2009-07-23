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
