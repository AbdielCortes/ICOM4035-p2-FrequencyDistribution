package strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import sortedlist.SortedList;
import sortedlist.SortedArrayList;

/**
 * This class implements the SortedList strategy to count frequencies in an ArrayList.
 * @author Abdiel Cortés Cortés
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SortedListFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	/**
	 * Our SortedList classes require the data type to be Comparable.
	 * However, Map.Entry and AbstractMap.SimpleEntry are not Comparable,
	 * so we extend AbstractMap.SimpleEntry and create a Comparable
	 * version that we can use with our SortedList.
	 * Note: The K (key) of this class will be the E of SortedListFD,
	 *       so it will be Comparable.
	 * @author Juan O. Lopez
	 *
	 * @param <K>  The type of the key of each entry
	 * @param <V>  The type of the value of each entry
	 */
	@SuppressWarnings("serial")
	private static class ComparableEntry<K extends Comparable<K>, V>
			extends AbstractMap.SimpleEntry<K, V>
			implements Comparable<Map.Entry<K, V>> {

		public ComparableEntry(K key, V value) {
			super(key, value);
		}

		@Override
		public int compareTo(Map.Entry<K, V> entry) {
			/* Entries will be compared based on their keys, which are Comparable */
			return getKey().compareTo(entry.getKey());
		}
		
	} // End of ComparableEntry class

	/* Constructor */
	public SortedListFD() {
		super("SortedList");
	}

	/**
	 * Frequency distribution object that inserts entries into a sortedList first as to
	 * make finding previous entries faster and then copies them over to results.
	 * Time complexity is O(n*m), but since m approaches n, then it behaves like O(n^2).
	 * 
	 * @param dataSet: ArrayList of elements to determine their frequency distribution
	 * @return ArrayList containing entries where the key = element 
	 *         and the value = amount of times that element was present in the dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		SortedList<ComparableEntry<E, Integer>> sortedList = new SortedArrayList<ComparableEntry<E, Integer>>(1000); 
		
		for (E e: dataSet) { // iterate through entire sorted dataSet, traverses through n elements
			ComparableEntry<E, Integer> temp = new ComparableEntry<E, Integer>(e, 1);
			boolean found = false; // false when an entry corresponding to e is not present in the sortedList
			
			// traverses through m elements
			// iterates through sortedList until we find and entry corresponding to e or we reach an entry larger than e
			for (int i = 0; i < sortedList.size() && !found && sortedList.get(i).compareTo(temp) <= 0; i++) { 
				if (sortedList.get(i).compareTo(temp) == 0) { // we found an occurrence of e
					// insert new entry with the same key and a value equal to value+1 
					sortedList.get(i).setValue(sortedList.get(i).getValue() + 1);
					found = true; // breaks loop
				}
			} 
			
			if (!found) { // if e didn't already exist in sortedList, or sortedList was empty (first iteration of while loop)
				sortedList.add(temp); // add entry with value 1
				found = false;
			}
		}
		
		// inserting all entries in sortedList into results
		// takes up O(m), the loop before it takes a lot longer so this term cancels out
		for (int j = 0; j < sortedList.size(); j++) {
			results.add(sortedList.get(j));
		}

		return results;
	}

}