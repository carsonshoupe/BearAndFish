class RiverSimulator{
	//Instance Variables:
	public Animal[] originalRiver;
	public Animal[] workingRiver;
	public Animal[][] simulatedRiver; 
	public int riverLength = 0;
	
	//Constructors:
	RiverSimulator(int lengthOfRiver, int numberOfBears, int numberOfFish){
		this.riverLength = lengthOfRiver;
		this.originalRiver = new Animal[this.riverLength]; 
		//this.originalRiver = assignAnimalLocations(numberOfBears, numberOfFish); 
		
		this.workingRiver = new Animal[this.riverLength]; 
		this.workingRiver = originalRiver.clone();
	}
		

		
	//Methods:
	/*
	Animal[] assignAnimalLocations(int inputNumberOfBears, int inputNumberOfFish){
		set<Integer> locations = new HashSet<Integer>();
		for (int counter = 0; counter < this.riverLength; counter++){
			locations.add(counter);
		}
		*/

	
	public Animal[][] runSimulation(int numberOfIterations){
		this.simulatedRiver = new Animal[numberOfIterations][this.riverLength]; 
		this.simulatedRiver[0] = this.workingRiver;
		
		for (int iteration = 1; iteration < numberOfIterations + 1; iteration++){
			
			//Set Animals' Direction
			for (int riverLocation = 0; riverLocation < this.riverLength;){
				Animal currentAnimal = this.workingRiver[riverLocation];
				if (currentAnimal instanceof Animal){
					currentAnimal.setAnimalDirection(currentAnimal.decideDirection());
				}
			}
			
			

			//Consider movements and execute collisions that result in fish death -- also mark when reproduction happens
				/*
				1. find first(next relevant) animal left to right
				2. look at 3 slots after
				3. if only animal, continue
				4. if 2 animals:
					consider direction of first animal relevant to other animal movement
					if no collision, execute movement
						if fish/bear collision, eliminate fish
					if same-animal-type collision
						increment animaltype reproduction counter
				5. if 3 or more animals, 
					only consider animal adjacent to original animal and execute step 4. 
				*/
			//execute reproductions 
			
				
				
				
		}
		return simulatedRiver;
	}
}
		
		
		
	