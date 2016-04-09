package hillbillies.model;

public class Boulder extends RawMaterial {

	public Boulder(World world, double[] position) throws IllegalArgumentException {
		super(world, position);
		world.addBoulder(this);

	}

	public void terminate() {
		super.terminate();
		this.getWorld().removeBoulder(this);
		this.setWorld(null);
		this.stopCarrying();

	}

	public void setIsCarriedBy(Unit isCarriedBy) {
		super.setIsCarriedBy(isCarriedBy);
		this.getIsCarriedBy().setCarryingBoulder(true);
		this.getWorld().removeBoulder(this);
	}

	public void stopCarrying() {
		this.getIsCarriedBy().setCarryingBoulder(false);
		this.setIsCarriedBy(null);
	}

}
