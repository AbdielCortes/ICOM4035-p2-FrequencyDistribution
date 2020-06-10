package strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that implements Ordered strategy to count frequencies in an ArrayList,
 * by first sorting the data set and then proceeding to count the frequencies.
 * @author Abdiel Cortés Cortés
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class OrderedFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public OrderedFD() {
		super("Ordered");
	}

	/**
	 * Frequency distribution object that sorts the data set and then iterates through
	 * it and counts how many times each element is repeated.
	 * Time complexity is O(n) although there is a nested for loop, the outside for loop is only
	 * used to update the value of e and to add entries to results.
	 * 
	 * @param dataSet: ArrayList of elements to determine their frequency distribution
	 * @return ArrayList containing entries where the key = element 
	 *         and the value = amount of times that element was present in the dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		dataSet.sort(null); // sorts data set using built in Arralist.sort() taking up O(n*log(n)) time
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		
		int lastIndex = 0; // keeps track on what part of the data set we're at
		// iterates through u elements, u = amount of unique elements in the dataSet
		for (int i = lastIndex; i < dataSet.size(); i = lastIndex) { // assigns the value of e and adds to entry to results
			E e = dataSet.get(lastIndex);
			
			int count = 0; // counts how many times the value e appears in the data set
			
			// lets say our sorted dataSet is [A, A, B, C, C, C]
			// the first time this loop is called it counts the As, then lastIndex = 2
			// the second time it counts the B, then lastIndex = 3
			// the third and final time it counts the Cs, then lastIndex = 6 so we're finished
			// notice how this loop only visits each value in the data set once,
			// the only thing the outer loop does is update e and add entries to results
			
			// iterates through n elements, stops when it finds an element larger than e
			for (int j = lastIndex; j < dataSet.size() && dataSet.get(j).compareTo(e) <= 0; j++) { 
				if (dataSet.get(j).equals(dataSet.get(dataSet.size()-1))) { // when e is the last unique element in the dataSet
					lastIndex = dataSet.size(); // set lastIndex equal to the size so that we break the outer loop
				} else if (j+1 < dataSet.size() && dataSet.get(j+1).compareTo(e) > 0) { // we found an entry larger than e
					lastIndex = j + 1; // set lastIndex to j + 1 so that the outer loop continues where this one ended
				}
				
				// since the list is sorted we know that every element after e is either equal or larger,
				// since our loop would break if the value were larger than e, the only possibility left
				// is that it be equal to e
				count++; 
			}
			
			results.add(new AbstractMap.SimpleEntry<E, Integer>(e, count)); // adds e to the list with the count from the previous loop
			
		}
		
		return results;
	}

}
