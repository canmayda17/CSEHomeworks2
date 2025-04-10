package myjava;


import java.util.*;
import java.io.*;

public class Project2_AkinAtes_CanMayda_NurBetulCakir {
	/*private static String [] arr1 = {"(","DEFINE","IDENTIFIER","IDENTIFIER",")"};
	
	private static String [] arr2 = {"(","DEFINE","(","IDENTIFIER","IDENTIFIER",")",
									"(","LET","IDENTIFIER","(","(","IDENTIFIER","NUMBER",")","(",
									"IDENTIFIER","NUMBER",")","(","IDENTIFIER","NUMBER",")",")",
									"(","IF","(","IDENTIFIER","IDENTIFIER","IDENTIFIER",")",
									"IDENTIFIER","(","IDENTIFIER","IDENTIFIER","(","IDENTIFIER",
									"IDENTIFIER","IDENTIFIER",")","(","IDENTIFIER","IDENTIFIER",
									"NUMBER",")",")",")",")",")"};*/
	
	public static ArrayList<String> arr = new ArrayList<String>(200);
	private static int position=0;
	private static int count=0;
	
	public static String calculate(int num) {
		String str="";
		for(int i =0;i<num;i++) {
			String string =str;
			string+=" ";
			str=string;
		}
		return str;
	}
	
	static int counthere = 0;
	public void parseProgram() {
		System.out.println(calculate(counthere)+"<Program>");
      
       		if(arr.get(position).equals("LEFTPAR")) {
       			count++;
       			counthere++;
       			
       			parseTopLevelForm();
       			parseProgram();
       			
       		}
       		else {
       			counthere++;
				System.out.println(calculate(counthere)+"___");
				
				
			}
            
        
       	
       	
        /*parseTopLevelForm();
        parseProgram();*/
        
    }
	public void parseTopLevelForm() {
			System.out.println(calculate(count)+"<TopLevelForm>");//1 tab
			count++;
	        if (arr.get(position).equals("LEFTPAR")) {
	        	System.out.println(calculate(count)+ "LEFTPAR (" + arr.get(position) + ")");//2 tab
	        	position++;
	        	int countwas=count;
	            parseSecondLevelForm();
	            if (arr.get(position).equals("RIGHTPAR")) {
	            	
	            	
	            	System.out.println(calculate(countwas)+"RIGHTPAR ("+arr.get(position) + ")");
	            	//position++;
	            }
	            else {
	            	throw new IllegalArgumentException("Invalid top-level form.");
	            }
	        } else {
	            throw new IllegalArgumentException("Invalid top-level form.");
	        }
	}
	 public void parseSecondLevelForm() {
		 System.out.println(calculate(count)+"<SecondLevelForm>");//2 tab
		 if(arr.get(position).equals("LEFTPAR")) {
	        System.out.println(arr.get(position)+" <SecondLevelForm>");
			 position++;
			 parseFunCall();
			 if(arr.get(position).equals("RIGHTPAR")) {
				 System.out.println(calculate(count)+"<SecondLevelForm>");//2 tab
				 //position++;
			 }
		 }
		 else {
			 count++;
			parseDefiniton();
		
			 
		 }
	 }	 
	 
	 public void parseDefiniton() {
		 System.out.println(calculate(count)+"<Definiton>");//3 tab
		 
	        if(arr.get(position).equals("DEFINE")) {
	        	count++;
	        	System.out.println(calculate(count)+arr.get(position)+" (define)");//4 tab
	        	position++;        	
	        	parseDefinitionRight();	      
	        }
	 }
	 
	 public void parseDefinitionRight() {
	        if (arr.get(position).equals("IDENTIFIER")) {
	        	System.out.println(arr.get(position)+" <DefinitonRight>");
	        	position++;	        	
	            parseExpression();
	        }
	        else if (arr.get(position).equals("LEFTPAR")) { 
	        	System.out.println(calculate(count)+" <DefinitonRight>");
	        	count++;
	        	System.out.println(calculate(count)+ "LEFTPAR (" + arr.get(position) +")");//5 tab
	        	position++;
	        	if(arr.get(position).equals("IDENTIFIER")) {
	        		
	        		System.out.println(calculate(count)+arr.get(position) + "(identifier)" );//5 tab
	        		position++;
	        		
		        	int count_was=count;
	        		parseArgList();
	        		if(arr.get(position).equals("RIGHTPAR")) {
	        			
	        			System.out.println(calculate(count_was)+ "RIGHTPAR (" +arr.get(position)+")");
	    	        	position++;
	    	        	count=count_was;
	        			parseStatements();	
	        		}
	        		else {
	        			 throw new IllegalArgumentException("Invalid definition right.");
	        		}
	        	}
	        	else {
	        		 throw new IllegalArgumentException("Invalid definition right.");
	        	}
	           
	        } 
	        else {
	            throw new IllegalArgumentException("Invalid definition right.");
	        }
	    }

