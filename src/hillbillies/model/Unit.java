package hillbillies.model;

import java.util.Arrays;
import java.util.regex.Pattern;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.helper.Helper;
import ogp.framework.util.Util;

/**
 * 
 * @invar The position of each unit must be a valid position for any unit. |
 *        isValidPosition(getPosition())
 * @invar The hitpoints of each unit must be a valid hitpoints for any unit. |
 *        isValidHitpoints(getHitpoints())
 * @invar The name of each unit must be a valid name for any unit. |
 *        isValidName(getName())
 * @invar The weight of each unit must be a valid weight for any unit. |
 *        isValidWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit. |
 *        isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any unit. |
 *        isValidAgility(getAgility())
 * @invar The toughness of each unit must be a valid toughness for any unit. |
 *        isValidToughness(getToughness())
 * @invar The staminapoints of each unit must be a valid staminapoints for any
 *        unit. | isValidStaminapoints(getStaminapoints())
 * @invar The orientation of each unit must be a valid orientation for any unit.
 *        | isValidOrientation(getOrientation())
 * @invar The isMoving of each unit must be a valid isMoving for any unit. |
 *        isValidIsMoving(getIsMoving())
 * 
 * @author Pieter and Matthias
 *
 */
public class Unit {
	/**
	 * Initialize this new unit with given position.
	 *
	 * @param position
	 *            The position for this new unit.
	 * @effect The position of this new unit is set to the given position. |
	 *         this.setPosition(position)
	 */
	public Unit(double[] position) throws IllegalArgumentException {
		this.setPosition(position);
	}

