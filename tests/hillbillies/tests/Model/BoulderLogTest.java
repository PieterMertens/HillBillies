package hillbillies.tests.Model;

import org.junit.Assert;
import org.junit.Test;

import hillbillies.helper.Helper;
import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class BoulderLogTest {

	@Test
	public void testBoulder() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		World world = new World(types, new DefaultTerrainChangeListener());
		Boulder boulder = new Boulder(world, new double[]{2.5,2.5,0.5});
		
		Assert.assertTrue(boulder.getWeight() >= 10 && boulder.getWeight() <= 50);
		Assert.assertTrue(Helper.getIsSamePosition(new double[]{2.5,2.5,0.5}, boulder.getPosition()));
		Assert.assertTrue(world.getBoulders().contains(boulder));
		Assert.assertEquals(world,boulder.getWorld());
	}
	
	@Test
	public void testLog() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		World world = new World(types, new DefaultTerrainChangeListener());
		Log log = new Log(world, new double[]{2.5,2.5,0.5});
		
		Assert.assertTrue(log.getWeight() >= 10 && log.getWeight() <= 50);
		Assert.assertTrue(Helper.getIsSamePosition(new double[]{2.5,2.5,0.5}, log.getPosition()));
		Assert.assertTrue(world.getLogs().contains(log));
		Assert.assertEquals(world,log.getWorld());
	}
	
	@Test
	public void testBoulderTerminate() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		World world = new World(types, new DefaultTerrainChangeListener());
		Boulder boulder = new Boulder(world, new double[]{2.5,2.5,0.5});
		
		boulder.terminate();
		
		Assert.assertTrue(boulder.getIsTerminated());
		Assert.assertEquals(null, boulder.getIsCarriedBy());
		Assert.assertTrue(!world.getBoulders().contains(boulder));
		Assert.assertEquals(null,boulder.getWorld());
	}
	
	@Test
	public void testlogTerminate() throws IllegalArgumentException {
		int[][][] types = new int[5][5][5];
		World world = new World(types, new DefaultTerrainChangeListener());
		Log log = new Log(world, new double[]{2.5,2.5,0.5});
		
		log.terminate();
		
		Assert.assertTrue(log.getIsTerminated());
		Assert.assertEquals(null, log.getIsCarriedBy());
		Assert.assertTrue(!world.getLogs().contains(log));
		Assert.assertEquals(null,log.getWorld());
	}
}
