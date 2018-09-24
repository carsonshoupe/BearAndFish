import java.util.Random;

abstract class Animal{
	//Instance Variables: 
	private int animalDirection = 0;
	private int animalLocation; 
	
	//Methods: 
	public int getAnimalDirection(){return this.animalDirection;}
	public int getAnimalLocation(){return this.animalLocation;}
	public void setAnimalDirection(int direction){this.animalDirection = direction;}
	public void setAnimalLocation(int location){this.animalLocation = location;}
	
	public int decideDirection(){
		Random rand = new Random(); 
		int direction = rand.nextInt(3)-1;
		return direction; 
	}
}


class Bear extends Animal{
	//Instance Variables: 
	private int bearID; 

	//Constructors:
	Bear(int bearCounter, int location){
		this.bearID = bearCounter; 
		setAnimalLocation(location);
	}

	//Methods: 
	public int getBearID(){return this.bearID;}
	public void setBearID(int ID){bearID = ID;}
	
	@Override
	public String toString(){
		String outputString = "Bear" + this.bearID;
		return outputString;
	}
}

	
class Fish extends Animal{
	//Instance Variables:  
	private int fishID; 
	
	//Constructors:
	Fish(int fishCounter, int location){
		this.fishID = fishCounter; 
		setAnimalLocation(location);
	}
	
	//Methods: 
	public int getFishID(){return this.fishID;}
	public void setFishID(int ID){this.fishID = ID;}
	
	@Override
	public String toString(){
		String outputString = "Fish" + this.fishID;
		return outputString;
	}
}
	
		
	
	