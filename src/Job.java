import java.util.Comparator;


public class Job implements Comparator<Job>, Comparable<Job>{
	String name;
	int processingTime;
	int remaining;
	public Job(){
		
	}
	public Job(String s,int p){
		this.processingTime=p;
		this.remaining=p;
		this.name=s;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	@Override
	public int compareTo(Job d) {
		// TODO Auto-generated method stub
		return (this.name).compareTo(d.name);
	}
	@Override
	public int compare(Job d, Job d1) {
		// TODO Auto-generated method stub
		 return d1.remaining - d.remaining;
	}
	

}
