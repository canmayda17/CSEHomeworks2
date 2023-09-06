import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Horspool {
	private String pattern;
	private String htmlFile;
	private long time;
	private HashMap<String, Integer> table;
	private List<Integer> indexes;
	private int compNumber;
	
	
	public Horspool(String pattern, String comeFromFile) {
		this.pattern = pattern;
		this.htmlFile = comeFromFile;
		this.table = generateTable(this.pattern,this.htmlFile);
		this.count();
	}
	
	private HashMap<String, Integer> generateTable(String pattern, String htmlFile) {
		
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		
		htmlFile = withOutDublicate(htmlFile);//Table shouldn't include dublicate
		
		
		for(int i = 0; i<htmlFile.length();i++) {
			// if character is not inside the pattern directly add pattern length
			if (!isThere(htmlFile.substring(i,i+1), pattern)) { table.put(htmlFile.substring(i,i+1), pattern.length());
			
			}
			else {				
				int k = 1;
				int l = pattern.length()-2;
				while(!htmlFile.substring(i,i+1).equals(pattern.substring(l,l+1))) {
					k++;
					l--;
					if(l<0)break;
				}
				table.put(htmlFile.substring(i,i+1), k);
			}
		}
		return table;
	}
	
	private boolean isThere(String a, String pattern) {
		boolean isThere = false;
		for (int i = 0; i<pattern.length();i++) {
			if (pattern.substring(i,i+1).equals(a)) isThere = true;
		}		
		return isThere;
	}
	
	private String withOutDublicate(String string) {
		String temp = "";
		for(int i = 0; i<string.length(); i++) {
			boolean isThere = false;
			for(int j = 0; j<temp.length(); j++) {
				if(string.substring(i, i+1).equals(temp.substring(j,j+1))) isThere = true;
			}
			if (!isThere) temp = temp + string.substring(i, i+1);
		}
		return temp;	
	}
	
	private void count() {
		int comprasion = 0;
		List<Integer> temp = new ArrayList();
		long startTime = System.currentTimeMillis();
		int i = 0;
		while(i<=this.htmlFile.length() - this.pattern.length()) {
			int j = this.pattern.length()-1;
			int k = i + this.pattern.length()-1 ;
			while (this.htmlFile.substring(k, k+1).equals(this.pattern.substring(j,j+1)) && j!=0) {
				
				comprasion ++;
				if(j==1 && this.htmlFile.substring(k-1, k).equals(this.pattern.substring(j-1,j))) {
					temp.add(i);
				}
				j--;
				k--;
			}
			comprasion ++;
			i += table.get(htmlFile.substring(i + pattern.length() - 1, i + pattern.length()));	//Just jump amount is different from BruteForce
		}
		
		this.time = System.currentTimeMillis() - startTime;
		this.compNumber = comprasion;
		this.indexes = temp;
	}
	

	public void printTable() {
		System.out.println("Table is:");
		String tempPattern = "";
		for (int i = 0; i < pattern.length(); i++) {
			for (int j = 0; j < this.table.toString().length(); j = j + 5) {
				
			if (!(this.table.toString().substring(j + 1, j + 2).equals(this.pattern.substring(i , i + 1))) && !(tempPattern.contains(this.pattern.substring(i , i + 1)))) {
				if ((this.table.toString().substring(j + 1, j + 3).equals("\n"))
						|| (this.table.toString().substring(j + 1, j + 3).equals("\r"))
						|| (this.table.toString().substring(j + 1, j + 3).equals("\n"))) {
				tempPattern += this.pattern.substring(i , i + 1);
				j++;
				}
				if (!(this.table.containsKey(this.pattern.substring(i , i + 1)))) {
				this.table.put(this.pattern.substring(i , i + 1), pattern.length());
				
				}
				break;
			}
			else if ((this.table.toString().substring(j + 1, j + 2).equals(this.pattern.substring(i , i + 1))) && !(tempPattern.contains(this.pattern.substring(i , i + 1)))) {
				if ((this.table.toString().substring(j + 1, j + 3).equals("\n"))
						|| (this.table.toString().substring(j + 1, j + 3).equals("\r"))
						|| (this.table.toString().substring(j + 1, j + 3).equals("\n"))) {
				tempPattern += this.pattern.substring(i , i + 1);
				j++;
				}
			}
			else {
				if (!(tempPattern.contains(this.pattern.substring(i, i + 1))) || !(tempPattern.contains((this.table.toString().substring(j + 1, j + 2))))) {
					if ((this.table.toString().substring(j + 1, j + 3).equals("\n"))
							|| (this.table.toString().substring(j + 1, j + 3).equals("\r"))
							|| (this.table.toString().substring(j + 1, j + 3).equals("\n"))) {
						tempPattern += this.table.toString().substring(j + 1, j + 3);
						j++;
					}
						
				}
			}
			
			
		}
	}
		
        for (int i = 1; i < this.table.keySet().toString().length(); i = i + 3) {
			
			if (this.table.keySet().toString().charAt(i) == '\n'
				||this.table.keySet().toString().charAt(i) == '\r'
				||this.table.keySet().toString().charAt(i) == '\t') {
				System.out.print("*" + " |");
			}
			else
				System.out.print(this.table.keySet().toString().charAt(i) + " |");
			
		}
		System.out.println();
		for (int i = 1; i < this.table.values().toString().length(); i = i + 3) {
			if (this.table.values().toString().charAt(i + 1) == ']') {
				System.out.print(this.table.values().toString().charAt(i)  + " |");
				break;
			}
			if (this.table.values().toString().charAt(i + 1) == ',') {
			System.out.print(this.table.values().toString().charAt(i) + " |");
			}
			else {
				
				System.out.print(this.table.values().toString().substring(i, i + 2)  + "|");
				i++;
			}
		}
		System.out.println("");
		System.out.println("* sign mean the character is new line or tab");
		
		
	
		
		
	}
	
	public List<Integer> getIndexes() {
		return indexes;
	}
	
	public void print() {
		System.out.println("Comparison time is " + this.time + "(ms) comparison number is " + this.compNumber + " matching number is " + this.indexes.size() +" algorithm is Horspool");
	}
}