package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.statements.Statement;

/**
 * @invar Each Task can have its name as name. | canHaveAsName(this.getName())
 * @invar Each Task can have its activity as activity. |
 *        canHaveAsActivity(this.getActivity())
 * @invar Each task can have its selected position as selected position. |
 *        canHaveAsSelectedPosition(this.getSelectedPosition())
 * 
 * @author Pieter and Matthias
 * @version 1.0
 *
 */
public class Task {

	/**
	 * Initialize this new task with given name, priority, activity and selected
	 * position.
	 * 
	 * @param name
	 *            The name for this new task.
	 * @param priority
	 *            The priority for this new task.
	 * @param activity
	 *            The activity for this new task.
	 * @param selectedPosition
	 *            The position for this new task.
	 * @post The name of this new task is equal to the given name. |
	 *       new.getName() == name
	 * @post The activity of this new task is equal to the given activity. |
	 *       new.getActivity() == activity
	 * @post The selected position of this new task is equal to the given
	 *       selected position. | new.getSelectedPosition() == selectedPosition
	 * @effect The priority of this new Task is set to the given priority. |
	 *         this.setPriority(priority)
	 * @throws IllegalArgumentException
	 *             This new task cannot have the given name as its name. | !
	 *             canHaveAsName(this.getName())
	 * @throws IllegalArgumentException
	 *             This new task cannot have the given activity as its activity.
	 *             | ! canHaveAsActivity(this.getActivity())
	 * @throws IllegalArgumentException
	 *             This new task cannot have the given selected position as its
	 *             selected position. | !
	 *             canHaveAsSelectedPosition(this.getSelectedPosition())
	 */
	public Task(String name, int priority, Statement activity, int[] selectedPosition) throws IllegalArgumentException {
		if (!canHaveAsName(name))
			throw new IllegalArgumentException();
		this.name = name;

		this.setPriority(priority);

		if (!canHaveAsActivity(activity))
			throw new IllegalArgumentException();
		this.activity = activity;
		getActivity().setTask(this);

		if (!canHaveAsSelectedPosition(selectedPosition))
			throw new IllegalArgumentException();
		this.selectedPosition = selectedPosition;
	}

	/**
	 * Return the name of this task.
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
	 * @return True if the given name is not null. | result == true if (name !=
	 *         null)
	 */
	@Raw
	private boolean canHaveAsName(String name) {
		return (name != null);
	}

	/**
	 * Variable registering the name of this task.
	 */
	private final String name;

	/**
	 * Return the priority of this Task.
	 */
	@Basic
	@Raw
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Set the priority of this task to the given priority.
	 * 
	 * @param priority
	 *            The new priority for this task.
	 * @post The priority of this new task is equal to the given priority. |
	 *       new.getPriority() == priority
	 */
	@Raw
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Variable registering the priority of this task.
	 */
	private int priority;

	/**
	 * Return the activity of this task.
	 */
	@Basic
	@Raw
	@Immutable
	public Statement getActivity() {
		return this.activity;
	}

	/**
	 * Check whether this task can have the given activity as its activity.
	 * 
	 * @param activity
	 *            The activity to check.
	 * @return | result == true if the activity is not null.
	 */
	@Raw
	private boolean canHaveAsActivity(Statement activity) {
		if (activity != null) {
			return true;
		}
		return false;
	}

	/**
	 * Variable registering the activity of this task.
	 */
	private final Statement activity;

	/**
	 * Return the selected position of this task.
	 */
	@Basic
	@Raw
	@Immutable
	public int[] getSelectedPosition() {
		return this.selectedPosition;
	}

	/**
	 * Check whether this task can have the given selected position as its
	 * selected position.
	 * 
	 * @param selectedPosition
	 *            The selected position to check.
	 * @return | result == true if the array contains 3 elements that are all
	 *         bigger than or equal to zero.
	 */
	@Raw
	private boolean canHaveAsSelectedPosition(int[] selectedPosition) {
		if (selectedPosition.length == 3 && selectedPosition[0] >= 0 && selectedPosition[1] >= 0
				&& selectedPosition[2] >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Variable registering the selected position of this task.
	 */
	private final int[] selectedPosition;

	/**
	 * Return the assigned unit of this task.
	 */
	@Basic
	@Raw
	public Unit getAssignedUnit() {
		return this.assignedUnit;
	}

	/**
	 * Check whether the given assigned unit is a valid assigned unit for any
	 * task.
	 * 
	 * @param assignedUnit
	 *            The unit to check.
	 * @return True if the unit has no task assigned. | result == true if
	 *         assignedUnit.getTask() == null
	 */
	public static boolean isValidAssignedUnit(Unit assignedUnit) {
		if (assignedUnit.getTask() == null) {
			return true;
		}
		return false;
	}

	/**
	 * Set the assigned unit of this task to the given assigned unit.
	 * 
	 * @param assignedUnit
	 *            The new assigned unit for this task.
	 * @post The assigned unit of this new task is equal to the given unit. |
	 *       new.getAssignedUnit() == AssignedUnit
	 * @throws IllegalArgumentException
	 *             The given unit is not a valid assigned unit for any task. | !
	 *             isValidAssignedUnit(getAssignedUnit())
	 */
	@Raw
	public void setAssignedUnit(Unit assignedUnit) throws IllegalArgumentException {
		if (!isValidAssignedUnit(assignedUnit))
			throw new IllegalArgumentException();
		this.assignedUnit = assignedUnit;
	}

	/**
	 * Variable registering the assigned unit of this task.
	 */
	private Unit assignedUnit = null;

	/**
	 * Return whether task has a unit assigned to it.
	 */
	public boolean isBeingExecuted() {
		return getAssignedUnit() != null;
	}

	/**
	 * Return the schedulerSet of this task.
	 */
	@Basic
	@Raw
	public Set<Scheduler> getSchedulerSet() {
		return this.schedulerSet;
	}

	/**
	 * Variable registering the scheduler set of this task.
	 */
	private Set<Scheduler> schedulerSet = new HashSet<>();

	/**
	 * Add the given scheduler to the task's scheduler set.
	 * 
	 * @param scheduler
	 *            The scheduler to add.
	 * @effect The scheduler is added to the task's scheduler set.
	 * @throws IllegalArgumentException
	 *             The given scheduler is already in the scheduler set.
	 */
	public void addScheduler(Scheduler scheduler) {
		if (!getSchedulerSet().contains(scheduler)) {
			getSchedulerSet().add(scheduler);
		} 
	}

	/**
	 * Remove the given scheduler from the task's scheduler set.
	 * 
	 * @param scheduler
	 *            The scheduler to remove.
	 * @throws IllegalArgumentException
	 *             The given scheduler is not in the scheduler set. |
	 *             !getSchedulerSet().contains(scheduler)
	 */
	public void removeScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if (!getSchedulerSet().contains(scheduler)) {
			throw new IllegalArgumentException("The scheduler is not in the scheduler set.");
		}
		getSchedulerSet().remove(scheduler);
	}

	/**
	 * Return whether the task's activity is executed.
	 * 
	 * @return result == getActivity().isExecuted()
	 */
	public boolean isExecuted() {
		return getActivity().isExecuted();
	}

	public boolean isWellFormed() {
		return (getActivity().getExpression() != null) ? (getActivity().isWellFormed())
				: (getActivity().isWellFormed() && getActivity().getExpression().isWellFormed());
	}
}
