package hillbillies.model;

import hillbillies.helper.Helper;
import hillbillies.part2.listener.TerrainChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.util.ConnectedToBorder;

/**
 * @invar The terrain of each World must be a valid terrain for any World. |
 *        isValidTerrain(getTerrain())
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
	 * @effect The terrain of this new World is set to the given terrain. |
	 *         this.setTerrain(terrain)
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {

		this.setTerrain(terrainTypes);
		this.setTerrainChangeListener(modelListener);
		this.connectedToBorder = new ConnectedToBorder(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());

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
	 * @post The terrain of this new World is equal to the given terrain. |
	 *       new.getTerrain() == terrain
	 * @throws IllegalArgumentException
	 *             The given terrain is not a valid terrain for any World. | !
	 *             isValidTerrain(getTerrain())
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
	 * Set the terrainChangeListener of this World to the given
	 * terrainChangeListener.
	 * 
	 * @param terrainChangeListener
	 *            The new terrain for this World.
	 * @post The terrainChangeListener of this new World is equal to the given
	 *       terrainChangeListener.
	 */
	@Raw
	public void setTerrainChangeListener(TerrainChangeListener modelListener) {
		this.modelListener = modelListener;

	}

	/**
	 * Variable registering the terrainChangeListener of this World.
	 */
	private TerrainChangeListener modelListener;

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

	public void advanceTime(double dt) throws IllegalArgumentException {

		// System.out.println("--- advance time ---");
		// System.out.println("-- world");
		for (int[] pos : disconnected) {
			System.out.println("disconnected cave in" + pos[0] + " " + pos[1] + " " + pos[2]);
			this.collapseCube(pos[0], pos[1], pos[2], false);
		}
		disconnected.clear();
		this.getDisconnectedCubes();

		// System.out.println("-- unit");
		for (Unit unit : this.getUnits()) {
			unit.advanceTime(dt);
		}

		// System.out.println("-- boulder");
		for (Boulder boulder : this.getBoulders()) {
			boulder.advanceTime(dt);
		}

		// System.out.println("-- log");
		for (Log log : this.getLogs()) {
			log.advanceTime(dt);
		}

		// System.out.println("--- --- --- --- ---");

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
	 * Return if the terrain type of the cube at the given coordinates is
	 * passable.
	 */
	public boolean isPassable(double x, double y, double z) {
		int terraintype = getCubeType((int) x, (int) y, (int) z);
		if (terraintype == air || terraintype == workshop)
			return true;

		return false;
	}

	/**
	 * Return if any of the neighbouring cubes at the given coordinates are
	 * impassable.
	 */
	public boolean hasImpassableNeighbour(double x, double y, double z) {

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					if (!isPassable(x + i, y + j, z + k))
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * Return if the terrain type of the cube below the given coordinates is
	 * impassable.
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
	 * Set the terrain type of the cube at the given coordinates the given
	 * value.
	 * 
	 * @param terrain
	 *            The new terrain for this World.
	 * @post The terrain of this new World is equal to the given terrain. |
	 *       new.getTerrain() == terrain
	 * @throws IllegalArgumentException
	 *             The given terrain is not a valid terrain for any World. | !
	 *             isValidTerrain(getTerrain())
	 */
	@Raw
	public void setCubeType(int x, int y, int z, int value) throws IllegalArgumentException {
		if (!isValidCubeType(value))
			throw new IllegalArgumentException();
		System.out.println("SetCubeType at:" + x + " " + y + " " + z + " from " + this.getCubeType(x, y, z) + " or "
				+ this.getTerrain()[x][y][z]);
		this.getTerrain()[x][y][z] = value;
		System.out.println("to: " + this.getCubeType(x, y, z) + " or " + this.getTerrain()[x][y][z]);

		modelListener.notifyTerrainChanged(x, y, z);
	}

	public void collapseCube(int x, int y, int z, boolean certainDrop) {

		int cubeType = this.getCubeType(x, y, z);
		this.setCubeType(x, y, z, air);

		if (certainDrop || Helper.randInt(1, 4) == 1) {
			int[] pos = { x, y, z };
			double[] position = Helper.getCenterOfPosition(pos);
			if (cubeType == rock)
				new Boulder(this, position);
			if (cubeType == tree)
				new Log(this, position);

		}

	}

	/**
	 * Variable registering if blocks are connected to the border.
	 */
	private ConnectedToBorder connectedToBorder;

	/**
	 * Return whether the cube at the given coordinates is solid and connected
	 * to the border of the world.
	 */
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return connectedToBorder.isSolidConnectedToBorder(x, y, z);
	}

	public void getDisconnectedCubes() {
		// XXX kan effecienter wrschnlk
		for (int x = 0; x < this.getNbCubesX(); x++) {
			for (int y = 0; y < this.getNbCubesY(); y++) {
				for (int z = 0; z < this.getNbCubesZ(); z++) {
					if (isPassable(x, y, z))
						disconnected.addAll(connectedToBorder.changeSolidToPassable(x, y, z));
				}
			}
		}

	}

	/**
	 * Variable registering the blocks disconnected from the border.
	 */
	private List<int[]> disconnected = new ArrayList<>();

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
		this.units.add(unit);
		unit.getFaction().addUnit(unit);
		// System.out.println("faction name:" + unit.getFaction().getName());

		System.out.println("faction size:" + unit.getFaction().getUnitsOfFaction().size());
		return unit;
	}

	public Unit createUnit(boolean enableDefaultBehavior) {

		String name = Character.toString((char) Helper.randInt(65, 90))
				+ Character.toString((char) Helper.randInt(97, 122))
				+ Character.toString((char) Helper.randInt(97, 122));

		int[] initialPosition = Helper.getRandomPosition(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());

		// System.out.println("voor while: " + initialPosition[0] + " " +
		// initialPosition[1] + " " + initialPosition[2]);

		while (!isValidInitialPosition(initialPosition)) {
			initialPosition = Helper.getRandomPosition(getNbCubesX(), getNbCubesY(), getNbCubesZ());
			// System.out.println("in while" + initialPosition[0] +
			// initialPosition[1] + initialPosition[2]);
		}
		// System.out.println("na while: " + initialPosition[0] + " " +
		// initialPosition[1] + " " + initialPosition[2]);

		return new Unit(name, initialPosition, 0, 0, 0, 0, enableDefaultBehavior);
	}

	public boolean isValidInitialPosition(int[] position) {

		if (isPassable(position[0], position[1], position[2])) {
			if (position[2] == 0)
				return true;
			if (hasImpassableBelow(position[0], position[1], position[2]))
				return true;
		}

		return false;

	}

	/**
	 * Adds the given unit to the given world.
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {

		if ((units.size() < 100) // &&
		// unit.inFaction() && unit.getFactionSize <= 50//TODO inFaction
		// toevoegen aan Unit
		)
			this.units.add(unit);

	}
	
	public void removeUnit(Unit unit) {
		this.units.remove(unit);
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

	public Set<Log> getLogs() {
		return this.logs;
	}

	public void addLog(Log log) throws IllegalArgumentException {

		logs.add(log);

	}

	public void removeLog(Log log) throws IllegalArgumentException {

		logs.remove(log);

	}

//	public boolean logAtCube(int[] position) {
//
//		for (Log log : this.getLogs()) {
//			if (Helper.doubleArrayToIntArray(log.getPosition()) == position)
//				return true;
//		}
//
//		return false;
//
//	}
	
	public Log getLog(int[] position) {
		for (Log log : this.getLogs()) {
			if (Helper.doubleArrayToIntArray(log.getPosition())[0] == position[0] &&
				Helper.doubleArrayToIntArray(log.getPosition())[1] == position[1] &&
				Helper.doubleArrayToIntArray(log.getPosition())[2] == position[2]) {
				return log;
			}
		}
		return null;
	}


	private Set<Log> logs = new HashSet<>();

	public Set<Boulder> getBoulders() {
		return this.boulders;
	}

	public void addBoulder(Boulder boulder) throws IllegalArgumentException {

		boulders.add(boulder);

	}

	public void removeBoulder(Boulder boulder) throws IllegalArgumentException {

		boulders.remove(boulder);

	}

//	public boolean boulderAtCube(int[] position) {
//
//		for (Boulder boulder : this.getBoulders()) {
//			if (Helper.doubleArrayToIntArray(boulder.getPosition()) == position)
//				return true;
//		}
//
//		return false;
//
//	}

	public Boulder getBoulder(int[] position) {
		for (Boulder boulder : this.getBoulders()) { //TODO efficienter
			System.out.println(Helper.doubleArrayToIntArray(boulder.getPosition())[0]);
			System.out.println(Helper.doubleArrayToIntArray(boulder.getPosition())[1]);
			System.out.println(Helper.doubleArrayToIntArray(boulder.getPosition())[2]);
			if (Helper.doubleArrayToIntArray(boulder.getPosition())[0] == position[0] &&
				Helper.doubleArrayToIntArray(boulder.getPosition())[1] == position[1] &&
				Helper.doubleArrayToIntArray(boulder.getPosition())[2] == position[2]) {
				return boulder;
			}
		}
		return null;
	}

	private Set<Boulder> boulders = new HashSet<>();

	public void terminate() {
		this.isTerminated = true;
	}

	public boolean getIsTerminated() {
		return this.isTerminated;
	}

	private boolean isTerminated;

}
