import java.util.HashMap;
import java.util.HashSet;

/**
 * The WorldModel class is part of the IPPO Assignment 2
 * @author Karen Trippler (s1642764)
 */
public class Location {

    private HashMap<Direction, Location> exits;
    private HashMap<Direction, LocationWall> walls;

    public Location() {
        //need HashMap of all Walls for each Location
        exits = new HashMap<Direction, Location>();
        //HashMap holding the Walls for each Location
        walls = new HashMap<Direction,LocationWall>();
    }

    /**
     *  function that sets the exits for each location into a HashMap
     * @param direction that the exit should be in
     * @param neighbor location that the exit leads to
     */
    public void setExit(Direction direction, Location neighbor){
        exits.put(direction, neighbor);
    }

    /**
     * function that sets the walls of each location
     * @param direction which way the wall is facing
     * @param wall which wall object is associated with the direction
     */
    public void setWalls(Direction direction, LocationWall wall){
        walls.put(direction, wall);
    }

    /**
     * returns the Wall that is associated with the current direction
     * @param newDirection gets the new image
     * @return returns the new wall
     */
    public LocationWall getWall(Direction newDirection){
        return walls.get(newDirection);
    }

    /**
     * gets the exit to a new location
     * @param exitDirection the direction the exit should be taken
     * @return returns the new Location the exit lead to
     */
    public Location getExit(Direction exitDirection){
        return exits.get(exitDirection);
    }

}
