package hillbillies.tests.Unit;

import org.junit.Assert;
import org.junit.Test;
import hillbillies.model.Unit;
import ogp.framework.util.Util;

public class UnitTest {

	@Test
	public void testUnitSetCorrectName() throws IllegalArgumentException {
		Unit unit = new Unit("Homer \" J\' Simpson");
		Assert.assertEquals("Homer \" J\' Simpson", unit.getName());
	}

	@Test
	public void testUnitSetIncorretName() throws IllegalArgumentException {
		Unit unit = new Unit(50);
		try {
			unit.setName("Homer J. Simpson");
		} catch (IllegalArgumentException e) {
		}
		Assert.assertEquals("This name is invalid because it contains a .", "Homer J. Simpson", unit.getName());
	}

	@Test
	public void testUnitSetAnyWeightStrengthAgilityToughess() throws IllegalArgumentException {
		Unit unit = new Unit(0, 1000, 75, -50);
		Assert.assertTrue(unit.getWeight() >= 25 && unit.getWeight() <= 100
				&& unit.getWeight() > (unit.getStrength() + unit.getAgility()) / 2);
		Assert.assertTrue(unit.getStrength() >= 25 && unit.getStrength() <= 100);
		Assert.assertTrue(unit.getAgility() >= 25 && unit.getAgility() <= 100);
		Assert.assertTrue(unit.getToughness() >= 25 && unit.getToughness() <= 100);
	}

	@Test
	public void testUnitSetCorrectHitPoints() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setHitpoints(200 * (unit.getWeight() / 100) * (unit.getToughness() / 100));
		Assert.assertEquals(200 * (unit.getWeight() / 100) * (unit.getToughness() / 100), unit.getHitpoints());
	}

	@Test
	public void testUnitSetCorrectStaminaPoints() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		unit.setStaminapoints(200 * (unit.getWeight() / 100) * (unit.getToughness() / 100));
		Assert.assertEquals(200 * (unit.getWeight() / 100) * (unit.getToughness() / 100), unit.getStaminapoints());
	}

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

}
