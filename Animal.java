import java.util.Random;

class Animal{
	//Instance Variables: 
	private static int animalCounter = 0; 
	private int animalID; 
	private int animalDirection;
	
	//Constructors: 
	Animal(){
		Animal.animalCounter++; 
		this.animalID = animalCounter; 
		this.animalDirection = 0;
	}
	
	//Methods: 
	public static int getAnimalCounter(){return Animal.animalCounter;}
	public int getAnimalID(){return this.animalID;}
	public int getAnimalDirection(){return this.animalDirection;}
	public void setAnimalDirection(int direction){
		this.animalDirection = direction;
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
		Bear(){
			Bear.bearCounter++;
			this.bearID = bearCounter; 
		}

		//Methods: 
		public static int getBearCounter(){return Bear.bearCounter;}
		public int getBearID(){return this.bearID;}
	}

	
	class Fish extends Animal{
		//Instance Variables: 
		private static int fishCounter = 0; 
		private int fishID; 
		
		//Constructors:
		Fish(){
			Fish.fishCounter++; 
			this.fishID = fishCounter; 
		}
		
		//Methods: 
		public static int getFishCounter(){return Fish.fishCounter;}
		public int getFishID(){return this.fishID;}
	}
		
	
	