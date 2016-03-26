package hillbillies.model;

import java.util.Set;

public class Log extends RawMaterial {

	public Log(double[] position) throws IllegalArgumentException {
		super(position);
	}

	public void setIsPresent(boolean present) {
		super.setIsPresent(present);
		if (present) {
			logs.add(this);
		} else {
			logs.remove(this);
		}
	}

	public static Set<Log> logs;

}
