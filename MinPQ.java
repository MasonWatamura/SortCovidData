package p1cs232;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates and implements a minimum priority queue that will take in a key being new cases
 * and add the data to an array sorting the data from smallest data to largest data. It 
 * will then delete the minimum key when called until there are three and return the key
 * data to the driver to pass the data to {@code BST}
 * 
 * Got code from the book: @author Robert Sedgewick @author Kevin Wayne
 * modified @author Water
 *
 * @param <Key> that the min pq will use for data comparison
 * @throws NA
 * @return NA
 */

public class MinPQ<Key> implements Iterable<CovidData> {
	private CovidData[] pq;
	private int n;
	
	/**
	 * Initializes empty min pq with given initial capacity
	 * 
	 * @return NA
	 * @throws NA
	 * @param initCapacity
	 */
	public MinPQ(int initcapacity) {
		pq = (CovidData[]) new CovidData[initcapacity + 1];
		n = 0;
	}

    /**
     * Returns true if this pq is empty.
     *
     * @return {@code true} if this pq is empty {@code false} otherwise
     * @param NA
     * @throws NA
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys in this pq
     *
     * @return the number of keys in the min pq 
     * @param NA
     * @throws NA
     */
    public int size() {
        return n;
    }


    /**
     * Adds a new key to the min pq
     *
     * @param  x the key to add to this pq
     * @return NA
     * @throws NA
     */
    public void insert(CovidData x) {
    	pq[n] = x;
    	n++;
    }

    /**
     * Removes and returns a smallest key from the min pq
     *
     * @return smallest key from the min pq
     * @param NA
     * @throws NoSuchElementException if this priority queue is empty
     */
    public CovidData delMin() {
        CovidData temp = pq[0];
        
        for(int i = 0; i < n; i++) {      //finds smallest values
        	if(pq[i].compareTo(temp) == 1) {
        		temp = pq[i];
        	}
        }
        for(int j = 0; j < n; j++) {     //removes one of the smallest values is there are multiple
        	if(pq[j] == temp) {
        		pq[j] = null;
        		break;
        	}
        }
        for (int j = 0; j < n; j++){   //moves null to the end if it is not
            if (pq[j] == null){
                for (int k = j + 1; k < n; k++){
                    pq[k-1] = pq[k];
                }
                pq[n-1] = null;
            }
        }
        n--;  //subtracts one from size
        return temp;
    }
    
    /**
     * temporary method that was used to test the program and make sure the minPQ was working correctly
     * 
     * @param NA
     * @return NA
     * @throws NA
     */
    public void print() {
    	for(int i = 0; i < n; i++) {
    		System.out.println(pq[i].new_cases() + " " + pq[i].country());
    	}
    	System.out.println();
    }

	@Override
	public Iterator<CovidData> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
