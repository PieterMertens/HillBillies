package hillbillies.model;


import be.kuleuven.cs.som.annotate.*;
import hillbillies.helper.Helper;

/**
 * @invar The position of each raw material must be a valid position for any raw
 *        material. | isValidPosition(getPosition())
 * @invar Each raw material can have its weight as weight. |
 *        canHaveAsWeight(this.getWeight())
 * @invar The world of each raw material must be a valid world for any raw
 *        material. | isValidWorld(getWorld())*
 * @invar The isCarriedBy of each raw material must be a valid isCarriedBy for
 *        any raw material. | isValidIsCarriedBy(getIsCarriedBy())
 * @invar The isAvailible of each raw material must be a valid isAvailible for
 *        any raw material. | isValidIsAvailible(getIsAvailible())
 */
public abstract class RawMaterial {

	/**
	 * Initialize this new raw material in given world, with given position.
	 * 
	 * @param world
	 *            The world for this new raw material.
	 * 
	 * @param position
	 *            The position for this new raw material.
	 * @effect The world of this new raw material is set to the given world. |
	 *         this.setWorld(world)
	 *
	 * @effect The position of this new raw material is set to the given
	 *         position. | this.setPosition(position)
	 * 
	 */
	public RawMaterial(World world, double[] position) throws IllegalArgumentException {
		this.setPosition(position);
		this.weight = Helper.randInt(10, 50);
		this.setWorld(world);
	}

	/**
	 * Return the world of this raw material.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return this.world;
	}

	/**
	 * Check whether the given world is a valid world for any raw material.
	 * 
	 * @param world
	 *            The world to check.
	 * @return | result ==
	 */
	public static boolean isValidWorld(World world) {
		if (world != null && !world.getIsTerminated())
			return true;
		return false;
	}

	/**
	 * Set the world of this raw material to the given world.
	 * 
	 * @param world
	 *            The new world for this raw material.
	 * @post The world of this new raw material is equal to the given world. |
	 *       new.getWorld() == world
	 * @throws IllegalArgumentException
	 *             The given world is not a valid world for any raw material. |
	 *             ! isValidWorld(getWorld())
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (!isValidWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
	}

	/**
	 * Variable registering the world of this raw material.
	 */
	private World world;

	/**
	 * Return the weight of this raw material.
	 */
	@Basic
	@Raw
	@Immutable
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Variable registering the weight of this raw material.
	 */
	private final int weight;

	/**
	 * Return the position of this raw material.
	 */
	@Basic
	@Raw
	public double[] getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given position is a valid position for any raw
	 * material.
	 * 
	 * @param position
	 *            The position to check.
	 * @return | result ==
	 */
	public static boolean isValidPosition(double[] position) {

		return true;
		// TODO kijken f geldige pos is
	}

	/**
	 * Set the position of this raw material to the given position.
	 * 
	 * @param position
	 *            The new position for this raw material.
	 * @post The position of this new raw material is equal to the given
	 *       position. | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any raw
	 *             material. | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (!isValidPosition(Helper.getCenterOfPosition(position)))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Variable registering the position of this raw material.
	 */
	private double[] position;

	/**
	 * Return if this raw material is terminated.
	 */
	@Basic
	@Raw
	public boolean getIsTerminated() {
		return this.terminated;
	}

	/**
	 * Set if this raw material is terminated.
	 * 
	 * @param present
	 *            The new terminated status for this raw material.
	 * @post The terminated status of this new raw material is equal to the
	 *       given terminated status. | new.getIsTerminated() == terminated
	 * @throws IllegalArgumentException
	 *             The given presence is not a valid presence for any raw
	 *             material. | ! isValidIsPresent(getIsPresent())
	 */
	@Raw
	public void setIsTerminated(boolean terminated) {
		this.terminated = terminated;

	}

	/**
	 * @post The raw material gets terminated.
	 * 
	 */
	public void terminate() {
		this.setIsTerminated(true);
		this.setIsAvailible(false);

	}

	/**
	 * Variable registering the terminated status of this raw material.
	 */
	private boolean terminated;

