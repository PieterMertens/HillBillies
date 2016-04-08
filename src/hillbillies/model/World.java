package hillbillies.model;

import hillbillies.helper.Helper;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.util.ConnectedToBorder;

/**
 * @invar The terrain of each World must be a valid terrain for any World. | isValidTerrain(getTerrain())
 */
public class World {

	public static final int air = 0;
	public static final int rock = 1;
	public static final int tree = 2;
	public static final int workshop = 3;

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
	 * Return if the terrain type of the cube at the given coordinates is passable.
	 */
	public boolean isPassable(double x, double y, double z) {
		int terraintype = getCubeType((int) x, (int) y, (int) z);
		if (terraintype == air || terraintype == workshop)
			return true;

		return false;
	}

	/**
	 * Return if any of the neighbouring cubes at the given coordinates are impassable.
	 */
	public boolean hasImpassableNeighbour(double x, double y, double z) {

		for (int i = -1; i < 1; i++) {
			for (int j = -1; i < 1; i++) {
				for (int k = -1; i < 1; i++) {
					if (!isPassable(x + i, y + j, z + k))
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * Return if the terrain type of the cube below the given coordinates is impassable.
	 */
	public boolean hasImpassableBelow(double x, double y, double z) {
		if (z > 0)
			return !isPassable(x, y, z - 1);
		return false;
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

	
	public void collapseCube(int x, int y, int z, boolean certainDrop) {

		int cubeType = this.getCubeType(x, y, z);
		this.setCubeType(x, y, z, air);
		if (certainDrop || Helper.randInt(1, 4) == 1) {
			if (cubeType == rock)
				new Boulder(Helper.getCenterOfPosition(Helper.toIntArray(x, y, z)));
			if (cubeType == tree)
				new Log(Helper.getCenterOfPosition(Helper.toIntArray(x, y, z)));

		}

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

		if (this.units.size() >= 100) {
			throw new IllegalArgumentException("Max amount of units reached.");
		}
		System.out.println("units size " + units.size());
		Unit unit = createUnit(enableDefaultBehavior);

		unit.setFaction(this.getSmallestFaction());

		unit.getFaction().addUnit(unit);
		System.out.println("faction size:" + unit.getFaction().getUnitsOfFaction().size());
		return unit;
	}

	public Unit createUnit(boolean enableDefaultBehavior) {

		String name = Character.toString((char) Helper.randInt(65, 90))
				+ Character.toString((char) Helper.randInt(97, 122))
				+ Character.toString((char) Helper.randInt(97, 122));

		int[] initialPosition = Helper.getRandomPosition(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());

		System.out.println("voor while: " + initialPosition[0] + " " + initialPosition[1] + " " + initialPosition[2]);
		System.out.println(isPassable(initialPosition[0], initialPosition[1], initialPosition[2]) + " "
				+ hasImpassableBelow(initialPosition[0], initialPosition[1], initialPosition[2]) + " "
				+ (initialPosition[2] == 0));
		// TODO while conditie checken..
		while ((!isPassable(initialPosition[0], initialPosition[1], initialPosition[2])
				&& !((hasImpassableBelow(initialPosition[0], initialPosition[1], initialPosition[2]))
						|| initialPosition[2] == 0))) {
			initialPosition = Helper.getRandomPosition(getNbCubesX(), getNbCubesY(), getNbCubesZ());
			System.out.println("in while" + initialPosition[0] + initialPosition[1] + initialPosition[2]);
		}
		System.out.println("na while: " + initialPosition[0] + " " + initialPosition[1] + " " + initialPosition[2]);
		System.out.println(isPassable(initialPosition[0], initialPosition[1], initialPosition[2]) + " "
				+ hasImpassableBelow(initialPosition[0], initialPosition[1], initialPosition[2]) + " "
				+ (initialPosition[2] == 0));

		// return new Unit(name, initialPosition, 0, 0, 0, 0, enableDefaultBehavior);//TODO zou random waarden moeten zoeken voor 0
		return new Unit(name, initialPosition, 30, 30, 30, 30, enableDefaultBehavior);
	}

	/**
	 * Adds the given unit to the given world.
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {

		if ((units.size() < 100) // &&
		// unit.inFaction() && unit.getFactionSize <= 50//TODO inFaction
		// toevoegen aan Unit
		)
			units.add(unit);

	}

	private Set<Unit> units = new HashSet<>();

	/**
	 * Return all units that are currently part of the world.
	 */
	public Set<Unit> getUnits() throws IllegalArgumentException {
		return units;
	}

	private void addFaction(Faction faction) {
		this.factions.add(faction);
	}

	public Set<Faction> getActiveFactions() {
		return this.factions;
	}

	private Set<Faction> factions = new HashSet<>();

	private Faction getSmallestFaction() {
		if (this.getActiveFactions().size() < 5) {
			Faction faction = new Faction(this);
			this.addFaction(faction);
			return faction;
		} else {
			int smallestSize = 51;
			Faction smallestFaction = null;
			for (Faction faction : this.getActiveFactions()) {
				if (faction.getUnitsOfFaction().size() < smallestSize) {
					smallestSize = faction.getUnitsOfFaction().size();
					smallestFaction = faction;
				}
			}
			return smallestFaction;
		}
	}

}
