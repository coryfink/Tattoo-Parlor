//Cory Fink
//Michael Arbedola
//COP3502
//04/3/17
public class TattooCustomer {
	
	//instance variables which will be instantiated with constructors
	String name;
	String tattoo;
	int minutes;
	

	
	public TattooCustomer(String name1, String tattoo1, int min){
		//sets the instance variables to the formal parameters
		
		name = name1;
		tattoo = tattoo1;
		minutes = min;
	}
	
	public String getName(){
		//returns the name
		return name;
	}
	
	public String getTattoo(){
		//returns the tattoo
		return tattoo;
	}
	
	public int getMinutes(){
		//returns the minutes
		return minutes;
	}
	
	
}
