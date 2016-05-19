package hillbillies.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.helper.Helper;

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
 * @invar The isMoving of each unit must be either true or false for any unit. |
 *        isValidIsMoving(getIsMoving())
 * @invar The timenotresting of each unit must be a valid timenotresting for any
 *        unit. | isValidTimeNotResting(getTimeNotResting())
 * @invar The wantToAttack of each unit must be either true or false for any
 *        unit. | isValidWantToAttack(getWantToAttack())
 * @invar The defender of each unit must be a valid defender for any unit. |
 *        isValidDefender(getDefender())
 * @invar The pickedUpBoulder of each unit must be a valid pickedUpBoulder for
 *        any unit. | isValidPickedUpBoulder(getPickedUpBoulder())
 * @invar The pickedUpLog of each unit must be a valid pickedUpLog for any unit.
 *        | isValidPickedUpLog(getPickedUpLog())
 * 
 * @author Pieter and Matthias
 *
 */
public class Unit {
	// TODO checken of interrupties werken
	/**
	 * Initialize this new unit with given name, position, weight, strength,
	 * agility and toughness and whether or not default behavior is enabled.
	 * 
	 * @param name
	 *            The name for this new unit.
	 * @param initialPosition
	 *            The position for this new unit.
	 * @param weight
	 *            The weight for this new unit.
	 * @param strength
	 *            The strength for this new unit.
	 * @param agility
	 *            The agility for this new unit.
	 * @param toughness
	 *            The toughness for this new unit.
	 * @param enableDefaultBehavior
	 *            Checker if default behavior needs to be enabled.
	 * @effect The name of this new unit is set to the given name. |
	 *         this.setName(name)
	 * @effect The position of this new unit is set to the given position. |
	 *         this.setPosition(position)
	 * @post If the given weight is a valid value for any unit, the weight of
	 *       this new unit is equal to the given weight. Otherwise, the weight
	 *       of this new unit is equal to its default value. | if
	 *       (isValidWeight(weight)) | then new.getWeight() == weight | else
	 *       new.getWeight() == defaultWeight
	 * @post If the given strength is a valid value for any unit, the strength
	 *       of this new unit is equal to the given strength. Otherwise, the
	 *       strength of this new unit is equal to its default value. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength |
	 *       else new.getStrength() == defaultStrength
	 * @post If the given agility is a valid value for any unit, the agility of
	 *       this new unit is equal to the given agility. Otherwise, the agility
	 *       of this new unit is equal to its default value. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility | else
	 *       new.getAgility() == defaultAgility
	 * @post If the given toughness is a valid value for any unit, the toughness
	 *       of this new unit is equal to the given toughness. Otherwise, the
	 *       toughness of this new unit is equal to its default value. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness | else new.getToughness() == defaultToughness
	 * @post The hitpoints are set. |new.getHitpoints() == 200 * weight *
	 *       toughness / 10000
	 * @post The staminapoints are set. |new.getHitpoints() == 200 * weight *
	 *       toughness / 10000
	 * @post If the given default behavior boolean is true, default behavior is
	 *       started | if (enableDefaultBehavior) then defaultRandomBehavior()
	 */
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
		setTotalWeight(weight);

