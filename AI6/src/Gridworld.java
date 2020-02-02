import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Gridworld {
	public static void main(String[] args) throws FileNotFoundException { 
		Scanner in = new Scanner(new File("GW1.txt"));
		int states = in.nextInt();
		double[] rewards = new double[states];
		for(int i = 0; i < states; i++) { 
			rewards[i] = in.nextDouble();
		}
		double[][][] transition = new double[4][states][states];
		// actions: 0 = left, 1 = up, 2 = right, 3 = down
		in.nextLine();
		for(int i = 0; i < 4; i++) { 
			for(int j = 0; j < states; j++) { 
				String[] d = in.nextLine().split(",");
				for(int k = 0; k < states; k++) {
					transition[i][j][k] = new Double(d[k]);
				}
			}
		}
		
		MDP m = new MDP(states, rewards, transition);
		double[] utility = m.valueIteration();
		int[] policy = m.getPolicy(utility);
		
		for(int i = 0; i < states; i++) { 
			System.out.println("State " + i + ": "  + utility[i] + " " + policy[i]);
		}
		
		in.close();
	}
}
