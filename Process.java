/**
 * @author Jennifer Storozum
 * COSI 21a PA3
 * 8/8/17
 * Process class creates processes that hold an ID, priority value and name. 
 * The operations a Process should have are: 1.  The ability to construct a Process. 
 * 2.  The ability to return all values.
 */
public class Process {
	
	//fields
	int id;
	int priorityVal;
	String name;
	
	//constructors
	public Process(int id, int priorityVal, String name){
		this.id = id;
		this.priorityVal = priorityVal;
		this.name = name;
	}
	
	public Process(){
		this.id = 0;
		this.priorityVal = 0;
		this.name = null;
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getPriority(){
		return this.priorityVal;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setPriority(int priority){
		this.priorityVal = priority;
	}
	
	public void setName(String name){
		this.name = name;
	}
}		
	
	
	

	


