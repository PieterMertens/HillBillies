package hillbillies.tests.Model;

import org.junit.Assert;
import org.junit.Test;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.Util;

public class WorldTest {

	@Test
	public void testWorldgetCubeType() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][0] = 2;
		types[1][1][1] = 2;
		types[1][1][2] = 3;		
		World world = new World(types,new DefaultTerrainChangeListener());
		Assert.assertEquals(2, world.getCubeType(1, 1, 0));
		Assert.assertEquals(3, world.getCubeType(1,1,2));
	}
	
	@Test
	public void testWorldIsPassable() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][0] = 2;
		types[1][1][1] = 2;
		types[1][1][2] = 3;		
		World world = new World(types,new DefaultTerrainChangeListener());
		
		Assert.assertEquals(false, world.isPassable(1, 1, 0));
		Assert.assertEquals(true, world.isPassable(2,2,2));
	}
	
	@Test
	public void testWorldCollapseCube() throws IllegalArgumentException {
		int[][][] types = new int[3][3][4];
		types[1][1][3] = 1;
		World world = new World(types,new DefaultTerrainChangeListener());
		
		
		Assert.assertEquals(1, world.getCubeType(1, 1, 3));
		world.collapseCube(1, 1, 3, false);
		Assert.assertEquals(0, world.getCubeType(1,1,3));
	}



}
