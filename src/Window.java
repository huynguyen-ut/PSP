import java.util.ArrayList;


public class Window {
      int id;
      int width;
      int minsplit;
      ArrayList<Job> job;
      int remaining;
      int Bt;
      public Window(int w,int b,int m){
    	  this.width=w;
    	  this.remaining=w;
    	  this.minsplit=m;
    	  this.Bt=b;
    	  job=new ArrayList<Job>();
      }
      public boolean isEmpty(){
    	  return job.isEmpty();
      }
      public boolean assignjob(Job j){
    	  if(j.getRemaining()>0)
    	  if(j.getRemaining()>this.remaining){
    		  if(this.remaining>=this.minsplit){
    		      if(j.getRemaining()-this.remaining>=this.minsplit){
    		       	job.add(new Job(j.getName(),this.remaining));
    			    j.setRemaining(j.getRemaining()-this.remaining);
    			    this.remaining=0;
    		       return true;}
    		       else
    			   return false;}
    		  else return false;
    	  }else
    	  {
    		  job.add(new Job(j.getName(),j.getRemaining()));
    		  this.remaining=this.remaining-j.getRemaining();
    		  j.setRemaining(0);
    		  return true;
    	  }
    	  return false;
      }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getMinsplit() {
		return minsplit;
	}
	public void setMinsplit(int minsplit) {
		this.minsplit = minsplit;
	}
	public ArrayList<Job> getJob() {
		return job;
	}
	public void setJob(ArrayList<Job> job) {
		this.job = job;
	}
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public int getBt() {
		return Bt;
	}
	public void setBt(int bt) {
		Bt = bt;
	}
      
}
