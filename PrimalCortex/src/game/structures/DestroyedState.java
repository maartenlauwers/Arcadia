package game.structures;

public class DestroyedState extends StructureState {

	private Structure structure;
	
	public DestroyedState(Structure structure) {
		this.structure = structure;		
	}
	
	public void build(int timeToBuild) {
		System.out.println("Cannot build now (destroyedState)");
	}
	
	public void destroy() {
		System.out.println("Already destroyed (destroyedState)");
	}
	
	public void repair(int timeToRepair) {
		structure.setState(new ConstructionState(structure));
		build(timeToRepair);
	}
	
	public int getProgress() {
		return -1;
	}
	
	public boolean isConstructed() {
		return false;
	}
	
	public boolean isActive() {
		return false;
	}
	
	public boolean isDestroyed() {
		return true;
	}
}
