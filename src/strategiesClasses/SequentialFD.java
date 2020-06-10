package strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class corresponds to the Sequential strategy to count frequencies in an
 * array list.
 * @author pedroirivera-vega
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SequentialFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public SequentialFD() {
		super("Sequential");
	}

	/**
	 * Frequency distribution object that iterates through dataSet and counts how many times
	 * each elements appears.
	 * Time complexity is O(n*m), but since m approaches n, then it behaves like O(n^2).
	 * 
	 * @param dataSet: ArrayList of elements to determine their frequency distribution
	 * @return ArrayList containing entries where the key = element 
	 *         and the value = amount of times that element was present in the dataSet
	 */
	// The following method was provided with by the professor
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		
		for (E e : dataSet) { // traverses through n elements
			boolean entryFound = false; 
			for (int i=0; i<results.size() && !entryFound; i++) { // traverses through m elements
				Map.Entry<E, Integer> entry = results.get(i); 
		
				if (entry.getKey().equals(e)) { 
					entry.setValue(entry.getValue()+1);
					entryFound = true; 
				}
			}
			if (!entryFound) { 
				//need to create a new entry for the first instance found of object e
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(e, 1); 
				results.add(entry); 
			}
		}
		
		return results;
	}

}
