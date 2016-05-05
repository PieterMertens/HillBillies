package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

public class Task {

	
	/** TO BE ADDED TO CLASS HEADING
	 * @invar  Each Task can have its name as name.
	 *       | canHaveAsName(this.getName())
	 */

/**
 * Initialize this new Task with given name.
 * 
 * @param  name
 *         The name for this new Task.
 * @post   The name of this new Task is equal to the given
 *         name.
 *       | new.getName() == name
 * @throws IllegalArgumentException
 *         This new Task cannot have the given name as its name.
 *       | ! canHaveAsName(this.getName())
 */
public Task(String name) throws IllegalArgumentException {
	if (! canHaveAsName(name))
		throw new IllegalArgumentException();
	this.name = name;
}

/**
 * Return the name of this Task.
 */
@Basic @Raw @Immutable
public String getName() {
	return this.name;
}

/**
 * Check whether this Task can have the given name as its name.
 *  
 * @param  name
 *         The name to check.
 * @return 
 *       | result == 
*/
@Raw
public boolean canHaveAsName(String name) {
	return false;
}

/**
 * Variable registering the name of this Task.
 */
private final String name;


/** TO BE ADDED TO CLASS HEADING
 * @invar  The priority of each Task must be a valid priority for any
 *         Task.
 *       | isValidPriority(getPriority())
 */


/**
 * Initialize this new Task with given priority.
 *
 * @param  priority
 *         The priority for this new Task.
 * @effect The priority of this new Task is set to
 *         the given priority.
 *       | this.setPriority(priority)
 */
public Task(int priority)
		throws IllegalArgumentException {
	this.setPriority(priority);
}


/**
 * Return the priority of this Task.
 */
@Basic @Raw
public int getPriority() {
	return this.priority;
}

/**
 * Check whether the given priority is a valid priority for
 * any Task.
 *  
 * @param  priority
 *         The priority to check.
 * @return 
 *       | result == 
*/
public static boolean isValidPriority(int priority) {
	return false;
}

/**
 * Set the priority of this Task to the given priority.
 * 
 * @param  priority
 *         The new priority for this Task.
 * @post   The priority of this new Task is equal to
 *         the given priority.
 *       | new.getPriority() == priority
 * @throws IllegalArgumentException
 *         The given priority is not a valid priority for any
 *         Task.
 *       | ! isValidPriority(getPriority())
 */
@Raw
public void setPriority(int priority) 
		throws IllegalArgumentException {
	if (! isValidPriority(priority))
		throw new IllegalArgumentException();
	this.priority = priority;
}

/**
 * Variable registering the priority of this Task.
 */
private int priority;


	
}
