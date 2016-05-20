package hillbillies.tests.Model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Task;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.programs.TaskParser;
import ogp.framework.util.ModelException;

public class TaskFactoryTest {
	
	private Facade facade;

	@Before
	public void setup() {
		this.facade = new Facade();
	}
	
	@Test
	public void testTaskFactoryValidMoveTo() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"MoveTo task\"\npriority: 1\nactivities: moveTo selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task is created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		
	}
	
	@Test
	public void testTaskFactoryInvalidExpressionMoveTo() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"moveTo task\"\npriority: 1\nactivities: moveTo this;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task isn't created
		assertNull(tasks);
		
	}

	@Test
	public void testTaskFactoryValidWork() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work log;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task is created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		
	}
	
	@Test
	public void testTaskFactoryInvalidExpressionWork() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"moveTo task\"\npriority: 1\nactivities: work this;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task isn't created
		assertNull(tasks);
		
	}
	
	@Test
	public void testTaskFactoryValidFollow() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"follow task\"\npriority: 1\nactivities: follow friend;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task is created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		
	}
	
	@Test
	public void testTaskFactoryInvalidExpressionFollow() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"follow task\"\npriority: 1\nactivities: follow selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task isn't created
		assertNull(tasks);
	}	
	
	@Test
	public void testTaskFactoryValidAttack() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"attack task\"\npriority: 1\nactivities: attack enemy;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task is created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		
	}
	
	@Test
	public void testTaskFactoryInvalidExpressionAttack() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"attack task\"\npriority: 1\nactivities: attack log;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task isn't created
		assertNull(tasks);
		
	}
	
	@Test
	public void testTaskFactoryValidIf() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"if task\"\npriority: 1\nactivities: if is_solid(selected) then work(selected); fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task is created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		
	}
	
	@Test
	public void testTaskFactoryInvalidExpressionIf() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"if task\"\npriority: 1\nactivities: if this then work(selected); fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task isn't created
		assertNull(tasks);
		
	}
	
	@Test
	public void testTaskFactoryValidWhile() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while task\"\npriority: 1\nactivities: while carries_item(this) do follow(friend); done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task is created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		
	}
	
	@Test
	public void testTaskFactoryInvalidExpressionWhile() throws ModelException {

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while task\"\npriority: 1\nactivities: while workshop do moveTo(boulder); done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		// task isn't created
		assertNull(tasks);
		
	}
}
