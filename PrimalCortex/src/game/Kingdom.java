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
