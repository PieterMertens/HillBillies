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
		this.setWorld(world);
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
		//TODO fixen
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
	private int maxUnits = 50;

	/**
	 * Set of units in the faction.
	 */
	private Set<Unit> unitsOfFaction = new HashSet<>();

	/**
	 * Adds the given unit to this faction.
	 */
	public void addUnit(Unit unit) {
		if (unitsOfFaction.size() < maxUnits && unit.getFaction() == null) {
			this.unitsOfFaction.add(unit);
		}
	}

	/**
	 * Returns a set of the units if this faction.
	 */
	public Set<Unit> getUnitsOfFaction() {
		return this.unitsOfFaction;
	}

	/**
	 * Removes unit from this faction.
	 */
	public void removeUnit(Unit unit) {
		this.unitsOfFaction.remove(unit);
	}

}
