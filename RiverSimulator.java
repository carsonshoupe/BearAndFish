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
	public String[] simulatedRiver; 
	public int riverLength = 0;
	private static int bearReproductions; 
	private static int fishReproductions; 
	
	//Constructors:
	RiverSimulator(int lengthOfRiver, int numberOfBears, int numberOfFish){
		this.riverLength = lengthOfRiver;
		this.originalRiver = new Animal[this.riverLength]; 
		assignAnimalLocations(numberOfBears, numberOfFish); 
		
		this.workingRiver = new Animal[this.riverLength]; 
		this.workingRiver = originalRiver.clone();
		
		this.bearReproductions = 0; 
		this.fishReproductions = 0;
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
			System.out.println("nextLocation: " + nextLocation);
			animalCounter++;
			originalRiver[nextLocation] = new Bear(nextLocation);
			System.out.println("Bear thinks he's at location " + originalRiver[nextLocation].getAnimalLocation()); 
		}
		
		for (int fishCounter = 0; fishCounter < inputNumberOfFish; fishCounter++){
			int nextLocation = locations.get(animalCounter);
			System.out.println("nextLocation: " + nextLocation);
			animalCounter++;
			originalRiver[nextLocation] = new Fish(nextLocation); 
			System.out.println("Fish thinks he's at location " + originalRiver[nextLocation].getAnimalLocation()); 
		}
	}
	
	
		
		

	
	public String[] runSimulation(int numberOfIterations){
		this.simulatedRiver = new String [numberOfIterations+1]; 
		this.simulatedRiver[0] = Arrays.toString(this.workingRiver);
		
		for (int iteration = 1; iteration < numberOfIterations + 1; iteration++){
			System.out.println("We're on iteration " + iteration); 
			
			//Set Animals' Direction
			for (int riverLocation = 0; riverLocation < this.riverLength; riverLocation++){
				Animal currentAnimal = this.workingRiver[riverLocation];
				if (currentAnimal instanceof Animal){
					currentAnimal.setAnimalDirection(currentAnimal.decideDirection());
				}
			}
			
			System.out.println(Arrays.toString(this.workingRiver)); 
			
			//update workingRiver
			for (int riverLocation = 0; riverLocation < this.riverLength; riverLocation++){
				
				System.out.println("We're on river location " + riverLocation);
				
				Animal currentAnimal = this.workingRiver[riverLocation]; 
				
				System.out.println("\t" + currentAnimal + " is at this location"); 
				
				
				if (currentAnimal instanceof Animal){	

					System.out.println("\t Location: " + currentAnimal.getAnimalLocation());
					System.out.println("\t Direction: " + currentAnimal.getAnimalDirection());
					
					System.out.println(Arrays.toString(this.workingRiver));
					
					int potentialNewLocation = currentAnimal.getAnimalLocation() + currentAnimal.getAnimalDirection();
					if (potentialNewLocation < 0 || potentialNewLocation > this.riverLength-1){
						continue;
					}
					if (determineShallowCollision(currentAnimal) == false){
						this.executeMovement(currentAnimal); 
						if (currentAnimal.getAnimalDirection() == 1){
							riverLocation++;
						}
						continue;
					}
					if (determineShallowCollision(currentAnimal) == true && this.workingRiver[potentialNewLocation] == null){
						this.resolveCollision(currentAnimal); 
						continue;
					}
					if (this.determineShallowCollision(currentAnimal) == true && this.workingRiver[potentialNewLocation] instanceof Animal){
						if (this.determineDeepCollision(currentAnimal) == true){
							this.resolveCollision(currentAnimal); 
							continue;
						}
						else{
							int locationOfAnimalToUpdate = riverLocation;
							while (this.workingRiver[locationOfAnimalToUpdate] instanceof Animal){
								locationOfAnimalToUpdate = locationOfAnimalToUpdate + currentAnimal.getAnimalDirection(); 
							}
							
							locationOfAnimalToUpdate = locationOfAnimalToUpdate - currentAnimal.getAnimalDirection(); //while loop runs 1 too many times, this is a quick fix
							
							int numOfUpdates = locationOfAnimalToUpdate - currentAnimal.getAnimalLocation();
							
							for (int updateLocations = locationOfAnimalToUpdate; updateLocations != riverLocation; updateLocations = updateLocations - currentAnimal.getAnimalDirection()){
								this.executeMovement(workingRiver[updateLocations]); 
							}
							this.executeMovement(currentAnimal);
							if (currentAnimal.getAnimalDirection() == 1){
								riverLocation = riverLocation + numOfUpdates;
							}
							continue;
						}
					}
					else{
						this.resolveCollision(currentAnimal); 
					}
				}
			}
			this.executeReproductions(); 
			
			System.out.println("Updating simulatedRiver . . . "); 
			this.simulatedRiver[iteration] = Arrays.toString(this.workingRiver); 
		}
		
		return simulatedRiver;
	}
	
	public boolean determineShallowCollision(Animal inputAnimal){
		if (inputAnimal.getAnimalDirection() == 0){
			return false; 
		}
		
		int potentialNewLocation = inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection(); 
		
		if (this.workingRiver[potentialNewLocation] == null && inputAnimal.getAnimalDirection() == 1){
			int checkLocation = inputAnimal.getAnimalLocation() + 2;
			if (checkLocation < 0 || checkLocation > this.riverLength-1){
				return false; 
			}
			else if (this.workingRiver[checkLocation] instanceof Animal && this.workingRiver[checkLocation].getAnimalDirection() == -1){
				
				System.out.println("This animal collides in the middle with the input animal" + this.workingRiver[checkLocation].toString());
				System.out.println("Direction: " + this.workingRiver[checkLocation].getAnimalDirection());
				
				return true;
			}
			else{
				return false;
			}
		}
		
		if (this.workingRiver[potentialNewLocation] instanceof Animal){
			return true;
		}
		return false; //I think the above covers all posibilities, but i'm not confident enough to use the else for the last statement. 
	}
	
	public boolean determineDeepCollision(Animal inputAnimal){
		int potentialNewLocation = inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection(); 
		Animal hitAnimal; 
		
		if (potentialNewLocation < 0 || potentialNewLocation > this.riverLength-1){
			return true;
		}
		
		if (this.workingRiver[potentialNewLocation] == null){
			return false;
		}
		
		hitAnimal = this.workingRiver[potentialNewLocation];
		
		if (hitAnimal.getAnimalDirection() == inputAnimal.getAnimalDirection()){
			if (determineDeepCollision(hitAnimal) == true){
				return true;
			}
			else{
				return false;
			}
		}
		
		if (hitAnimal.getAnimalDirection() != inputAnimal.getAnimalDirection()){
			return true;
		}
		
		System.out.println("The program should never reach this point, but I put a return true down here to get it to compile"); 
		return true;
	}
	
	public Animal animalCollidedWith(Animal inputAnimal){
		Animal movingAnimal = inputAnimal; 
		Animal hitAnimal; 
		
		if (workingRiver[movingAnimal.getAnimalLocation() + movingAnimal.getAnimalDirection()] instanceof Animal){
			hitAnimal = workingRiver[movingAnimal.getAnimalLocation() + movingAnimal.getAnimalDirection()]; 
		}
		else{//this is risky, but it might work.  maybe revisit.
			hitAnimal = workingRiver[movingAnimal.getAnimalLocation() + (movingAnimal.getAnimalDirection())*2];
		}
		return hitAnimal;
	}
	
	public void executeMovement(Animal inputAnimal){
		if (inputAnimal.getAnimalDirection() == 0){
			//do nothing
		}
		else{
			int currentLocation = inputAnimal.getAnimalLocation();
			int updatedLocation = inputAnimal.getAnimalLocation() + inputAnimal.getAnimalDirection();
			
			this.workingRiver[updatedLocation] = this.workingRiver[currentLocation];
			
			inputAnimal.setAnimalLocation(updatedLocation);
			
			this.workingRiver[currentLocation] = null;
		}
	}
	
	public void resolveCollision(Animal inputAnimal){
		Animal movingAnimal = inputAnimal;
		Animal hitAnimal = animalCollidedWith(movingAnimal); 
		
		if (movingAnimal instanceof Bear && hitAnimal instanceof Bear){
			this.bearReproductions++;
		}
		
		if (movingAnimal instanceof Bear && hitAnimal instanceof Fish){
			this.workingRiver[hitAnimal.getAnimalLocation()] = null;
		}
		
		if (movingAnimal instanceof Fish && hitAnimal instanceof Bear){
			this.workingRiver[movingAnimal.getAnimalLocation()] = null;
		}
		
		if (movingAnimal instanceof Fish && hitAnimal instanceof Fish){
			this.fishReproductions++; 
		}	
	}
	
	public void executeReproductions(){
		ArrayList<Integer> openLocations = new ArrayList<Integer>(this.riverLength); 
		
		for (int locationCounter = 0; locationCounter < this.riverLength; locationCounter++){
			if (this.workingRiver[locationCounter] == null){
				openLocations.add(locationCounter); 
			}
		}
		
		System.out.println("openLocations before shuffle: " + openLocations.toString()); 
		
		Collections.shuffle(openLocations); 
		
		System.out.println("openLocations after shuffle: " + openLocations.toString()); 
		
		System.out.println("numberOfBearReproductions: " + this.bearReproductions); 
		System.out.println("numberOfFishReproductions: " + this.fishReproductions);
		
		int numOpenLocations = openLocations.size(); 
		int locationCounter = 0; 
		while (locationCounter < numOpenLocations){
			if (this.bearReproductions > 0){
				this.workingRiver[openLocations.get(locationCounter)] = new Bear(openLocations.get(locationCounter)); 
				this.bearReproductions--; 
				locationCounter++; 
			}
			if (locationCounter == numOpenLocations){
				break;
			}
			if (this.fishReproductions > 0){
				this.workingRiver[openLocations.get(locationCounter)] = new Fish(openLocations.get(locationCounter)); 
				this.fishReproductions--;
				locationCounter++;
			}
			if (this.bearReproductions == 0 && this.fishReproductions == 0){
				break;
			}
		}
		
		this.bearReproductions = 0;
		this.fishReproductions = 0; 
	}
}
		
		
		
	