package proj1;

import java.util.Random;

public class RandomSingleton{

	private static Random gen;

	/**
	 * Gets the Random object
	 * 
	 * @return A Random object, if there is no object, it is created.
	 */
	public static synchronized Random getInstance() {
		// Don't know what this is? Google: "Java Ternary Operator"
		gen = (gen == null) ? new Random() : gen;
		return gen;
	}

	/**
	 * Sets the seed on the Random object. This is useful for JUnit testing to
	 * provide reliable, same results throughout tests.
	 * 
	 * @param seed
	 *            Seed to set in Random object
	 */
	public static void setSeed(long seed) {
		synchronized (gen) {
			gen = new Random(seed);
		}
	}
}