package hillbillies.model;

public class Log extends RawMaterial {

	public Log(World world, double[] position) throws IllegalArgumentException {
		super(world, position);
		world.addLog(this);
	}

	public void terminate() {
		super.terminate();
		this.getWorld().removeLog(this);
		this.setWorld(null);
		this.stopCarrying();

	}

	public void setIsCarriedBy(Unit isCarriedBy) {
		super.setIsCarriedBy(isCarriedBy);
		this.getIsCarriedBy().setCarryingLog(true);
		this.getWorld().removeLog(this);
	}
	
	public void stopCarrying() {
		this.getIsCarriedBy().setCarryingLog(false);
		this.setIsCarriedBy(null);
	}

}
