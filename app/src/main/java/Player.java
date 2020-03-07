import javafx.scene.image.Image;
import java.util.HashSet;
import javafx.util.Pair;

/**
 * The WorldModel class is part of the IPPO Assignment 2
 * @author Karen Trippler (s1642764)
 */
public class Player {

    private Location currentLocation;
    private Direction currentDirection;
    private HashSet<Static_Item> carriedItems;
    private Compass playerCompass;

    public Player(Location startLocation, Direction startDirection){
        currentLocation = startLocation;
        currentDirection = startDirection;
        playerCompass = new Compass();
        carriedItems = new HashSet<>();
    }

    /**
     * function that puts down an item in the world and removes it from the players bag
     * @param item to be placed down
     * @return returns a pair of Location and Direction for the Model to store
     */
    public Pair<Location, Direction> putDownItem(Static_Item item){
        carriedItems.remove(item);
        Pair<Location, Direction> itemPlace = new Pair<Location, Direction>(currentLocation, currentDirection);
        return itemPlace;
    }


    /**
     * puts the item back into the players bag when it is picked up
     * @param item to be picked up again
     */
    public void pickUpItem(Static_Item item) {
        carriedItems.add(item);
    }

    /**
     * turns the player to the right
     */
    public void turnright(){
        currentDirection = playerCompass.nextright();
    }

    /**
     * turns the player to the left
     */
    public void turnleft(){
        currentDirection = playerCompass.nextleft();
    }

    /**
     * moves the player from one Location to the next
     */
    public void go(){
        if (canexit()){
            currentLocation = currentLocation.getExit(currentDirection);
        }
    }

    /**
     * tests if the current Location has an exit
     * @return boolean (false if no exit, true if there is an exit)
     */
    public boolean canexit(){
        return currentLocation.getExit(currentDirection) != null;
    }

    /**
     * gets the current Image from the Location
     * @return the Image of the current Location and Direction
     */
    public Image getCurrentImage(){
        return currentLocation.getWall(currentDirection).getWallPicture();
    }

    /**
     * function that gets all items in the players bag
     * @return HashSet of all items in the players bag
     */
    public HashSet<Static_Item> getCarriedItems(){
        return carriedItems;
    }

    /**
     * gets the players current Location and Direction
     * @return a Pair of Location and Direction
     */
    public Pair<Location, Direction> getcurrentLocatinPair(){
        return new Pair<Location,Direction>(currentLocation, currentDirection);
    }
}
