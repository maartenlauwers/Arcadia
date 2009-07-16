package game;


public class Kingdom {
	
	private String name;
	private int population;
	private int wealth;
	private int food;
	
	public Kingdom(String name) {
		this.name = name;
		this.population = 500;
		this.wealth = 5000;
		this.food = 30000;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWealth() {
		return wealth;
	}	
	
	public void setWealth(int wealth) {
		this.wealth = wealth;		
	}
	
	public int getFood() {
		return food;
	}
	
	public void setFood(int food) {
		this.food = food;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public boolean gotTheCoin(int cost) {
		if (wealth >= cost) {
			return true;
		}
		
		return false;
	}
	
	public boolean gotTheFood(int cost) {
		if(food >= cost) {
			return true;
		}
		
		return false;
	}

}
