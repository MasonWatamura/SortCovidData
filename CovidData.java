package p1cs232;

/**
 * The {@code DataStructure} class represents the excess data that we want to store
 * to be able to call later. It is our value that follows the amount of new cases which is
 * the key until needing to be called for the output file.
 * 
 * @author Mason Watamura
 */
public class CovidData implements Comparable<CovidData>{
	private String country;
	private long population;
	private String continent;
	private long total_cases;
	private String date;
	private long new_cases;
	
	public CovidData(String continent, String country, String date, long total_cases, long new_cases, long population){
		this.continent = continent;
		this.population = population;
		this.country = country;
		this.total_cases = total_cases;
		this.date = date;
		this.new_cases = new_cases;
	}
	public CovidData() {
		
	}
	
	/**
	 * Compares current new cases with new new cases coming in to see if one is bigger than the other
	 * to figure out where in the min pq to insert the data
	 * 
	 * @param a to call on methods in {@code DataStructure}
	 * @return
	 * @throws NA
	 */
	public int compareTo(CovidData a) {
		if(a.new_cases() > new_cases){
			return 1;
    	}
		else if(a.new_cases() < new_cases) {
			return -1;
		}
		return 0;
    }
	
	/**
	 * returns the string statement that will go to the output file
	 * 
	 * @return the string statement that will go to the output file
	 * @throws NA
	 * @param NA
	 */
	public String returnAll() {
		return " at " + country + "/" + continent + " on " + date + " Total: " + total_cases + " Pop: " + population;
	}
	
	/**
	 * returns the number of new cases stored
	 * 
	 * @return the new cases
	 * @throws NA
	 * @param NA
	 */
	public long new_cases() {
		return new_cases;
	}
	/**
	 * returns the country stored
	 * 
	 * @return the country
	 * @throws NA
	 * @param NA
	 */
	public String country() {
		return country;
	}
	
}
