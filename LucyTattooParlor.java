//Cory Fink
//Michael Ardebola
//COP3502
//04/03/17

import java.util.Scanner;

public class LucyTattooParlor {


	
	int[][]myArray;
	public static void main(String[]args){
		
		//sets the first command line int to the # of artists
		//sets the second command line int to the # of customers per artist
		//creates an array "shop" that is (Artists X Customers)
		
		int rowArtist = Integer.parseInt(args[0]);
		int colCustomer = Integer.parseInt(args[1]);
		
		TattooCustomer[][]shop = new TattooCustomer[rowArtist][colCustomer];
		
		Scanner keyboard = new Scanner(System.in);
		
		
	while(true){
		
			//menu that keeps running until "Exit" is entered
			System.out.println(" ___________________");
			System.out.println("|     Main Menu     |"); 
			System.out.println("| 1. Add Customer   |");  
			System.out.println("| 2. Print Waitlist |");
			System.out.println("| 3. Exit           |");
			System.out.println("|___________________|");
			System.out.print("\nInput: ");
			int option = keyboard.nextInt();
				
		if(option==1){
			//if option 1, program asks for given parameters of a TattooCustomer customer
			//stores this information and creates an object with the given responses
			
			System.out.println("Customer Name: ");
			String name = keyboard.next();
		
			System.out.println("Tattoo?");
			String tattoo = keyboard.next();
			
			System.out.println("How many minutes will your tattoo take?");
			int minutes = keyboard.nextInt();
		
			System.out.println("Specific Artist? If so, say yes, otherwise say no.");
			String response = keyboard.next();
		
			TattooCustomer customer = new TattooCustomer(name,tattoo,minutes);
		
		
				if(response.equals("no")){
					addCustomer(shop,customer);
				}
				
				else if(response.equals("yes")){
					System.out.println("What number artist?");
					int tech = keyboard.nextInt();
					addCustomer(shop,customer,tech-1);
				}
				
				else{
					System.out.println("Error, please try again.");
				}
			}
			
			
		if(option==2){
			//if option 2 is selected, the printWaitList method is run on the current shop
			
				printWaitList(shop);
				System.out.println("");	
		}
		
		if(option==3){
			//if option 3 is run, the program ends and a closing message is displayed
			
			System.out.println("Thank you for using this program!");
			System.exit(0);
		}
	}
	}
	
	public static void printWaitList(TattooCustomer[][]a){
		//print array that runs a for-loop accessing the entire 2D array (shop)
		//adds the word "Artist" plus the index+1 (since index starts at 0 and you can't have Artist 0) in each row of the first column
		//inner loop runs if the element is NOT null, it prints the name (getName()) of that specific element/customer
		
		for(int i =0; i<a.length;i++){
			System.out.print("\n");
			System.out.print("Artist "+(i+1)+": ");
			
			for(int j=0;j<a[i].length;j++){
				
				if(a[i][j]!=null){
					System.out.print(a[i][j].getName()+" ");
				}
				else
					System.out.print(" null ");
			}
		}
	
	}
	
	public static int computeMinutesOfWork(TattooCustomer[]a){
		//runs through an array (the row of the Artist) 
		//if the value is NOT null, it totals "+=" the minutes of that element (getMinutes())
		//returns that totel
		
		int total=0;
		for(int i =0; i<a.length;i++){
			if(a[i]!=null)
				total+=a[i].getMinutes();
		}
		return total;
	}
	
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c, int artistNum){
		//Method is run if that customer requests a SPECIFIC ARTIST
		//searches the row array of that specific artistNum (ex: Artist 2 would access row 2 [index is changed to -1 to allow this]
		//must satisfy the following: 
		//       1. if the spot is NULL (open)
		//       2. the minutes for that specific tattoo + the minutes of work the artist already has is LESS THAN 480 min (8 hours)
		//if these are true, then array at spot[artistNum][i] = c (that customer object)
		//returns true
		
		for(int i =0; i<a[artistNum].length; i++){	
			if(a[artistNum][i]==null&&(c.getMinutes()+computeMinutesOfWork(a[artistNum])<480)){
				a[artistNum][i]=c;
				return true;
			}
		}
		//error message displays and returns FALSE if the entire row is FULL or if the customer's tattoo would exceed allowed time 
		System.out.println("**ERROR**");
		return false;	
	}
	
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c) {
		//This method adds a customer to the SHORTEST WAITLIST in terms of minutes
		//set the minimum minutes to be 480
		//set artist = 1, (irrelevant because it gets overrided)
		
		int min = 480;
		int artist = 1;
		
		//this for-loop accesses each row of artists (starting at the first one)
		//calculates the shortest wait=time for each row using a temp value of "min"
		//at the end of the for-loop "Artist" becomes the index of the spot where the customer will be added
		
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[i].length;j++){
				
				if(a[i][j]==null){	
					if(computeMinutesOfWork(a[i])<min){
						min = computeMinutesOfWork(a[i]);
						artist = i;
					}
					
				}
			}
			
		}
		//this for-loop adds that customer to said index of "artist"
		//the specifications are provided below, but the spot must be null and the minutes cannot exceed 480 minutes
		//returns true if this passes and adds the customer
		
		for(int z =0; z<a[artist].length; z++){	
			if(a[artist][z]==null&&(c.getMinutes()+computeMinutesOfWork(a[artist])<480)){
				a[artist][z]=c;
				return true;
			}
		}
		return false;
}
}	
