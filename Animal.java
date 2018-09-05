import java.util.Random;

class Animal{
	//Instance Variables: 
	public static int animalCounter = 0; 
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
	public void setAnimalID(int ID){
		this.animalID = ID;
	}
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
	
	/*
	@Override
	public Animal clone(){
		Animal animalClone = new Animal(this.getAnimalLocation()){
			Animal.animalCounter--; 
			animalClone.setAnimalID(this.getAnimalID());
			animalClone.setAnimalDirection(this.getAnimalDirection);
			return animalClone;
		}
	}
	*/
}


	class Bear extends Animal{
		//Instance Variables: 
		public static int bearCounter = 0; 
		private int bearID; 

		//Constructors:
		Bear(int location){
			super(location);
			Bear.bearCounter++;
			this.bearID = bearCounter; 
		}
		
		Bear(){
			this(0); 
		}

		//Methods: 
		public static int getBearCounter(){return Bear.bearCounter;}
		public int getBearID(){return this.bearID;}
		public void setBearID(int ID){
			bearID = ID; 
		}
		
		@Override
		public String toString(){
			String outputString = "Bear" + this.bearID;
			return outputString;
		}
		
		/*
		@Override
		public Bear clone(){
			Animal bearClone = new Bear(this.getAnimalLocation()); 
			Bear.bearCounter--;
			Animal.animalCounter--;
			bearClone.setBearID(this.getBearID());
			bearClone.setAnimalID(this.getAnimalID);
			bearClone.setAnimalDirection(this.getAnimalDirection);
			return bearClone;
		}
		*/
	}

	
	class Fish extends Animal{
		//Instance Variables: 
		public static int fishCounter = 0; 
		private int fishID; 
		
		//Constructors:
		Fish(int location){
			super(location);
			Fish.fishCounter++; 
			this.fishID = fishCounter; 
		}
		
		Fish(){
			this(0); 
		}
		
		//Methods: 
		public static int getFishCounter(){return Fish.fishCounter;}
		public int getFishID(){return this.fishID;}
		public void setFishID(int ID){
			this.fishID = ID;
		}
		
		@Override
		public String toString(){
			String outputString = "Fish" + this.fishID;
			return outputString;
		}
		
		/*
		@Override
		public Fish clone(){
			Animal fishClone = new Fish(this.getAnimalLocation()); 
			Fish.fishCounter--;
			Animal.animalCounter--;
			fishClone.setFishID(this.getFishID());
			fishClone.setAnimalID(this.getAnimalID);
			fishClone.setAnimalDirection(this.getAnimalDirection);
			return fishClone;
		}
		*/
	}
	
		
	
	