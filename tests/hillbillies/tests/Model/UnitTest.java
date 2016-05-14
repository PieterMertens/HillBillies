package hillbillies.tests.Model;

import org.junit.Assert;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.Util;

public class UnitTest {
	
	/**
	 * Helper method to advance time for the given unit by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Unit unit, double time, double step) throws IllegalArgumentException {
		int n = (int) (time / step);
		for (int i = 0; i < n+1; i++)
			unit.advanceTime(step);
		unit.advanceTime(time - n * step);
	}
	
	//NAME TESTS

	@Test
	public void testUnitSetCorrectName() throws IllegalArgumentException {
		Unit unit = new Unit("Homer \" J\' Simpson");
		Assert.assertEquals("Homer \" J\' Simpson", unit.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnitSetNameWithIvalidCharacter() throws IllegalArgumentException {
		Unit unit = new Unit("Homer J. Simpson");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnitSetEmptyName() throws IllegalArgumentException {
		Unit unit = new Unit("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUnitSetLowercaseName() throws IllegalArgumentException {
		Unit unit = new Unit("homer");
	}

	//CHARACTERISTICS TESTS
	
	@Test
	public void testUnitSetAnyWeightStrengthAgilityToughess() throws IllegalArgumentException {
		Unit unit = new Unit(0, 1000, 75, -50);
		Assert.assertTrue(unit.getWeight() >= 25 && unit.getWeight() <= 100
				&& unit.getWeight() > (unit.getStrength() + unit.getAgility()) / 2);
		Assert.assertTrue(unit.getStrength() >= 25 && unit.getStrength() <= 100);
		Assert.assertTrue(unit.getAgility() >= 25 && unit.getAgility() <= 100);
		Assert.assertEquals(unit.getAgility(), 75);
		Assert.assertTrue(unit.getToughness() >= 25 && unit.getToughness() <= 100);
	}

	@Test
	public void testUnitSetCorrectHitPoints() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setHitpoints(200 * unit.getWeight() * unit.getToughness() / 10000);
		Assert.assertEquals(200 * unit.getWeight() * unit.getToughness() / 10000, unit.getHitpoints());
	}
	
	@Test
	public void testUnitSetNegativeHitPoints() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setHitpoints(-100);
		Assert.assertTrue(unit.isTerminated());
	}

	@Test
	public void testUnitSetCorrectStaminaPoints() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setStaminapoints(200 * unit.getWeight() * unit.getToughness() / 10000);
		Assert.assertEquals(200 * unit.getWeight() * unit.getToughness() / 10000, unit.getStaminapoints());
	}
	
	@Test(expected=AssertionError.class)
	public void testUnitSetNegativeStaminaPoints() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setStaminapoints(-100);
	}
	
	@Test
	public void testUnitCorrectLevelUp() throws IllegalArgumentException {
		Unit unit = new Unit(50,50,50,50);
		unit.setExperience(104);
		Assert.assertEquals(50 + 50 + 50 + 10, unit.getStrength() + unit.getAgility() + unit.getToughness());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testUnitSetNegativeExperience() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setExperience(-10);
	}
	
	@Test
	public void testUnitTakeDamage() throws IllegalArgumentException {
		Unit unit = new Unit(50,50,50,50);
		Unit attacker = new Unit(50,50,50,50);
		unit.setHitpoints(50);
		unit.takeDamage(attacker);
		Assert.assertEquals(45, unit.getHitpoints());
	}
	
	//ORIENTATION TESTS
	
	@Test
	public void testUnitSetOrientation() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setOrientation((float) Math.PI);
		Assert.assertEquals((float) Math.PI, unit.getOrientation(), Util.DEFAULT_EPSILON);
	}

	@Test
	public void testUnitSetInorrectOrientation() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setOrientation(10);
		Assert.assertEquals((float) Math.PI / 2, unit.getOrientation(), Util.DEFAULT_EPSILON);
	}
	
	//POSITION TESTS
	
	@Test
	public void testUnitSetCorrectPosition() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Homer");
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,2.5});
		Assert.assertEquals(1, (int) unit.getPosition()[0]);
		Assert.assertEquals(1, (int) unit.getPosition()[1]);
		Assert.assertEquals(2, (int) unit.getPosition()[2]);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testUnitSetPositionOutOfBounds() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Homer");
		world.addUnit(unit);
		unit.setPosition(new double[]{100.5,1.5,1.5});
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testUnitSetImpassablePosition() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Homer");
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,1.5});
	}

	@Test (expected = IllegalArgumentException.class)
	public void testUnitSetNegativeCoordinates() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setPosition(new double[]{-10.5,0.5,0.5});
	}
	
	//FALLING TESTS
	
	@Test
	public void testUnitFallLoseHitpoints() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit(50,50,50,50);
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,4.5});
		unit.setHitpoints(50);
		advanceTimeFor(unit,100,0.2);
		Assert.assertEquals(30, unit.getHitpoints());
	}
	
	@Test
	public void testUnitFallLowerZCoordinate() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit(50,50,50,50);
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,4.5});
		unit.setHitpoints(50);
		advanceTimeFor(unit,100,0.2);
		Assert.assertEquals(2, (int) unit.getPosition()[2]);
	}
	
	//WORK TESTS
	
	@Test
	public void testUnitPickUpBoulder() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit(50,50,50,50);
		Boulder boulder = new Boulder(world, new double[]{1.5,1.5,2.5});
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,2.5});
		unit.workAt(1, 1, 2);
		advanceTimeFor(unit,100,0.2);
		Assert.assertTrue(unit.getCarryingBoulder());
		Assert.assertEquals(null,world.getBoulder(new int[]{1,1,2}));
	}
	
	@Test
	public void testUnitPickUpLog() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit(50,50,50,50);
		Log log = new Log(world, new double[]{1.5,1.5,2.5});
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,2.5});
		unit.workAt(1, 1, 2);
		advanceTimeFor(unit,100,0.2);
		Assert.assertTrue(unit.getCarryingLog());
		Assert.assertEquals(null,world.getLog(new int[]{1,1,2}));
	}
	
	@Test
	public void testUnitUpgrade() throws IllegalArgumentException {
		int[][][] types = new int[50][50][50];
		types[1][1][1] = 1;
		types[1][1][2] = 3;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit(50,50,50,50);
		Log log = new Log(world, new double[]{1.5,1.5,2.5});
		Boulder boulder = new Boulder(world, new double[]{1.5,1.5,2.5});
		world.addUnit(unit);
		unit.setPosition(new double[]{1.5,1.5,2.5});
		unit.workAt(1, 1, 2);
		advanceTimeFor(unit,100,0.2);
		Assert.assertEquals(null,world.getBoulder(new int[]{1,1,2}));
		Assert.assertEquals(null,world.getLog(new int[]{1,1,2}));
		Assert.assertTrue(110 <= unit.getWeight()+unit.getToughness());
	}
	
}
