import java.util.Arrays;

class RiverSimulatorTester{
public static void main(String args[]){
	RiverSimulator riverSim = new RiverSimulator(100, 10, 30);
	System.out.println(Arrays.toString(riverSim.workingRiver)); 
	
	riverSim.runSimulation(5);
}
}