package hillbillies.model;

public class Log extends RawMaterial {

	public Log(World world, double[] position) throws IllegalArgumentException {
		super(world, position);
		world.addLog(this);
	}

	public void setIsTerminated(boolean terminated) {
		super.setIsTerminated(terminated);
		if (terminated) {
			this.getWorld().addLog(this);
		} else {
			this.getWorld().removeLog(this);
		}
	}


}