	/**
	 * Return the position of this unit.
	 */
	@Basic
	@Raw
	public double[] getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given position is a valid position for any unit.
	 * 
	 * @param position
	 *            The position to check.
	 * @return | result == true if the 3 coordinates are between the given
	 *         limits.
	 */
	public static boolean isValidPosition(double[] position) {

		Double lowerlimit = 0d;
		Double upperlimit = 49d;

		if (position.length == 3) {

			for (int k = 0; k < position.length; k++) {
				if (position[k] > upperlimit || position[k] < lowerlimit)
					return false;
			}
			return true;
		}

		return false;
	}

	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param position
	 *            The new position for this unit.
	 * @post The position of this new unit is equal to the given position. |
	 *       new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any unit. |
	 *             !isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		else {
			for (int i = 0; i < position.length; ++i) {
				position[i] += .5;
			}
			this.position = position;
		}
	}

	/**
	 * Variable registering the position of this unit.
	 */
	private double[] position;

	/**
	 * Initialize this new unit with given name.
	 *
	 * @param name
	 *            The name for this new unit.
	 * @effect The name of this new unit is set to the given name. |
	 *         this.setName(name)
	 */
	public Unit(String name) throws IllegalArgumentException {
		this.setName(name);
	}

	/**
	 * Return the name of this unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;

	}

	/**
	 * Check whether the given name is a valid name for any unit.
	 * 
	 * @param name
	 *            The name to check.
	 * @return | result == true if first letter is uppercase and no symbols
	 *         except ' and " are used.
	 */
	public static boolean isValidName(String name) {

		if (Pattern.matches("[a-zA-Z.\\s\\'\\\"]*", name) && Character.isUpperCase(name.codePointAt(0))
				&& name.length() > 1)
			return true;

		return false;
	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param name
	 *            The new name for this unit.
	 * @post The name of this new unit is equal to the given name. |
	 *       new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for any unit. | !
	 *             isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Variable registering the name of this unit.
	 */
	private String name;

	/**
	 * Initialize this new unit with given weight, strength, agility and
	 * toughness.
	 * 
	 * @param weight
	 *            The weight for this new unit.
	 * @param strength
	 *            The strength for this new unit.
	 * @param agility
	 *            The agility for this new unit.
	 * @param toughness
	 *            The toughness for this new unit.
	 * @post If the given weight, strength, agility and toughness are valid
	 *       values for any unit, the attributes of this new unit are equal to
	 *       the given attributes. Otherwise, the attribute of this new unit is
	 *       equal to its default value. | if (isValidWeight(weight)) | then
	 *       new.getWeight() == weight | else new.getWeight() == defaultWeight |
	 *       if (isValidStrength(strength)) | then new.getStrength() == strength
	 *       | else new.getStrength() == defaultStrength | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility | else
	 *       new.getAgility() == defaultAgility | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness | else new.getToughness() == defaultToughness
	 */
	public Unit(int weight, int strength, int agility, int toughness) {

		if (strength < 25 || strength > 100)
			strength = 25 + (int) (Math.random() * (75));
		setStrength(strength);

		if (agility < 25 || agility > 100)
			agility = 25 + (int) (Math.random() * (75));
		setAgility(agility);

		if (weight < 25 || weight > 100 || weight < (strength + agility) / 2)
			weight = (strength + agility) / 2 + (int) (Math.random() * (100 - ((strength + agility) / 2)));
		setWeight(weight);

		if (toughness < 25 || toughness > 100)
			toughness = 25 + (int) (Math.random() * (75));
		setToughness(toughness);
	}

	/**
	 * Return the weight of this unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether the given weight is a valid weight for any unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return | result == true if weight >= 1 and weight <= 200 && weight >=
	 *         (strength + agility) / 2
	 */
	public static boolean isValidWeight(int weight, int strength, int agility) {
		if (weight >= 1 && weight <= 200 && weight >= (strength + agility) / 2)
			return true;
		return false;
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post If the given weight is a valid weight for any unit, the weight of
	 *       this new unit is equal to the given weight. | if
	 *       (isValidWeight(weight)) | then new.getWeight() == weight
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight, this.getStrength(), this.getAgility()))
			this.weight = weight;
	}

	/**
	 * Variable registering the weight of this unit.
	 */
	private int weight;

	/**
	 * Return the strength of this unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Check whether the given strength is a valid strength for any unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return | result == true if strength >= 1 and strength <= 200
	 */
	public static boolean isValidStrength(int strength) {
		if (strength >= 1 && strength <= 200)
			return true;
		return false;
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength;

	/**
	 * Return the agility of this unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given agility is a valid agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return | result == true if agility >= 1 and agility <= 200
	 */
	public static boolean isValidAgility(int agility) {
		if (agility >= 1 && agility <= 200)
			return true;
		return false;
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post If the given agility is a valid agility for any unit, the agility
	 *       of this new unit is equal to the given agility. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
	}

	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;

	/**
	 * Return the toughness of this unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given toughness is a valid toughness for any unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return | result == if toughness >= 1 and toughness <= 200
	 */
	public static boolean isValidToughness(int toughness) {
		if (toughness >= 1 && toughness <= 200)
			return true;
		return false;
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post If the given toughness is a valid toughness for any unit, the
	 *       toughness of this new unit is equal to the given toughness. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
	}

	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;

	/**
	 * Initialize this new unit with given hitpoints.
	 * 
	 * @param hitpoints
	 *            The hitpoints for this new unit.
	 * @param staminapoints
	 *            The staminapoints for this new unit.
	 * @pre The given hitpoints must be a valid value for any unit. |
	 *      isValidHitpoints(hitpoints)
	 * @pre The given staminapoints must be a valid value for any unit. |
	 *      isValidStaminapoints(staminapoints)
	 * @post The hitpoints of this new unit are equal to the given hitpoints. |
	 *       new.getHitpoints() == hitpoints
	 * @post The staminapoints of this new unit are equal to the given
	 *       staminapoints. | new.getStaminapoints() == staminapoints
	 */
	public Unit(int hitpoints, int staminapoints) {
		this.setHitpoints(hitpoints);
		this.setStaminapoints(staminapoints);
	}

	/**
	 * Return the hitpoints of this unit.
	 */
	@Basic
	@Raw
	public int getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * Check whether the given hitpoints is a valid hitpoints for any unit.
	 * 
	 * @param hitpoints
	 *            The hitpoints to check.
	 * @return | result == hitpoints <= (200 * (weight / 100) * (toughness /
	 *         100))
	 */
	public static boolean isValidHitpoints(int hitpoints, int weight, int toughness) {
		if (hitpoints <= (200 * (weight / 100) * (toughness / 100)))
			return true;
		return false;
	}

	/**
	 * Set the hitpoints of this unit to the given hitpoints.
	 * 
	 * @param hitpoints
	 *            The new hitpoints for this unit.
	 * @pre The given hitpoints must be a valid hitpoints for any unit. |
	 *      isValidHitpoints(hitpoints)
	 * @post The hitpoints of this unit is equal to the given hitpoints. |
	 *       new.getHitpoints() == hitpoints
	 */
	@Raw // TODO private
	public void setHitpoints(int hitpoints) {
		assert isValidHitpoints(hitpoints, this.getWeight(), this.getToughness());
		this.hitpoints = hitpoints;
	}

	/**
	 * Variable registering the hitpoints of this unit.
	 */
	private int hitpoints;

	/**
	 * Return the staminapoints of this unit.
	 */
	@Basic
	@Raw
	public int getStaminapoints() {
		return this.staminapoints;
	}

	/**
	 * Check whether the given staminapoints is a valid staminapoints for any
	 * unit.
	 * 
	 * @param staminapoints
	 *            The staminapoints to check.
	 * @return | result == true if staminapoints <= (200 * (weight / 100) *
	 *         (toughness / 100)) && staminapoints >= 0)
	 */
	public static boolean isValidStaminapoints(int staminapoints, int weight, int toughness) {
		if (staminapoints <= (200 * (weight / 100) * (toughness / 100)) && staminapoints >= 0)
			return true;
		return false;
	}

	/**
	 * Set the staminapoints of this unit to the given staminapoints.
	 * 
	 * @param staminapoints
	 *            The new staminapoints for this unit.
	 * @pre The given staminapoints must be a valid staminapoints for any unit.
	 *      | isValidStaminapoints(staminapoints)
	 * @post The staminapoints of this unit is equal to the given staminapoints.
	 *       | new.getStaminapoints() == staminapoints
	 */
	@Raw
	public void setStaminapoints(int staminapoints) {
		assert isValidStaminapoints(staminapoints, this.getWeight(), this.getToughness());
		this.staminapoints = staminapoints;
	}

	/**
	 * Variable registering the staminapoints of this unit.
	 */
	private int staminapoints;

	/**
	 * Initialize this new unit with given orientation.
	 * 
	 * @param orientation
	 *            The orientation for this new unit.
	 * @post If the given orientation is a valid orientation for any unit, the
	 *       orientation of this new unit is equal to the given orientation.
	 *       Otherwise, the orientation of this new unit is equal to PI/2. | if
	 *       (isValidOrientation(orientation)) | then new.getOrientation() ==
	 *       orientation | else new.getOrientation() == PI/2
	 */
	public Unit(float orientation) {
		if (!isValidOrientation(orientation))
			orientation = (float) (Math.PI / 2);
		setOrientation(orientation);
	}

	/**
	 * Return the orientation of this unit.
	 */
	@Basic
	@Raw
	public float getOrientation() {
		return this.orientation;
	}

	/**
	 * Check whether the given orientation is a valid orientation for any unit.
	 * 
	 * @param orientation
	 *            The orientation to check.
	 * @return If the given orientation is between 0 and 2*PI, the method
	 *         returns true. | result == true if (orientation >= 0 &&
	 *         orientation <= Math.PI * 2)
	 */
	public static boolean isValidOrientation(float orientation) {
		if (orientation >= (float) -Math.PI && orientation <= (float) Math.PI)
			return true;
		return false;
	}

	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param orientation
	 *            The new orientation for this unit.
	 * @post If the given orientation is a valid orientation for any unit, the
	 *       orientation of this new unit is equal to the given orientation. |
	 *       if (isValidOrientation(orientation)) | then new.getOrientation() ==
	 *       orientation
	 */
	@Raw
	public void setOrientation(float orientation) {
		if (isValidOrientation(orientation))
			this.orientation = orientation;
		else
			this.orientation = (float) (Math.PI / 2);
	}

	/**
	 * Variable registering the orientation of this unit.
	 */
	private float orientation = (float) Math.PI / 2;
	private double timenotresting;

	public void advanceTime(double dt) throws IllegalArgumentException {

		if (this.getIsResting()) {
			timenotresting = 0;
		} else {
			timenotresting += dt;
			if (timenotresting >= 180) {
				rest();
			}
			if (this.getIsMoving()) {
				if (this.getIsSprinting()) {
					int newStaminaPoints = (int) (this.getStaminapoints() - 0.1 * dt);
					if (newStaminaPoints >= 0)
						this.setStaminapoints(newStaminaPoints);
					else
						this.setIsSprinting(false);
				}
			}
		}

	}

	private int zDirection;

	public double getCurrentSpeed() {

		double currentSpeed;
		double baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100);
		if (this.getIsSprinting())
			baseSpeed *= 2;

		if (zDirection == 1) {
			currentSpeed = baseSpeed * 0.5;
		} else if (zDirection == -1) {
			currentSpeed = baseSpeed * 1.2;
		} else {
			currentSpeed = baseSpeed;
		}

		return currentSpeed;

	}

	/**
	 * TO BE ADDED TO CLASS HEADING
	 * 
	 * 
	 */

	/**
	 * Initialize this new unit with given isMoving.
	 * 
	 * @param isMoving
	 *            The isMoving for this new unit.
	 * @effect The isMoving of this new unit is set to the given isMoving. |
	 *         this.setIsMoving(isMoving)
	 */
	public Unit(boolean isMoving, boolean isSprinting, boolean isWorking, boolean isAttacking, boolean isResting)
			throws IllegalArgumentException {
		this.setIsMoving(isMoving);
		this.setIsSprinting(isSprinting);
		this.setIsWorking(isWorking);
		this.setIsAttacking(isAttacking);
		this.setIsResting(isResting);
	}

	/**
	 * Return the isMoving of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsMoving() {
		return this.isMoving;
	}

	/**
	 * Check whether the given isMoving is a valid isMoving for any unit.
	 * 
	 * @param isMoving
	 *            The isMoving to check.
	 * @return | result == true if given value is boolean
	 */
	public static boolean isValidIsMoving(boolean isMoving) {
		if (isMoving || isMoving == false)
			return true;
		return false;
	}

	/**
	 * Set the isMoving of this unit to the given isMoving.
	 * 
	 * @param isMoving
	 *            The new isMoving for this unit.
	 * @post The isMoving of this new unit is equal to the given isMoving. |
	 *       new.getIsMoving() == isMoving
	 * @throws IllegalArgumentException
	 *             The given isMoving is not a valid isMoving for any unit. | !
	 *             isValidIsMoving(getIsMoving())
	 */
	@Raw
	public void setIsMoving(boolean isMoving) throws IllegalArgumentException {
		if (!isValidIsMoving(isMoving))
			throw new IllegalArgumentException();
		this.isMoving = isMoving;
		if (isMoving) {
			this.setIsWorking(false);
			this.setIsResting(false);
			this.setIsAttacking(false);
		}
	}

	/**
	 * Variable registering the isMoving of this unit.
	 */
	private boolean isMoving;

	/**
	 * Return the isSprinting of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsSprinting() {
		return this.isSprinting;
	}

	/**
	 * Check whether the given isSprinting is a valid isSprinting for any unit.
	 * 
	 * @param isSprinting
	 *            The isSprinting to check.
	 * @return | result == true if given value is boolean
	 */
	public static boolean isValidIsSprinting(boolean isSprinting) {
		if (isSprinting || isSprinting == false)
			return true;
		return false;
	}

	/**
	 * Set the isSprinting of this unit to the given isSprinting.
	 * 
	 * @param isSprinting
	 *            The new isSprinting for this unit.
	 * @post The isSprinting of this new unit is equal to the given isSprinting.
	 *       | new.getIsSprinting() == isSprinting
	 * @post If unit was resting, working or attacking, that activity stopped.
	 * @post If he wasn't already, the unit started moving.
	 * @throws IllegalArgumentException
	 *             The given isSprinting is not a valid isSprinting for any
	 *             unit. | ! isValidIsSprinting(getIsSprinting())
	 */
	@Raw
	public void setIsSprinting(boolean isSprinting) throws IllegalArgumentException {
		if (!isValidIsSprinting(isSprinting))
			throw new IllegalArgumentException();
		this.isSprinting = isSprinting;
		if (isSprinting) {
			this.setIsResting(false);
			this.setIsWorking(false);
			this.setIsAttacking(false);
			this.setIsMoving(true);
		}
	}

	/**
	 * Variable registering the isSprinting of this unit.
	 */
	private boolean isSprinting;

	/**
	 * Return the isWorking of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsWorking() {
		return this.isWorking;
	}

	/**
	 * Check whether the given isWorking is a valid isWorking for any unit.
	 * 
	 * @param isWorking
	 *            The isWorking to check.
	 * @return | result == true if given value is boolean
	 */
	public static boolean isValidIsWorking(boolean isWorking) {
		if (isWorking || isWorking == false)
			return true;
		return false;
	}

	/**
	 * Set the isWorking of this unit to the given isWorking.
	 * 
	 * @param isWorking
	 *            The new isWorking for this unit.
	 * @post The isWorking of this new unit is equal to the given isWorking. |
	 *       new.getIsWorking() == isWorking
	 * @post If any other activities are running they are stopped.
	 * @throws IllegalArgumentException
	 *             The given isWorking is not a valid isWorking for any unit. |
	 *             ! isValidIsWorking(getIsWorking())
	 */
	@Raw
	public void setIsWorking(boolean isWorking) throws IllegalArgumentException {
		if (!isValidIsWorking(isWorking))
			throw new IllegalArgumentException();
		this.isWorking = isWorking;
		if (isWorking) {
			this.setIsResting(false);
			this.setIsMoving(false);
			this.setIsAttacking(false);
			this.setIsSprinting(false);
		}
	}

	/**
	 * Variable registering the isWorking of this unit.
	 */
	private boolean isWorking;

	/**
	 * Return the isAttacking of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsAttacking() {
		return this.isAttacking;
	}

	/**
	 * Check whether the given isAttacking is a valid isAttacking for any unit.
	 * 
	 * @param isAttacking
	 *            The isAttacking to check.
	 * @return | result == true if given value is boolean
	 */
	public static boolean isValidIsAttacking(boolean isAttacking) {
		if (isAttacking || isAttacking == false)
			return true;
		return false;
	}

	/**
	 * Set the isAttacking of this unit to the given isAttacking.
	 * 
	 * @param isAttacking
	 *            The new isAttacking for this unit.
	 * @post The isAttacking of this new unit is equal to the given isAttacking.
	 *       | new.getisAttacking() == isAttacking
	 * @post If any other activities are running they are stopped.
	 * @throws IllegalArgumentException
	 *             The given isAttacking is not a valid isAttacking for any
	 *             unit. | ! isValidIsAttacking(getisAttacking())
	 */
	@Raw
	public void setIsAttacking(boolean isAttacking) throws IllegalArgumentException {
		if (!isValidIsAttacking(isAttacking))
			throw new IllegalArgumentException();
		this.isAttacking = isAttacking;
		if (isAttacking) {
			this.setIsWorking(false);
			this.setIsMoving(false);
			this.setIsResting(false);
			this.setIsSprinting(false);
		}
	}

	/**
	 * Variable registering the isAttacking of this unit.
	 */
	private boolean isAttacking;

	/**
	 * Return the isResting of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsResting() {
		return this.isResting;
	}

	/**
	 * Check whether the given isResting is a valid isResting for any unit.
	 * 
	 * @param isResting
	 *            The isResting to check.
	 * @return | result == true if given value is boolean
	 */
	public static boolean isValidIsResting(boolean isResting) {
		if (isResting || isResting == false)
			return true;
		return false;
	}

	/**
	 * Set the isResting of this unit to the given isResting.
	 * 
	 * @param isResting
	 *            The new isResting for this unit.
	 * @post The isResting of this new unit is equal to the given isResting. |
	 *       new.getIsResting() == isResting
	 * @post If any other activities are running they are stopped.
	 * @throws IllegalArgumentException
	 *             The given isResting is not a valid isResting for any unit. |
	 *             ! isValidIsResting(getIsResting())
	 */
	@Raw
	public void setIsResting(boolean isResting) throws IllegalArgumentException {
		if (!isValidIsResting(isResting))
			throw new IllegalArgumentException();
		this.isResting = isResting;
		if (isResting) {
			this.setIsWorking(false);
			this.setIsMoving(false);
			this.setIsAttacking(false);
			this.setIsSprinting(false);
		}
	}

	/**
	 * Variable registering the isResting of this unit.
	 */
	private boolean isResting;

	public void rest() {
		setIsResting(true);
		while (staminapoints != this.getMaxPoints()) {
			advanceTime(0.2);
			if (hitpoints != getMaxPoints())
				hitpoints += this.getToughness() / 200;
			else
				staminapoints += this.getToughness() / 100;
		}
	}

	public int getMaxPoints() {
		return 200 * (this.getWeight() / 100) * (this.getToughness() / 100);
	}

	public void moveToAdjecent(int dx, int dy, int dz) throws IllegalArgumentException {

		this.setIsMoving(true);

		zDirection = dz;

		double[] velocity = getVelocity(dx, dy, dz);

		this.setOrientation((float) (Math.atan2(velocity[1], velocity[0])));

		double[] oldPosition = this.getPosition();

		double[] newPosition = new double[3];
		for (int k = 0; k < oldPosition.length; k++) {
			newPosition[k] = Math.floor(oldPosition[k]);
		}

		newPosition[0] += dx;
		newPosition[1] += dy;
		newPosition[2] += dz;

		this.setPosition(newPosition);

		this.setIsMoving(false);

	}

	public void moveTo(int[] targetPosition) throws IllegalArgumentException {

		int x;
		int y;
		int z;
		
		System.out.println("voor while");
		

		int i = 0;
		while (!Arrays.equals(Helper.doubleArrayToIntArray(this.getPosition()),targetPosition) || i>10) {

			i++;
			
			System.out.println("in while    pos: " + this.getPosition()[0] +" "+this.getPosition()[1] +" target: "+targetPosition[0]+" "+targetPosition[1]);
			
			// TODO compacter
			int x1 = Helper.doubleArrayToIntArray(this.getPosition())[0];
			int y1 = Helper.doubleArrayToIntArray(this.getPosition())[1];
			int z1 = Helper.doubleArrayToIntArray(this.getPosition())[2];
			int x2 = targetPosition[0];
			int y2 = targetPosition[1];
			int z2 = targetPosition[2];

			if (x1 == x2) {
				x = 0;
			} else if (x1 < x2) {
				x = 1;
			} else {
				x = -1;
			}
			if (y1 == y2) {
				y = 0;
			} else if (y1 < y2) {
				y = 1;
			} else {
				y = -1;
			}
			if (z1 == z2) {
				z = 0;
			} else if (z1 < z2) {
				z = 1;
			} else {
				z = -1;
			}
			moveToAdjecent(x, y, z);
		}

		this.setIsMoving(false);

	}

	public void work() {

		this.setIsWorking(true);
		double timeWorking = 0;
		while (timeWorking < 500 / this.getStrength()) {
			advanceTime(0.2);//FIXME
			timeWorking += 0.2;
		}
		this.setIsResting(true);

	}

	public double getDisctance(int dx, int dy, int dz) {

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));

	}

	public double[] getVelocity(int dx, int dy, int dz) {

		double[] velocity = new double[3];
		double distance = getDisctance(dx, dy, dz);

		velocity[0] = this.getCurrentSpeed() * dx / distance;
		velocity[1] = this.getCurrentSpeed() * dy / distance;
		velocity[2] = this.getCurrentSpeed() * dz / distance;

		return velocity;

	}

}
