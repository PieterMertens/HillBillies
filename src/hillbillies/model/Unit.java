package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

public class Unit {
	
	
	
	
	
	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The position of each unit must be a valid position for any
	 *         unit.
	 *       | isValidPosition(getPosition())
	 */


/**
 * Initialize this new unit with given position.
 *
 * @param  position
 *         The position for this new unit.
 * @effect The position of this new unit is set to
 *         the given position.
 *       | this.setPosition(position)
 */
public Unit(double[] position)
		throws IllegalArgumentException {
	this.setPosition(position);
}


/**
 * Return the position of this unit.
 */
@Basic @Raw
public double[] getPosition() {
	return this.position;
}

/**
 * Check whether the given position is a valid position for
 * any unit.
 *  
 * @param  position
 *         The position to check.
 * @return 
 *       | result == 
*/
public static boolean isValidPosition(double[] position) {
	
	Double lowerlimit = 0d;
	Double upperlimit = 50d;

	if (position.length==3) {

		for(int k = 0; k < position.length; k++){
			if (position[k] > upperlimit && position[k] <= lowerlimit)
				return false;
		}
		return true;
	}

	return false;
}

/**
 * Set the position of this unit to the given position.
 * 
 * @param  position
 *         The new position for this unit.
 * @post   The position of this new unit is equal to
 *         the given position.
 *       | new.getPosition() == position
 * @throws IllegalArgumentException
 *         The given position is not a valid position for any
 *         unit.
 *       | ! isValidPosition(getPosition())
 */
@Raw
public void setPosition(double[] position) 
		throws IllegalArgumentException {
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	this.position = position;
}

/**
 * Variable registering the position of this unit.
 */
private double[] position;







/** TO BE ADDED TO CLASS HEADING
 * @invar  The name of each unit must be a valid name for any
 *         unit.
 *       | isValidName(getName())
 */


/**
 * Initialize this new unit with given name.
 *
 * @param  name
 *         The name for this new unit.
 * @effect The name of this new unit is set to
 *         the given name.
 *       | this.setName(name)
 */
public Unit(String name)
		throws IllegalArgumentException {
	this.setName(name);
}


/**
 * Return the name of this unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
	
	
}

/**
 * Check whether the given name is a valid name for
 * any unit.
 *  
 * @param  name
 *         The name to check.
 * @return 
 *       | result == 
*/
public static boolean isValidName(String name) {
	
	if (name.matches("[A-Za-z.\\s\'\"]")&&name.matches("^[A-Z]")&&name.length()>1)
		return true;
	
	return false;
}

/**
 * Set the name of this unit to the given name.
 * 
 * @param  name
 *         The new name for this unit.
 * @post   The name of this new unit is equal to
 *         the given name.
 *       | new.getName() == name
 * @throws IllegalArgumentException
 *         The given name is not a valid name for any
 *         unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) 
		throws IllegalArgumentException {
	if (! isValidName(name))
		throw new IllegalArgumentException();
	this.name = name;
}

/**
 * Variable registering the name of this unit.
 */
private String name;




/**
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | isValidWeight(getWeight())
 */

/**
 * Initialize this new unit with given weight.
 * 
 * @param  weight
 *         The weight for this new unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight. Otherwise, the weight of this new unit is equal
 *         to defaultWeight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == defaultWeight
 */
public Unit(int weight) {
	int defaultWeight = 25 + (int)(Math.random() * (76)); //(strength + agility)/2
	if (! isValidWeight(weight))
		weight = defaultWeight;
	setWeight(weight);
}

/**
 * Return the weight of this unit.
 */
@Basic @Raw
public int getWeight() {
	return this.weight;
}

/**
 * Check whether the given weight is a valid weight for
 * any unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return 
 *       | result == //TODO
*/
public static boolean isValidWeight(int weight) {
	if (weight >= 1 && weight <= 200 && weight >= (this.getStrength() + this.getAgility())/2)
		return true;
	return false;
}

/**
 * Set the weight of this unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight))
		this.weight = weight;
}

/**
 * Variable registering the weight of this unit.
 */
private int weight;





/**
 * @invar  The strength of each unit must be a valid strength for any
 *         unit.
 *       | isValidStrength(getStrength())
 */

/**
 * Initialize this new unit with given strength.
 * 
 * @param  strength
 *         The strength for this new unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength. Otherwise, the strength of this new unit is equal
 *         to 25 + (int)(Math.random() * (76)).
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 25 + (int)(Math.random() * (76))
 */
public Unit(int strength) {
	if (! isValidStrength(strength))
		strength = 25 + (int)(Math.random() * (76));
	setStrength(strength);
}

/**
 * Return the strength of this unit.
 */
@Basic @Raw
public int getStrength() {
	return this.strength;
}

/**
 * Check whether the given strength is a valid strength for
 * any unit.
 *  
 * @param  strength
 *         The strength to check.
 * @return 
 *       | result == 
*/
public static boolean isValidStrength(int strength) {
	if (strength >= 1 && strength <= 200)
		return true;
	return false;
}

/**
 * Set the strength of this unit to the given strength.
 * 
 * @param  strength
 *         The new strength for this unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
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
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 */

/**
 * Initialize this new unit with given agility.
 * 
 * @param  agility
 *         The agility for this new unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility. Otherwise, the agility of this new unit is equal
 *         to default_value_Java.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 *       |   else new.getAgility() == default_value_Java
 */
public Unit(int agility) {
	if (! isValidAgility(agility))
		agility = 25 + (int)(Math.random() * (76));
	setAgility(agility);
}

/**
 * Return the agility of this unit.
 */
@Basic @Raw
public int getAgility() {
	return this.agility;
}

/**
 * Check whether the given agility is a valid agility for
 * any unit.
 *  
 * @param  agility
 *         The agility to check.
 * @return 
 *       | result == 
*/
public static boolean isValidAgility(int agility) {
	if (agility >= 1 && agility <= 200)
		return true;
	return false;
}

/**
 * Set the agility of this unit to the given agility.
 * 
 * @param  agility
 *         The new agility for this unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
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
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 */

/**
 * Initialize this new unit with given toughness.
 * 
 * @param  toughness
 *         The toughness for this new unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness. Otherwise, the toughness of this new unit is equal
 *         to 25 + (int)(Math.random() * (76)).
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 *       |   else new.getToughness() == 25 + (int)(Math.random() * (76))
 */
public Unit(int toughness) {
	if (! isValidToughness(toughness))
		toughness = 25 + (int)(Math.random() * (76)); //TODO kan hier nu geen 101 uitkomen???
	setToughness(toughness);
}

/**
 * Return the toughness of this unit.
 */
@Basic @Raw
public int getToughness() {
	return this.toughness;
}

/**
 * Check whether the given toughness is a valid toughness for
 * any unit.
 *  
 * @param  toughness
 *         The toughness to check.
 * @return 
 *       | result == 
*/
public static boolean isValidToughness(int toughness) {
	if (toughness >= 1 && toughness <= 200)
		return true;
	return false;
}

/**
 * Set the toughness of this unit to the given toughness.
 * 
 * @param  toughness
 *         The new toughness for this unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
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
 * @invar  The hitpoints of each unit must be a valid hitpoints for any
 *         unit.
 *       | isValidHitpoints(getHitpoints())
 */

/**
 * Initialize this new unit with given hitpoints.
 * 
 * @param  hitpoints
 *         The hitpoints for this new unit.
 * @pre    The given hitpoints must be a valid hitpoints for any unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this new unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 */
public Unit(int hitpoints) {
	this.setHitpoints(hitpoints);
}

/**
 * Return the hitpoints of this unit.
 */
@Basic @Raw
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Check whether the given hitpoints is a valid hitpoints for
 * any unit.
 *  
 * @param  hitpoints
 *         The hitpoints to check.
 * @return 
 *       | result == //TODO
*/
public static boolean isValidHitpoints(int hitpoints) {
	return false;
}

/**
 * Set the hitpoints of this unit to the given hitpoints.
 * 
 * @param  hitpoints
 *         The new hitpoints for this unit.
 * @pre    The given hitpoints must be a valid hitpoints for any
 *         unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 */
@Raw
public void setHitpoints(int hitpoints) {
	assert isValidHitpoints(hitpoints);
	this.hitpoints = hitpoints;
}

/**
 * Variable registering the hitpoints of this unit.
 */
private int hitpoints;




/**
 * @invar  The staminapoints of each unit must be a valid staminapoints for any
 *         unit.
 *       | isValidStaminapoints(getStaminapoints())
 */

/**
 * Initialize this new unit with given staminapoints.
 * 
 * @param  staminapoints
 *         The staminapoints for this new unit.
 * @pre    The given staminapoints must be a valid staminapoints for any unit.
 *       | isValidStaminapoints(staminapoints)
 * @post   The staminapoints of this new unit is equal to the given
 *         staminapoints.
 *       | new.getStaminapoints() == staminapoints
 */
public Unit(int staminapoints) {
	this.setStaminapoints(staminapoints);
}

/**
 * Return the staminapoints of this unit.
 */
@Basic @Raw
public int getStaminapoints() {
	return this.staminapoints;
}

/**
 * Check whether the given staminapoints is a valid staminapoints for
 * any unit.
 *  
 * @param  staminapoints
 *         The staminapoints to check.
 * @return 
 *       | result == //TODO ...
*/
public static boolean isValidStaminapoints(int staminapoints) {
	return false;
}

/**
 * Set the staminapoints of this unit to the given staminapoints.
 * 
 * @param  staminapoints
 *         The new staminapoints for this unit.
 * @pre    The given staminapoints must be a valid staminapoints for any
 *         unit.
 *       | isValidStaminapoints(staminapoints)
 * @post   The staminapoints of this unit is equal to the given
 *         staminapoints.
 *       | new.getStaminapoints() == staminapoints
 */
@Raw
public void setStaminapoints(int staminapoints) {
	assert isValidStaminapoints(staminapoints);
	this.staminapoints = staminapoints;
}

/**
 * Variable registering the staminapoints of this unit.
 */
private int staminapoints;







}
