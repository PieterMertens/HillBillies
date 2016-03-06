package hillbillies.util;

public class Helper {
	
	public int[] doubleArrayToIntArray(double[] array) {
		//TODO commentaar

		int[] result = new int[array.length];

		for (int k = 0; k < array.length; k++) {
			result[k] = (int) array[k];
		}

		return result;

	}
	
	public double[] intArrayToDoubleArray(int[] array) {
		//TODO commentaar

		double[] result = new double[array.length];

		for (int k = 0; k < array.length; k++) {
			result[k] = (double) array[k];
		}

		return result;

	}

}
