package hillbillies.tests.Model;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.programs.TaskParser;

public class SchedulerTest {

	@Test
	public void testNewScheduler() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		World world = new World(types, new DefaultTerrainChangeListener());
		Faction faction = new Faction(world);
		Scheduler scheduler = new Scheduler(faction);

		Assert.assertTrue(faction.getScheduler()==scheduler);
	}
	
	@Test
	public void testSchedulerAddtask() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		Facade facade = new Facade();
		World world = new World(types, new DefaultTerrainChangeListener());
		Faction faction = new Faction(world);
		Scheduler scheduler = new Scheduler(faction);
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		scheduler.addTasks(tasks);
		Task task = tasks.get(0);

		Assert.assertTrue(task.getSchedulerSet().contains(scheduler));
		Assert.assertTrue(scheduler.getAllTasksIterator().next()==task);
	}
	
	@Test
	public void testSchedulerReplacetask() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		Facade facade = new Facade();
		World world = new World(types, new DefaultTerrainChangeListener());
		Faction faction = new Faction(world);
		Scheduler scheduler = new Scheduler(faction);
		List<Task> tasks1 = TaskParser.parseTasksFromString(
				"name: \"work task1\"\npriority: 3\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		scheduler.addTasks(tasks1);
		Task task1 = tasks1.get(0);
		List<Task> tasks2 = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		scheduler.addTasks(tasks2);
		Task task2 = tasks2.get(0);
		scheduler.replaceTask(task1, task2);
		

		Assert.assertFalse(task1.getSchedulerSet().contains(scheduler));
		Assert.assertTrue(scheduler.getAllTasksIterator().next()==task2);
	}
	
	

}
