package hillbillies.model;

public class Boulder extends RawMaterial {

	/**
	 * Initialize this new boulder in given world, with given position.
	 * 
	 * @param world
	 *            The world for this new boulder.
	 * 
	 * @param position
	 *            The position for this new boulder.
	 * @effect The world of this new boulder is set to the given world. |
	 *         this.setWorld(world)
	 *
	 * @effect The position of this new boulder is set to the given position. |
	 *         this.setPosition(position)
	 * 
	 */
	public Boulder(World world, double[] position) throws IllegalArgumentException {
		super(world, position);
		world.addBoulder(this);

	}

	/**
	 * @post The boulder gets terminated.
	 * 
	 */
	public void terminate() {
		this.getWorld().removeBoulder(this);
		this.stopCarrying();
		super.terminate();

	}

	/**
	 * Set the isCarriedBy of this boulder to the given isCarriedBy.
	 * 
	 * @param isCarriedBy
	 *            The new isCarriedBy for this boulder.
	 * @post The isCarriedBy of this new boulder is equal to the given
	 *       isCarriedBy. | new.getIsCarriedBy() == isCarriedBy
	 * @effect The boulder gets removed from its world
	 * @throws IllegalArgumentException
	 *             The given isCarriedBy is not a valid isCarriedBy for any
	 *             boulder. | ! isValidIsCarriedBy(getIsCarriedBy())
	 */
	public void setIsCarriedBy(Unit isCarriedBy) {
		if (isCarriedBy != null) {
			super.setIsCarriedBy(isCarriedBy);
			this.getIsCarriedBy().setCarryingBoulder(true);
			this.getWorld().removeBoulder(this);
		} else {
			if (!this.getIsTerminated()) {
				this.getWorld().addBoulder(this);
			}
		}
	}

	/**
	 * Set the isCarriedBy of this boulder to the given isCarriedBy.
	 * 
	 * @post The isCarriedBy of this boulder is set to null. |
	 *       this.getIsCarriedBy() == null
	 * @post The boulder is no longer carried by the unit. |
	 *       this.getIsCarriedBy().getCarryingBoulder() == false
	 */
	public void stopCarrying() {
		if (this.getIsCarriedBy() != null) {
			this.getIsCarriedBy().setCarryingBoulder(false);
			this.setIsCarriedBy(null);
		}
	}

}
