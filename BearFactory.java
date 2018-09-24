class BearFactory{
	//Instance Variables:
	private int bearCounter = 0; 
	
	//Constructors:
	
	//Methods:
	public int getNumBears(){return this.bearCounter;}; 
	
	public Bear getBear(int location){
		this.bearCounter++; 
		return new Bear(this.bearCounter, location); 
	}
}
	