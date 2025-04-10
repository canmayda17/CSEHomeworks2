import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadAndWrite {
	
	private List<City> cities = new ArrayList<City>();
	public ReadAndWrite(String filename){
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filename + ".txt"));
			while (scanner.hasNextLine()) {
				
				String line = scanner.nextLine();
				line = makeNormal(line);
				addCity(line);					
				 	   	   
			}
		} catch (FileNotFoundException e) {
			System.out.print("txt file problem");
		}
		
	}
	
	private void addCity(String line) {
		City city = new City(findId(line),findX(line),findY(line));
		this.cities.add(city);
	}
	
	private int findId(String line) {
		int s = 0;
		int t = 0;
		for(char c : line.toCharArray()) {
			t ++;
			if(c==' ') {
				if(s==0)s = t;
			}
		}
		int Id = 0;
		String str = line.substring(0, s-1);
		try{
            Id = Integer.parseInt(str);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
		return Id;
	}
	
	private int findX(String line) {
		int s = 0;
		int f = 0;
		int t = 0;
		for(char c : line.toCharArray()) {
			t ++;
			if(c==' ') {
				if(s==0)s = t;
				else f = t;
			}
		}
		int X = 0;
		String str = line.substring(s, f-1);
		try{
            X = Integer.parseInt(str);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
		return X;
	}
	
	private int findY(String line) {
		int s = 0;
		int f = 0;
		int t = 0;
		for(char c : line.toCharArray()) {
			t ++;
			if(c==' ') {
				if(s==0)s = 1;
				else f = t;
			}
		}
		int Y = 0;
		String str = line.substring(f);
		try{
            Y = Integer.parseInt(str);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
		return Y;
	}
	
	private String makeNormal(String line) {
		int t = 0;
		for(char c : line.toCharArray()) {
			if(c==' ')t ++;
			else break;
		}
		
		line = line.substring(t);
		int s = 0;
		t = 0;
		for (char c : line.toCharArray()) {
			if (s== 0) t++;
			if (c==' ') {
				s++;
			}
			if (c!=' ' && s>0) break;
		}
		line = line.substring(0,t) + line.substring(t+s-1);
		int k = 0;
		s = 0;
		t = 0;
		for(char c : line.toCharArray()) {
			if (s == 0) t++;
			if (c==' ') {
				if (k==0)k=1;
				else s++;
			}
			if (c!=' ' && s>0) break;
		}
		
		line = line.substring(0,t) + line.substring(t+s-1);

		return line;
	}

	public List<City> getCities() {
		return cities;
	}
	
	public void writeHTML(String output, String outputName) {
		try {
			FileWriter writer = new FileWriter(outputName + "-output.txt"); // our output file is output.html
			writer.write(output);
			
			writer.close();
		      System.out.println("Successfully wrote to the file in testable format.(Output name is input file name + -output.txt)");
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred at writing.");
		    }
	}
	
	

}