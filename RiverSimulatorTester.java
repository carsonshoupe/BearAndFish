import java.util.Arrays;

class RiverSimulatorTester{
public static void main(String args[]){
	RiverSimulator riverSim = new RiverSimulator(50, 10, 25);
	
	riverSim.runSimulation(100);
	
	for (int iterations = 0; iterations < 101; iterations++){
		System.out.println(riverSim.simulatedRiver[iterations] + "\n");
	}
}
}