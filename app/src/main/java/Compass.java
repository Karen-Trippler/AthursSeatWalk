import java.util.HashMap;

public class Compass {
    private Direction currentDirection;
    private HashMap<Direction, Direction> directionToLeft;
    private HashMap <Direction, Direction> directionToRight;

    public Compass() {
        //sets the current Direction to NORTH
        currentDirection = Direction.NORTH;

        //initialises the HashMaps needed for the next direction
        directionToRight = new HashMap<Direction, Direction>();
        directionToLeft = new HashMap<Direction, Direction>();

        //Filly the HashMaps with their values
        fillDirectionToLeft();
        fillDirectionToRight();
    }

    private void fillDirectionToRight(){
        directionToRight.put(Direction.NORTH, Direction.EAST);
        directionToRight.put(Direction.EAST, Direction.SOUTH);
        directionToRight.put(Direction.SOUTH, Direction.WEST);
        directionToRight.put(Direction.WEST, Direction.NORTH);
    }

    private void fillDirectionToLeft(){
        directionToLeft.put(Direction.NORTH, Direction.WEST);
        directionToLeft.put(Direction.WEST, Direction.SOUTH);
        directionToLeft.put(Direction.SOUTH, Direction.EAST);
        directionToLeft.put(Direction.EAST, Direction.NORTH);
    }

    //@returns the enum type of the Direction that is currently set
    public Direction getCurrentDirection(){
        return currentDirection;
    }

    //@returns the enum of the Direction to the right
    public Direction nextright(){
        //updates the current direction to the one on the right
        currentDirection =  directionToRight.get(currentDirection);
        return currentDirection;
    }

    //@returns the enum of the Direction to the right
    public Direction nextleft(){
        //updates the current direction to the one on the left
        currentDirection = directionToLeft.get(currentDirection);
        return currentDirection;
    }
}
