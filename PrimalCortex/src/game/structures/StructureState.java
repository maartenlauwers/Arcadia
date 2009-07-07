package game.structures;

public abstract class StructureState {

	public StructureState() {	
	}
	
	public abstract void build(int timeToBuild);
	public abstract void destroy();
	public abstract void repair(int timeToRepair);
	public abstract int getProgress();
	
	public abstract boolean isConstructed();
	public abstract boolean isActive();
	public abstract boolean isDestroyed();
}
