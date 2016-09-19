import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;


public class mathModel1 {
	private int minsplit;
	private int nJob;
	private int nWin;
	private int [] Pi;
	private int [] Wt;
	private int [] Bt;
	private GRBVar[][] Xit;
	private GRBVar[][] Yit;
	GRBModel model; 
	GRBEnv env;
	long procTime;
	public mathModel1(String file) throws FileNotFoundException, GRBException{
		 Scanner inFile = new Scanner(new File(file));
		 this.nJob = inFile.nextInt();
		 this.Pi=new int[this.nJob];
		 this.nWin=inFile.nextInt();
		 this.Bt=new int[this.nWin];
		 this.Bt[0]=0;
		 this.Wt=new int[this.nWin];
		 this.Wt[this.nWin-1]=10000;
		 this.minsplit=inFile.nextInt();
		 
		 for(int i=0;i<this.nJob;i++)
			 this.Pi[i]=inFile.nextInt();
		 for(int i=1;i<this.nWin;i++){
			 this.Bt[i]=inFile.nextInt();
			 this.Wt[i-1]=this.Bt[i]-this.Bt[i-1];
			 }
		 env = new GRBEnv("NRS.log");
		 model = new GRBModel(env);	 
		 Xit = new GRBVar[nJob][nWin];
		 Yit = new GRBVar[nJob][nWin];
		 inFile.close();
	}
	public void buildModel() throws GRBException{
		GRBLinExpr expr = new GRBLinExpr();
			
		for(int i=0;i<nJob;i++)
			for(int t=0;t<nWin;t++){
				Xit[i][t]=model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "X_"+i+"_"+t);
				Yit[i][t]=model.addVar(0.0, GRB.INFINITY, 0.0, GRB.INTEGER, "Y_"+i+"_"+t);
			}
		model.update();
		
		// constraint 1
		for(int i=0;i<nJob;i++)
		 {
			 expr=new GRBLinExpr();
			 for(int t=0;t<nWin;t++){
				 expr.addTerm(1.0, Yit[i][t]);
			 }
			 model.addConstr(expr, GRB.EQUAL, Pi[i], "constr1_"+i);
		  }
		//constraint 2
		for(int t=0;t<nWin;t++){
			expr=new GRBLinExpr();
			for(int i=0;i<nJob;i++)
				expr.addTerm(1.0, Yit[i][t]);
			model.addConstr(expr, GRB.LESS_EQUAL, Wt[t], "constr2_"+t);
		}
		//constrait 4
		for(int t=0;t<nWin;t++)
			for(int i=0;i<nJob;i++){
				expr=new GRBLinExpr();
				expr.addTerm(1.0, Yit[i][t]);
				expr.addTerm(-this.minsplit, Xit[i][t]);
				model.addConstr(expr, GRB.GREATER_EQUAL, 0, "constr4_"+t);
			}
		//constrait 5
		for(int t=0;t<nWin;t++)
			for(int i=0;i<nJob;i++){
				expr=new GRBLinExpr();
				expr.addTerm(1.0, Yit[i][t]);
				expr.addTerm(-this.Pi[i], Xit[i][t]);
				model.addConstr(expr, GRB.LESS_EQUAL, 0, "constr5_"+t);
				}
		// constrait 6
		/*for(int t=0;t<nWin-1;t++){
			expr=new GRBLinExpr();
			for(int i=0;i<nJob;i++)
				expr.addTerm(1.0, Yit[i][t]);
			model.addConstr(expr, GRB.LESS_EQUAL, this.Wt[t]-2*this.minsplit-1, "constr6_"+t);
		}*/
		// constrait 7
		/*for(int i=0;i<nJob;i++){
			expr=new GRBLinExpr();
			for(int t=0;t<nWin;t++)
				expr.addTerm(1.0, Xit[i][t]);
				double nM=Math.ceil(this.Pi[i]/this.minsplit);
				if(nM>this.nWin)
					model.addConstr(expr, GRB.LESS_EQUAL, this.nWin, "constr7_"+i);
				else
					model.addConstr(expr, GRB.LESS_EQUAL, nM, "constr7_"+i);
			
		}*/
		//constrait i
		GRBVar []temp1=new GRBVar[this.nWin];
		
		for(int t=0;t<nWin;t++){
			temp1[t]=model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "temp1_"+t);
			model.update();
			expr=new GRBLinExpr();
			for(int i=0;i<nJob;i++)
				expr.addTerm(-1.0, Xit[i][t]);
			expr.addTerm(this.nJob, temp1[t]);
		    model.addConstr(expr, GRB.GREATER_EQUAL, 0, "constrI_"+t);
		}
		
		//constrait ii
		GRBVar op=model.addVar(0.0, GRB.INFINITY, 0.0, GRB.INTEGER, "Obj");;
		model.update();
		for(int t=0;t<nWin;t++){
			expr=new GRBLinExpr();
			expr.addTerm(1.0,op);
			expr.addTerm(-Bt[t], temp1[t]);
			for(int i=0;i<nJob;i++)
				expr.addTerm(-1.0, Yit[i][t]);
		    model.addConstr(expr, GRB.GREATER_EQUAL, 0, "constrII_"+t);
		}
		
		//Objective
		  expr = new GRBLinExpr();
	      expr.addTerm(1.0, op); 
	      model.setObjective(expr, GRB.MINIMIZE);
	      model.update();
	      //model.write("model_lp1.lp");
	      //model.write("model_lp1.mps");
	      
	      
	      // model.write("solution1.sol");
	      
	}
	 
	public long processingTime(){
		return procTime;
	}
	public double optimize() {
		//try {
			//this.model.getEnv().set(GRB.DoubleParam.TimeLimit, 10.0);
	//	} catch (GRBException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
	//	}
		double optimstatus=0;
		 try {
			 long start = System.currentTimeMillis();
		      model.optimize();
			 long end = System.currentTimeMillis();
			 procTime= end - start;
		} catch (GRBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			optimstatus = model.get(GRB.DoubleAttr.ObjVal);
		} catch (GRBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return optimstatus;
	}
	public void dispose(){
		  model.dispose();
	      try {
			env.dispose();
		} catch (GRBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
