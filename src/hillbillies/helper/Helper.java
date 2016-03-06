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

}
