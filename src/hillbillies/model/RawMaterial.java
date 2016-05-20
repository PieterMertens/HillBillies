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
	private static boolean isValidWorld(World world) {
		if (world == null || !world.getIsTerminated())
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
	private void setWorld(World world) throws IllegalArgumentException {
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
	 * @return | result == true if the raw material has no world or if the
	 *         position is within the world boundaries
	 */
	private static boolean isValidPosition(double[] position, World world) {
		if (world == null || world.withinBoundaries((int) position[0], (int) position[1], (int) position[2])) {
			return true;
		}
		return false;
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
		if (!isValidPosition(position, this.getWorld()))
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
	private void setIsTerminated(boolean terminated) {
		this.terminated = terminated;

	}

	/**
	 * @post The raw material gets terminated.
	 * 
	 */
	public void terminate() {
		this.setIsTerminated(true);
		this.setIsAvailable(false);
		this.setWorld(null);

	}

	/**
	 * Variable registering the terminated status of this raw material.
	 */
	private boolean terminated;

	/**
	 * Return the unit that is carrying this raw material.
	 */
	@Basic
	@Raw
	public Unit getIsCarriedBy() {
		return this.isCarriedBy;
	}

	/**
	 * Check whether the given carrier is a valid carrier for any raw material.
	 * 
	 * @param isCarriedBy
	 *            The carrier to check.
	 * @return | result == true if the unit isn't terminated or if no-one is
	 *         carrying it
	 */
	private static boolean isValidIsCarriedBy(Unit isCarriedBy) {
		if (isCarriedBy == null || !isCarriedBy.isTerminated())
			return true;
		return false;
	}

	/**
	 * Set the carrier of this raw material to the given carrier.
	 * 
	 * @param isCarriedBy
	 *            The new carrier for this raw material.
	 * @post The carrier of this new raw material is equal to the given carrier.
	 *       | new.getIsCarriedBy() == isCarriedBy
	 * @throws IllegalArgumentException
	 *             The given carrier is not a valid carrier for any raw
	 *             material. | ! isValidIsCarriedBy(getIsCarriedBy())
	 */
	@Raw
	public void setIsCarriedBy(Unit isCarriedBy) throws IllegalArgumentException {
		if (!isValidIsCarriedBy(isCarriedBy))
			throw new IllegalArgumentException();
		this.setIsAvailable(false);
		this.isCarriedBy = isCarriedBy;
	}

	/**
	 * Variable registering the carrier of this raw material.
	 */
	private Unit isCarriedBy;

	/**
	 * Return whether this raw material is available.
	 */
	@Basic
	@Raw
	private boolean getIsAvailable() {
		return this.isAvailable;
	}

	/**
	 * Set the availability of this raw material to the given boolean.
	 * 
	 * @param isAvailable
	 *            The new availability for this raw material.
	 * @post The availability of this new raw material is equal to the given
	 *       availability. | new.getIsAvailible() == isAvailible
	 * @throws IllegalArgumentException
	 *             The given availability is not a valid availability for any
	 *             raw material. | ! isValidIsAvailible(getIsAvailible())
	 */
	@Raw
	private void setIsAvailable(boolean isAvailable) throws IllegalArgumentException {
		this.isAvailable = isAvailable;
	}

	/**
	 * Variable registering the availability of this raw material.
	 */
	private boolean isAvailable = true;

	/**
	 * Return the falling state of this unit.
	 */
	@Basic
	@Raw
	private boolean getIsFalling() {
		return this.isFalling;
	}

	/**
	 * Set the falling state of this unit to the given falling state.
	 * 
	 * @param isFalling
	 *            The new falling state for this unit.
	 * @post The falling state of this new unit is equal to the given falling
	 *       state. | new.getIsFalling() == isFalling
	 * @throws IllegalArgumentException
	 *             The given falling state is not a valid falling state for any
	 *             unit. | ! isValidIsFalling(getIsFalling())
	 */
	@Raw
	private void setIsFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

	/**
	 * Variable registering whether the raw material is falling.
	 */
	private boolean isFalling;

	/**
	 * Return the z target of this raw material.
	 */
	@Basic
	@Raw
	public double getZTarget() {
		return this.zTarget;
	}

	/**
	 * Check whether the given z target is a valid z target for any raw
	 * material.
	 * 
	 * @param z
	 *            target The z target to check.
	 * @return | result == true if the z target is between the z boundary
	 */
	public static boolean isValidZTarget(double zTarget,World world) {
		if(zTarget >= 0 && zTarget <= world.getNbCubesZ()) {
			return true;
		}
		return false;
	}

	/**
	 * Set the z target of this raw material to the given z target.
	 * 
	 * @param zTarget
	 *            The new z target for this raw material.
	 * @post The z target of this new raw material is equal to the given z
	 *       target. | new.getZTarget() == zTarget
	 * @throws IllegalArgumentException
	 *             The given z target is not a valid z target for any raw
	 *             material. | ! isValidZTarget(getZTarget())
	 */
	@Raw
	public void setZTarget(double zTarget) throws IllegalArgumentException {
		if (!isValidZTarget(zTarget,this.getWorld()))
			throw new IllegalArgumentException();
		this.zTarget = zTarget;
	}

	/**
	 * Variable registering the z target of this raw material.
	 */
	private double zTarget;

	public void advanceTime(double dt) {
		if (this.getIsFalling()) {
			double z;
			if (this.getPosition()[2] - this.getZTarget() > 0) {
				z = this.getPosition()[2] + dt * fallingSpeed;
			} else {
				z = this.getZTarget();
				this.setIsFalling(false);
			}
			double[] position = { this.getPosition()[0], this.getPosition()[1], z };
			this.setPosition(position);
		} else {
			if (this.getIsAvailable() && !this.getIsTerminated() && !this.getIsFalling() && !this.blockBelow()) {

				this.setZTarget(this.getPosition()[2] - 1);
				this.setIsFalling(true);

			}

		}
	}

	/**
	 * Return whether there is an impassable terrain type below the current
	 * position.
	 */
	private boolean blockBelow() {
		return (this.getPosition()[2] == 0.5 || this.getWorld().hasImpassableBelow((int) this.getPosition()[0],
				(int) this.getPosition()[1], (int) this.getPosition()[2]));
	}

	/**
	 * Variable holding the fallingSpeed of raw materials.
	 */
	private static final int fallingSpeed = -3;

}
