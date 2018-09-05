class AnimalTester{
public static void main(String args[]){
	Animal animalBoy = new Animal();
	Animal animalgirl = new Animal();
	Bear bearBoy = new Bear(); 
	Bear bearGirl = new Bear(); 
	Fish fishBoy = new Fish(); 
	Fish fishGirl = new Fish(); 
	
	System.out.println("Animal counter = " + Animal.getAnimalCounter());
	System.out.println("Bear counter = " + Bear.getBearCounter());
	System.out.println("Fish counter = " + Fish.getFishCounter());
	
	System.out.println("bearGirl BearID: " + bearGirl.getBearID());
	System.out.println("bearGirl AnimalID: " + bearGirl.getAnimalID());
	
	for (int counter = 0; counter < 100; counter ++){
		System.out.println("Testing " + counter + ": " + bearGirl.decideDirection());
	}
	
	System.out.println("bearGirl direction: " + bearGirl.getAnimalDirection());
	
	bearGirl.setAnimalDirection(bearGirl.decideDirection()); 
	
	System.out.println("bearGirl direction: " + bearGirl.getAnimalDirection());
	
	boolean ans = bearGirl.getClass() == fishGirl.getClass();
	
	System.out.println("bearGirl class equal fishGirl class?: " + ans);
	
	System.out.println("Class of bearGirl = " + bearGirl.getClass()); 
}
}
	