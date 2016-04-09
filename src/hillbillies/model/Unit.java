package hillbillies.model;

import java.util.Arrays;
import java.util.Random;
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

	// TODO documentatie
	public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) {

		if (strength < 25 || strength > 100)
			strength = 25 + Helper.randInt(0, 75);
		setStrength(strength);

		if (agility < 25 || agility > 100)
			agility = 25 + Helper.randInt(0, 75);
		setAgility(agility);

		if (weight < 25 || weight > 100 || weight < (strength + agility) / 2)
			weight = ((strength + agility) / 2) + Helper.randInt(0, 100 - ((strength + agility) / 2));
		setWeight(weight);

		if (toughness < 25 || toughness > 100)
			toughness = 25 + Helper.randInt(0, 75);
		setToughness(toughness);
		this.setName(name);
		this.setPosition(Helper.getCenterOfPosition(initialPosition));
		this.setHitpoints(200 * weight * toughness / 10000);
		this.setStaminapoints(200 * weight * toughness / 10000);
		if (enableDefaultBehavior == true) {
			startDefaultBehavior();
		}
	}

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

		// TODO check if passable

		Double lowerlimit = 0d;
		Double upperlimit = 50d;// TODO aan grenzen vn wereld aanpassen

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
		// System.out.println("set pos:" + position[0]+" "+position[1]+"
		// "+position[2]);
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		else {
			// for (int i = 0; i < position.length; ++i) {
			// position[i] += .5;
			// }
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
			strength = 25 + Helper.randInt(0, 75);
		setStrength(strength);

		if (agility < 25 || agility > 100)
			agility = 25 + Helper.randInt(0, 75);
		setAgility(agility);

		if (weight < 25 || weight > 100 || weight < (strength + agility) / 2)
			weight = ((strength + agility) / 2) + Helper.randInt(0, 100 - ((strength + agility) / 2));
		setWeight(weight);

		if (toughness < 25 || toughness > 100)
			toughness = 25 + Helper.randInt(0, 75);
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
	 * @return | result == hitpoints <= (200 * weight * toughness / 10000)
	 */
	public static boolean isValidHitpoints(int hitpoints, int weight, int toughness) {
		if (hitpoints <= (200 * weight * toughness / 10000))
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
		// System.out.println(hitpoints);
		assert isValidHitpoints(hitpoints, this.getWeight(), this.getToughness());
		if (hitpoints <= 0) {
			terminate(); // weet niet of ge dit in een setter moogt bijzetten
			// TODO documentatie voor if-statement toevoegen
		} else {
			this.hitpoints = hitpoints;
		}
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
	 * @return | result == true if staminapoints <= (200 * weight * toughness /
	 *         10000) && staminapoints >= 0)
	 */
	public static boolean isValidStaminapoints(int staminapoints, int weight, int toughness) {
		if (staminapoints <= (200 * weight * toughness / 10000) && staminapoints >= 0)
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

		if (!getWorld().hasImpassableNeighbour(this.getPosition()[0], this.getPosition()[1], this.getPosition()[2])
				&& !isFalling) {
			this.setIsFalling(true);
			moveToAdjecent(0, 0, -1);
		} else {
			if (this.getIsResting()) {
				doRest(dt);
				timenotresting = 0;
			} else {
				timenotresting += dt;
				if (timenotresting >= 180 && !this.getIsFalling()) {
					rest();
				}
				if (defaultBehaviorEnabled && !this.getIsAttacking() && !this.getIsMoving() && !this.getIsResting()
						&& !this.getIsWorking()) {
					startDefaultBehavior();
				}
				if (this.getIsAttacking()) {
					doAttack(dt, this.defender);
				}
				if (this.getIsWorking()) {
					doWork(dt);
				}
				if (this.getIsMoving()) {
					if (this.getIsSprinting()) {
						int newStaminaPoints = (int) (this.getStaminapoints() - 0.1 * dt);
						if (newStaminaPoints >= 0)
							this.setStaminapoints(newStaminaPoints);
						else
							this.setIsSprinting(false);
					}
					this.updatePosition(dt);
					if (this.moveToAdjecentTargetReached()) {
						this.setIsMoving(false);
						if (this.getIsFalling()) {
							this.setHitpoints(this.getHitpoints() - 10);
							this.setIsFalling(false);
						} else {this.setExperience(this.getExperience() + 1);
						if (isMovingTo) {
							if (this.moveToTargetReached()) {
								isMovingTo = false;// TODO setter maken
								// System.out.println("---------------- moveto
								// beeindigd ----------------------");
							} else {
								this.moveToAdjecent(getMoveToDirectionX(), getMoveToDirectionY(),
										getMoveToDirectionZ());
							}

						}
					}
				}

			}
		}

	}

	public void updatePosition(double dt) {

		velocity = getVelocity();

		this.setOrientation((float) (Math.atan2(velocity[1], velocity[0])));

		double[] newPosition = this.getPosition();
		newPosition[0] += velocity[0] * dt;
		newPosition[1] += velocity[1] * dt;
		newPosition[2] += velocity[2] * dt;
		this.setPosition(newPosition);

	}

	private int[] adjecentDelta = new int[3];

	public double getCurrentSpeed() {

		double currentSpeed;
		double baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100);
		// TODO weight vn bouldres en logs inrekening brengen
		if (this.getIsFalling())
			return 3;
		if (this.getIsSprinting())
			baseSpeed *= 2;

		if (adjecentDelta[2] == 1) {
			currentSpeed = baseSpeed * 0.5;
		} else if (adjecentDelta[2] == -1) {
			currentSpeed = baseSpeed * 1.2;
		} else {
			currentSpeed = baseSpeed;
		}
		return currentSpeed;

	}

	public boolean isMovingTo;

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
			// this.setIsMoving(true);
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
		setRestTime((40 / (float) this.getToughness()));
		setIsResting(true);

	}

	private void doRest(double dt) {
		// System.out.println("dorest");
		float time = getRestTime() - (float) dt;
		// System.out.println("time" + time);
		if (time <= 0) {
			if (hitpoints < getMaxPoints()) {
				hitpoints += 1;
				if (hitpoints < getMaxPoints()) {
					setRestTime(40 / (float) this.getToughness());
				} else {
					setRestTime(20 / (float) this.getToughness());
				}
			} else if (staminapoints < getMaxPoints()) {
				staminapoints += 1;
				setRestTime(20 / (float) this.getToughness());
			} else {
				this.setIsResting(false);
			}
		} else {
			setRestTime(time);
		}
	}

	/**
	 * Return the isFalling of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsFalling() {
		return this.isFalling;
	}

	/**
	 * Check whether the given isFalling is a valid isFalling for any unit.
	 * 
	 * @param isFalling
	 *            The isFalling to check.
	 * @return | result ==
	 */
	public static boolean isValidIsFalling(boolean isFalling) {
		if (isFalling || isFalling == false)
			return true;
		return false;
	}

	/**
	 * Set the isFalling of this unit to the given isFalling.
	 * 
	 * @param isFalling
	 *            The new isFalling for this unit.
	 * @post The isFalling of this new unit is equal to the given isFalling. |
	 *       new.getIsFalling() == isFalling
	 * @throws IllegalArgumentException
	 *             The given isFalling is not a valid isFalling for any unit. |
	 *             ! isValidIsFalling(getIsFalling())
	 */
	@Raw
	public void setIsFalling(boolean isFalling) throws IllegalArgumentException {
		if (!isValidIsFalling(isFalling))
			throw new IllegalArgumentException();
		this.isFalling = isFalling;
		if (isFalling) {
			this.setIsWorking(false);
			this.setIsMoving(true);
			this.setIsAttacking(false);
			this.setIsSprinting(false);
			this.setIsResting(false);
		}
	}

	/**
	 * Variable registering the isFalling of this unit.
	 */
	private boolean isFalling;

	public int getMaxPoints() {
		return 200 * weight * toughness / 10000;
	}

	public double[] velocity = new double[3];
	// keeps the position from where the last movetoadjecent started.
	public static double[] adjecentStart = new double[3];// TODO moet deze
															// statiic zijn?
	// keeps the postion of the targeted position from the last movetoadjecent.
	public double[] adjecentTarget = new double[3];
	// keeps the postion of the targeted position from the last moveto.
	public double[] target = new double[3];

	public void moveToAdjecent(int dx, int dy, int dz) throws IllegalArgumentException {

		// System.out.println("start movetoadj");

		adjecentStart = this.getPosition().clone();
		// System.out.println("adjecentStart:" + adjecentStart[0] + " " +
		// adjecentStart[1] + " " + adjecentStart[2]);

		double[] newPosition = new double[3];// TODO functie gebruiken
		for (int k = 0; k < adjecentStart.length; k++) {
			newPosition[k] = Math.floor(adjecentStart[k]) + 0.5d;
		}

		newPosition[0] += dx;
		newPosition[1] += dy;
		newPosition[2] += dz;

		if (!isValidTarget(newPosition))
			throw new IllegalArgumentException();
		else {

			adjecentTarget = newPosition;

			// System.out.println("new AdjTarget: " + adjecentTarget[0] + " " +
			// adjecentTarget[1] + " " + adjecentTarget[2]);

			this.setIsMoving(true);

			adjecentDelta[0] = dx;
			adjecentDelta[1] = dy;
			adjecentDelta[2] = dz;

			distance = getDistance(dx, dy, dz);// in principe overbodig mr beter
												// in variabel -> minder
												// rekenwerk...
			// System.out.println("velocity=" + velocity[0] + " " + velocity[1]
			// + " dist: " + distance);

		}

	}

	private boolean isValidTarget(double[] target) {// TODO lijkt hard op
													// isvalidPods

		Double lowerlimit = 0.5d;// variabelen in class declaren?
		Double upperlimit = 49.5d;
		boolean differentPos = false;

		if (target.length == 3) {

			for (int k = 0; k < target.length; k++) {
				if (target[k] > upperlimit || target[k] < lowerlimit)
					return false;
				if (target[k] != adjecentStart[k]) {
					differentPos = true;
				}
			}
		}
		if (differentPos)
			return true;

		return false;

	}

	public boolean moveToAdjecentTargetReached() throws IllegalArgumentException {

		if (getDisctanceBetweenPositions(adjecentStart, adjecentTarget) <= getDisctanceBetweenPositions(adjecentStart,
				this.getPosition())) {

			// System.out.println("moveToAdjecentTargetReached ");
			this.setPosition(adjecentTarget);
			return true;

		}

		return false;

	}

	public void moveTo(int[] targetPosition) throws IllegalArgumentException { // FIXME
																				// 2
																				// keer
																				// moveto
																				// achter
																				// elkaar
																				// fout

		// System.out.println("---------------- moveto begonnen
		// ----------------------");

		isMovingTo = true;// TODO getter en setter

		if (!isValidTarget(Helper.getCenterOfPosition(targetPosition))) {
			throw new IllegalArgumentException();
		} else {

			target[0] = targetPosition[0] + 0.5d;// TODO forlus
			target[1] = targetPosition[1] + 0.5d;
			target[2] = targetPosition[2] + 0.5d;
			// System.out.println("new target: " + target[0] + " " + target[1] +
			// " " + target[2]);
			moveToAdjecent(getMoveToDirectionX(), getMoveToDirectionY(), getMoveToDirectionZ());

		}

	}

	public int getMoveToDirectionX() {

		return getMoveToDirection((int) this.getPosition()[0], (int) target[0]);
	}

	public int getMoveToDirectionY() {

		return getMoveToDirection((int) this.getPosition()[1], (int) target[1]);
	}

	public int getMoveToDirectionZ() {

		return getMoveToDirection((int) this.getPosition()[2], (int) target[2]);
	}

	public int getMoveToDirection(int currentPosition, int targetPosition) {

		int direction;

		if (currentPosition == targetPosition) {
			direction = 0;
		} else if (currentPosition < targetPosition) {
			direction = 1;
		} else {
			direction = -1;
		}

		return direction;
	}

	public boolean moveToTargetReached() throws IllegalArgumentException {

		if (getDisctanceBetweenPositions(this.getPosition(), target) < 1) {
			return true;
		}

		return false;

	}

	public void work() {
		setWorkTime(500 / (float) this.getStrength());
		this.setIsWorking(true);

	}

	private void doWork(double dt) { // TODO functies schrijven
		float time = getWorkTime() - (float) dt;
		if (time <= 0) {
			int experience = 10;
			if (this.isCarryingBoulder() || this.isCarryingLog()) {
				this.drop();
			} else if (this.getWorld().getCubeType(x, y, z) == 0 && itemsAvailable()) { // TODO
																						// targetcoord
				this.improve();
			} else if (boulderAvailable()) {
				this.pickUpBoulder();
			} else if (logAvailable()) {
				this.pickUpLog();
			} else if (this.getWorld().getCubeType(x, y, z) == 2) { // TODO
																	// targetcoord
				this.getWorld().collapseCube(x, y, z, true);
			} else if (this.getWorld().getCubeType(x, y, z) == 1) { // TODO
																	// targetcoord
				this.getWorld().collapseCube(x, y, z, true);
			} else {
				experience = 0;
			}
			this.setExperience(this.getExperience() + experience);
			this.setIsWorking(false);
		} else {
			setWorkTime(time);
		}
	}

	/**
	 * Return the work time of this unit.
	 */
	@Basic
	@Raw
	public float getWorkTime() {
		return this.workTime;
	}

	/**
	 * Check whether the given work time is a valid work time for any unit.
	 * 
	 * @param work
	 *            time The work time to check.
	 * @return | result ==
	 */
	public static boolean isValidWorkTime(float workTime, int strength) {
		if (workTime <= 500 / (float) strength) {
			return true;
		}
		return false;
	}

	/**
	 * Set the work time of this unit to the given work time.
	 * 
	 * @param workTime
	 *            The new work time for this unit.
	 * @post The work time of this new unit is equal to the given work time. |
	 *       new.getWorktime() == workTime
	 * @throws IllegalArgumentException
	 *             The given work time is not a valid work time for any unit. |
	 *             ! isValidWorktime(getWorktime())
	 */
	@Raw
	public void setWorkTime(float workTime) throws IllegalArgumentException {
		if (!isValidWorkTime(workTime, this.getStrength()))
			throw new IllegalArgumentException();
		this.workTime = workTime;
	}

	/**
	 * Variable registering the work time of this unit.
	 */
	private float workTime;

	public double getDistance(int dx, int dy, int dz) {// TODO nr helper

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));

	}

	public double getDistance(double dx, double dy, double dz) {// TODO nr
																// helper

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));

	}

	private double distance;

	public double getDisctanceBetweenPositions(double[] position1, double[] position2) {// TODO
																						// nr
																						// helper

		return Math.sqrt(Math.pow(position2[0] - position1[0], 2) + Math.pow(position2[1] - position1[1], 2)
				+ Math.pow(position2[2] - position1[2], 2));

	}

	public double[] getVelocity() {

		double[] velocity = new double[3];

		velocity[0] = this.getCurrentSpeed() * adjecentDelta[0] / distance;
		velocity[1] = this.getCurrentSpeed() * adjecentDelta[1] / distance;
		velocity[2] = this.getCurrentSpeed() * adjecentDelta[2] / distance;

		return velocity;

	}

	public void startDefaultBehavior() {
		defaultBehaviorEnabled = true;
		int rand = Helper.randInt(0, 3);
		if (rand == 0) {

			this.moveTo(Helper.getRandomPosition());
			this.setIsSprinting(Helper.randBoolean());

		} else if (rand == 1) {
			this.work();

		} else {
			this.rest();
		}

	}

	public void stopDefaultBehavior() {
		defaultBehaviorEnabled = false;
	}

	public void setDefaultBehavior(boolean defaultBehavior) {
		this.defaultBehaviorEnabled = defaultBehavior;
	}

	public boolean defaultBehaviorEnabled = false;

	public void attack(Unit attacker, Unit defender) {

		double[] attackerpos = attacker.getPosition();
		double[] defenderpos = defender.getPosition();
		// int[] defendercubepos = new int[3];
		// for (int i = 0; i < 3; ++i)
		// defendercubepos[i] = (int) defenderpos[i];
		double dx = attackerpos[0] - defenderpos[0];
		double dy = attackerpos[1] - defenderpos[1];
		double dz = attackerpos[2] - defenderpos[2];
		if (getDistance(dx, dy, dz) <= Math.sqrt(2)) {
			setAttackOrientation(attackerpos, defenderpos, defender);
			this.defender = defender;
			this.setIsAttacking(true);

			// } else {
			// this.moveTo(defendercubepos);
			// attack(attacker, defender);
		}

	}

	private Unit defender;

	private void doAttack(double dt, Unit defender) {
		float time = getAttackTime() - (float) dt;
		if (time <= 0) {
			defender.defend(this);
			setAttackTime(1);
			setIsAttacking(false);
		} else {
			setAttackTime(time);
		}
	}

	private void setAttackOrientation(double[] attackerpos, double[] defenderpos, Unit defender) {

		this.setOrientation((float) Math.atan2((defenderpos[1] - attackerpos[1]), (defenderpos[0] - attackerpos[0])));
		defender.setOrientation(
				(float) Math.atan2((attackerpos[1] - defenderpos[1]), (attackerpos[0] - defenderpos[0])));
	}

	public void defend(Unit attacker) {
		System.out.println("defend");
		Random random = new Random();
		double dodgeProb = random.nextDouble();
		double blockProb = random.nextDouble();
		if (dodgeProb <= getDodgeProb(attacker, this)) {
			dodge();
			this.setExperience(this.getExperience() + 20);
		} else if (blockProb > getBlockProb(attacker, this)) {
			takeDamage(attacker);
			attacker.setExperience(attacker.getExperience() + 20);
		} else {
			this.setExperience(this.getExperience() + 20);
		}
		// attack(this, attacker);

	}

	public void dodge() {// TODO dit kan eventueel nog aangepast worden als
							// blijkt dat ge naar achter moet gaan ipv random
							// beschikbare plek
		System.out.println("dodge");
		Random random = new Random();
		int xdirection = random.nextInt(3) - 1;
		int ydirection = random.nextInt(3) - 1;
		double[] oldPosition = this.getPosition();
		moveToAdjecent(xdirection, ydirection, 0);
		if (oldPosition == this.getPosition()) {
			dodge();
		}
	}

	public void takeDamage(Unit attacker) {
		System.out.println("take damage");
		this.setHitpoints(this.getHitpoints() - (int) attacker.getStrength() / 10);
		System.out.println("hitpoints: " + this.getHitpoints());
	}

	private double getDodgeProb(Unit attacker, Unit defender) {
		return 0.20 * defender.getAgility() / attacker.getAgility();
	}

	private double getBlockProb(Unit attacker, Unit defender) {
		return 0.25 * (defender.getStrength() + defender.getAgility())
				/ (attacker.getStrength() + attacker.getAgility());
	}

	/**
	 * Return the attack time of this unit.
	 */
	@Basic
	@Raw
	public float getAttackTime() {
		return this.attackTime;
	}

	/**
	 * Check whether the given attack time is a valid attack time for any unit.
	 * 
	 * @param attack
	 *            time The attack time to check.
	 * @return | result == true if attackTime <= 1
	 */
	public static boolean isValidAttackTime(float attackTime) {
		if (attackTime <= 1f) {
			return true;
		}
		return false;
	}

	/**
	 * Set the attack time of this unit to the given attack time.
	 * 
	 * @param attackTime
	 *            The new attack time for this unit.
	 * @post The attack time of this new unit is equal to the given attack time.
	 *       | new.getAttackTime() == attackTime
	 * @throws ExceptionName_Java
	 *             The given attack time is not a valid attack time for any
	 *             unit. | ! isValidAttackTime(getAttackTime())
	 */
	@Raw
	public void setAttackTime(float attackTime) throws IllegalArgumentException {
		if (!isValidAttackTime(attackTime))
			throw new IllegalArgumentException();
		this.attackTime = attackTime;
	}

	/**
	 * Variable registering the attack time of this unit.
	 */
	private float attackTime = 1f;

	/**
	 * Return the rest time of this unit.
	 */
	@Basic
	@Raw
	public float getRestTime() {
		return this.restTime;
	}

	/**
	 * Check whether the given rest time is a valid rest time for any unit.
	 * 
	 * @param restTime
	 *            The rest time to check.
	 * @param tougness
	 *            The toughness of the unit.
	 * @return | result == true if restTime <= 40 / toughness
	 */
	public static boolean isValidRestTime(float restTime, int toughness) {
		if (restTime <= (40 / (float) toughness)) {
			System.out.println("valid");
			return true;
		}
		return false;
	}

	/**
	 * Set the rest time of this unit to the given rest time.
	 * 
	 * @param restTime
	 *            The new rest time for this unit.
	 * @post The rest time of this new unit is equal to the given rest time. |
	 *       new.getRestTime() == restTime
	 * @throws IllegalArgumentException
	 *             The given rest time is not a valid rest time for any unit. |
	 *             ! isValidRestTime(getRestTime(),getToughness)
	 */
	@Raw
	public void setRestTime(float restTime) throws IllegalArgumentException {
		if (!isValidRestTime(restTime, this.getToughness()))
			throw new IllegalArgumentException();
		this.restTime = restTime;
		System.out.println(restTime + "rt");
	}

	/**
	 * Variable registering the rest time of this unit.
	 */
	private float restTime;

	/**
	 * Return the experience of this unit.
	 */
	@Basic
	@Raw
	public int getExperience() {
		return this.experience;
	}

	/**
	 * Check whether the given experience is a valid experience for any unit.
	 * 
	 * @param experience
	 *            The experience to check.
	 * @return | result == true if given experience is positive.
	 */
	public static boolean isValidExperience(int experience) {
		if (experience >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Set the experience of this unit to the given experience.
	 * 
	 * @param experience
	 *            The new experience for this unit.
	 * @post The experience of this new unit is equal to the given experience. |
	 *       new.getExperience() == experience
	 * @throws IllegalArgumentException
	 *             The given experience is not a valid experience for any unit.
	 *             | ! isValidExperience(getExperience())
	 */
	@Raw
	public void setExperience(int experience) throws IllegalArgumentException {
		if (!isValidExperience(experience))
			throw new IllegalArgumentException();
		this.experience = experience;
	}

	/**
	 * Variable registering the experience of this unit.
	 */
	private int experience;

	/**
	 * Terminate this unit.
	 *
	 * @post This unit is terminated. | new.isTerminated()
	 * @post ... | ...
	 */
	public void terminate() {
		System.out.println("terminate");
		// TODO drop carrying items
		this.isTerminated = true;
	}

	/**
	 * Return a boolean indicating whether or not this unit is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Variable registering whether this person is terminated.
	 */
	private boolean isTerminated = false;

	public World getWorld() {
		try {
			return this.getFaction().getWorld();
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Return the faction of this unit.
	 */
	@Basic
	@Raw
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Check whether the given faction is a valid faction for any unit.
	 * 
	 * @param faction
	 *            The faction to check.
	 * @return | result ==
	 */
	public static boolean isValidFaction(Faction faction) {
		// TODO fixen
		return true;
	}

	/**
	 * Set the faction of this unit to the given faction.
	 * 
	 * @param faction
	 *            The new faction for this unit.
	 * @post The faction of this new unit is equal to the given faction. |
	 *       new.getFaction() == faction
	 * @throws IllegalArgumentException
	 *             The given faction is not a valid faction for any unit. | !
	 *             isValidFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) throws IllegalArgumentException {
		if (!isValidFaction(faction))
			throw new IllegalArgumentException();
		this.faction = faction;
	}

	/**
	 * Variable registering the faction of this unit.
	 */
	private Faction faction;

}