	/**
	 * Return the isCarriedBy of this raw material.
	 */
	@Basic
	@Raw
	public Unit getIsCarriedBy() {
		return this.isCarriedBy;
	}

	/**
	 * Check whether the given isCarriedBy is a valid isCarriedBy for any raw
	 * material.
	 * 
	 * @param isCarriedBy
	 *            The isCarriedBy to check.
	 * @return | result ==
	 */
	public static boolean isValidIsCarriedBy(Unit isCarriedBy) {
		if (isCarriedBy == null || !isCarriedBy.isTerminated())
			return true;
		return false;
	}

	/**
	 * Set the isCarriedBy of this raw material to the given isCarriedBy.
	 * 
	 * @param isCarriedBy
	 *            The new isCarriedBy for this raw material.
	 * @post The isCarriedBy of this new raw material is equal to the given
	 *       isCarriedBy. | new.getIsCarriedBy() == isCarriedBy
	 * @throws IllegalArgumentException
	 *             The given isCarriedBy is not a valid isCarriedBy for any raw
	 *             material. | ! isValidIsCarriedBy(getIsCarriedBy())
	 */
	@Raw
	public void setIsCarriedBy(Unit isCarriedBy) throws IllegalArgumentException {
		if (!isValidIsCarriedBy(isCarriedBy))
			throw new IllegalArgumentException();
		this.setIsAvailible(false);
		this.isCarriedBy = isCarriedBy;
	}

	/**
	 * Variable registering the isCarriedBy of this raw material.
	 */
	private Unit isCarriedBy;

	/**
	 * Return the isAvailible of this raw material.
	 */
	@Basic
	@Raw
	public boolean getIsAvailible() {
		return this.isAvailible;
	}

	/**
	 * Set the isAvailible of this raw material to the given isAvailible.
	 * 
	 * @param isAvailible
	 *            The new isAvailible for this raw material.
	 * @post The isAvailible of this new raw material is equal to the given
	 *       isAvailible. | new.getIsAvailible() == isAvailible
	 * @throws IllegalArgumentException
	 *             The given isAvailible is not a valid isAvailible for any raw
	 *             material. | ! isValidIsAvailible(getIsAvailible())
	 */
	@Raw
	public void setIsAvailible(boolean isAvailible) throws IllegalArgumentException {
		this.isAvailible = isAvailible;
	}

	/**
	 * Variable registering the isAvailible of this raw material.
	 */
	private boolean isAvailible = true;

	private boolean isFalling;
	private double ztarget;

	public void advanceTime(double dt) {
//		System.out.println(this.getPosition()[2]);
		if (this.isFalling) {
			double z;
			if (this.getPosition()[2] - this.ztarget > 0) {
				z = this.getPosition()[2] + dt * fallingSpeed;
			} else {
				z = this.ztarget;
				this.isFalling = false;
			}
			double[] position = { this.getPosition()[0], this.getPosition()[1], z };
			this.setPosition(position);
		} else {
		if (this.isAvailible && !this.getIsTerminated() && !this.isFalling && !this.blockBelow()) {// TODO isAvail & isTerm hetzelfde?

			this.ztarget = this.getPosition()[2] - 1;
			this.isFalling = true;

		}

	}}

	// /**
	// * Return whether the terrain type is passable at the current position.
	// */
	// public boolean atPassable() {
	// return this.getWorld().isPassable((int) this.getPosition()[0], (int)
	// this.getPosition()[1],
	// (int) this.getPosition()[2]);
	// }

	/**
	 * Return whether there is an impassable terrain type below the current
	 * position.
	 */
	public boolean blockBelow() {
		// if(this.getPosition()[2] - (int) this.getPosition()[2] == 0.5)
		return (this.getPosition()[2] == 0.5 || this.getWorld().hasImpassableBelow((int) this.getPosition()[0],
				(int) this.getPosition()[1], (int) this.getPosition()[2]));
	}

	/**
	 * Variable holding the fallingSpeed of raw materials.
	 */
	public static final int fallingSpeed = -3;

}
