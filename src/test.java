import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import gurobi.GRBException;


public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
          int []J={10,20};//,30,100};
           int []W={10,20,30};//,40,50};
           int []min={5,6,7};
           
		   mathModel1 m1;
           mathModel2 m2;
           String inputfile=null;
           PrintWriter writer=null;
        	try {
        		writer= new PrintWriter("processingtime.txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       /*   for(int f=1;f<=10;f++)
           for(int i=0;i<J.length;i++)
        	   for(int j=0;j<W.length;j++)
        		   for(int k=0;k<min.length;k++){
        			   inputfile="input";
        			   inputfile=inputfile+"_"+J[i]+"_"+W[j]+"_"+min[k]+"_"+f+".txt";
        		   */
        	//Heuristic h=new Heuristic("test1.txt");
        	//h.LPT();
 			//System.out.println(h.completionTime());
 			//h.printSolution();
		  try {
			try {
				System.out.print(inputfile+"\n");
				//m1 = new mathModel1("data\\"+inputfile);
				//m1 = new mathModel1("test1.txt");
				m1=new mathModel1("data\\input_100_100_5_1.txt");
     			m1.buildModel();
     		    
     			writer.print(inputfile+":"+m1.optimize()+"\t");
     			writer.println(m1.processingTime());
     			m1.dispose();
     			
     			
			} catch (GRBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
      }	
      /*    writer.close();
		
	}*/

}
