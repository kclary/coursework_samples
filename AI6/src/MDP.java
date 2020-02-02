
public class MDP {
	int states;
	double[] reward;
	double[][][] transition; 
	
	double discount = 1;
	double epsilon;
	
	public MDP(int s, double[] r, double[][][] t) { 
		states = s;
		reward = r;
		transition = t;
	}
	
	public double[] valueIteration() { 
		double[] utility = new double[states];
		double delta = 1;
		int idx = 0;
		
		while(delta > epsilon*(1-discount)/discount) { 
			delta = 0;
			for(int i = 0; i < states; i++) { 
				double u  = utility[i]; 
				utility[i] = reward[i] + discount * maxState(utility, i);
				double d = Math.abs(u - utility[i]);
				if(d > delta) { 
					delta = d;
				}
			}
			idx++;
		}
		System.out.println(idx + " iterations");
		return utility;
	}
	
	public double maxState(double[] utility, int state) { 
		double[] t = new double[4];
		for(int a = 0; a < 4; a++) { 
			for(int s = 0; s < states;  s++) { 
				t[a] += transition[a][state][s]*utility[s];
			}
		}
		double max = Double.MIN_VALUE; 
		for(int i = 0; i < 4; i++) { 
			if(t[i] > max) { 
				max = t[i];
			}
		}
		return max;
	}
	
	public int[] getPolicy(double[] utility) { 
		int[] policy = new int[states];
		
		for(int i = 0; i < states; i++) { 			
			double[] t = new double[4];
			for(int a = 0; a < 4; a++) { 
				for(int j = 0; j < states;  j++) { 
					t[a] += transition[a][i][j]*utility[j];
				}
			}

			double m = Double.MIN_VALUE;
			int  s = -1;
			
			for(int j = 0; j < 4; j++) { 
				if(t[j] > m) { 
					m = t[j];
					s = j;
				}
			}
			policy[i] = s;
		}
		
		return policy;
	}
}
