package hillbillies.model;

public class Log extends RawMaterial {

	/**
	 * Initialize this new log in given world, with given position.
	 * 
	 * @param world
	 *            The world for this new log.
	 * 
	 * @param position
	 *            The position for this new log.
	 * @effect The world of this new log is set to the given world. |
	 *         this.setWorld(world)
	 *
	 * @effect The position of this new log is set to the given position. |
	 *         this.setPosition(position)
	 * 
	 */
	public Log(World world, double[] position) throws IllegalArgumentException {
		super(world, position);
		world.addLog(this);
	}

	/**
	 * @post The log gets terminated.
	 * 
	 */
	public void terminate() {
		super.terminate();
		this.getWorld().removeLog(this);
		this.setWorld(null);
		this.stopCarrying();

	}

	/**
	 * Set the isCarriedBy of this log to the given isCarriedBy.
	 * 
	 * @param isCarriedBy
	 *            The new isCarriedBy for this log.
	 * @post The isCarriedBy of this new log is equal to the given isCarriedBy.
	 *       | new.getIsCarriedBy() == isCarriedBy
	 * @throws IllegalArgumentException
	 *             The given isCarriedBy is not a valid isCarriedBy for any log.
	 *             | ! isValidIsCarriedBy(getIsCarriedBy())
	 */
	public void setIsCarriedBy(Unit isCarriedBy) {
		if (isCarriedBy != null) {
			super.setIsCarriedBy(isCarriedBy);
			this.getIsCarriedBy().setCarryingLog(true);
			this.getWorld().removeLog(this);
		} else {
			this.getWorld().addLog(this);
		}
	}

	/**
	 * Set the isCarriedBy of this log to the given isCarriedBy.
	 * 
	 * @post The isCarriedBy of this log is set to null. | this.getIsCarriedBy()
	 *       == null
	 * @post The log is no longer carried by the unit. |
	 *       this.getIsCarriedBy().getCarryingLog() == false
	 */
	public void stopCarrying() {
		if (this.getIsCarriedBy() != null) {
			this.getIsCarriedBy().setCarryingLog(false);
			this.setIsCarriedBy(null);
		}
	}

}
