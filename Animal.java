import java.util.Random;

class Animal{
	//Instance Variables: 
	private static int animalCounter = 0; 
	private int animalID; 
	private int animalDirection;
	private int animalLocation; 
	
	//Constructors: 
	Animal(int location){
		Animal.animalCounter++; 
		this.animalID = animalCounter; 
		this.animalDirection = 0;
		this.animalLocation = location; 
	}
	
	Animal(){
		this(0); 
	}
	
	//Methods: 
	public static int getAnimalCounter(){return Animal.animalCounter;}
	public int getAnimalID(){return this.animalID;}
	public int getAnimalDirection(){return this.animalDirection;}
	public int getAnimalLocation(){return this.animalLocation;}
	public void setAnimalDirection(int direction){
		this.animalDirection = direction;
	}
	public void setAnimalLocation(int location){
		this.animalLocation = location;
	}
	
	public int decideDirection(){
		Random rand = new Random(); 
		int direction = rand.nextInt(3)-1;
		return direction; 
	}
}


	class Bear extends Animal{
		//Instance Variables: 
		private static int bearCounter = 0; 
		private int bearID; 

		//Constructors:
		Bear(int location){
			Bear.bearCounter++;
			this.bearID = bearCounter; 
		}
		
		Bear(){
			this(0); 
		}

		//Methods: 
		public static int getBearCounter(){return Bear.bearCounter;}
		public int getBearID(){return this.bearID;}
		
		@Override
		public String toString(){
			String outputString = "Bear: " + this.bearID;
			return outputString;
		}
	}

	
	class Fish extends Animal{
		//Instance Variables: 
		private static int fishCounter = 0; 
		private int fishID; 
		
		//Constructors:
		Fish(int location){
			Fish.fishCounter++; 
			this.fishID = fishCounter; 
		}
		
		Fish(){
			this(0); 
		}
		
		//Methods: 
		public static int getFishCounter(){return Fish.fishCounter;}
		public int getFishID(){return this.fishID;}
		
		@Override
		public String toString(){
			String outputString = "Fish: " + this.fishID;
			return outputString;
		}
	}
		
	
	