import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;


public class mathModel2 {
	private int minsplit;
	private int nJob;
	private int nWin;
	private int [] Pi;
	private int [] Wt;
	private int [] Bt;
	private GRBVar[][] Yit;
	GRBModel model; 
	GRBEnv env;
	public mathModel2(String file) throws FileNotFoundException, GRBException{
		 Scanner inFile = new Scanner(new File(file));
		 this.nJob = inFile.nextInt();
		 this.Pi=new int[this.nJob];
		 this.nWin=inFile.nextInt();
		 this.Bt=new int[this.nWin];
		 this.Bt[0]=0;
		 this.Wt=new int[this.nWin];
		 this.Wt[this.nWin-1]=1000;
		 this.minsplit=inFile.nextInt();
		 
		 for(int i=0;i<this.nJob;i++)
			 this.Pi[i]=inFile.nextInt();
		 for(int i=1;i<this.nWin;i++){
			 this.Bt[i]=inFile.nextInt();
			 this.Wt[i-1]=this.Bt[i]-this.Bt[i-1];
			 }
		  env = new GRBEnv("NRS.log");
		  model = new GRBModel(env);	 
		  Yit = new GRBVar[nJob][nWin];
		  inFile.close();
	}
	public void buildModel() throws GRBException{
		GRBLinExpr expr = new GRBLinExpr();
		
		for(int i=0;i<nJob;i++)
			for(int t=0;t<nWin;t++){
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
		//constraint 3
		for(int i=0;i<nJob;i++)
		 for(int t=0;t<nWin;t++){
			 expr=new GRBLinExpr();
			 expr.addTerm(1.0+Pi[i], Yit[i][t]);
			 model.addConstr(expr, GRB.GREATER_EQUAL, this.minsplit-this.Pi[i], "constr3_"+t);
		 }
		// constrait 4
				for(int t=0;t<nWin-1;t++){
					expr=new GRBLinExpr();
					for(int i=0;i<nJob;i++){
						expr.addTerm(1.0, Yit[i][t]);
						model.addConstr(expr, GRB.GREATER_EQUAL, this.Wt[t]-2*this.minsplit-1, "constr6_"+t);
					}
				}
				//constrait i
				GRBVar []temp1=new GRBVar[this.nWin];
				
				for(int t=0;t<nWin;t++){
					temp1[t]=model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "temp1_"+t);
					model.update();
					expr=new GRBLinExpr();
					for(int i=0;i<nJob;i++)
						expr.addTerm(-1.0, Yit[i][t]);
					expr.addTerm(this.Wt[t], temp1[t]);
				    model.addConstr(expr, GRB.GREATER_EQUAL, 0, "constrI_"+t);
				}
				
				//constrait ii
				GRBVar op=model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "Obj");;
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
			      model.write("model_lp2.lp");
			      model.write("model_lp2.mps");
			      model.optimize();
			      model.write("solution2.sol");
			      model.dispose();
			      env.dispose();
	}
}
