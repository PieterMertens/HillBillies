package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Faction {

	/**
	 * Initialize this new faction in the given world.
	 *
	 * @param world
	 *            The world for this new faction.
	 * @effect The world of this new faction is set to the given world. |
	 *         this.setWorld(world)
	 */
	public Faction(World world) throws IllegalArgumentException {
		world.addFaction(this);
		this.setWorld(world);
		setScheduler(new Scheduler(this));
	}

	/**
	 * Return the world of this faction.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return this.world;
	}

	/**
	 * Check whether the given world is a valid world for any faction.
	 * 
	 * @param world
	 *            The world to check.
	 * @return | result ==
	 */
	public static boolean isValidWorld(World world) {
		// TODO fixen
		return true;
	}

	/**
	 * Set the world of this faction to the given world.
	 * 
	 * @param world
	 *            The new world for this faction.
	 * @post The world of this new faction is equal to the given world. |
	 *       new.getWorld() == world
	 * @throws IllegalArgumentException
	 *             The given world is not a valid world for any faction. | !
	 *             isValidWorld(getWorld())
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (!isValidWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
	}

	/**
	 * Variable registering the world of this faction.
	 */
	private World world;

	/**
	 * Maximum number of units a faction can have.
	 */
	private static final int maxUnits = 50;

	/**
	 * Set of units in the faction.
	 */
	private Set<Unit> unitsOfFaction = new HashSet<>();

	/**
	 * Adds the given unit to this faction.
	 */
	public void addUnit(Unit unit) {
		if (unitsOfFaction.size() < maxUnits) {
			this.unitsOfFaction.add(unit);
		}
	}

	/**
	 * Return a set of the units if this faction.
	 */
	public Set<Unit> getUnitsOfFaction() {
		return this.unitsOfFaction;
	}

	/**
	 * Remove the given unit from the faction's set of units.
	 * 
	 * @param unit
	 *            The unit to remove.
	 * @throws IllegalArgumentException
	 *             The given unit is not in the set. |
	 *             !getUnitsOfFaction().contains(unit)
	 */
	public void removeUnit(Unit unit) throws IllegalArgumentException {
		if (!getUnitsOfFaction().contains(unit)) {
			throw new IllegalArgumentException("The unit is not in the set.");
		}
		this.getUnitsOfFaction().remove(unit);
	
	}

	/**
	 * Return the scheduler of this Faction.
	 */
	@Basic
	@Raw
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * Check whether the given scheduler is a valid scheduler for any Faction.
	 * 
	 * @param scheduler
	 *            The scheduler to check.
	 * @return | result ==
	 */
	public static boolean isValidScheduler(Scheduler scheduler) {
		return scheduler != null;
	}

	/**
	 * Set the scheduler of this Faction to the given scheduler.
	 * 
	 * @param scheduler
	 *            The new scheduler for this Faction.
	 * @post The scheduler of this new Faction is equal to the given scheduler.
	 *       | new.getScheduler() == scheduler
	 * @throws IllegalArgumentException
	 *             The given scheduler is not a valid scheduler for any Faction.
	 *             | ! isValidScheduler(getScheduler())
	 */
	@Raw
	public void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if (!isValidScheduler(scheduler))
			throw new IllegalArgumentException();
		this.scheduler = scheduler;
	}

	/**
	 * Variable registering the scheduler of this Faction.
	 */
	private Scheduler scheduler;

}
