package hillbillies.helper;

public class Helper {

	public static int[] doubleArrayToIntArray(double[] array) {

		int[] result = new int[array.length];

		for (int k = 0; k < array.length; k++) {
			result[k] = (int) array[k];
		}

		return result;

	}

	public static double[] intArrayToDoubleArray(int[] array) {

		double[] result = new double[array.length];

		for (int k = 0; k < array.length; k++) {
			result[k] = (double) array[k];
		}

		return result;

	}

	public static double[] getCenterOfPosition(int[] position) {

		double[] result = new double[position.length];

		for (int k = 0; k < position.length; k++) {
			result[k] = (double) position[k] + 0.5d;
		}

		return result;

	}

	public static double[] getCenterOfPosition(double[] position) {

		double[] result = new double[position.length];

		for (int k = 0; k < position.length; k++) {
			result[k] = (int) position[k] + 0.5d;
		}

		return result;

	}

	public static int randInt(int min, int max) {

		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;

	}

	public static boolean randBoolean() {

		return Math.random() < 0.5;

	}

	public static int[] getRandomPosition(int nbX, int nbY, int nbZ) {

		int[] targetPosition = new int[3];
		targetPosition[0] = Helper.randInt(0, nbX-1);
		targetPosition[1] = Helper.randInt(0, nbY-1);
		targetPosition[2] = Helper.randInt(0, nbZ-1);

		return targetPosition;

	}

	/*
	 * ----- World -----
	 */

	// public static boolean isPassable(){
	//
	// }

}
