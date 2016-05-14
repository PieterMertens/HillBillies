package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar The task list of each Scheduler must be a valid task list for any
 *        Scheduler. | isValidTaskList(getTaskList())
 */
public class Scheduler {

	/**
	 * Initialize this new Scheduler.
	 *
	 */
	public Scheduler() {// TODO wordt deze gebruikt?
	}

	/**
	 * Return the task list of this Scheduler.
	 */
	@Basic
	@Raw
	public List<Task> getTaskList() {
		return this.taskList;
	}

	/**
	 * Variable registering the task list of this Scheduler.
	 */
	private List<Task> taskList = new ArrayList<>();

	public void addTask(Task task) {
		getTaskList().add(task);
		task.addScheduler(this);
	}

	public void addTasks(Collection<Task> tasks) {
		for (Task task : tasks) {
			addTask(task);
		}
	}

	public void removeTask(Task task) {
		if (task.getAssignedUnit() != null) {
			stopTaskOfUnit(task.getAssignedUnit());
		}
		getTaskList().remove(task);
		task.removeScheduler(this);
	}

	public void removeTasks(Collection<Task> tasks) {
		for (Task task : tasks) {
			removeTask(task);
		}
	}

	public void replaceTask(Task original, Task replacement) {

		removeTask(original);
		addTask(replacement);

	}
	public void stopTaskOfUnit(Unit unit) {
		// TODO hier checken of task deel is vn scheduler? -> nrml wel want is
		// gechecked bij toekenning, en als het niet zo is is het stopzetten van
		// task niet erg?

		unit.getAssignedTask().setAssignedUnit(null);
		unit.setAssignedTask(null);

	}

	public boolean areTasksPartOf(Collection<Task> tasks) {
		for (Task task : tasks) {
			if (!getTaskList().contains(task))
				return false;
		}
		return true;
	}

	public Task getUnexecutedTaskWithHighestPriority() throws NoSuchElementException {

		Iterator<Task> iterator = getAllTasksIterator();
		while (iterator.hasNext()) {
			Task task = iterator.next();
			if (!task.isBeingExecuted()) {
				return task;
			}
		}
		throw new NoSuchElementException("There's no unexecuted task left.");

	}

	public Iterator<Task> getAllTasksIterator() throws NoSuchElementException {
		System.out.println("--- test vn tasklist sizes " + getTaskList().size());

		Iterator<Task> iterator = new Iterator<Task>() {

			List<Task> remainingTaskList = getTaskList();

			@Override
			public boolean hasNext() {
				return remainingTaskList.size() > 0;
			}

			@Override
			public Task next() {
				int highestPriority = Integer.MIN_VALUE;
				Task highestPriorityTask = null;
				if (hasNext()) {
					for (Task task : remainingTaskList) {
						if (task.getPriority() > highestPriority) {
							highestPriority = task.getPriority();
							highestPriorityTask = task;
						}
					}
					remainingTaskList.remove(highestPriorityTask);
					return highestPriorityTask;
				} else {
					throw new NoSuchElementException("There's no task left.");
				}
			}
		};

		System.out.println("- test vn tasklist sizes " + getTaskList().size());
		return iterator;
	}

	public Set<Task> getAllTasksSatisfying(Predicate<Task> condition) {
		return getTaskList().stream().filter(condition).collect(Collectors.toSet());
	}

	public Set<Task> getAllTasksWithPositivePriorrity() {
		return getAllTasksSatisfying(t -> t.getPriority() > 0);
	}

	public Set<Task> getAllTasksBeingExecuted() {
		return getAllTasksSatisfying(t -> t.isBeingExecuted());
	}

	public void scheduleTaskForUnit(Unit unit, Task task) throws IllegalStateException {
		if (getTaskList().contains(task) && !task.isBeingExecuted()) {
			unit.setAssignedTask(task);
			task.setAssignedUnit(unit);
		} else {
			throw new IllegalStateException();
		}
	}

	public void scheduleUnexecutedHighestPriorityTaskForUnit(Unit unit) throws IllegalStateException {
		scheduleTaskForUnit(unit, getUnexecutedTaskWithHighestPriority());
	}



}
