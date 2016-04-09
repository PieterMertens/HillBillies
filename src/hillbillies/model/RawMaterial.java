package hillbillies.model;

import java.math.BigInteger;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.helper.Helper;

/**
 * @invar The position of each raw material must be a valid position for any raw material. | isValidPosition(getPosition())
 * @invar Each raw material can have its weight as weight. | canHaveAsWeight(this.getWeight())
 * @invar The world of each raw material must be a valid world for any raw material. | isValidWorld(getWorld())*
 */
public abstract class RawMaterial {

	/**
	 * Initialize this new raw material with given weight.
	 * 
	 * @param weight
	 *            The weight for this new raw material.
	 * @post The weight of this new raw material is equal to the given weight. | new.getWeight() == weight
	 * @throws IllegalArgumentException
	 *             This new raw material cannot have the given weight as its weight. | ! canHaveAsWeight(this.getWeight())
	 *
	 *             Initialize this new raw material with given position.
	 *
	 * @param position
	 *            The position for this new raw material.
	 * @effect The position of this new raw material is set to the given position. | this.setPosition(position)
	 *
	 * 
	 *         Initialize this new raw material with given world.
	 *
	 * @param world
	 *            The world for this new raw material.
	 * @effect The world of this new raw material is set to the given world. | this.setWorld(world)
	 * 
	 */
	public RawMaterial(World world, double[] position) throws IllegalArgumentException {
		this.setPosition(position);
		int weight = Helper.randInt(10, 50);
		if (!canHaveAsWeight(weight))
			throw new IllegalArgumentException();
		this.weight = weight;
		this.setIsPresent(true);
		this.world = world;
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
		//TODO checken f geldige wereld
		return true;
	}

	/**
	 * Set the world of this raw material to the given world.
	 * 
	 * @param world
	 *            The new world for this raw material.
	 * @post The world of this new raw material is equal to the given world. | new.getWorld() == world
	 * @throws IllegalArgumentException
	 *             The given world is not a valid world for any raw material. | ! isValidWorld(getWorld())
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
	 * Check whether this raw material can have the given weight as its weight.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return | result ==
	 */
	@Raw
	public boolean canHaveAsWeight(int weight) {
		// TODO checken
		return false;
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
	 * Check whether the given position is a valid position for any raw material.
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
	 * @post The position of this new raw material is equal to the given position. | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any raw material. | ! isValidPosition(getPosition())
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
	 * @post The terminated status of this new raw material is equal to the given terminated status. | new.getIsTerminated() == terminated
	 * @throws IllegalArgumentException
	 *             The given presence is not a valid presence for any raw material. | ! isValidIsPresent(getIsPresent())
	 */
	@Raw
	public void setIsTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	/**
	 * Variable registering the terminated status of this raw material.
	 */
	private boolean terminated;

	public void advanceTime() {

		// TODO fall
	}

}
