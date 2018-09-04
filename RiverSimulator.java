import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
		assignAnimalLocations(numberOfBears, numberOfFish); 
		
		this.workingRiver = new Animal[this.riverLength]; 
		this.workingRiver = originalRiver.clone();
	}
		

		
	//Methods:
	
	public void assignAnimalLocations(int inputNumberOfBears, int inputNumberOfFish){ 
		ArrayList<Integer> locations = new ArrayList<Integer>(this.riverLength);
		for (int counter = 0; counter < this.riverLength; counter++){
			locations.add(counter);
		}
		
		//String listString = locations.stream().map(Object::toString).collect(Collectors.joining(", "));
		//System.out.println("Locations before Shuffle: " + listString); 
		
		Collections.shuffle(locations);
		
		//listString = locations.stream().map(Object::toString).collect(Collectors.joining(", "));
		//System.out.println("Locations after Shuffle: " + listString); 
		
		int animalCounter = 0;
		
		for (int bearCounter = 0; bearCounter < inputNumberOfBears; bearCounter++){
			int nextLocation = locations.get(animalCounter);
			animalCounter++;
			originalRiver[nextLocation] = new Bear(nextLocation);
		}
		
		for (int fishCounter = 0; fishCounter < inputNumberOfFish; fishCounter++){
			int nextLocation = locations.get(animalCounter);
			animalCounter++;
			originalRiver[nextLocation] = new Fish(nextLocation); 
		}
	}
		
		

	
	public Animal[][] runSimulation(int numberOfIterations){
		this.simulatedRiver = new Animal[numberOfIterations][this.riverLength]; 
		this.simulatedRiver[0] = this.workingRiver;
		
		for (int iteration = 1; iteration < numberOfIterations + 1; iteration++){
			
			//Set Animals' Direction
			for (int riverLocation = 0; riverLocation < this.riverLength; riverLocation++){
				Animal currentAnimal = this.workingRiver[riverLocation];
				if (currentAnimal instanceof Animal){
					currentAnimal.setAnimalDirection(currentAnimal.decideDirection());
				}
			}
			
			//update workingRiver
			for (int riverLocation = 0; riverLocation < this.riverLength; riverLocation++){
				Animal currentAnimal = this.workingRiver[riverLocation]; 
				if (currentAnimal instanceof Animal){
					if (this.determineDeepCollision(currentAnimal) == false){
						this.executeMovement(currentAnimal); 
					}
					else{
						this.resolveCollision(currentAnimal); 
					}
				}
			}
			this.executeReproductions(); 
			
			this.simulatedRiver[iteration] = this.workingRiver; 
		}
		
		return simulatedRiver;
	}
	
	public boolean determineShallowCollision(Animal inputAnimal){
		if (inputAnimal.getAnimalDirection() == 0){
			return false; 
		}
		
		if (inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection() <= 0 || inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection() >= this.riverLength-1){
			return true; 
		}  // maybe have this mark as a collision and in the collision resolver, have it check at the beginning to see if its a collision with the edge of the river
		
		if (this.workingRiver[inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection()] == null){
			return false;
		}
		
		if (this.workingRiver[inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection()] instanceof Animal){
			return true;
		}
		return false; //I think the above covers all posibilities, but i'm not confident enough to use the else for the last statement. 
	}
	
	public boolean determineDeepCollision(Animal inputAnimal){
		if (determineShallowCollision(inputAnimal) == true){
			Animal animalMoving = inputAnimal;
			Animal animalHit = this.workingRiver[animalMoving.getAnimalLocation() + animalMoving.getAnimalDirection()];
			
			Animal[] animalCollisionArray = new Animal[this.riverLength]; 			 
			animalCollisionArray[0] = inputAnimal; 
			animalCollisionArray[1] = animalHit;
			
			animalMoving = animalHit;
			
			int collisionCounter = 2;
			if (animalMoving.getAnimalDirection() == 0){
				return true; 
			}
			
			if (this.workingRiver[animalMoving.getAnimalLocation() + animalMoving.getAnimalDirection()] == null){
				return false; 
			}
			
			while (this.determineShallowCollision(animalMoving) == true){
				animalHit = this.workingRiver[animalMoving.getAnimalLocation() + animalMoving.getAnimalDirection()];
				if (animalMoving.getAnimalDirection() != animalHit.getAnimalDirection()){
					return true;
				}
				if (animalHit.getAnimalLocation() == 0 || animalHit.getAnimalLocation() == this.riverLength-1){
					return true;
				}
				animalCollisionArray[collisionCounter] = animalHit; 
				animalMoving = animalHit; 
				collisionCounter++;
			}
		}
		return false; 
	}
	
	public void executeMovement(Animal inputAnimal){
		
	}
	
	public void resolveCollision(Animal inputAnimal){
	
	}
	
	public void executeReproductions(){
		
	}
			
			
			
		
		
		
		
		
		
		
		
				/*
					
					
			//handling basecase and simple moves
										
					if (currentAnimal.getAnimalDirection() == -1){
						if (riverLocation == 0){
							continue;
						}
						workingRiver.excuteMovement(currentAnimal); 
					}
			
			//handling 1 animal case
					
					if (!(this.workingRiver[riverLocation+1] instanceof Animal) && !(this.workingRiver[riverLoction+2] instanceof Animal)){
						workingRiver.executeMovement(currentAnimal); 
					}
					
			//handling 2 animal cases
					
					if (this.workingRiver[riverLocation+1] instanceof Animal){
						//check collision
						if (currentAnimal.getAnimalDirection() == 1 && this.workingRiver[riverLocation+1] == 1){
							//check next animal -- potentially more
						}
						
					
					
					
			
			//look at next two animals
			
			//all three are animals
				if (this.workingRiver[riverLocation+1] instanceof Animal && this.workingRiver[riverLoction+2] instanceof Animal){
					
			
			

			//Consider movements and execute collisions that result in fish death -- also mark when reproduction happens
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
			//execute reproductions 
			
			*/
}
		
		
		
	