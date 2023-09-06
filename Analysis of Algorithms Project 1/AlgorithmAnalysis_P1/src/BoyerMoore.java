import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoyerMoore {
	
	private String pattern;
	private String htmlFile;
	private long time;
	private HashMap<String, Integer> table;
	private List<Integer> indexes;
	private int compNumber;
	private List<Integer> suffix;

	
	public BoyerMoore(String pattern, String comeFromFile) {
		this.pattern = pattern;
		this.htmlFile = comeFromFile;
		this.table = generateTable(this.pattern,this.htmlFile);
		this.suffix = generateSuffixTable(this.pattern);
		this.count();
	}
	
	private HashMap<String, Integer> generateTable(String pattern, String htmlFile) {
		
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		
		htmlFile = withOutDublicate(htmlFile);
		
		
		for(int i = 0; i<htmlFile.length();i++) {
			
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
	
	
	private List<Integer> generateSuffixTable(String pattern) {
		
		List<Integer> table = new ArrayList();	
		int k;	
		for(k=1;k<=pattern.length();k++) {
			
			if(pattern.substring(0,pattern.length()-1).contains((pattern.substring(pattern.length()-k))) ) {
				table.add(pattern.length()-k-pattern.substring(0,pattern.length()-1).lastIndexOf(pattern.substring(pattern.length()-k)));
			}
			
			else {
				
				if(k==1) {
					table.add(pattern.length());
				}
				
				else{
					
					int index = pattern.substring(0,k-1).lastIndexOf(pattern.charAt(pattern.length()-1));
					int last›ndex;
					int end=0;
					
					for(int t = index,j = 1;t>=0;t--,j++) {
						
						if(pattern.substring(pattern.length()-k).charAt(k-j) == pattern.charAt(t)) {
							end++;
						}
							
						else {
							last›ndex = index;
							index = pattern.substring(0,last›ndex).lastIndexOf(pattern.charAt(pattern.length()-1));
							t = index + 1;
							j = 0;
							end = 0;
						}
					
					}				
						table.add(pattern.length()- end);		
				}
					
			}				
		}
			return table;
	}
	
	
	private void count() {
		int comprasion = 0;
		List<Integer> temp = new ArrayList();
		long startTime = System.currentTimeMillis();
		
		int i = 0;
		while(i<=this.htmlFile.length() - this.pattern.length()) {	
			int j = this.pattern.length()-1;
			int n = 0;
			int k = i + this.pattern.length()-1 ;
			
			while (this.htmlFile.substring(k, k+1).equals(this.pattern.substring(j,j+1)) && j!=0) {
				
				comprasion ++;
				if(j==1 && this.htmlFile.substring(k-1, k).equals(this.pattern.substring(j-1,j))) {
					temp.add(i);
					n++;
				}
				j--;
				k--;
				n++;
				
			}
			
			comprasion ++;
			
			int l = table.get(this.htmlFile.substring(i + this.pattern.length()-1-n, i + this.pattern.length()-n)) - n;// this is d1 in lecture slides
			
			
			int m = 0;
			if (n == pattern.length()) m = this.suffix.get(pattern.length()-1);//in match situations we take the last element in suffix table
			else if (n==0) m=0;// if there is no any matching character d2=0
			else m = this.suffix.get(n-1);
			
			i += Math.max(l,m);//Jump amount is bigger one of d1 and d2
			
		}
		
		this.time = System.currentTimeMillis() - startTime;
		this.compNumber = comprasion;
		this.indexes = temp;
	}
	
	public void printSuffixTable() {
		System.out.println("Suffix table is = ");
		for ( int i = 0; i<this.suffix.size(); i++)
			System.out.println((i+1) + " = " + this.suffix.get(i) + " \n ----");
		
	}

	public List<Integer> getSuffix() {
		return suffix;
	}
	
	public void print() {
		System.out.println("Comparison time is " + this.time + "(ms) comparison number is " + this.compNumber + " matching number is " + this.indexes.size() +" algorithm is BoyerMoore");
	}

	public HashMap<String, Integer> getTable() {
		return table;
	}

	public List<Integer> getIndexes() {
		return indexes;
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
	

}
