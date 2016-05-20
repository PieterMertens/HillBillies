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
 *        
 * @author Pieter and Matthias
 * @version 2.0
 */
public class World {

	public static final int AIR = 0;
	public static final int ROCK = 1;
	public static final int TREE = 2;
	public static final int WORKSHOP = 3;

	/**
	 * Initialize this new World with given terrain.
	 *
	 * @param terrainTypes
	 *            The terrain for this new World.
	 * @param modelListener
	 *            The terrain change listener for this new World.
	 * @effect The terrain of this new World is set to the given terrain. |
	 *         this.setTerrain(terrain)
	 * @effect The terrain change listener of this new World is set to the given
	 *         terrain change listener. |
	 *         this.setTerrainChangeListener(modelListener)
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {

		this.setTerrain(terrainTypes);
		this.setTerrainChangeListener(modelListener);
		this.connectedToBorder = new ConnectedToBorder(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());
		makeListWorkshops();

	}

	/**
	 * Return the terrain of this World.
	 */
	@Basic
	@Raw
	private int[][][] getTerrain() {
		return this.terrain;
	}

	/**
	 * Check whether the given terrain is a valid terrain for any World.
	 * 
	 * @param terrain
	 *            The terrain to check.
	 * @return | result == true
	 */
	private static boolean isValidTerrain(int[][][] terrain) {
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
	private void setTerrain(int[][][] terrain) throws IllegalArgumentException {
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
	private void setTerrainChangeListener(TerrainChangeListener modelListener) {
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

	/**
	 * Return if the coordinates are within the boundaries of the world.
	 */
	public boolean withinBoundaries(int x, int y, int z) {

		return (x >= 0 && this.getNbCubesX() > x && y >= 0 && this.getNbCubesY() > y && z >= 0
				&& this.getNbCubesZ() > z);
	}

	/**
	 * Update the program dt (<= 0.2) seconds. - Collapse the cubes disconnected
	 * from the border. - Update the units, boulders and logs.
	 * 
	 * @param dt
	 *            The time the game advances in seconds.
	 * @throws IllegalArgumentException
	 *             Thrown if dt is larger than 0.2.
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {

		if (dt > 0.2) {
			throw new IllegalArgumentException();
		} else {
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
	 * Return whether the terrain type of the cube at the given coordinates is
	 * passable.
	 */
	public boolean isPassable(double x, double y, double z) {
		int terraintype = getCubeType((int) x, (int) y, (int) z);
		if (terraintype == AIR || terraintype == WORKSHOP)
			return true;

		return false;
	}

	/**
	 * Return whether any of the neighboring cubes at the given coordinates are
	 * impassable.
	 */
	public boolean hasImpassableNeighbour(double x, double y, double z) {

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					if (withinBoundaries((int) x + i, (int) y + j, (int) z + k)) {
						if (!isPassable(x + i, y + j, z + k)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Return whether the terrain type of the cube below the given coordinates
	 * is impassable.
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
	private static boolean isValidCubeType(int value) {
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
		this.setCubeType(x, y, z, AIR);

		if (certainDrop || Helper.randInt(1, 4) == 1) {
			int[] pos = { x, y, z };
			double[] position = Helper.getCenterOfPosition(pos);
			if (cubeType == ROCK)
				new Boulder(this, position);
			if (cubeType == TREE)
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

	// TODO docu
	private void getDisconnectedCubes() {
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
		Unit unit = createUnit(enableDefaultBehavior);
		this.addUnit(unit);
		
		return unit;
	}

	/**
	 * Create a unit with a random name on a random valid position.
	 * 
	 * @param enableDefaultBehavior
	 *            Indicates whether default behavior of the new unit should be
	 *            enabled.
	 * @return result== a unit a random 3 letter name, a random valid initial
	 *         position, random attributes and default behavior state set to the
	 *         given boolean | Unit(name, initialPosition, 0, 0, 0, 0,
	 *         enableDefaultBehavior)
	 */
	private Unit createUnit(boolean enableDefaultBehavior) {

		String name = Character.toString((char) Helper.randInt(65, 90))
				+ Character.toString((char) Helper.randInt(97, 122))
				+ Character.toString((char) Helper.randInt(97, 122));

		int[] initialPosition = Helper.getRandomPosition(this.getNbCubesX(), this.getNbCubesY(), this.getNbCubesZ());

		while (!isValidInitialPosition(initialPosition)) {
			initialPosition = Helper.getRandomPosition(getNbCubesX(), getNbCubesY(), getNbCubesZ());
		}

		return new Unit(name, initialPosition, 0, 0, 0, 0, enableDefaultBehavior);
	}

	/**
	 * Check whether the given position is a valid spawn position.
	 * 
	 * @param position
	 *            The position to examine
	 * @return result == true if the given position is a valid spawn position
	 */
	private boolean isValidInitialPosition(int[] position) {

		if (isPassable(position[0], position[1], position[2])) {
			if (position[2] == 0)
				return true;
			if (hasImpassableBelow(position[0], position[1], position[2]))
				return true;
		}

		return false;

	}

	/**
	 * Add the given unit to this world.
	 * 
	 * @param unit
	 *            The unit to add to the world.
	 * @post the unit is added to the set of units of this world |
	 *       this.units.add(unit)
	 * @throws IllegalArgumentException
	 *             The maximum number of units in this world is reached. |
	 *             units.size() >= 100
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {

		if (units.size() >= 100) {
			throw new IllegalArgumentException("The maximum number of units in this world is reached.");
		} else {
			unit.setFaction(this.getSmallestFaction());
			unit.getFaction().addUnit(unit);
			this.units.add(unit);
			unit.setWorld(this);
		}

	}

	/**
	 * Remove the given unit from this world.
	 * 
	 * @param unit
	 *            The unit to remove from this world.
	 * @post the given unit is removed from the set of units of this world |
	 *       this.units.remove(unit)
	 * @throws IllegalArgumentException
	 *             The unit is not in this world. | !units.contains(unit)
	 */
	public void removeUnit(Unit unit) throws IllegalArgumentException {
		if (!this.getUnits().contains(unit)) {
			throw new IllegalArgumentException("The unit is not in this world.");
		} else {
			this.units.remove(unit);
		}
	}

	/**
	 * A set containing all the units of this world.
	 */
	private Set<Unit> units = new HashSet<>();

	/**
	 * Return all units that are currently part of the world.
	 */
	public Set<Unit> getUnits() {
		return units;
	}

	/**
	 * Add the given faction to this world.
	 * 
	 * @param faction
	 *            The faction to add
	 * @post the given faction is added to the set of factions of this world |
	 *       this.factions.add(faction)
	 */
	public void addFaction(Faction faction) {
		this.factions.add(faction);
	}

	/**
	 * Return a set of all the factions active in this world.
	 */
	public Set<Faction> getActiveFactions() {
		return this.factions;
	}

	/**
	 * A set containing all the factions active in this world.
	 */
	private Set<Faction> factions = new HashSet<>();

	/**
	 * Return the faction with the smallest number of members.
	 * 
	 * @return a new faction if the max amount of factions in the world isn't
	 *         reached
	 * @return the faction with the smallest amount of members in this world
	 */
	private Faction getSmallestFaction() {
		if (this.getActiveFactions().size() < 5) {
			Faction faction = new Faction(this);
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

	/**
	 * Return a set of all the logs in this world.
	 */
	public Set<Log> getLogs() {
		return this.logs;
	}

	/**
	 * Add the given log to this world.
	 * 
	 * @param log
	 *            The log to add
	 * @post the given log is added to the set of logs of this world |
	 *       this.logs.add(log)
	 */
	public void addLog(Log log) {
		this.logs.add(log);

	}

	/**
	 * Remove the given log from this world.
	 * 
	 * @param log
	 *            The log to remove from this world.
	 * @post the given log is removed from the set of logs of this world |
	 *       this.logs.remove(log)
	 * @throws IllegalArgumentException
	 *             The log is not in this world. | !logs.contains(log)
	 */
	public void removeLog(Log log) throws IllegalArgumentException {
		if (!this.getLogs().contains(log)) {
			throw new IllegalArgumentException("The log is not in this world.");
		} else {
			this.logs.remove(log);
		}
	}

	/**
	 * Return the log at the given position.
	 * 
	 * @param position
	 *            The position to examine.
	 * @return a log on the given position if there is at least one
	 * @return null if there is no log on that position
	 */
	public Log getLog(int[] position) {
		for (Log log : this.getLogs()) {
			if (Helper.getIsSamePosition(Helper.doubleArrayToIntArray(log.getPosition()), position)) {
				return log;
			}
		}
		return null;
	}

	/**
	 * A set containing all the logs in this world.
	 */
	private Set<Log> logs = new HashSet<>();

	/**
	 * Return a set of all the logs in this world.
	 */
	public Set<Boulder> getBoulders() {
		return this.boulders;
	}

	/**
	 * Add the given boulder to this world.
	 * 
	 * @param boulder
	 *            The boulder to add
	 * @post the given boulder is added to the set of boulders of this world |
	 *       this.boulders.add(boulder)
	 */
	public void addBoulder(Boulder boulder) {
		this.boulders.add(boulder);

	}

	/**
	 * Remove the given boulder from this world.
	 * 
	 * @param boulder
	 *            The boulder to remove from this world.
	 * @post the given boulder is removed from the set of boulders of this world
	 *       | this.boulders.remove(boulder)
	 * @throws IllegalArgumentException
	 *             The boulder is not in this world. |
	 *             !boulders.contains(boulder)
	 */
	public void removeBoulder(Boulder boulder) throws IllegalArgumentException {
		if (!this.getBoulders().contains(boulder)) {
			throw new IllegalArgumentException("The boulder is not in this world.");
		} else {
			boulders.remove(boulder);
		}
	}

	/**
	 * Return the boulder at the given position.
	 * 
	 * @param position
	 *            The position to examine.
	 * @return a boulder on the given position if there is at least one
	 * @return null if there is no boulder on that position
	 */
	public Boulder getBoulder(int[] position) {
		for (Boulder boulder : this.getBoulders()) {
			if (Helper.getIsSamePosition(Helper.doubleArrayToIntArray(boulder.getPosition()), position)) {
				return boulder;
			}
		}
		return null;
	}

	/**
	 * A set containing all the boulders in this world.
	 */
	private Set<Boulder> boulders = new HashSet<>();

	/**
	 * Terminate this world.
	 */
	public void terminate() {
		this.isTerminated = true;
	}

	/**
	 * Return whether the world is terminated.
	 */
	public boolean getIsTerminated() {
		return this.isTerminated;
	}

	/**
	 * Variable registering whether the world is terminated.
	 */
	private boolean isTerminated;

	/**
	 * A set containing the positions of all the workshops in this world.
	 */
	private Set<double[]> workshops = new HashSet<>();

	/**
	 * Make a set of double arrays with the positions of all the workshops in
	 * the world
	 * 
	 * @post workshops contains double arrays with the positions of all the
	 *       workshops in the world
	 */
	private void makeListWorkshops() {
		for (int x = 0; x < this.getNbCubesX(); x++) {
			for (int y = 0; y < this.getNbCubesY(); y++) {
				for (int z = 0; z < this.getNbCubesZ(); z++) {
					if (getCubeType(x, y, z) == WORKSHOP) {
						workshops.add(new double[] { x + .5, y + .5, z + .5 });
					}
				}
			}
		}
	}

	/**
	 * Return all the workshops in this world.
	 *  
	 * @return a set of the positions of all the workshops in this world
	 */
	private Set<double[]> getWorkshops() {
		return this.workshops;
	}

	/**
	 * Return the boulder that is closest to the given unit in distance.
	 * 
	 * @param unit
	 *            The observed unit.
	 * @return result == boulder nearest to the given unit
	 */
	public Boulder getNearestBoulder(Unit unit) {
		double distance = Double.POSITIVE_INFINITY;
		Boulder nearestBoulder = null;
		for (Boulder boulder : this.getBoulders()) {
			double newDistance = Helper.getDistanceBetweenPositions(unit.getPosition(), boulder.getPosition());
			if (newDistance < distance) {
				distance = newDistance;
				nearestBoulder = boulder;
			}
		}
		return nearestBoulder;
	}

	/**
	 * Return the log that is closest to the given unit in distance.
	 * 
	 * @param unit
	 *            The observed unit.
	 * @return result == log nearest to the given unit
	 */
	public Log getNearestLog(Unit unit) {
		double distance = Double.POSITIVE_INFINITY;
		Log nearestLog = null;
		for (Log log : this.getLogs()) {
			double newDistance = Helper.getDistanceBetweenPositions(unit.getPosition(), log.getPosition());
			if (newDistance < distance) {
				distance = newDistance;
				nearestLog = log;
			}
		}
		return nearestLog;
	}

	/**
	 * Return the position of the workshop that is closest to the given unit in
	 * distance.
	 * 
	 * @param unit
	 *            The observed unit.
	 * @return result == int array with coordinates of workshop nearest to the
	 *         given unit
	 */
	public int[] getNearestWorkhop(Unit unit) {
		double distance = Double.POSITIVE_INFINITY;
		double[] nearestWorkshop = null;
		for (double[] workshop : this.getWorkshops()) {
			double newDistance = Helper.getDistanceBetweenPositions(unit.getPosition(), workshop);
			if (newDistance < distance) {
				distance = newDistance;
				nearestWorkshop = workshop;
			}
		}
		return Helper.doubleArrayToIntArray(nearestWorkshop);
	}

	/**
	 * Return the position of the enemy that is closest to the given unit in
	 * distance.
	 * 
	 * @param unit
	 *            The observed unit.
	 * @return result == unit nearest to the given unit who's from a different
	 *         faction
	 */
	public Unit getNearestEnemy(Unit unit) {
		double distance = Double.POSITIVE_INFINITY;
		Unit nearestEnemy = null;
		for (Unit enemy : this.getUnits()) {
			if (enemy.getFaction() != unit.getFaction()) {
				double newDistance = Helper.getDistanceBetweenPositions(unit.getPosition(), enemy.getPosition());
				if (newDistance < distance) {
					distance = newDistance;
					nearestEnemy = enemy;
				}
			}
		}
		return nearestEnemy;
	}

	/**
	 * Return the position of the friend that is closest to the given unit in
	 * distance.
	 * 
	 * @param unit
	 *            The observed unit.
	 * @return result == unit nearest to the given unit who's from the same
	 *         faction
	 */
	public Unit getNearestFriend(Unit unit) {
		double distance = Double.POSITIVE_INFINITY;
		Unit nearestFriend = null;
		for (Unit friend : this.getUnits()) {
			if (friend.getFaction() == unit.getFaction() && unit != friend) {
				double newDistance = Helper.getDistanceBetweenPositions(unit.getPosition(), friend.getPosition());
				if (newDistance < distance) {
					distance = newDistance;
					nearestFriend = friend;
				}
			}
		}
		return nearestFriend;
	}

	/**
	 * Return the position of the unit that is closest to the given unit in
	 * distance.
	 * 
	 * @param unit
	 *            The observed unit.
	 * @return result == unit nearest to the given unit
	 */
	public Unit getNearestUnit(Unit unit) {
		double distance = Double.POSITIVE_INFINITY;
		Unit nearestUnit = null;
		for (Unit other : this.getUnits()) {
			if (unit != other) {
				double newDistance = Helper.getDistanceBetweenPositions(unit.getPosition(), other.getPosition());
				if (newDistance < distance) {
					distance = newDistance;
					nearestUnit = other;
				}
			}
		}
		return nearestUnit;
	}
}
