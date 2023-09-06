import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReadHTML {

	private String htmlContent;
	
	public ReadHTML(String filename){
		Scanner scanner;
		StringBuilder str = new StringBuilder();
		try {
			scanner = new Scanner(new File(filename + ".html"));
			while (scanner.hasNextLine()) {
				
				String line = scanner.nextLine();
				
				str.append(line);
				str.append(System.getProperty("line.separator"));//We decided to add line separator as a character
				
				 	   	   
			}
		} catch (FileNotFoundException e) {
			System.out.print("HTML file problem");
		}
		
		this.htmlContent = str.toString();
		
	}
	
	private void writeHTML(String output) {
		try {
			FileWriter writer = new FileWriter("output.html"); // our output file is output.html
			writer.write(output);
			
			writer.close();
		      System.out.println("Successfully wrote to the file with marks.");
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred at writing.");
		    }
	}
	
	public void addMarkAndWrite(List<Integer> indexes, int length) {
        StringBuilder sb = new StringBuilder(this.htmlContent);
        int jump = 0;
        for (int i = 0; i <indexes.size(); i++) {
            int index = jump + indexes.get(i);
            sb.insert(index, "<mark>");
            sb.insert(index + length + 6, "</mark>");
            jump += 13;   //After every transaction indexes should decrease 13 
        }
        writeHTML(sb.toString());
    }

	public String getHtmlContent() {
		return htmlContent;
	}
	

}