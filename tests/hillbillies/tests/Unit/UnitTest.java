package hillbillies.tests.Unit;

import org.junit.Assert;
import org.junit.Test;
import hillbillies.model.Unit;

public class UnitTest {

	@Test
	public void testUnitName() throws IllegalArgumentException {
		Unit unit = new Unit("Home r");
		Assert.assertEquals("Home r", unit.getName());
	}
	
	@Test
	public void testUnitName2() throws IllegalArgumentException {
		Unit unit = new Unit("Homer\'");
		Assert.assertEquals("Homer\'", unit.getName());
	}
	
	@Test
	public void testUnitName3() throws IllegalArgumentException {
		Unit unit = new Unit("Homer\"");
		Assert.assertEquals("Homer\"", unit.getName());
	}
	
	@Test
	public void testUnitName4() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		Assert.assertEquals("Homer", unit.getName());
	}

}
