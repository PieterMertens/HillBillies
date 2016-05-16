package hillbillies.tests.Model;

import org.junit.Assert;
import org.junit.Test;

import hillbillies.helper.Helper;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.facade.IFacade;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

public class WorldTest {

	@Test
	public void testWorldgetCubeType() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][0] = 2;
		types[1][1][1] = 2;
		types[1][1][2] = 3;
		World world = new World(types, new DefaultTerrainChangeListener());
		Assert.assertEquals(2, world.getCubeType(1, 1, 0));
		Assert.assertEquals(3, world.getCubeType(1, 1, 2));
	}

	@Test
	public void testWorldIsPassable() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][0] = 2;
		types[1][1][1] = 2;
		types[1][1][2] = 3;
		World world = new World(types, new DefaultTerrainChangeListener());

		Assert.assertEquals(false, world.isPassable(1, 1, 0));
		Assert.assertEquals(true, world.isPassable(2, 2, 2));
	}

	@Test
	public void testWorldCollapseCube() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][3] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());

		Assert.assertEquals(1, world.getCubeType(1, 1, 3));
		world.collapseCube(1, 1, 3, false);
		Assert.assertEquals(0, world.getCubeType(1, 1, 3));
	}

	@Test
	public void testWorldRockToBoulder() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][3] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());

		world.collapseCube(1, 1, 3, true);
		Assert.assertEquals(0, world.getCubeType(1, 1, 3));
		Assert.assertTrue(world.getBoulder(new int[] { 1, 1, 3 }) != null);
	}

	@Test
	public void testWorldWoodToLog() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][3] = 2;
		World world = new World(types, new DefaultTerrainChangeListener());

		world.collapseCube(1, 1, 3, true);
		Assert.assertEquals(0, world.getCubeType(1, 1, 3));
		Assert.assertTrue(world.getLog(new int[] { 1, 1, 3 }) != null);
	}

	@Test
	public void testWorldWithinBoundaries() throws IllegalArgumentException {
		int[][][] types = new int[1][1][1];
		World world = new World(types, new DefaultTerrainChangeListener());

		Assert.assertFalse(world.withinBoundaries(50, 50, 50));
	}

	@Test
	public void testWorldHasImpassableNeighbor() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][3] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());

		Assert.assertTrue(world.hasImpassableNeighbour(1, 1, 2));
	}

	@Test
	public void testWorldHasNoImpassableNeighbor() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		World world = new World(types, new DefaultTerrainChangeListener());

		Assert.assertFalse(world.hasImpassableNeighbour(1, 1, 2));
	}

	@Test
	public void testWorldHasImpassableBelow() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());

		Assert.assertTrue(world.hasImpassableBelow(1, 1, 2));
		Assert.assertFalse(world.hasImpassableBelow(1, 1, 3));
	}

	@Test
	public void testWorldGetNearestWorkshop() throws IllegalArgumentException {
		int[][][] types = new int[10][10][10];
		types[1][1][1] = 3;
		types[9][9][1] = 3;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Homer");
		unit.setPosition(new double[] { 8.5, 8.5, 3.5 });

		Assert.assertEquals(9, world.getNearestWorkhop(unit)[0]);
		Assert.assertEquals(9, world.getNearestWorkhop(unit)[1]);
		Assert.assertEquals(1, world.getNearestWorkhop(unit)[2]);
	}

	@Test
	public void testWorldGetNearestBoulder() throws IllegalArgumentException {
		int[][][] types = new int[10][10][10];
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Homer");
		Boulder farBoulder = new Boulder(world, new double[] { 1.5, 1.5, 1.5 });
		Boulder nearBoulder = new Boulder(world, new double[] { 10.5, 10.5, 1.5 });
		unit.setPosition(new double[] { 8.5, 8.5, 3.5 });
		world.addBoulder(farBoulder);
		world.addBoulder(nearBoulder);

		Assert.assertEquals(nearBoulder, world.getNearestBoulder(unit));
	}

	@Test
	public void testWorldGetNearestLog() throws IllegalArgumentException {
		int[][][] types = new int[10][10][10];
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Homer");
		Log farLog = new Log(world, new double[] { 1.5, 1.5, 1.5 });
		Log nearLog = new Log(world, new double[] { 10.5, 10.5, 1.5 });
		unit.setPosition(new double[] { 8.5, 8.5, 3.5 });
		world.addLog(farLog);
		world.addLog(nearLog);

		Assert.assertEquals(nearLog, world.getNearestLog(unit));
	}

	
}
