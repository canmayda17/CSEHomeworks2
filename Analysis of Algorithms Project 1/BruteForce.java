import java.util.ArrayList;
import java.util.List;

public class BruteForce {
	
	private String pattern;
	private String htmlFile;
	private int comp;
	private long time;
	private List<Integer> indexes;
	

	public BruteForce(String pattern, String comeFromFile) {
		this.pattern = pattern;
		this.htmlFile = comeFromFile;
		this.count();
	}
	
	//Compare characters one by one creating variables for the indexes and dividing them into char along with substrings.
	private void count() {
		int comprasion = 0; // Variable to keep the number of comparisons
		List<Integer> temp = new ArrayList(); // List to store the indexes where the pattern matches
		long startTime = System.currentTimeMillis(); // Start time of the function execution
		int i = 0;
		while(i<=this.htmlFile.length() - this.pattern.length()) {	
			int j = this.pattern.length()-1;
			int k = i+ this.pattern.length()-1;
			while (this.htmlFile.substring(k, k+1).equals(this.pattern.substring(j,j+1)) && j!=0) {
				// Compare characters of the text and pattern from the end
				comprasion ++;
				if(j==1 && this.htmlFile.substring(k-1, k).equals(this.pattern.substring(j-1,j))) {
					temp.add(i); // If the first two characters match, add the index to the list
				}
				j--;
				k--;
			}
			comprasion ++;
			i++;
		}
		
		this.time = System.currentTimeMillis() - startTime;
		this.comp = comprasion;
		this.indexes = temp;
	}
	
	public void print() {
		//The index size is used for number of matching.
		System.out.println("Comparison time is " + this.time + "(ms) comparison number is " + this.comp + " matching number is " + this.indexes.size() +" algorithm is Brute Force");
	}
	
	public List<Integer> getIndexes() {
		return indexes;
	}

	
	

}
