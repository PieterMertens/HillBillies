package hillbillies.model;

import java.util.Set;

public class Boulder extends RawMaterial{

	public Boulder(double[] position) throws IllegalArgumentException {
		super(position);
	}
	

	public void setIsPresent(boolean present) {
		super.setIsPresent(present);
		if (present) {
			boulders.add(this);
		} else {
			boulders.remove(this);
		}
	}

	public static Set<Boulder> boulders;

}
