package util;

import java.util.Random;

public class RandomClass {

	private static RandomClass instance;
	
	private Random random;
	
	private RandomClass() {
		random = new Random();
	}
	
	public static RandomClass getInstance() {
		if(instance == null) {
			instance = new RandomClass();
		}
		return instance;
	}
	
	public int getRandomNumber(int lowerBound, int upperBound) {
		return random.nextInt(upperBound + 1) + lowerBound;
	}
}
