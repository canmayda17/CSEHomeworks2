import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("First of all program will calculate given example at note 5 and than start calculate with choosens!!");
		
		System.out.println("Results for given example=");
		String example = "<HTML><BODY>WHICH_FINALLY_HALTS. _ _ AT_THAT POINT</BODY></HTML>";
		String patten = "AT_THAT";
		BruteForce bfe = new BruteForce(patten, example);
		bfe.print();
		Horspool hpe = new Horspool(patten, example);
		hpe.print();
		BoyerMoore bme = new BoyerMoore(patten, example);
		bme.print();
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		int exit  = 0;
		while (exit == 0) {
			System.out.print("Choose input file \n"
					+ "Type 1 = English text \n"
					+ "Type 2 = Random bit-strings \n"
					+ "Write just 1 or 2 =");
			
			int typeChoose = scanner.nextInt();
			String a = "";
			if (typeChoose == 1) {
				a = "typeoneinput";
			}
			else if (typeChoose == 2){
				a = "typetwoinput";
			}
			
			ReadHTML html = new ReadHTML(a);
			
			System.out.println("Choose one  \n"
					+ "1 = Use BruteForce Algorithm and create output file \n"
					+ "2 = Use Horspool Algorithm, create output file and see table\n"
					+ "3 = Use BoyerMoore Algorithm, create output file and see tables \n"
					+ "4 = Compare all algorithms \n"
					+ "Write just 1,2,3 or 4 =");
			int Choose = scanner.nextInt();
			System.out.println("Please give pattern=");
			String b = scanner.next();
			switch(Choose) {
			  case 1:
				  BruteForce bc = new BruteForce(b,html.getHtmlContent());
				  bc.print();
				  html.addMarkAndWrite(bc.getIndexes(), b.length());
			    break;
			  case 2:
				  Horspool hp = new Horspool(b,html.getHtmlContent());
				  hp.print();
				  html.addMarkAndWrite(hp.getIndexes(), b.length());
				  hp.printTable();
			    break;
			  case 3:
				  BoyerMoore bm = new BoyerMoore(b,html.getHtmlContent());
				  bm.print();
				  html.addMarkAndWrite(bm.getIndexes(), b.length());
				  bm.printSuffixTable();
				  bm.printTable();
				break;
			  case 4:
				  BruteForce bcc = new BruteForce(b,html.getHtmlContent());
				  bcc.print();
				  Horspool hpc = new Horspool(b,html.getHtmlContent());
				  hpc.print();
				  BoyerMoore bmc = new BoyerMoore(b,html.getHtmlContent());
				  bmc.print();
				break;
			}
			System.out.println("Exit or try again \n"
					+ "1 = Exit \n"
					+ "2 = Try again \n"
					+ "Write just 1 or 2 =");
			if(scanner.nextInt() == 1) exit = 23;
		}
		
		scanner.close();
		System.out.print("Exited");
		
	}
	
	
	

}
