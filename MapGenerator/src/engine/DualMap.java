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

package engine;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class DualMap implements Iterable {

	private List<Object> values;
	private List<String> keys;
	
	public DualMap() {
		values = new ArrayList<Object>();
		keys = new ArrayList<String>();
	}
	
	/**
	 * Adds a key/value pair to the map
	 * @param key The unique key
	 * @param value The value
	 */
	public void add(String key, Object value) {
		if(!keys.contains(key)) {
			keys.add(key);
			values.add(value);	
		}		
	}
	
	/**
	 * Retrieves a value corresponding to the key
	 * 
	 * @param key The corresponding key
	 * @return the corresponding value
	 */
	public Object get(String key) {
		int index = keys.indexOf(key);
		if (index != -1) {
			return values.get(index);
		}
		
		return null;
	}
	
	/**
	 * Retrieves a value based on the index
	 * 
	 * @param index
	 * @return the corresponding value
	 */
	public Object get(int index) {
		return values.get(index);
	}
	
	/**
	 * Retrieves all values
	 * 
	 * @return A list with all values stored
	 */
	public List<Object> getValues() {
		return values;
	}
	
	/** 
	 * Removes a key/value pair based on the key
	 * 
	 * @param key The key of the key/value pair to be removed
	 */
	public void remove(String key) {
		int index = keys.indexOf(key);
		if (index != -1) {
			keys.remove(index);
			values.remove(index);
		}			
	}
	
	/**
	 * Removes a key/value pair based on the index
	 * 
	 * @param index The corresponding index
	 */
	public void remove(int index) {
		keys.remove(index);
		values.remove(index);
	}
	
	/**
	 * Replaces a value with a new one
	 * @param key The corresponding key of the value
	 * @param value The new value
	 */
	public void replace(String key, Object value) {
		if(keys.contains(key)) {
			int index = keys.indexOf(key);
			values.set(index, value);			
		}		
	}
	
	public int size() {
		return keys.size();
	}

	@Override
	public Iterator iterator() {		
		Iterator it = values.iterator();
        return it;
	}

}
