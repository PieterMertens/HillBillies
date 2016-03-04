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
	int defaultWeight = (strength + agility)/2 //FIXME na de kluut
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



}
