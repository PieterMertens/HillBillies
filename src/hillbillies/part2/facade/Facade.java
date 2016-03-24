package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.facade.IFacade;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		return new World(terrainTypes, modelListener);
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getNbCubesX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getNbCubesY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getNbCubesZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		world.advanceTime(dt);
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		return world.getCubeType(x, y, z);
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		
		
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		
		return false;
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		
		return null;
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		
		
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		
		return null;
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		
		return false;
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		
		return false;
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		
		return false;
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		
		return 0;
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		
		
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		
		return null;
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		
		return null;
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		
		return null;
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		
		return null;
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		
		return null;
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		
		return null;
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		
		return null;
	}

}
