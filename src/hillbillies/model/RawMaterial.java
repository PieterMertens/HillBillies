package hillbillies.model;

import java.math.BigInteger;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.helper.Helper;

/**
 * @invar The position of each raw material must be a valid position for any raw material. | isValidPosition(getPosition())
 * @invar Each raw material can have its weight as weight. | canHaveAsWeight(this.getWeight())
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
	 *Initialize this new raw material with given position.
	 *
	 * @param position
	 *            The position for this new raw material.
	 * @effect The position of this new raw material is set to the given position. | this.setPosition(position)
	 
	 *
	 */
	public RawMaterial(double[] position) throws IllegalArgumentException {
		this.setPosition(position);
		int weight = Helper.randInt(10, 50)
		if (!canHaveAsWeight(weight))
			throw new IllegalArgumentException();
		this.weight = weight;
		this.setIsPresent(present);
	}

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
		//TODO kijken f geldige pos is
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
	 * Return the presence of this raw material.
	 */
	@Basic
	@Raw
	public boolean getIsPresent() {
		return this.present;
	}

	/**
	 * Set the presence of this raw material to the given presence.
	 * 
	 * @param present
	 *            The new presence for this raw material.
	 * @post The presence of this new raw material is equal to the given presence. | new.getIsPresent() == present
	 * @throws IllegalArgumentException
	 *             The given presence is not a valid presence for any raw material. | ! isValidIsPresent(getIsPresent())
	 */
	@Raw
	public void setIsPresent(boolean present) {
		this.present = present;
	}

	/**
	 * Variable registering the presence of this raw material.
	 */
	private boolean present;

	public void advanceTime() {

		// TODO fall
	}

}
