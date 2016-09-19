import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBModel;
import gurobi.GRBVar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Heuristic {
	private int minsplit;
	private int nJob;
	private int nWin;
	
	private ArrayList<Job> J;
	private ArrayList<Window> W;
	private Window lastWindow;
	private int [] Bt;
	public Heuristic(String file) throws FileNotFoundException{
		 @SuppressWarnings("resource")
		Scanner inFile = new Scanner(new File(file));
		 this.nJob = inFile.nextInt();
		 J=new ArrayList<Job>();
		 W=new ArrayList<Window>();
		 this.nWin=inFile.nextInt();
		 this.Bt=new int[this.nWin];
		 this.Bt[0]=0;
		
		 this.minsplit=inFile.nextInt();
		 
		 for(int i=0;i<this.nJob;i++)
			J.add(new Job("J"+(i+1),inFile.nextInt()));
		 
		 for(int i=1;i<this.nWin;i++){
			 this.Bt[i]=inFile.nextInt();
			 this.W.add(new Window(this.Bt[i]-this.Bt[i-1],this.Bt[i-1],this.minsplit));
			 }
		 lastWindow=new Window(1000,this.Bt[this.nWin-1],this.minsplit);
		inFile.close();
	}
	public void LPT(){
	  for(Window w:this.W)
		{
		  Collections.sort(J,new Job());
		  for(Job j:this.J)
			 w.assignjob(j);
		}
		for(Job j:J)
			if(j.getRemaining()>0)
		      lastWindow.assignjob(j);
		W.add(this.lastWindow);
		
	}
	public int completionTime(){
		int completion=0;
	
		for(Window w:W){
			if (!w.isEmpty()) {
				completion=w.getBt();
				for(Job j:w.getJob())
					completion=completion+j.getProcessingTime();
			}
		}
		return completion;
	}
	public void printSolution(){
		for(Window w:W)
			if (!w.isEmpty()){
				for(Job j :w.getJob())
				System.out.print(j.getName()+":"+j.getProcessingTime()+" ");
				System.out.print("\n");
			}
	}
}
