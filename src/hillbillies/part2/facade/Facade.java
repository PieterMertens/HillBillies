package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.helper.Helper;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.facade.IFacade;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	
	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		try {
			return new World(terrainTypes, modelListener);

		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
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
		try {
			world.setCubeType(x, y, z, value);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.isSolidConnectedToBorder(x, y, z);
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {

		return world.spawnUnit(enableDefaultBehavior);
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		world.addUnit(unit);

	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {

		return unit.getCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {

		return unit.getCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return !unit.isTerminated();
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {

		return unit.getExperience();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		
		unit.workAt(x,y,z);

	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {

		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {

		return faction.getUnitsOfFaction();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {

		return world.getActiveFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		return boulder.getPosition();
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {

		return world.getBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {

		return log.getPosition();
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {

		return world.getLogs();
	}

	// XXX -------------------------------------- PT1 below------------------------------------

	public Facade() {
	};

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {

		unit.advanceTime(dt);

	}

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {

		try {
			Unit newUnit = new Unit(name, initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
			return newUnit;
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}

		// try {
		// Unit newUnit = new Unit(weight, strength, agility, toughness);
		// newUnit.setPosition(Helper.intArrayToDoubleArray(initialPosition));//TODO niewe constr ofz mr nr midden vn blokje
		// newUnit.setName(name);
		// newUnit.setOrientation((float) Math.PI/2);
		//
		// return newUnit;
		// } catch (IllegalArgumentException e) {
		// throw new ModelException();
		// }
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {

		attacker.attack(attacker, defender);
		//defender.defend(attacker, defender);

	}

	@Override
	public int getAgility(Unit unit) throws ModelException {

		return unit.getAgility();

	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {

		double[] position = unit.getPosition();
		return Helper.doubleArrayToIntArray(position);
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {

		return unit.getHitpoints();

	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {

		return unit.getCurrentSpeed();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {

		return unit.getStaminapoints();

	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {

		return unit.getMaxPoints();

	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {

		return unit.getMaxPoints();

	}

	@Override
	public String getName(Unit unit) throws ModelException {

		return unit.getName();

	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {

		return unit.getOrientation();

	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {

		return unit.getPosition();
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {

		return unit.getStrength();

	}

	@Override
	public int getToughness(Unit unit) throws ModelException {

		return unit.getToughness();

	}

	@Override
	public int getWeight(Unit unit) throws ModelException {

		return unit.getTotalWeight();
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {

		return unit.getIsAttacking();

	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {

		return unit.getDefaultBehavior();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {

		return unit.getIsMoving();

	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {

		return unit.getIsResting();

	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {

		return unit.getIsSprinting();
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {

		return unit.getIsWorking();

	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {

		unit.moveTo(cube);
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {

		unit.moveToAdjacent(dx, dy, dz);
	}

	@Override
	public void rest(Unit unit) throws ModelException {

		unit.rest();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {

		unit.setAgility(newValue);

	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {

		unit.setDefaultBehavior(value);

	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {
			unit.setName(newName);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}

	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {

		unit.setStrength(newValue);

	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {

		unit.setToughness(newValue);

	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {

		unit.setWeight(newValue);

	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {

		unit.setIsSprinting(true);

	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {

		unit.setIsSprinting(false);

	}

	@Override
	public void work(Unit unit) throws ModelException {

		unit.work();

	}

}