	 public void parseArgList() {
		 
		 System.out.println(calculate(count)+"<ArgList>");//6 tabs
	        	count++;
	        if (arr.get(position).equals("IDENTIFIER")) {
	        	
	        	
	        	System.out.println(calculate(count)+arr.get(position)+" (identifier)");//7tabs
	        	position++;
	        	
	            parseArgList();
	        }
	        else {
				System.out.println(calculate(count)+"___");
			}
	 }	 
	 
	 public void parseStatements() {
		 System.out.println(calculate(count)+"<Statements>");
		 count++;
	        if (arr.get(position).equals("DEFINE")) {
	        	position++;
	        	parseDefiniton();
	            parseStatements();
	        }
	        else {
	           parseExpression();
	           }
	    }
	 public void parseExpressions() {
		 System.out.println(calculate(--count)+"<Expressions>");
		 count++;
		 //System.out.println(arr.get(position));
	        if (arr.get(position).equals("IDENTIFIER")) {
	            int countwas=count;
	            count=countwas;
	            parseExpression();      
	            parseExpressions();
	        }
	        else if(arr.get(position).equals("NUMBER")) {
	        	//position++;
	        	int countwas=count;
	            count=countwas;
	        	parseExpression();    	
	            parseExpressions();
	           
	        }
	        else if(arr.get(position).equals("CHAR")) {
	        	int countwas=count; 
	            count=countwas;
	        	parseExpression();
	            parseExpressions();
	        }
	        else if(arr.get(position).equals("BOOLEAN")) {
	        	
	        	int countwas=count;
	            count=countwas;
	        	parseExpression();
	            parseExpressions();
	        }
	        else if(arr.get(position).equals("STRING")) {
	        	int countwas=count;
	            count=countwas;
	        	parseExpression();
	            parseExpressions();
	        }
	        else if (arr.get(position).equals("LEFTPAR")){
	        	int countwas2=count;
	        	int countwas3=countwas2--;
	        	count=countwas2;
	        	parseExpression();
	        	count=countwas3;
	            parseExpressions();
	        }
	        else {
	        	System.out.println(calculate(count)+"___");
			}
	    }
	 public void parseExpression() {
		 	System.out.println(calculate(count)+"Expression");
		 	count++;
		 	if (arr.get(position).equals( "IDENTIFIER" )) {
		 		System.out.println(calculate(count)+arr.get(position)+" (identifier)");
	            position++;
	        }
	        else if(arr.get(position).equals("NUMBER")) {
	        	System.out.println(calculate(count)+arr.get(position)+" (number)");
	        	position++;
	        }
	        else if(arr.get(position).equals("CHAR")) {
	        	System.out.println(calculate(count)+arr.get(position)+" (char)");
	        	position++;
	        }
	        else if(arr.get(position).equals("BOOLEAN")) {
	        	System.out.println(calculate(count)+arr.get(position)+" (boolean)");
	        	position++;
	        }
	        else if(arr.get(position).equals("STRING")) {
	        	System.out.println(calculate(count)+arr.get(position)+" (string)");
	        	position++;
	        }
	        else if(arr.get(position).equals("LEFTPAR")) {
	        	int countwas=count;
	        	count=countwas;
	        	System.out.println(calculate(count)+ "LEFTPAR ("+arr.get(position)+ ")");
	        	position++;
	        	
	        	parseExpr();
	        	if (arr.get(position).equals("RIGHTPAR")) {
	        		System.out.println(calculate(countwas)+ "RIGHTPAR (" +arr.get(position)+")");
					position++;
				}
	        	else {
	        		 throw new IllegalArgumentException("Invalid expression.");
				}
	        }
		 
	         
	        else {
	            throw new IllegalArgumentException("Invalid expression.");
	        }
	    }
	 public void parseExpr() {
		 System.out.println(calculate(count)+"<Expr>");
		 	count++;
	        if (arr.get(position).equals("LET")) {
	            parseLetExpression();
	            
	        } 
	        else if (arr.get(position).equals("COND")) {
				

	            parseCondExpression();
	        }
	        else if (arr.get(position).equals("IF")) {
	        	
	            parseIfExpression();
	           // parseEndExpression();
	        }
	        else if (arr.get(position).equals("BEGIN")) {
				

	            parseBeginExpression();
	        }
	        else if (arr.get(position).equals("IDENTIFIER")) {
				
	        
	            parseFunCall();
	        }
	        else {
	            throw new IllegalArgumentException("Invalid expression.");
	        }
	    }
	 public void parseFunCall() {
		 System.out.println(calculate(count)+"<FunCall>");
		 	count++;
		 if (arr.get(position).equals("IDENTIFIER")) {
			 System.out.println(calculate(count)+arr.get(position)+" (identifier)");
				position++;
				parseExpressions();
		 }
	 }
	 
