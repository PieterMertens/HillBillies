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
		targetPosition[0] = Helper.randInt(0, nbX - 1);
		targetPosition[1] = Helper.randInt(0, nbY - 1);
		targetPosition[2] = Helper.randInt(0, nbZ - 1);

		return targetPosition;

	}

	public static boolean getIsSamePosition(double[] position1, double[] position2) {
		if (position1[0] == position2[0] && position1[1] == position2[1] && position1[2] == position2[2]) {
			return true;
		}
		return false;
	}
	
	public static boolean getIsSamePosition(int[] position1, int[] position2) {
		if (position1[0] == position2[0] && position1[1] == position2[1] && position1[2] == position2[2]) {
			return true;
		}
		return false;
	}
	

	/**
	 * Return the distance between two given positions.
	 * 
	 * @param position1
	 *            The first position
	 * @param position2
	 *            The second position
	 * @return The square root of the sum of the difference between the two x, y
	 *         and z coordinates squared
	 */
	public static double getDistanceBetweenPositions(double[] position1, double[] position2) {

		return Math.sqrt(Math.pow(position2[0] - position1[0], 2) + Math.pow(position2[1] - position1[1], 2)
				+ Math.pow(position2[2] - position1[2], 2));

	}
}
