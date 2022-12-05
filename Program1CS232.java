package p1cs232;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Mason Watamura
 *
 * The driver code that implements the {@code BST} class and the {@code MinPq} class. It will read in a csv file and use a scanner to read
 * the lines read through the first line since it is the column names. It will open the output file and print the 
 * "inorder traversal" then start an if statement going through the data by each line. It will split each line of data and put the data in 
 * {@code DataStructure} and then insert into {@code MinPQ} until the country changes. It will call {@code delMin} until there are three countries
 * in the min pq. When size hits three, it will return the data and with the data use the put method in {@code BST}to put data in a binary search tree
 * It will repeat this process for all the countries in the data and call a method in {@code BST} to print all the data
 * into an output file using the {@code InOrder} method.
 * 
 * @throws FileNotFoundException if the file is not found
 * @returns NA
 * //@param NA
 */

public class Program1CS232 {
	
	public static void start() throws FileNotFoundException {
		MinPQ<CovidData> minPQ = new MinPQ<CovidData>(3);  //for calling methods in the classes
		BST<Long, String> st = new BST<Long, String>(); //for calling methods in the classes
		File file = new File("owid-covid-data.csv");
		FileOut fo = new FileOut("output.txt");
		fo.writer("Inorder traversal");
		Scanner in = new Scanner(file);
		String str = "";
		in.nextLine();       //passes over the first line with the column names
		while(in.hasNext()) {
			String[] a = in.nextLine().split(",");
			CovidData data = new CovidData(a[0], a[1], a[2], Long.parseLong(a[3]), Long.parseLong(a[4]), Long.parseLong(a[5]));
			if(str.equals("")) {  //starts off as empty string making sure to add first data point and setting a string to be compared to
				str= data.country();
				minPQ.insert(data);
			}
			else if(str.equals(data.country())) {  //as the program reads the same country, continues to add to the last spot
				minPQ.insert(data);  				//and delete the minimum value
				if(minPQ.size() > 3){
				     minPQ.delMin().new_cases();
				}
			}
			else if (!str.equals(data.country()) && minPQ.size() == 3){  //for if the country changes. It will put all the values from the 
				for(int i = 0; i < 3; i++) {							//minPQ into the BST, change which country it needs and insert 
					CovidData delMin = minPQ.delMin();					//the first data point for that country into the minPQ
					st.put(delMin.new_cases(), delMin.returnAll());
				}
				str = data.country();
				minPQ.insert(data);
				if(minPQ.size() > 3){
				     minPQ.delMin().new_cases();
				}
			}
			else if(!str.equals(data.country()) && minPQ.size() < 3){//for any country with less than three data points and is changing what 
				CovidData delMin = minPQ.delMin();					//country the program sees. 
				st.put(delMin.new_cases(), delMin.returnAll());
				str = data.country();
				minPQ.insert(data);
			}
			if(!in.hasNext()) {  //for the last set of data points when there is no other line to be read to put those data points into the BST
				for(int i = 0; i < 3; i++) {
					CovidData delMin = minPQ.delMin();
					st.put(delMin.new_cases(), delMin.returnAll());
				}
			}
		}
		st.InOrder(fo); //prints the output to the output file
		
		
	}
	public static void main(String[]args) throws FileNotFoundException {
		start();
	}
}
