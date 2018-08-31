class Animal{
	//Instance Variables: 
	public static int animalId = 0; 
	private int nThAnimal; 
	
	//Constructors: 
	Animal(){
		Animal.animalId++; 
		nThAnimal = animalId; 
	}
	
	//Methods: 
	public int getAnimalId(){return nThAnimal;}

	
	
	class Bear extends Animal{
		//Instance Variables: 
		public static int bearId = 0; 
		
		
		//Constructors:
		Bear(){
			bearId++;
			
			private int nThBear = bearId; 
		}
			 
		//Methods: 
		public int getBearId(){return nThBear;}
	}
	
	class Fish extends Animal{
		//Instance Variables: 
		public static int fishId = 0; 
		
		//Constructors:
		Fish(){
			fishId++; 
			
			private int nThFish = fishId; 
		
		//Methods: 
		public int getFishId(){return nThFish;}
		
	}
		
	
	