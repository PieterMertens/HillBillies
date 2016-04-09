package hillbillies.model;

public class Boulder extends RawMaterial{

	public Boulder(World world, double[] position) throws IllegalArgumentException {
		super(world, position);
		world.addBoulder(this);
		
	}
	

	public void setIsTerminated(boolean terminated) {
		super.setIsTerminated(terminated);
		if (terminated) {
			this.getWorld().addBoulder(this);;
		} else {
			this.getWorld().removeBoulder(this);;
		}
	}


}
