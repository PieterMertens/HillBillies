package hillbillies.model.unit;

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
public Unit(Position position)
		throws IllegalArgumentException {
	this.setPosition(position);
}


/**
 * Return the position of this unit.
 */
@Basic @Raw
public Position getPosition() {
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
public static boolean isValidPosition(Position position) {
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
public void setPosition(Position position) 
		throws IllegalArgumentException {
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	this.position = position;
}

/**
 * Variable registering the position of this unit.
 */
private property_type position;

}
