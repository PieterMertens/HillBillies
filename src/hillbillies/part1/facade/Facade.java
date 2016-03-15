package hillbillies.part1.facade;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;
import hillbillies.helper.Helper;

/**
 * @author
 *
 */
public class Facade implements IFacade {

	public Facade() {
	};

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		

		try {
			Unit newUnit = new Unit(weight, strength, agility, toughness);
			newUnit.setPosition(Helper.intArrayToDoubleArray(initialPosition));
			newUnit.setName(name);
			newUnit.setOrientation((float) Math.PI/2);

			return newUnit;
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		

		return unit.getPosition();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		

		double[] position = unit.getPosition();
		return Helper.doubleArrayToIntArray(position);
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		

		return unit.getName();

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
	public int getWeight(Unit unit) throws ModelException {
		

		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		

		unit.setWeight(newValue);

	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		

		return unit.getStrength();

	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		

		unit.setStrength(newValue);

	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		

		return unit.getAgility();

	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		

		unit.setAgility(newValue);

	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		

		return unit.getToughness();

	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		

		unit.setToughness(newValue);

	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		

		return unit.getMaxPoints();

	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		

		return unit.getHitpoints();

	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		

		return unit.getMaxPoints();

	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		

		return unit.getStaminapoints();

	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		
		unit.advanceTime(dt);
		

	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		
		unit.moveToAdjecent(dx, dy, dz);
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		

		return unit.getCurrentSpeed();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		

		return unit.getIsMoving();

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
	public boolean isSprinting(Unit unit) throws ModelException {
		

		return unit.getIsSprinting();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		

		return unit.getOrientation();

	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		
		unit.moveTo(cube);
	}

	@Override
	public void work(Unit unit) throws ModelException {
		
		
		unit.work();

	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		

		return unit.getIsWorking();

	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		
		//TODO hier orientation vr gevecht ook aanpassen?
		attacker.attack();
		defender.defend();

	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		

		return unit.getIsAttacking();

	}

	@Override
	public void rest(Unit unit) throws ModelException {
		
		unit.rest();
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		

		return unit.getIsResting();

	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		
		//TODO setDefaultBehaviorEnabled

	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		
		//TODO isDefaultBehaviorEnabled
		return false;
	}

}