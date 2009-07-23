package game.structures;

import org.newdawn.slick.Image;

public abstract class Structure {

	private Image texture_construction;
	private Image texture_active;
	private Image texture_destroyed;
	
	private String name;
	private int cost;
	private int timeToBuild;
	private int health;
	private int currentLevel;
	private int maxLevel;
	private StructureState currentState;
	
	public Structure(String name, int cost, int timeToBuild, int maxLevel, 
			Image texture_construction, Image texture_active, Image texture_destroyed) {
			this.name = name;
			this.cost = cost;
			this.timeToBuild = timeToBuild;		
			this.health = 0;
			this.currentLevel = 0;
			this.maxLevel = maxLevel;
			
			this.texture_construction = texture_construction;
			this.texture_active = texture_active;
			this.texture_destroyed = texture_destroyed;					
	}
	
	/**
	 * Starts the building process of this structure
	 * 
	 * @param timeToBuild time required in seconds to finish building the structure
	 */
	public void build(int timeToBuild) {
		currentState.build(timeToBuild);		
	}
	
	/**
	 * Destroys the current building
	 */
	public void destroy() {
		currentState.destroy();
	}
	
	/**
	 * Returns the name of the structure
	 * 
	 * @return name of the structure
	 */
	public String getName() {
		return name;
	}	
	
	/**
	 * Returns the cost of the structure
	 * 
	 * @return the cost of the structure
	 */
	public int getCost() {
		return cost;
	}	
	
	/**
	 * Returns the health of this structure
	 * 
	 * @param health the health of the structure
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Returns the health of this structure
	 * 
	 * @return the health of the structure
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the time to build this structure
	 * 
	 * @return the time to build the structure
	 */
	public int getTimeToBuild() {
		return timeToBuild;
	}		
	
	/**
	 * Returns the construction progress of the structure
	 * 
	 * @return the progress percentage
	 */
	public int getBuildProgress() {
		return currentState.getProgress();
	}
	
	/**
	 * Returns the current level of the structure
	 * 
	 * @return the level of the structure
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * Sets the current level of the structure
	 * 
	 * @param currentLevel the new level of the structure
	 */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	/**
	 * Checks whether another building upgrade is possible
	 * 
	 * @return true if another upgrade is possible
	 */
	public boolean isUpgradePossible() {
		return currentLevel < maxLevel;			
	}
	
	/**
	 * Returns the required time to upgrade this structure to the next level.
	 * 
	 * @return the required upgrade time
	 */
	public int getRequiredUpgradeTime() {
		double upgradeTime = timeToBuild * (1 + (currentLevel * 0.20));
		return (int)upgradeTime;
	}
	
	/**
	 * Upgrades the current structure (when possible)
	 */
	public void upgrade() {
		if(isUpgradePossible()) {			
			build(getRequiredUpgradeTime());
		}			
	}
	
	/**
	 * Returns the texture of this structure based on its status
	 * 
	 * @return the current texture of the structure
	 */
	public Image getTexture() {
		if(isConstructed()) {
			return texture_construction;
		} else if(isActive()) {
			return texture_active;
		} else if(isDestroyed()) {
			return texture_destroyed;
		}
		
		return null;
	}
	
	
	public boolean isConstructed() {
		return currentState.isConstructed();
	}
	
	public boolean isActive() {
		return currentState.isActive();
	}
	
	public boolean isDestroyed() {
		return currentState.isDestroyed();
	}
	
	/**
	 * Sets the state of this structure object
	 * 
	 * @param state the new state
	 */
	public void setState(StructureState state) {
		this.currentState = state;
	}
	
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the type of the structure (Farm, market, ...)
	 * 
	 * @return the type of the structure
	 */
	public abstract StructureType getType();
	
}
