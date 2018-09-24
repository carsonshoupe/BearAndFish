import java.util.Arrays;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

public class RiverSimulatorTester{
	@Test
	public void riverSimulatorTest(){
		RiverSimulator riverSim = new RiverSimulator(20, new BearFactory(), 4, new FishFactory(), 10);
		
		riverSim.runSimulation(20);
		
		for (int iterations = 0; iterations < 21; iterations++){
			System.out.println(riverSim.simulatedRiver[iterations] + "\n");
		}
}
}

//javac -cp .;C:\junit\junit-4.10.jar RiverSimulatorTester.java
//java -cp .;C:\junit\junit-4.10.jar org.junit.runner.JUnitCore RiverSimulatorTester