		if (toughness < 25 || toughness > 100)
			toughness = 25 + Helper.randInt(0, 75);
		setToughness(toughness);
		this.setName(name);
		this.setPosition(Helper.getCenterOfPosition(initialPosition));
		this.setHitpoints(200 * weight * toughness / 10000);
		this.setStaminapoints(200 * weight * toughness / 10000);
		System.out.println("staminap: " + this.getStaminapoints());
		this.setDefaultBehavior(enableDefaultBehavior);
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
	 * @return result == true if the given position is passable and between the
	 *         world borders if there is a world assigned to the unit.
	 *         Otherwise, result = true if the 3 coordinates are bigger than 0.
	 */
	private static boolean isValidPosition(double[] position, World world) {

		if (position.length == 3) {
			for (int k = 0; k < position.length; k++) {
				if (position[k] < 0)
					return false;
			}
			if (world != null) {
				if (position[0] > world.getNbCubesX() || position[1] > world.getNbCubesY()
						|| position[2] > world.getNbCubesZ()) {
					return false;
				}
				if (!world.isPassable(position[0], position[1], position[2])) {
					return false;
				}
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
		if (!isValidPosition(position, this.getWorld()))
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
	private static boolean isValidName(String name) {

		if (name.length() > 1 && Pattern.matches("[a-zA-Z\\s\\'\\\"]*", name)
				&& Character.isUpperCase(name.codePointAt(0)))
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
	 * @post If the given weight is a valid value for any unit, the weight of
	 *       this new unit is equal to the given weight. Otherwise, the weight
	 *       of this new unit is equal to its default value. | if
	 *       (isValidWeight(weight)) | then new.getWeight() == weight | else
	 *       new.getWeight() == defaultWeight
	 * @post If the given strength is a valid value for any unit, the strength
	 *       of this new unit is equal to the given strength. Otherwise, the
	 *       strength of this new unit is equal to its default value. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength |
	 *       else new.getStrength() == defaultStrength
	 * @post If the given agility is a valid value for any unit, the agility of
	 *       this new unit is equal to the given agility. Otherwise, the agility
	 *       of this new unit is equal to its default value. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility | else
	 *       new.getAgility() == defaultAgility
	 * @post If the given toughness is a valid value for any unit, the toughness
	 *       of this new unit is equal to the given toughness. Otherwise, the
	 *       toughness of this new unit is equal to its default value. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness | else new.getToughness() == defaultToughness
	 */
	public Unit(int weight, int strength, int agility, int toughness) {

		if (strength < 25 || strength > 100) {
			strength = 25 + Helper.randInt(0, 75);
		}
		setStrength(strength);

		if (agility < 25 || agility > 100) {
			agility = 25 + Helper.randInt(0, 75);
		}
		setAgility(agility);

		if (weight < 25 || weight > 100 || weight < (strength + agility) / 2) {
			weight = ((strength + agility) / 2) + Helper.randInt(0, 100 - ((strength + agility) / 2));
		}
		setWeight(weight);

		if (toughness < 25 || toughness > 100) {
			toughness = 25 + Helper.randInt(0, 75);
		}
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
	private static boolean isValidWeight(int weight, int strength, int agility) {
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
	 * Return the total weight of this unit.
	 */
	@Basic
	@Raw
	public int getTotalWeight() {
		return this.totalWeight;
	}

	/**
	 * Check whether the given total weight is a valid total weight for any
	 * unit.
	 * 
	 * @param totalWeight
	 *            The total weight to check.
	 * @return | result ==
	 */
	private static boolean isValidTotalWeight(int totalWeight, int weight) {
		if (totalWeight <= weight + 50)
			return true;
		return false;
	}

	/**
	 * Set the total weight of this unit to the given total weight.
	 * 
	 * @param totalWeight
	 *            The new total weight for this unit.
	 * @post If the given total weight is a valid total weight for any unit, the
	 *       total weight of this new unit is equal to the given total weight. |
	 *       if (isValidTotalWeight(totalWeight)) | then new.getTotalWeight() ==
	 *       totalWeight
	 */
	@Raw
	private void setTotalWeight(int totalWeight) {
		if (isValidTotalWeight(totalWeight, this.getWeight()))
			this.totalWeight = totalWeight;
	}

	/**
	 * Variable registering the total weight of this unit.
	 */
	private int totalWeight;

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
	private static boolean isValidStrength(int strength) {
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
	private static boolean isValidAgility(int agility) {
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
	private static boolean isValidToughness(int toughness) {
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
	private static boolean isValidHitpoints(int hitpoints, int weight, int toughness) {
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
			this.hitpoints = 0;
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
	private static boolean isValidStaminapoints(int staminapoints, int weight, int toughness) {
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
		// System.out.println("staminaset: " + staminapoints);
		assert isValidStaminapoints(staminapoints, this.getWeight(), this.getToughness());
		this.staminapoints = staminapoints;
	}

	/**
	 * Variable registering the staminapoints of this unit.
	 */
	private int staminapoints;

	/**
	 * Drain the stamina of the unit with one point per 0.1 seconds.
	 * 
	 * @param dt
	 *            The period of time
	 */
	private void staminadrain(double dt) {
		// System.out.println("dt: " + dt);
		// if (dt >= 0.1) {
		// System.out.println("staminadr: "+this.getStaminapoints());
		// this.setStaminapoints(this.getStaminapoints() - 1);
		// dt += -0.1;
		// }
		// float time = (float) (getStaminaTime() - dt);
		//
		// if (time <= 0) {
		// this.setStaminapoints(this.getStaminapoints() - 1);
		// setStaminaTime(0.1f);
		// } else {
		// setStaminaTime(time);
		// }

		float time = (float) (getStaminaTime() + dt);
		while (time > 0.1f) {
			this.setStaminapoints(this.getStaminapoints() - 1);
			time -= 0.1f;
		}
		setStaminaTime(time);
	}

	/**
	 * Return the stamina time of this unit.
	 */
	@Basic
	@Raw
	private float getStaminaTime() {
		return this.time;
	}

	/**
	 * Check whether the given stamina time is a valid stamina time for any
	 * unit.
	 * 
	 * @param stamina
	 *            time The stamina time to check.
	 * @return | result ==
	 */
	private static boolean isValidStaminaTime(float time) {
		if (time <= 0.1f && time > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Set the stamina time of this unit to the given stamina time.
	 * 
	 * @param time
	 *            The new stamina time for this unit.
	 * @post The stamina time of this new unit is equal to the given stamina
	 *       time. | new.getStaminaTime() == time
	 * @throws IllegalArgumentException
	 *             The given stamina time is not a valid stamina time for any
	 *             unit. | ! isValidStaminaTime(getStaminaTime())
	 */
	@Raw
	private void setStaminaTime(float time) throws IllegalArgumentException {
		if (!isValidStaminaTime(time))
			throw new IllegalArgumentException();
		this.time = time;
	}

	/**
	 * Variable registering the stamina time of this unit.
	 */
	private float time;

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
	private static boolean isValidOrientation(float orientation) {
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
		if (isValidOrientation(orientation)) {
			this.orientation = orientation;
		} else {
			this.orientation = (float) (Math.PI / 2);
		}
	}

	/**
	 * Variable registering the orientation of this unit.
	 */
	private float orientation = (float) Math.PI / 2;

	/**
	 * Return the timenotresting of this unit.
	 */
	@Basic
	@Raw
	private double getTimeNotResting() {
		return this.timenotresting;
	}

	/**
	 * Check whether the given timenotresting is a valid timenotresting for any
	 * unit.
	 * 
	 * @param timenotresting
	 *            The timenotresting to check.
	 * @return | result == true if timenotresting >= 0 and timenotresting <=
	 *         180.2.
	 */
	private static boolean isValidTimeNotResting(double timenotresting) {
		if (timenotresting >= 0 && timenotresting <= 180.4) {
			return true;
		}
		return false;
	}

	/**
	 * Set the timenotresting of this unit to the given timenotresting.
	 * 
	 * @param timenotresting
	 *            The new timenotresting for this unit.
	 * @post The timenotresting of this new unit is equal to the given
	 *       timenotresting. | new.getTimeNotResting() == timenotresting
	 * @throws IllegalArgumentException
	 *             The given timenotresting is not a valid timenotresting for
	 *             any unit. | ! isValidTimeNotResting(getTimeNotResting())
	 */
	@Raw
	private void setTimeNotResting(double timenotresting) throws IllegalArgumentException {
		if (!isValidTimeNotResting(timenotresting))
			throw new IllegalArgumentException();
		this.timenotresting = timenotresting;
	}

	/**
	 * Variable registering the time since the unit last rested.
	 */
	private double timenotresting;

	/**
	 * Update the program dt (<= 0.2) seconds. - Checks whether unit needs to
	 * fall. If he needs to fall, he starts moving to the cube underneath him. -
	 * If the unit is resting, he will recover his hitpoints and then his
	 * staminapoints until he is fully recovered. - If it has been 3 minutes or
	 * longer since the unit last rested and he isn't falling, he starts
	 * resting. - If defaultbehavior is enabled for the unit and he isn't doing
	 * anything, he gets assigned a new task. - If the unit is attacking another
	 * unit, he will do this for 1 second. - If the unit is working, he will do
	 * this for 500/strength seconds. - If the unit is moving, his position is
	 * updated. - If the unit is sprinting and his staminapoints reach 0, the
	 * unit will stop sprinting. - If the unit is sprinting his staminapoints
	 * will drain 1 every 0.1 seconds. - If the unit reaches the next cube and
	 * he wanted to work or attack there, he will do so. - If the unit reaches
	 * the next cube but it is not his destination, he will move to the next
	 * cube. - The unit gains 1 experience point per cube he moves. - The unit
	 * loses 1 hitpoint per z-level he falls.
	 * 
	 * @param dt
	 *            The time the game advances in seconds.
	 * @throws IllegalArgumentException
	 *             Thrown if dt is larger than 0.2.
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {

		if (dt > 0.2) {
			throw new IllegalArgumentException();
		} else {
			if (!this.getWorld().hasImpassableNeighbour(this.getPosition()[0], this.getPosition()[1],
					this.getPosition()[2]) && !this.getIsFalling()) {
				this.setIsFalling(true);
				moveToAdjacent(0, 0, -1);
			} else {
				if (this.getIsResting()) {
					doRest(dt);
					this.setTimeNotResting(0);
				} else {
					this.setTimeNotResting(this.getTimeNotResting() + dt);
					if (this.getTimeNotResting() >= 180 && !this.getIsFalling()) {
						rest();
					}
					if (this.getDefaultBehavior() && !this.getIsAttacking() && !this.getIsMoving()
							&& !this.getIsResting() && !this.getIsWorking()) {
						startDefaultBehavior(dt);
					}
					if (this.getIsAttacking()) {
						doAttack(dt, this.getDefender());
					}
					if (this.getIsWorking()) {
						doWork(dt);
					}
					if (this.getIsMoving()) {
						if (this.getIsSprinting()) {
							System.out.println("advancetime stmaina: "+this.getStaminapoints());
							staminadrain(dt);
							if (this.getStaminapoints() <= 0) {
								this.setIsSprinting(false);
							}
						}
						this.updatePosition(dt);
						if (this.moveToAdjacentTargetReached()) {

							System.out.println(
									"unit" + this + " x: " + this.getPosition()[0] + " y: " + this.getPosition()[1]);
							this.setIsMoving(false);
							if (this.getIsFalling()) {
								this.setHitpoints(this.getHitpoints() - 10);
								this.setIsFalling(false);
							} else {
								this.setExperience(this.getExperience() + 1);
								if (isMovingTo) {
									if (this.moveToTargetReached()) {
										isMovingTo = false;// TODO setter maken
										// System.out.println("----------------
										// moveto
										// beeindigd ----------------------");
										if (this.getWantToWork()) {
											work();
										}
										if (this.getWantToAttack()) {
											attack(this, this.getDefender());
										}
									} else {

										if (inQ(Q, Helper.doubleArrayToIntArray(this.getPosition()))) {
											this.nextMoveToAdjacentFromQ();
										} else {
											moveTo(Helper.doubleArrayToIntArray(target));
										}
									}

								}
							}
						}
						if (this.getIsFollowing()) {
							followUnit(getLeader());
						}

					}
				}
			}
		}

	}

	/**
	 * Return whether the unit wants to work.
	 */
	@Basic
	@Raw
	private boolean getWantToWork() {
		return this.wantToWork;
	}

	/**
	 * Set the will to work of this unit to the given wantToWork.
	 * 
	 * @param wantToWork
	 *            The new wantToWork for this unit.
	 * @post The wantToWork of this new unit is equal to the given wantToWork. |
	 *       new.getWantToWork() == wantToWork
	 */
	@Raw
	private void setWantToWork(boolean wantToWork) {
		this.wantToWork = wantToWork;
	}

	/**
	 * Variable registering the wantToWork of this unit.
	 */
	private boolean wantToWork = false;

	/**
	 * Update the unit's position and orientation considering his velocity.
	 * 
	 * @param dt
	 *            The period of time
	 */
	private void updatePosition(double dt) {

		velocity = getVelocity();

		this.setOrientation((float) (Math.atan2(velocity[1], velocity[0])));

		double[] newPosition = this.getPosition();
		newPosition[0] += velocity[0] * dt;
		newPosition[1] += velocity[1] * dt;
		newPosition[2] += velocity[2] * dt;
		this.setPosition(newPosition);

	}

	private int[] adjacentDelta = new int[3];

	/**
	 * Return the current speed of the unit
	 * 
	 * @return result== 3 if the unit is falling
	 * @return result== basespeed if the unit is moving on the same z level
	 * @return result== basespeed/2 if the unit is moving to a higher z level
	 * @return result== basespeed*1.2 if the unit is moving to a lower z level
	 * @return result== double the speed if the unit is sprinting (and not
	 *         falling)
	 */
	public double getCurrentSpeed() {
		double currentSpeed;
		double baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100);
		if (!this.getIsMoving()) {
			return 0;
		}
		if (this.getIsFalling())
			return 3;
		if (this.getIsSprinting())
			baseSpeed *= 2;

		if (adjacentDelta[2] == 1) {
			currentSpeed = baseSpeed * 0.5;
		} else if (adjacentDelta[2] == -1) {
			currentSpeed = baseSpeed * 1.2;
		} else {
			currentSpeed = baseSpeed;
		}

		return currentSpeed;

	}

	/**
	 * Variable registering whether the unit is moving to somewhere.
	 */
	private boolean isMovingTo;

	/**
	 * Return whether this unit is moving.
	 */
	@Basic
	@Raw
	public boolean getIsMoving() {
		return this.isMoving;
	}

	/**
	 * Check whether the given moving state is a valid state for any unit.
	 * 
	 * @param isMoving
	 *            The state to check.
	 * @return | result == true if given value is boolean
	 */
	private static boolean isValidIsMoving(boolean isMoving) {
		if (isMoving || isMoving == false)
			return true;
		return false;
	}

	/**
	 * Set the moving state of this unit to the given isMoving.
	 * 
	 * @param isMoving
	 *            The new moving state for this unit.
	 * @post The moving state of this new unit is equal to the given isMoving. |
	 *       new.getIsMoving() == isMoving
	 * @throws IllegalArgumentException
	 *             The given isMoving is not a valid isMoving for any unit. | !
	 *             isValidIsMoving(getIsMoving())
	 */
	@Raw
	private void setIsMoving(boolean isMoving) throws IllegalArgumentException {
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
	 * Variable registering whether this unit is moving.
	 */
	private boolean isMoving;

	/**
	 * Return whether this unit is sprinting.
	 */
	@Basic
	@Raw
	public boolean getIsSprinting() {
		return this.isSprinting;
	}

	/**
	 * Check whether the given sprinting state is a valid state
	 * 
	 * @param isSprinting
	 *            The state to check.
	 * @return | result == true if given value is boolean
	 */
	private static boolean isValidIsSprinting(boolean isSprinting) {
		if (isSprinting || isSprinting == false)
			return true;
		return false;
	}

	/**
	 * Set the sprinting state of this unit to the given isSprinting.
	 * 
	 * @param isSprinting
	 *            The new sprinting state for this unit.
	 * @post The sprinting state of this new unit is equal to the given
	 *       isSprinting. | new.getIsSprinting() == isSprinting
	 * @post If isSprinting is true and if the unit was resting, working or
	 *       attacking, that activity stopped.
	 * @post If isSprinting is true and if he wasn't already, the unit started
	 *       moving.
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
			this.setStaminaTime(0.1f);
			// this.setIsMoving(true);
		}
	}

	/**
	 * Variable registering whether this unit is sprinting or not.
	 */
	private boolean isSprinting;

	/**
	 * Return whether this unit is working.
	 */
	@Basic
	@Raw
	public boolean getIsWorking() {
		return this.isWorking;
	}

	/**
	 * Check whether the given working state is a valid state for any unit.
	 * 
	 * @param isWorking
	 *            The isWorking to check.
	 * @return | result == true if given value is boolean
	 */
	private static boolean isValidIsWorking(boolean isWorking) {
		if (isWorking || isWorking == false)
			return true;
		return false;
	}

	/**
	 * Set the working state of this unit to the given isWorking.
	 * 
	 * @param isWorking
	 *            The new working state for this unit.
	 * @post The working state of this new unit is equal to the given isWorking.
	 *       | new.getIsWorking() == isWorking
	 * @post If isWorking is true any other running activities are stopped.
	 * @throws IllegalArgumentException
	 *             The given isWorking is not a valid isWorking for any unit. |
	 *             ! isValidIsWorking(getIsWorking())
	 */
	@Raw
	private void setIsWorking(boolean isWorking) throws IllegalArgumentException {
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
	 * Variable registering the working state of this unit.
	 */
	private boolean isWorking;

	/**
	 * Return the whether this unit is attacking.
	 */
	@Basic
	@Raw
	public boolean getIsAttacking() {
		return this.isAttacking;
	}

	/**
	 * Check whether the given attacking state is a valid state for any unit.
	 * 
	 * @param isAttacking
	 *            The isAttacking to check.
	 * @return | result == true if given value is boolean
	 */
	private static boolean isValidIsAttacking(boolean isAttacking) {
		if (isAttacking || isAttacking == false)
			return true;
		return false;
	}

	/**
	 * Set the the attacking state of this unit to the given isAttacking.
	 * 
	 * @param isAttacking
	 *            The new attacking state for this unit.
	 * @post The attacking state of this new unit is equal to the given
	 *       isAttacking. | new.getisAttacking() == isAttacking
	 * @post If isAttacking is true any other running activities are stopped.
	 * @throws IllegalArgumentException
	 *             The given isAttacking is not a valid isAttacking for any
	 *             unit. | ! isValidIsAttacking(getisAttacking())
	 */
	@Raw
	private void setIsAttacking(boolean isAttacking) throws IllegalArgumentException {
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
	 * Variable registering whether this unit is attacking or not.
	 */
	private boolean isAttacking;

	/**
	 * Return whether this unit is resting or not.
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
	 * @return | result == true if given value is either true or false
	 */
	private static boolean isValidIsResting(boolean isResting) {
		if (isResting || isResting == false)
			return true;
		return false;
	}

	/**
	 * Set whether this unit is resting or not.
	 * 
	 * @param isResting
	 *            The new resting state for this unit.
	 * @post The resting state of this new unit is equal to the given isResting.
	 *       | new.getIsResting() == isResting
	 * @post If any other activities are running they are stopped.
	 * @throws IllegalArgumentException
	 *             The given isResting is not a valid isResting for any unit. |
	 *             ! isValidIsResting(getIsResting())
	 */
	@Raw
	private void setIsResting(boolean isResting) throws IllegalArgumentException {
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

	/**
	 * Reset the rest time and start resting
	 */
	public void rest() {
		setRestTime((40 / (float) this.getToughness()));
		setIsResting(true);

	}

	/**
	 * Regain hitpoints first, staminapoints second when the rest time is 0.
	 * 
	 * @param dt
	 *            The period of time
	 */
	private void doRest(double dt) {
		float time = getRestTime() - (float) dt;
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
	 * Return whether this unit is falling or not.
	 */
	@Basic
	@Raw
	private boolean getIsFalling() {
		return this.isFalling;
	}

	/**
	 * Check whether the given isFalling is a valid isFalling for any unit.
	 * 
	 * @param isFalling
	 *            The isFalling to check.
	 * @return | result == true if isFalling is either true or false
	 */
	private static boolean isValidIsFalling(boolean isFalling) {
		if (isFalling || isFalling == false)
			return true;
		return false;
	}

	/**
	 * Set whether this unit is falling to the given isFalling.
	 * 
	 * @param isFalling
	 *            The new falling state for this unit.
	 * @post The falling state of this new unit is equal to the given isFalling.
	 *       | new.getIsFalling() == isFalling
	 * @throws IllegalArgumentException
	 *             The given isFalling is not a valid isFalling for any unit. |
	 *             ! isValidIsFalling(getIsFalling())
	 */
	@Raw
	private void setIsFalling(boolean isFalling) throws IllegalArgumentException {
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

	/**
	 * Return the maximum hitpoints and staminapoints a unit can have
	 * 
	 * @return result == 200 * weight * toughness / 10000
	 */
	public int getMaxPoints() {
		return 200 * this.getWeight() * this.getToughness() / 10000;
	}

	/**
	 * Variable registering the velocity of the unit
	 */
	private double[] velocity = new double[3];
	// keeps the position from where the last movetoadjacent started.
	/**
	 * Variable registering the start position of moveToAdjacent
	 */
	private static double[] adjacentStart = new double[3];// TODO moet deze
															// statiic zijn?
	// keeps the postion of the targeted position from the last movetoadjacent.
	/**
	 * Variable registering the targeted position of moveToAdjacent
	 */
	private double[] adjacentTarget = new double[3];
	// keeps the postion of the targeted position from the last moveto.
	/**
	 * Variable registering the targeted position of moveTo
	 */
	private double[] target = new double[3];

	/**
	 * Move the unit to an adjacent cube
	 * 
	 * @param dx
	 *            The difference between unit target and unit on the x axis
	 * @param dy
	 *            The difference between unit target and unit on the y axis
	 * @param dz
	 *            The difference between unit target and unit on the z axis
	 * @throws IllegalArgumentException
	 *             thrown if the target position is invalid
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {

		if(dx==0&&dy==0&&dz==0){
			throw new IllegalArgumentException("Already on that position.");
		}
		
		// System.out.println("start movetoadj");

		adjacentStart = this.getPosition().clone();
		// System.out.println("adjacentStart:" + adjacentStart[0] + " " +
		// adjacentStart[1] + " " + adjacentStart[2]);

		double[] newPosition = new double[3];// TODO functie gebruiken
		for (int k = 0; k < adjacentStart.length; k++) {
			newPosition[k] = Math.floor(adjacentStart[k]) + 0.5d;
		}

		newPosition[0] += dx;
		newPosition[1] += dy;
		newPosition[2] += dz;
		// System.out.println("x: " + newPosition[0] + "y: " + newPosition[1] +
		// "z: " + newPosition[2]);

		if (!isValidTarget(newPosition))
			throw new IllegalArgumentException();
		else {

			adjacentTarget = newPosition;

			// System.out.println("new AdjTarget: " + adjacentTarget[0] + " " +
			// adjacentTarget[1] + " " + adjacentTarget[2]);

			this.setIsMoving(true);

			adjacentDelta[0] = dx;
			adjacentDelta[1] = dy;
			adjacentDelta[2] = dz;

			distance = getDistance(dx, dy, dz);// in principe overbodig mr beter
												// in variabel -> minder
												// rekenwerk...
			// System.out.println("velocity=" + velocity[0] + " " + velocity[1]
			// + " dist: " + distance);

		}

	}

	/**
	 * Check if the given target is valid
	 * 
	 * @param target
	 *            The target to check
	 * @return result == true if the unit has no world
	 * @return result == true if the target is a passable block within the
	 *         world's boundaries
	 */
	private boolean isValidTarget(double[] target) {// TODO lijkt hard op
													// isvalidPods
		// System.out.println("isValidTarget currentpos "+ this.getPosition()[0]
		// + " "+this.getPosition()[1] +" "+ this.getPosition()[2]);
		// System.out.println("isValidTarget "+ target[0] + " "+target[1] +" "+
		// target[2]);
		// System.out.println(this.getWorld());
		// System.out.println("isValidTarget withinbound
		// "+this.getWorld().withinBoundaries((int)target[0], (int) target[1],
		// (int) target[2]));
		if (this.getWorld() == null)
			return true;
		if (this.getWorld().withinBoundaries((int) target[0], (int) target[1], (int) target[2])
				&& this.getWorld().isPassable(target[0], target[1], target[2])) {
			if (this.getIsFalling() || this.getWorld().hasImpassableNeighbour(target[0], target[1], target[2])) {
				return true;
			}
		}
		return false;

		// Double lowerlimit = 0.5d;// variabelen in class declaren?
		// Double upperlimit = 49.5d;
		// boolean differentPos = false;

		// if (target.length == 3) {

		// for (int k = 0; k < target.length; k++) {
		// if (target[k] > upperlimit || target[k] < lowerlimit)
		// return false;
		// if (target[k] != adjacentStart[k]) {
		// differentPos = true;
		// }
		// }
		// }
		// if (differentPos)
		// return true;

		// return false;

	}

	/**
	 * Check if the unit has reached its adjacent target
	 * 
	 * @return result == true if the position between the unit and the target <
	 *         1
	 * @throws IllegalArgumentException
	 *             thrown if the adjacentTarget is an invalid position
	 */
	private boolean moveToAdjacentTargetReached() throws IllegalArgumentException {

		if (Helper.getDistanceBetweenPositions(adjacentStart, adjacentTarget) <= Helper
				.getDistanceBetweenPositions(adjacentStart, this.getPosition())) {

			// System.out.println("moveToAdjacentTargetReached ");
			this.setPosition(adjacentTarget);
			return true;

		}

		return false;

	}

	/**
	 * Move the unit to the center of the given target cube
	 * 
	 * @param targetPosition
	 *            The target cube
	 * @throws IllegalArgumentException
	 *             thrown if the target is invalid
	 */
	public void moveTo(int[] targetPosition) throws IllegalArgumentException { // FIXME
																				// 2
																				// keer
																				// moveto
																				// achter
																				// elkaar
																				// fout

		// System.out.println("---------------- moveto begonnen
		// ----------------------");

		// isMovingTo = true;// TODO getter en setter
		//
		// if (!isValidTarget(Helper.getCenterOfPosition(targetPosition))) {
		// throw new IllegalArgumentException();
		// } else {
		//
		// target[0] = targetPosition[0] + 0.5d;// TODO forlus
		// target[1] = targetPosition[1] + 0.5d;
		// target[2] = targetPosition[2] + 0.5d;
		// System.out.println("new target: " + target[0] + " " + target[1] +
		// " " + target[2]);
		// moveToAdjacent(getMoveToDirectionX(), getMoveToDirectionY(),
		// getMoveToDirectionZ());

		// }
		isMovingTo = true;

		if (!isValidTarget(Helper.getCenterOfPosition(targetPosition))) {
			throw new IllegalArgumentException("This is not a valid target. ");
		} else {
			target[0] = targetPosition[0] + 0.5d;
			target[1] = targetPosition[1] + 0.5d;
			target[2] = targetPosition[2] + 0.5d;
		}

		findPath();
	}

	/**
	 * Check if the unit has reached its target
	 * 
	 * @return result == true if the position between the unit and the target <
	 *         1
	 * @throws IllegalArgumentException
	 */
	// TODO waarom IllegalArgumentException?
	public boolean moveToTargetReached() throws IllegalArgumentException {

		if (Helper.getDistanceBetweenPositions(this.getPosition(), this.target) < 1) {
			return true;
		}
		return false;

	}

	private List<Object[]> Q = new ArrayList<>();

	/**
	 * Find a path from the unit's current position to the target
	 * 
	 * @throws IllegalStateException
	 *             if the target cannot be reached
	 * 
	 */
	private void findPath() throws IllegalStateException {

		// System.out.println("------ findPath start ------");

		int[] moveToTarget = Helper.doubleArrayToIntArray(this.target);
		int[] currentPosition = Helper.doubleArrayToIntArray(this.getPosition()); // TODO
																					// dit
																					// als
																					// argumenten
																					// meegeven
																					// ipv
																					// hier
																					// zetten
																					// zodat
																					// findpath
																					// ook
																					// voor
																					// boulders
																					// enz
																					// gebruikt
																					// kan
																					// worden

		Q = new ArrayList<>();

		if (currentPosition != moveToTarget) {

			Object[] initialEntry = { moveToTarget, 0 };
			Q.add(initialEntry);

			int i = 0;
			while (!inQ(Q, currentPosition) && i < Q.size()) {

				Object[] newEntry = ((Object[]) Q.get(i));
				search((int[]) newEntry[0], (int) newEntry[1], Q);
				i++;

			}
			if (inQ(Q, currentPosition)) {

				this.nextMoveToAdjacentFromQ();

			} else {

				throw new IllegalStateException("The target cannot be reached.");
			}
		}
	}

	// TODO docu
	/**
	 * Move to the adjacent cube that has the shortest path to the target.
	 */
	private void nextMoveToAdjacentFromQ() {
		int[] currentPosition = Helper.doubleArrayToIntArray(this.getPosition());
		List<int[]> possibilities = getNeighbouringCubes(currentPosition);
		int[] nextPosition = null;
		int nextN = Integer.MAX_VALUE;

		for (int[] neigbouringCube : possibilities) {
			if (inQ(Q, neigbouringCube)) {
				int currentN = getNumberQ(Q, neigbouringCube);
				if (currentN < nextN) {
					nextN = currentN;
					nextPosition = neigbouringCube;

				}

			}
			if (nextPosition != null) {
				// System.out.println("-- moveToAdjacent");
				moveToAdjacent(nextPosition[0] - currentPosition[0], nextPosition[1] - currentPosition[1],
						nextPosition[2] - currentPosition[2]);
			}

		}
	}

	// TODO docu
	private void search(int[] position, int n, List<Object[]> Q) {

		// System.out.println("-- search(" + position[0] + " " + position[1] + "
		// " + position[2] + " , " + n + ")");

		List<int[]> possibilities = getNeighbouringCubes(position);
		World world = this.getWorld();

		for (int[] neigbouringCube : possibilities) {
			if (world.isPassable(neigbouringCube[0], neigbouringCube[1], neigbouringCube[2])
					&& world.hasImpassableNeighbour((double) neigbouringCube[0], (double) neigbouringCube[1],
							(double) neigbouringCube[2])
					&& !inQ(Q, neigbouringCube)) {
				Object[] a = { neigbouringCube, n + 1 };
				Q.add(a);

			}
		}
	}

	/**
	 * Return the number of steps from given position to the target.
	 * 
	 * @param Q
	 *            The list with positions of cubes on paths with the number of
	 *            steps to the target
	 * @param position
	 *            The position to examine
	 * @return result == int indicating number of steps to the target if the
	 *         given position is in Q
	 * @return result == the biggest possible int if the given position is not
	 *         in Q
	 */
	private int getNumberQ(List<Object[]> Q, int[] position) {

		try {
			for (int i = 0; i < Q.size(); i++) {
				int[] positionQ = (int[]) Q.get(i)[0];
				if (positionQ[0] == position[0] && positionQ[1] == position[1] && positionQ[2] == position[2])
					return (int) Q.get(i)[1];
			}
		} catch (IllegalStateException e) {
		}
		return Integer.MAX_VALUE;

	}

	/**
	 * Check if a given position is in list Q
	 * 
	 * @param Q
	 *            The list of positions to check
	 * @param position
	 *            The position to check
	 * @return result == true if the position is in the list
	 */
	private boolean inQ(List<Object[]> Q, int[] position) {

		for (int i = 0; i < Q.size(); i++) {
			int[] positionQ = (int[]) Q.get(i)[0];
			if (positionQ[0] == position[0] && positionQ[1] == position[1] && positionQ[2] == position[2])
				return true;
		}
		return false;
	}

	/**
	 * Return a list of the cubes neighboring the given position
	 * 
	 * @param position
	 *            The position to find the neighbors of
	 * @return a list of the positions of the cubes neighboring the given
	 *         position
	 */
	private List<int[]> getNeighbouringCubes(int[] position) {

		List<int[]> neighbouringCubes = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {

					if (!(i == 0 && j == 0 && k == 0)
							&& this.getWorld().withinBoundaries(position[0] + i, position[1] + j, position[2] + k)) {// TODO
																														// isvalidpositions
						int[] newNeighbour = { position[0] + i, position[1] + j, position[2] + k };
						neighbouringCubes.add(newNeighbour);
					}
				}
			}
		}
		return neighbouringCubes;
	}

	/**
	 * Reset the work time and start working
	 */
	public void work() {
		setWorkTime(500 / (float) this.getStrength());
		this.setWantToWork(false);
		this.setIsWorking(true);
	}

	/**
	 * Work at the work position if the unit is there or move to a cube next to
	 * the work position
	 * 
	 * @param x
	 *            X coordinate of the target location
	 * @param y
	 *            Y coordinate of the target location
	 * @param z
	 *            Z coordinate of the target location
	 */
	public void workAt(int x, int y, int z) {
		this.setWorkPosition(x, y, z);
		double distance = Double.POSITIVE_INFINITY;
		if (!Helper.getIsSamePosition(Helper.doubleArrayToIntArray(this.getPosition()), this.getWorkPosition())) {
			int[] nearestPos = null;
			for (int[] pos : this.getNeighbouringCubes(this.getWorkPosition())) {
				if (this.isValidTarget(Helper.intArrayToDoubleArray(pos))) {
					double newDistance = Helper.getDistanceBetweenPositions(this.getPosition(),
							Helper.getCenterOfPosition(pos));
					if (newDistance == 0) {
						distance = newDistance;
						nearestPos = pos;
						break;
					}
					if (newDistance < distance) {
						distance = newDistance;
						nearestPos = pos;
					}
				}
			}
			if (distance != 0) {
				this.setWantToWork(true);
				this.moveTo(nearestPos);
			}
		}
		if (distance == Double.POSITIVE_INFINITY || distance == 0) {
			work();
		}

	}

	/**
	 * Return the work position of this unit.
	 */
	@Basic
	@Raw
	private int[] getWorkPosition() {
		return this.workPosition;
	}

	/**
	 * Set the work position of this unit to the given work position.
	 * 
	 * @param workPosition
	 *            The new work position for this unit.
	 * @post The work position of this new unit is equal to the given work
	 *       position. | new.getWorkPosition() == workPosition
	 */
	@Raw
	private void setWorkPosition(int x, int y, int z) {
		this.workPosition[0] = x;
		System.out.println("workPos: " + this.workPosition[0]);
		this.workPosition[1] = y;
		this.workPosition[2] = z;
	}

	/**
	 * Variable registering the position where the unit works.
	 */
	private int[] workPosition;

	/**
	 * Try to work at the work position when the work time is 0.
	 * 
	 * @param dt
	 *            The period of time
	 */
	private void doWork(double dt) {

		float time = getWorkTime() - (float) dt;

		int[] workPosition = this.getWorkPosition();
		int x = workPosition[0];
		int y = workPosition[1];
		int z = workPosition[2];

		if (!Helper.getIsSamePosition(workPosition, Helper.doubleArrayToIntArray(this.getPosition()))) {
			// System.out.println("x: " + x + " y: " + y + " z: " + z);
			// System.out.println("Cube Type workpos: " +
			// this.getWorld().getCubeType(x, y, z));
			double dx = x + 0.5 - this.getPosition()[0];
			double dy = y + 0.5 - this.getPosition()[1];
			this.setOrientation((float) Math.atan2(dy, dx));
		}

		if (time <= 0) {

			int experience = 10;

			if ((this.getCarryingBoulder() || this.getCarryingLog()) && this.getWorld().isPassable(x, y, z)) {
				this.drop();

			} else if (this.getWorld().getCubeType(x, y, z) == World.WORKSHOP && itemsAvailable(workPosition)) {

				Boulder boulder = this.getWorld().getBoulder(workPosition);
				boulder.terminate();
				Log log = this.getWorld().getLog(workPosition);
				log.terminate();
				this.setWeight(this.getWeight() + 5);
				this.setTotalWeight(this.getWeight() + 5);
				this.setToughness(this.getToughness() + 5);

			} else if (this.getWorld().getBoulder(workPosition) != null) {

				Boulder boulder = this.getWorld().getBoulder(workPosition);
				this.setPickedUpBoulder(boulder);
				boulder.setIsCarriedBy(this);
				this.setTotalWeight(this.getWeight() + boulder.getWeight());

			} else if (this.getWorld().getLog(workPosition) != null) {

				Log log = this.getWorld().getLog(workPosition);
				this.setPickedUpLog(log);
				log.setIsCarriedBy(this);
				this.setTotalWeight(this.getWeight() + log.getWeight());

			} else if (this.getWorld().getCubeType(x, y, z) == World.TREE) {

				this.getWorld().collapseCube(x, y, z, true);

			} else if (this.getWorld().getCubeType(x, y, z) == World.ROCK) {

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
	 * Return the pickedUpBoulder of this unit.
	 */
	@Basic
	@Raw
	private Boulder getPickedUpBoulder() {
		return this.pickedUpBoulder;
	}

	/**
	 * Check whether the given pickedUpBoulder is a valid pickedUpBoulder for
	 * any unit.
	 * 
	 * @param pickedUpBoulder
	 *            The pickedUpBoulder to check.
	 * @return | result ==
	 */
	private static boolean isValidPickedUpBoulder(Boulder pickedUpBoulder) {
		// TODO isValid
		return true;
	}

	/**
	 * Set the pickedUpBoulder of this unit to the given pickedUpBoulder.
	 * 
	 * @param pickedUpBoulder
	 *            The new pickedUpBoulder for this unit.
	 * @post The pickedUpBoulder of this new unit is equal to the given
	 *       pickedUpBoulder. | new.getPickedUpBoulder() == pickedUpBoulder
	 * @throws IllegalArgumentException
	 *             The given pickedUpBoulder is not a valid boulder for any
	 *             unit. | ! isValidPickedUpBoulder(getPickedUpBoulder())
	 */
	@Raw
	private void setPickedUpBoulder(Boulder pickedUpBoulder) throws IllegalArgumentException {
		if (!isValidPickedUpBoulder(pickedUpBoulder))
			throw new IllegalArgumentException();
		this.pickedUpBoulder = pickedUpBoulder;
	}

	/**
	 * Variable registering the boulder that the unit has picked up.
	 */
	private Boulder pickedUpBoulder;

	/**
	 * Return the pickedUpLog of this unit.
	 */
	@Basic
	@Raw
	private Log getPickedUpLog() {
		return this.pickedUpLog;
	}

	/**
	 * Check whether the given pickedUpLog is a valid pickedUpLog for any unit.
	 * 
	 * @param pickedUpLog
	 *            The pickedUpLog to check.
	 * @return | result ==
	 */
	private static boolean isValidPickedUpLog(Log pickedUpLog) {
		// TODO isValid
		return true;
	}

	/**
	 * Set the pickedUpLog of this unit to the given pickedUpLog.
	 * 
	 * @param pickedUpLog
	 *            The new pickedUpLog for this unit.
	 * @post The pickedUpLog of this new unit is equal to the given pickedUpLog.
	 *       | new.getPickedUpLog() == pickedUpLog
	 * @throws IllegalArgumentException
	 *             The given pickedUpLog is not a valid log for any unit. | !
	 *             isValidPickedUpLog(getPickedUpLog())
	 */
	@Raw
	private void setPickedUpLog(Log pickedUpLog) throws IllegalArgumentException {
		if (!isValidPickedUpLog(pickedUpLog))
			throw new IllegalArgumentException();
		this.pickedUpLog = pickedUpLog;
	}

	/**
	 * Variable registering the log that the unit has picked up.
	 */
	private Log pickedUpLog;

	/**
	 * Return the work time of this unit.
	 */
	@Basic
	@Raw
	private float getWorkTime() {
		return this.workTime;
	}

	/**
	 * Check whether the given work time is a valid work time for any unit.
	 * 
	 * @param work
	 *            time The work time to check.
	 * @return | result == true if the given workTime <= 500 / (float) strength)
	 */
	private static boolean isValidWorkTime(float workTime, int strength) {
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
	private void setWorkTime(float workTime) throws IllegalArgumentException {
		if (!isValidWorkTime(workTime, this.getStrength()))
			throw new IllegalArgumentException();
		this.workTime = workTime;
	}

	/**
	 * Variable registering the work time of this unit.
	 */
	private float workTime;

	/**
	 * Check if there is a boulder and/or a log on the given position
	 * 
	 * @param position
	 *            The position to check
	 * @return result == true if there is a boulder and/or a log at the given
	 *         position
	 */
	private boolean itemsAvailable(int[] position) {
		if (this.getWorld().getBoulder(position) != null && this.getWorld().getLog(position) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Drop the log or boulder that the unit is carrying.
	 */
	private void drop() {
		if (this.getCarryingBoulder()) {
			this.setCarryingBoulder(false);
			this.getPickedUpBoulder().setPosition(Helper.getCenterOfPosition(this.getWorkPosition()));
			this.getPickedUpBoulder().setIsCarriedBy(null);
			this.setTotalWeight(this.getTotalWeight() - this.getPickedUpBoulder().getWeight());
			this.setPickedUpBoulder(null);
		}
		if (this.getCarryingLog()) {
			this.setCarryingLog(false);
			this.getPickedUpLog().setPosition(Helper.getCenterOfPosition(this.getWorkPosition()));
			this.getPickedUpLog().setIsCarriedBy(null);
			this.setTotalWeight(this.getTotalWeight() - this.getPickedUpLog().getWeight());
			this.setPickedUpLog(null);
		}
	}

	/**
	 * Return the distance between two positions. (int)
	 * 
	 * @param dx
	 *            Difference between two X coordinates
	 * @param dy
	 *            Difference between two Y coordinates
	 * @param dz
	 *            Difference between two Z coordinates
	 * @return The square root of the sum of dx, dy and dz squared
	 */
	private double getDistance(int dx, int dy, int dz) {// TODO nr helper

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));

	}

	/**
	 * Return the distance between two positions. (double)
	 * 
	 * @param dx
	 *            Difference between two X coordinates
	 * @param dy
	 *            Difference between two Y coordinates
	 * @param dz
	 *            Difference between two Z coordinates
	 * @return The square root of the sum of dx, dy and dz squared
	 */
	private double getDistance(double dx, double dy, double dz) {// TODO nr
																	// helper

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));

	}

	/**
	 * Variable registering the distance between two positions.
	 */
	private double distance;

	/**
	 * Return the velocity of the unit.
	 * 
	 * @return velocity of the unit
	 */
	private double[] getVelocity() {

		double[] velocity = new double[3];

		velocity[0] = this.getCurrentSpeed() * adjacentDelta[0] / distance;
		velocity[1] = this.getCurrentSpeed() * adjacentDelta[1] / distance;
		velocity[2] = this.getCurrentSpeed() * adjacentDelta[2] / distance;

		return velocity;

	}

	/**
	 * Start default behavior and select random activity for the unit to do.
	 */

	public void startDefaultBehavior(double dt) {
		System.out.println("------start default behaviour dt=" + dt);

		// System.out.println("----gettask "+getTask()+" scheduler hasnext:
		// "+getFaction().getScheduler().getAllTasksIterator().hasNext()+"
		// Scheduler"+getFaction().getScheduler());

		this.setDefaultBehavior(true);

		if (getTask() == null && getFaction().getScheduler().getAllTasksIterator().hasNext()) {
			System.out.println("--start default behaviour dt=" + dt);
			getFaction().getScheduler().scheduleUnexecutedHighestPriorityTaskForUnit(this);
		}
		if (getTask() != null && !getTask().getActivity().isExecuted()) {
			System.out.println("--execute task");
			executeTask(dt);
		} else {
			System.out.println("-start random default behaviour ");
			defaultRandomBehaviour();
		}

	}

	public void executeTask(double dt) {

		// TODO taak uitvoeren

		if (getTask().getActivity().isExecuted()) {
			getFaction().getScheduler().removeTask(getTask());
		} else {
			getTask().getActivity().execute();

		}
	}

	public void defaultRandomBehaviour() {
		int rand = Helper.randInt(0, 3);
		if (rand == 0) {
			int[] randomPosition = Helper.getRandomPosition(this.getWorld().getNbCubesX(),
					this.getWorld().getNbCubesY(), this.getWorld().getNbCubesZ());
			while (!this.isValidTarget(Helper.intArrayToDoubleArray(randomPosition))) {
				randomPosition = Helper.getRandomPosition(this.getWorld().getNbCubesX(), this.getWorld().getNbCubesY(),
						this.getWorld().getNbCubesZ());
			}
			this.moveTo(randomPosition);
			this.setIsSprinting(Helper.randBoolean());

		} else if (rand == 1) {
			this.workAt((int) this.getPosition()[0], (int) this.getPosition()[1], (int) this.getPosition()[2]);

		} else if (rand == 2) {

			attack(this, this.getRandomEnemy());

		} else {
			this.rest();
		}

	}

	/**
	 * Return a random unit that is not in the same faction as the unit
	 * 
	 * @return A random unit not in the same faction as the unit
	 */
	public Unit getRandomEnemy() {
		Unit enemy = this;
		System.out.println("enemyfaction: " + (enemy.getFaction() == this.getFaction()));
		while (enemy.getFaction() == this.getFaction()) {
			enemy = this.getRandomUnit();
			System.out.println("enemyfaction: " + enemy.getFaction());
		}
		return enemy;
	}

	/**
	 * Return a random unit of the same faction as the unit that's not the unit
	 * 
	 * @return A random unit of the same faction as the unit that's not the unit
	 */
	public Unit getRandomFriend() {
		Unit friend = null;
		while (friend.getFaction() != this.getFaction()) {
			friend = this.getRandomUnit();
		}
		return friend;
	}

	/**
	 * Return a random unit of the unit's world that's not the unit
	 * 
	 * @return A random unit of the unit's world that's not the unit
	 */
	public Unit getRandomUnit() {
		if (this.getWorld().getUnits().size() > 1) {
			int rand = Helper.randInt(0, this.getWorld().getUnits().size() - 1);
			int i = 0;
			for (Unit unit : this.getWorld().getUnits()) {
				if (i == rand) {
					if (unit == this) {
						getRandomUnit();
					}
					return unit;
				}
				i = +1;
			}
		}
		return null;
	}

	/**
	 * Enable or disable the default behavior of this unit.
	 * 
	 * @param defaultBehavior
	 *            The new state of the default behavior for this unit.
	 * @post The default behavior state of this new unit is equal to the given
	 *       state. | new.getDefaultBehavior() == defaultBehavior
	 */
	@Raw
	public void setDefaultBehavior(boolean defaultBehavior) {
		this.defaultBehaviorEnabled = defaultBehavior;
	}

	/**
	 * Return whether default behavior of this unit is enabled.
	 */
	@Basic
	@Raw
	public boolean getDefaultBehavior() {
		return this.defaultBehaviorEnabled;
	}

	/**
	 * Variable registering whether the default behavior of this unit is
	 * enabled.
	 */
	private boolean defaultBehaviorEnabled = false;

	/**
	 * Set the defender of the attack and check if he is close enough to attack.
	 * Otherwise, move to the defender.
	 * 
	 * @param attacker
	 *            The unit that attacks
	 * @param defender
	 *            The unit that defends
	 */
	public void attack(Unit attacker, Unit defender) {
		this.setWantToAttack(false);
		this.setDefender(defender);
		double[] attackerpos = attacker.getPosition();
		double[] defenderpos = defender.getPosition();
		double dx = attackerpos[0] - defenderpos[0];
		double dy = attackerpos[1] - defenderpos[1];
		double dz = attackerpos[2] - defenderpos[2];
		if (getDistance(dx, dy, dz) <= Math.sqrt(2)) {
			setAttackOrientation(attackerpos, defenderpos, defender);
			this.setIsAttacking(true);
		} else {
			this.moveTo(Helper.doubleArrayToIntArray(defender.getPosition()));
			this.setWantToAttack(true);
		}

	}

	/**
	 * Return whether this unit wants to attack.
	 */
	@Basic
	@Raw
	private boolean getWantToAttack() {
		return this.wantToAttack;
	}

	/**
	 * Check whether the given wanttoattack is a valid wanttoattack for any
	 * unit.
	 * 
	 * @param wanttoattack
	 *            The wanttoattack to check.
	 * @return | result == true if wanttoattack is true or false
	 */
	private static boolean isValidWantToAttack(boolean wanttoattack) {
		if (wanttoattack == true || wanttoattack == false) {
			return true;
		}
		return false;
	}

	/**
	 * Set the wanttoattack of this unit to the given wanttoattack.
	 * 
	 * @param wanttoattack
	 *            The new wanttoattack for this unit.
	 * @post The wanttoattack of this new unit is equal to the given
	 *       wanttoattack. | new.getWantToAttack() == wanttoattack
	 * @throws IllegalArgumentException
	 *             The given wanttoattack is not a valid wanttoattack for any
	 *             unit. | ! isValidWantToAttack(getWantToAttack())
	 */
	@Raw
	private void setWantToAttack(boolean wanttoattack) throws IllegalArgumentException {
		if (!isValidWantToAttack(wanttoattack))
			throw new IllegalArgumentException();
		this.wantToAttack = wanttoattack;
	}

	/**
	 * Variable registering the wanttoattack of this unit.
	 */
	private boolean wantToAttack = false;

	/**
	 * Return the defender of this unit.
	 */
	@Basic
	@Raw
	private Unit getDefender() {
		return this.defender;
	}

	/**
	 * Check whether the given defender is a valid defender for any unit.
	 * 
	 * @param defender
	 *            The defender to check.
	 * @return | result == true if the attacker and defender belong to different
	 *         factions
	 */
	private static boolean isValidDefender(Unit attacker, Unit defender) {
		if (attacker.getFaction() != defender.getFaction()) {
			return true;
		}
		return false;
	}

	/**
	 * Set the defender of this unit to the given defender.
	 * 
	 * @param defender
	 *            The new defender of this unit's attack.
	 * @post The defender of this new unit's attack is equal to the given
	 *       defender. | new.getDefender() == defender
	 * @throws IllegalArgumentException
	 *             The given defender is not a valid defender for any unit. |
	 *             !isValidDefender(getDefender())
	 */
	@Raw
	private void setDefender(Unit defender) throws IllegalArgumentException {
		if (!isValidDefender(this, defender))
			throw new IllegalArgumentException();
		this.defender = defender;
	}

	/**
	 * Variable registering the defender during an attack of this unit.
	 */
	private Unit defender;

	/**
	 * When the attacker has been attacking for 1 second, the defender tries to
	 * defend himself.
	 * 
	 * @param dt
	 *            The period of time
	 * @param defender
	 *            The unit that defends
	 */
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

	/**
	 * The orientation of the attacker and defender gets calculated to face each
	 * other
	 * 
	 * @param attackerpos
	 *            The position of the attacker
	 * @param defenderpos
	 *            The position of the defender
	 * @param defender
	 *            The unit that defends
	 * @post The attacker faces the defender.
	 * @post The defender faces the attacker.
	 */
	private void setAttackOrientation(double[] attackerpos, double[] defenderpos, Unit defender) {

		this.setOrientation((float) Math.atan2((defenderpos[1] - attackerpos[1]), (defenderpos[0] - attackerpos[0])));
		defender.setOrientation(
				(float) Math.atan2((attackerpos[1] - defenderpos[1]), (attackerpos[0] - defenderpos[0])));
	}

	/**
	 * The attacked unit tries to defend an attack dodging or blocking
	 * 
	 * @param attacker
	 */
	private void defend(Unit attacker) {
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

	/**
	 * The unit moves to a random valid adjacent cube on the same z-level
	 */
	private void dodge() {// TODO dit kan eventueel nog aangepast worden als
							// blijkt dat ge naar achter moet gaan ipv random
							// beschikbare plek
		System.out.println("dodge");
		Random random = new Random();
		int xdirection = random.nextInt(3) - 1;
		int ydirection = random.nextInt(3) - 1;
		double[] oldPosition = this.getPosition();
		moveToAdjacent(xdirection, ydirection, 0);
		if (oldPosition == this.getPosition()) {
			dodge();
		}
	}

	/**
	 * Lower the hitpoints of the attacked unit
	 * 
	 * @param attacker
	 *            The unit that attacks
	 * @post The hitpoints of the attacked unit are lowered by a tenth of the
	 *       attacker's strength |new.getHitpoints()= this.getHitpoints() -
	 *       (int) (attacker.getStrength() / 10
	 */
	public void takeDamage(Unit attacker) {
		System.out.println("take damage");
		this.setHitpoints(this.getHitpoints() - (int) (attacker.getStrength() / 10));
		System.out.println("hitpoints: " + this.getHitpoints());
	}

	/**
	 * Calculate the dodging probability
	 * 
	 * @param attacker
	 *            The unit that attacks
	 * @param defender
	 *            The unit that defends
	 * @return The the probability that the defender dodges the attack | result
	 *         == 0.20 * defender.getAgility() / attacker.getAgility()
	 */
	private double getDodgeProb(Unit attacker, Unit defender) {
		return 0.20 * defender.getAgility() / attacker.getAgility();
	}

	/**
	 * Calculate the blocking probability
	 * 
	 * @param attacker
	 *            The unit that attacks
	 * @param defender
	 *            The unit that defends
	 * @return The the probability that the defender blocks the attack | result
	 *         == 0.25 * (defender.getStrength() + defender.getAgility()) | /
	 *         (attacker.getStrength() + attacker.getAgility())
	 */
	private double getBlockProb(Unit attacker, Unit defender) {
		return 0.25 * (defender.getStrength() + defender.getAgility())
				/ (attacker.getStrength() + attacker.getAgility());
	}

	/**
	 * Return the attack time of this unit.
	 */
	@Basic
	@Raw
	private float getAttackTime() {
		return this.attackTime;
	}

	/**
	 * Check whether the given attack time is a valid attack time for any unit.
	 * 
	 * @param attack
	 *            time The attack time to check.
	 * @return | result == true if attackTime <= 1
	 */
	private static boolean isValidAttackTime(float attackTime) {
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
	private void setAttackTime(float attackTime) throws IllegalArgumentException {
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
	private float getRestTime() {
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
	private static boolean isValidRestTime(float restTime, int toughness) {
		if (restTime <= (40 / (float) toughness)) {
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
	private void setRestTime(float restTime) throws IllegalArgumentException {
		if (!isValidRestTime(restTime, this.getToughness()))
			throw new IllegalArgumentException();
		this.restTime = restTime;
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
	private static boolean isValidExperience(int experience) {
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
	 * @post Per 10 experience points the unit gains 1 strength, agility or
	 *       toughness.
	 * @throws IllegalArgumentException
	 *             The given experience is not a valid experience for any unit.
	 *             | ! isValidExperience(getExperience())
	 */
	@Raw
	public void setExperience(int experience) throws IllegalArgumentException {
		if (!isValidExperience(experience))
			throw new IllegalArgumentException();
		if (this.getExperience() != experience) {
			int newExperience = experience - this.getExperience();
			int i = 0;
			while (i < newExperience) {
				this.experience++;
				if (this.getExperience() % 10 == 0) {
					int rand = Helper.randInt(0, 2);
					if (rand == 0) {
						this.setStrength(this.getStrength() + 1);
					}
					if (rand == 1) {
						this.setAgility(this.getAgility() + 1);
					}
					if (rand == 2) {
						this.setToughness(this.getToughness() + 1);
					}
				}
				i++;
			}
		}
	}

	/**
	 * Variable registering the experience of this unit.
	 */
	private int experience;

	/**
	 * Terminate this unit.
	 *
	 * @post This unit is terminated. | new.isTerminated()
	 * @post The unit drops the item he is carrying.
	 * @post If the unit belonged to a world, the unit is removed from that
	 *       world.
	 * @post If the unit belonged to a faction, the unit is removed from that
	 *       faction.
	 */
	public void terminate() {
		System.out.println("terminate");
		this.drop();
		if (this.getWorld() != null) {
			this.getWorld().removeUnit(this);
		}
		if (this.getFaction() != null) {
			this.getFaction().removeUnit(this);
			this.setFaction(null);
		}
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

	// public World getWorld() {
	// try {
	// return this.getFaction().getWorld();
	// } catch (NullPointerException e) {
	// return null;
	// }
	// }

	/**
	 * Return the world of this unit.
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
	 * @return | result == true if the world isn't terminated
	 */
	private static boolean isValidWorld(World world) {
		if (!world.getIsTerminated())
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
	private static boolean isValidFaction(Faction faction) {
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

	/**
	 * Return the carryingBoulder of this unit.
	 */
	@Basic
	@Raw
	public boolean getCarryingBoulder() {
		return this.carryingBoulder;
	}

	/**
	 * Check whether the given carryingBoulder is a valid carryingBoulder for
	 * any unit.
	 * 
	 * @param carryingBoulder
	 *            The carryingBoulder to check.
	 * @return | result == true if carryingBoulder is either true or false
	 */
	private static boolean isValidCarryingBoulder(boolean carryingBoulder) {
		if (carryingBoulder == true || carryingBoulder == false) {
			return true;
		}
		return false;
	}

	/**
	 * Set the carryingBoulder of this unit to the given carryingBoulder.
	 * 
	 * @param carryingBoulder
	 *            The new carryingBoulder for this unit.
	 * @post The carryingBoulder of this new unit is equal to the given
	 *       carryingBoulder. | new.getCarryingBoulder() == carryingBoulder
	 * @throws IllegalArgumentException
	 *             The given carryingBoulder is not a valid carryingBoulder for
	 *             any unit. | ! isValidCarryingBoulder(getCarryingBoulder())
	 */
	@Raw
	public void setCarryingBoulder(boolean carryingBoulder) throws IllegalArgumentException {
		if (!isValidCarryingBoulder(carryingBoulder))
			throw new IllegalArgumentException();
		this.carryingBoulder = carryingBoulder;
	}

	/**
	 * Variable registering the carryingBoulder of this unit.
	 */
	private boolean carryingBoulder;

	/**
	 * Return the carryingLog of this unit.
	 */
	@Basic
	@Raw
	public boolean getCarryingLog() {
		return this.carryingLog;
	}

	/**
	 * Check whether the given carryingLog is a valid carryingLog for any unit.
	 * 
	 * @param carryingLog
	 *            The carryingLog to check.
	 * @return | result == true if carryingLog is either true or false
	 */
	private static boolean isValidCarryingLog(boolean carryingLog) {
		if (carryingLog == true || carryingLog == false) {
			return true;
		}
		return false;
	}

	/**
	 * Set the carryingLog of this unit to the given carryingLog.
	 * 
	 * @param carryingLog
	 *            The new carryingLog for this unit.
	 * @post The carryingLog of this new unit is equal to the given carryingLog.
	 *       | new.getCarryingLog() == carryingLog
	 * @throws IllegalArgumentException
	 *             The given carryingLog is not a valid carryingLog for any
	 *             unit. | ! isValidCarryingLog(getCarryingLog())
	 */
	@Raw
	public void setCarryingLog(boolean carryingLog) throws IllegalArgumentException {
		if (!isValidCarryingLog(carryingLog))
			throw new IllegalArgumentException();
		this.carryingLog = carryingLog;
	}

	/**
	 * Variable registering the carryingLog of this unit.
	 */
	private boolean carryingLog;

	/**
	 * Return the isFollowing of this unit.
	 */
	@Basic
	@Raw
	public boolean getIsFollowing() {
		return this.isFollowing;
	}

	/**
	 * Set the isFollowing of this unit to the given isFollowing.
	 * 
	 * @param isFollowing
	 *            The new isFollowing for this unit.
	 * @post The isFollowing of this new unit is equal to the given isFollowing.
	 *       | new.getIsFollowing() == isFollowing
	 */
	@Raw
	private void setIsFollowing(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}

	/**
	 * Variable registering the isFollowing of this unit.
	 */
	private boolean isFollowing;

	/**
	 * Return the leader of this unit.
	 */
	@Basic
	@Raw
	private Unit getLeader() {
		return this.leader;
	}

	/**
	 * Check whether the given leader is a valid leader for any unit.
	 * 
	 * @param leader
	 *            The leader to check.
	 * @return | result ==
	 */
	private static boolean isValidLeader(Unit leader) {
		return true;
	}

	/**
	 * Set the leader of this unit to the given leader.
	 * 
	 * @param leader
	 *            The new leader for this unit.
	 * @post The leader of this new unit is equal to the given leader. |
	 *       new.getLeader() == leader
	 * @throws IllegalArgumentException
	 *             The given leader is not a valid leader for any unit. | !
	 *             isValidLeader(getLeader())
	 */
	@Raw
	private void setLeader(Unit leader) throws IllegalArgumentException {
		if (!isValidLeader(leader))
			throw new IllegalArgumentException();
		this.leader = leader;
	}

	/**
	 * Variable registering the leader of this unit.
	 */
	private Unit leader;

	public void followUnit(Unit unit) {
		this.setIsFollowing(true);
		this.setLeader(unit);
		boolean arrived = false;
		if (Helper.getIsSamePosition(this.getPosition(), unit.getPosition()) || unit.isTerminated()) {
			System.out.println("Follow terminate");
			this.setIsFollowing(false);
			this.setIsMoving(false);
			arrived = true;
		} else {
			for (int[] neighbour : this.getNeighbouringCubes(Helper.doubleArrayToIntArray(this.getPosition()))) {
				if (Helper.getIsSamePosition(neighbour, Helper.doubleArrayToIntArray(this.getPosition()))) {
					this.setIsFollowing(false);
					this.setIsMoving(false);
					arrived = true;
					break;
				}
			}
		}
		if (!arrived) {
			this.moveTo(Helper.doubleArrayToIntArray(unit.getPosition()));
		}

	}

	/**
	 * Return the task of this unit.
	 */
	@Basic
	@Raw
	public Task getTask() {
		return this.task;
	}

	/**
	 * Check whether the given task is a valid task for any unit.
	 * 
	 * @param task
	 *            The task to check.
	 * @return | result ==
	 */
	public static boolean isValidTask(Task task) {
		return true;
	}

	/**
	 * Set the task of this unit to the given task.
	 * 
	 * @param task
	 *            The new task for this unit.
	 * @post The task of this new unit is equal to the given task. |
	 *       new.getTask() == task
	 * @throws IllegalArgumentException
	 *             The given task is not a valid task for any unit. | !
	 *             isValidTask(getTask())
	 */
	@Raw
	public void setTask(Task task) throws IllegalArgumentException {
		if (!isValidTask(task))
			throw new IllegalArgumentException();
		this.task = task;
	}

	/**
	 * Variable registering the task of this unit.
	 */
	private Task task = null;
}
