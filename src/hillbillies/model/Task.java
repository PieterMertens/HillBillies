package hillbillies.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Task {

	/**
	 * TO BE ADDED TO CLASS HEADING
	 * 
	 * @invar Each Task can have its name as name. |
	 *        canHaveAsName(this.getName())
	 */

	/**
	 * Initialize this new Task with given name.
	 * 
	 * @param name
	 *            The name for this new Task.
	 * @post The name of this new Task is equal to the given name. |
	 *       new.getName() == name
	 * @throws IllegalArgumentException
	 *             This new Task cannot have the given name as its name. | !
	 *             canHaveAsName(this.getName())
	 * 
	 *             Initialize this new Task with given priority.
	 *
	 * @param priority
	 *            The priority for this new Task.
	 * @effect The priority of this new Task is set to the given priority. |
	 *         this.setPriority(priority)
	 */
	public Task(String name, int priority) throws IllegalArgumentException {
		if (!canHaveAsName(name))
			throw new IllegalArgumentException();
		this.name = name;
		this.setPriority(priority);
	}

	/**
	 * Return the name of this Task.
	 */
	@Basic
	@Raw
	@Immutable
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether this Task can have the given name as its name.
	 * 
	 * @param name
	 *            The name to check.
	 * @return | result ==
	 */
	@Raw
	public boolean canHaveAsName(String name) {
		return (name != null);
	}

	/**
	 * Variable registering the name of this Task.
	 */
	private final String name;

	/**
	 * TO BE ADDED TO CLASS HEADING
	 * 
	 * @invar The priority of each Task must be a valid priority for any Task. |
	 *        isValidPriority(getPriority())
	 */

	/**
	 * Return the priority of this Task.
	 */
	@Basic
	@Raw
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Check whether the given priority is a valid priority for any Task.
	 * 
	 * @param priority
	 *            The priority to check.
	 * @return | result ==
	 */
	public static boolean isValidPriority(int priority) {
		return true;
	}

	/**
	 * Set the priority of this Task to the given priority.
	 * 
	 * @param priority
	 *            The new priority for this Task.
	 * @post The priority of this new Task is equal to the given priority. |
	 *       new.getPriority() == priority
	 * @throws IllegalArgumentException
	 *             The given priority is not a valid priority for any Task. | !
	 *             isValidPriority(getPriority())
	 */
	@Raw
	public void setPriority(int priority) throws IllegalArgumentException {
		if (!isValidPriority(priority))
			throw new IllegalArgumentException();
		this.priority = priority;
	}

	/**
	 * Variable registering the priority of this Task.
	 */
	private int priority;

	/**
	 * TO BE ADDED TO CLASS HEADING
	 * 
	 * @invar Each Task can have its activities as activities. |
	 *        canHaveAsActivities(this.getActivities())
	 */

	/**
	 * Initialize this new Task with given activities.
	 * 
	 * @param activities
	 *            The activities for this new Task.
	 * @post The activities of this new Task is equal to the given activities. |
	 *       new.getActivities() == activities
	 * @throws IllegalArgumentException
	 *             This new Task cannot have the given activities as its
	 *             activities. | ! canHaveAsActivities(this.getActivities())
	 */
	public Task(List<> activities) throws IllegalArgumentException {
		if (!canHaveAsActivities(activities))
			throw new IllegalArgumentException();
		this.activities = activities;
	}

	/**
	 * Return the activities of this Task.
	 */
	@Basic
	@Raw
	@Immutable
	public List<> getActivities() {
		return this.activities;
	}

	/**
	 * Check whether this Task can have the given activities as its activities.
	 * 
	 * @param activities
	 *            The activities to check.
	 * @return | result ==
	 */
	@Raw
	public boolean canHaveAsActivities(List<> activities) {
		return false;
	}

	/**
	 * Variable registering the activities of this Task.
	 */
	private final List<> activities;

	/**
	 * TO BE ADDED TO CLASS HEADING
	 * 
	 * @invar The assigned unit of each Task must be a valid assigned unit for
	 *        any Task. | isValidAssignedUnit(getAssignedUnit())
	 */

	/**
	 * Return the assigned unit of this Task.
	 */
	@Basic
	@Raw
	public Unit getAssignedUnit() {
		return this.assignedUnit;
	}

	/**
	 * Check whether the given assigned unit is a valid assigned unit for any
	 * Task.
	 * 
	 * @param assigned
	 *            unit The assigned unit to check.
	 * @return | result ==
	 */
	public static boolean isValidAssignedUnit(Unit assignedUnit) {
		return false;
	}

	/**
	 * Set the assigned unit of this Task to the given assigned unit.
	 * 
	 * @param assignedUnit
	 *            The new assigned unit for this Task.
	 * @post The assigned unit of this new Task is equal to the given assigned
	 *       unit. | new.getAssignedUnit() == AssignedUnit
	 * @throws IllegalArgumentException
	 *             The given assigned unit is not a valid assigned unit for any
	 *             Task. | ! isValidAssignedUnit(getAssignedUnit())
	 */
	@Raw
	public void setAssignedUnit(Unit assignedUnit) throws IllegalArgumentException {
		if (!isValidAssignedUnit(assignedUnit))
			throw new IllegalArgumentException();
		this.assignedUnit = assignedUnit;
	}

	/**
	 * Variable registering the assigned unit of this Task.
	 */
	private Unit assignedUnit = null;

	public boolean isBeingExecuted() {
		return getAssignedUnit() != null;
	}

	/**
	 * TO BE ADDED TO CLASS HEADING
	 * 
	 * @invar The schedulerSet of each task must be a valid schedulerSet for any
	 *        task. | isValidSchedulerSet(getSchedulerSet())
	 */

	/**
	 * Return the schedulerSet of this task.
	 */
	@Basic
	@Raw
	public Set<Scheduler> getSchedulerSet() {
		return this.schedulerSet;
	}

	/**
	 * Check whether the given schedulerSet is a valid schedulerSet for any
	 * task.
	 * 
	 * @param schedulerSet
	 *            The schedulerSet to check.
	 * @return | result ==
	 */
	public static boolean isValidSchedulerSet(Set<Scheduler> schedulerSet) {
		return (schedulerSet != null);
	}

	/**
	 * Set the schedulerSet of this task to the given schedulerSet.
	 * 
	 * @param schedulerSet
	 *            The new schedulerSet for this task.
	 * @post The schedulerSet of this new task is equal to the given
	 *       schedulerSet. | new.getSchedulerSet() == schedulerSet
	 * @throws IllegalArgumentException
	 *             The given schedulerSet is not a valid schedulerSet for any
	 *             task. | ! isValidSchedulerSet(getSchedulerSet())
	 */
	@Raw
	public void setSchedulerSet(Set<Scheduler> schedulerSet) throws IllegalArgumentException {
		if (!isValidSchedulerSet(schedulerSet))
			throw new IllegalArgumentException();
		this.schedulerSet = schedulerSet;
	}

	/**
	 * Variable registering the schedulerSet of this task.
	 */
	private Set<Scheduler> schedulerSet = new HashSet<>();

	public void addScheduler(Scheduler scheduler) {
		if (!getSchedulerSet().contains(scheduler)) {
			getSchedulerSet().add(scheduler);
		} else {
			throw new IllegalArgumentException("The scheduler is already in the schedulerset");
		}
	}

	public void removeScheduler(Scheduler scheduler) {
		getSchedulerSet().remove(scheduler);
	}
}
