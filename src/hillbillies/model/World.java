package hillbillies.model;

import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.util.ConnectedToBorder;

/**
 * @invar The terrain of each World must be a valid terrain for any World. | isValidTerrain(getTerrain())
 */
public class World {

	/**
	 * Initialize this new World with given terrain.
	 *
	 * @param terrain
	 *            The terrain for this new World.
	 * @effect The terrain of this new World is set to the given terrain. | this.setTerrain(terrain)
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {

		this.setTerrain(terrainTypes);

	}

	/**
	 * Return the terrain of this World.
	 */
	@Basic
	@Raw
	public int[][][] getTerrain() {
		return this.terrain;
	}

	/**
	 * Check whether the given terrain is a valid terrain for any World.
	 * 
	 * @param terrain
	 *            The terrain to check.
	 * @return | result ==
	 */
	public static boolean isValidTerrain(int[][][] terrain) {
		return true;// TODO vereisten checken
	}

	/**
	 * Set the terrain of this World to the given terrain.
	 * 
	 * @param terrain
	 *            The new terrain for this World.
	 * @post The terrain of this new World is equal to the given terrain. | new.getTerrain() == terrain
	 * @throws IllegalArgumentException
	 *             The given terrain is not a valid terrain for any World. | ! isValidTerrain(getTerrain())
	 */
	@Raw
	public void setTerrain(int[][][] terrain) throws IllegalArgumentException {
		if (!isValidTerrain(terrain))
			throw new IllegalArgumentException();
		this.terrain = terrain;
	}

	/**
	 * Variable registering the terrain of this World.
	 */
	private int[][][] terrain;

	/**
	 * Return the number of cubes in the world in the x-direction.
	 */
	@Basic
	@Raw
	public int getNbCubesX() {
		return this.getTerrain().length;
	}

	/**
	 * Return the number of cubes in the world in the y-direction.
	 */
	@Basic
	@Raw
	public int getNbCubesY() {
		return this.getTerrain()[0].length;
	}

	/**
	 * Return the number of cubes in the world in the z-direction.
	 */
	@Basic
	@Raw
	public int getNbCubesZ() {
		return this.getTerrain()[0][0].length;
	}

	public void advanceTime(double dt) throws IllegalArgumentException {// TODO
	}

	/**
	 * Return the terrain type of the cube at the given coordinates.
	 */
	@Basic
	@Raw
	public int getCubeType(int x, int y, int z) {
		return this.getTerrain()[x][y][z];
	}

	/**
	 * Check whether the given terrain type is a valid at the given position.
	 * 
	 * @param terrain
	 *            The terrain to check.
	 * @return | result ==
	 */
	public static boolean isValidCubeType(int value) {
		return true;// TODO vereisten checken
	}

	/**
	 * Set the terrain type of the cube at the given coordinates the given value.
	 * 
	 * @param terrain
	 *            The new terrain for this World.
	 * @post The terrain of this new World is equal to the given terrain. | new.getTerrain() == terrain
	 * @throws IllegalArgumentException
	 *             The given terrain is not a valid terrain for any World. | ! isValidTerrain(getTerrain())
	 */
	@Raw
	public void setCubeType(int x, int y, int z, int value) throws IllegalArgumentException {
		if (!isValidCubeType(value))
			throw new IllegalArgumentException();
		this.getTerrain()[x][y][z] = value;
	}

	/**
	 * Return whether the cube at the given coordinates is solid and connected to the border of the world.
	 */
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return isSolidConnectedToBorder(x, y, z);
	}

	/**
	 * Spawn a new unit in the world.
	 */
	public Unit spawnUnit(boolean enableDefaultBehavior) throws IllegalArgumentException {
		
		
		if 
		  
		return new Unit();//TODO gepast constructor maken voor deze toepassing
	}

	/**
	 * Adds the given unit to the given world.
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {
		
		if ((units.size() <= 100) //&& 
				//unit.inFaction() && unit.getFactionSize <= 50//TODO inFaction toevoegen aan Unit
				)
		units.add(unit);

	}

	private Set<Unit> units;
	
	/**
	 * Return all units that are currently part of the world.
	 */
	public Set<Unit> getUnits() throws IllegalArgumentException {
		return units;
	}
}
