class FishFactory{
	//Instance Variables:
	private int fishCounter = 0; 
	
	//Constructors:
	
	//Methods:
	public int getNumfish(){return this.fishCounter;}; 
	
	public Fish getFish(int location){
		this.fishCounter++; 
		return new Fish(this.fishCounter, location); 
	}
}
	