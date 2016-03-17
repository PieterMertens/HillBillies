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

	public static int randInt(int min, int max) {

		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;

	}

	public static boolean randBoolean() {
		
		return Math.random() < 0.5;
		
	}
	
	public static int[] getRandomPosition() {

		int[] targetPosition = new int[3];
		targetPosition[0] = Helper.randInt(0, 49);// TODO variabele voor groottevan wereld??
		targetPosition[1] = Helper.randInt(0, 49);
		targetPosition[2] = Helper.randInt(0, 49);

		return targetPosition;

	}
	
	public static double[] getCenterOfPosition(int[] position){
		
		double[] result = new double[position.length];

		for (int k = 0; k < position.length; k++) {
			result[k] = (double) position[k] + 0.5d;
		}

		return result;
		
	}
	
	

}
