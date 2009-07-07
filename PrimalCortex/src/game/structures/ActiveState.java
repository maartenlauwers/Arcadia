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
