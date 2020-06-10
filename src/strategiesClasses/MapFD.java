package strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that implements the Map strategy to count frequencies in an ArrayList.
 * @author Abdiel Cortés Cortés
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class MapFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public MapFD() {
		super("Map");
	}

	/**
	 * Frequency distribution object that uses a map to count how many times an element e
	 * appears in the dataSet.
	 * Time complexity is O(n + m), but since m approaches n, then it behaves like O(2n) 
	 * which simplifies to O(n).
	 * 
	 * @param dataSet: ArrayList of elements to determine their frequency distribution
	 * @return ArrayList containing entries where the key = element 
	 *         and the value = amount of times that element was present in the dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		Hashtable<E, Integer> map = new Hashtable<E, Integer>(); // map to store the elements and their frequency
		
		for (E e: dataSet) { // iterate through all values in the dataSet, iterates through n elements
			// thanks to hashing, using containsKey is O(1), so we don't have a nested loop
			if (map.containsKey(e)) { // if there is and entry corresponding to e already in the map
				map.put(e, map.get(e) + 1); // we update the entry by increasing its value by 1
			} else { // else there is no entry corresponding to e
				map.put(e, 1); // this is the first time we see e, so its only appeared 1 time
			}
		}
		
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		for (E key: map.keySet()) { // iterate through all the keys in the map, iterates through m elements
			// we insert into results a new entry corresponding to the same entry that was in the map
			results.add(new AbstractMap.SimpleEntry<E, Integer>(key, map.get(key))); 
		}
		
		return results;
	}

}