    public void parseLetExpression() {
    	System.out.println(calculate(count)+"<LetExpression>");
	 	count++;
    	if (arr.get(position).equals("LET")) {
    		System.out.println(calculate(count) + arr.get(position)+ " (let)");
			position++;
			parseLetExpr();
    	}
    }
    public void parseLetExpr() {
    	
        if (arr.get(position).equals("LEFTPAR")) {
        	position++;
    	 	count++;
            parseVarDefs();
            if(arr.get(position).equals("RIGHTPAR")) {
            	parseStatements();
            }
        } 
        else if (arr.get(position).equals("IDENTIFIER")) {
        	System.out.println(calculate(count)+"<LetExpr>");
        	count++;
        	System.out.println(calculate(count)+arr.get(position)+ " (identifier)");
        	position++;
        	int countwas=count;
        	if (arr.get(position).equals("LEFTPAR")) {
        		position++;
        		System.out.println(calculate(count)+ "LEFTPAR (" +arr.get(position)+")11");
        		parseVarDefs();
        		if(arr.get(position).equals("RIGHTPAR")) {
        			System.out.println(calculate(countwas)+ "RIGHTPAR (" +arr.get(position)+")");
        			position++;
        			count=countwas;
        			parseStatements();
        		}
        		else {
        			throw new IllegalArgumentException("Invalid let expression.");
        		}
        	}
        	else {
        		throw new IllegalArgumentException("Invalid let expression.");
        	}
           
        }
        else {
            throw new IllegalArgumentException("Invalid let expression.");
        }
    }
    
    public void parseVarDefs() {
    	System.out.println(calculate(count)+"<VarDefs>");
	 	count++;
    	
        if (arr.get(position).equals("LEFTPAR")) {
        	System.out.println(calculate(count)+ "LEFTPAR (" +arr.get(position)+")");
        	
        	position++;
        	if(arr.get(position).equals("IDENTIFIER")) {
        		System.out.println(calculate(count)+arr.get(position)+" (identifier)");
        		position++;
        		
        		int countwas = count;
        		
        		parseExpression();
        		if(arr.get(position).equals("RIGHTPAR")) {
        			System.out.println(calculate(countwas)+ "RIGHTPAR (" +arr.get(position)+")");
        			position++;
        			count=countwas;
        			parseVarDef();
        		}
        		else {
        			throw new IllegalArgumentException("Invalid let expression.");
				}
        	}
        	else {
        		throw new IllegalArgumentException("Invalid let expression.");
			}
           
        }
        else {
        	throw new IllegalArgumentException("Invalid let expression.");
		}
        
    }
    public void parseVarDef() {
    	System.out.println(calculate(count)+"<VarDef>");
	 	count++;
        if (arr.get(position).equals("LEFTPAR")) {
            //position++;
            parseVarDefs();
        } 
        else {
            System.out.println(calculate(count)+"___");
        }
    }
    public void parseCondExpression() {
    	System.out.println(calculate(count)+"<CondExpression>");
    	if (arr.get(position).equals("COND")) {
    		System.out.println(calculate(count)+arr.get(position)+"cond expression");
            position++;
            parseCondBranches();
        } 
    	
    }
    public void parseCondBranches() {
        if (arr.get(position).equals("LEFTPAR")) {
        	
        	position++;
            parseExpression();
            parseStatements();
            if(arr.get(position).equals("RIGHTPAR")) {
            	position++;
            	parseCondBranches();
            }
            else {
                throw new IllegalArgumentException("Invalid conditional branch.");
			}
        } 
        else {
            throw new IllegalArgumentException("Invalid conditional branch.");
        }
    }
    public void parseCondBranch() {
    	if (arr.get(position).equals("LEFTPAR")) {
    		
    		position++;
    		parseExpression();
    		parseStatements();
    	}
    	else {			
		}
    }
    
    
    public void parseIfExpression() {
    	System.out.println(calculate(count)+" <IfExpression>");
    	count++;
    	//position++;
    	int countwas=count;
    	if (arr.get(position).equals("IF")) {
    		
    		System.out.println(calculate(count)+arr.get(position)+ " (if)");
    		position++;
    		
    		parseExpression();
    		count=countwas;
    	    parseExpression();
    	    
    	    parseEndExpression();
    		
    	}
       
    }
    
    public void parseEndExpression() {
    	System.out.println(calculate(--count)+"<End expression>");
    	count++;
        if (arr.get(position).equals("RIGHTPAR")) {
        	
        	System.out.println(calculate(count) + "RIGHTPAR (" +arr.get(position)+")");
            position++;
        } 
        else {
        	//System.out.println(calculate(count)+arr.get(position)+" end expression");
        	//position++;
            parseExpression();
        }
    }
    
    public void parseBeginExpression() {
    	
    	 if (arr.get(position).equals("BEGIN")) {
             position++;
             parseStatements();
         } 
    	
    }
 
    
    
    
	 
	 
	
public static void main(String[] args) throws FileNotFoundException{
		
		
		File inputFile = new File("input.txt");
		Scanner lexicalAnalyzer = new Scanner(inputFile);
		while(lexicalAnalyzer.hasNextLine()) {
			String line = lexicalAnalyzer.nextLine();
			//System.out.println(line);
			
            String[] words = line.split(" "); // Split the line by whitespace
            
            //System.out.println(words);
            if (words.length > 0) {
                String firstWord = words[0];
                
                arr.add(firstWord);
        }
       
	
	}	
	
	
		Project2_AkinAtes_CanMayda_NurBetulCakir parsTest = new Project2_AkinAtes_CanMayda_NurBetulCakir();
	parsTest.parseProgram();

}
}
