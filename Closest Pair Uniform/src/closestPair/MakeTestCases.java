package closestPair;

import java.io.ByteArrayInputStream;
import java.util.Random;

public class MakeTestCases {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		for (int k = 1; k < 10; k++) {
			int nPoints = 2;
			Random rand = new Random();
			rand.setSeed(15*k);
			double[] x = rand.doubles(nPoints,-100000,100000).toArray();
			rand.setSeed(28*k);
			double[] y = rand.doubles(nPoints,-100000,100000).toArray();
			
			
			sb.append(nPoints + "\n");
			for (int i = 0; i < y.length; i++) {
				sb.append(String.format("%.2f",x[i]) + " " + String.format("%.2f",y[i]) + "\n");
				
			}
		}
		sb.append(0 + "\n");  
		
		ByteArrayInputStream bais = new ByteArrayInputStream(sb.toString().getBytes());
        System.setIn(bais);
			
	

	}

}
