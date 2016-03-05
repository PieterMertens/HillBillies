package hillbillies.tests.Unit;

import org.junit.Assert;
import org.junit.Test;
import hillbillies.model.Unit;

public class UnitTest {

	@Test
	public void testUnitName() throws IllegalArgumentException {
		Unit unit = new Unit("Homer");
		Assert.assertEquals("Homer", unit.getName());
	}

}
