import javafx.scene.image.Image;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The WorldModel class is part of the IPPO Assignment 2
 * @author Karen Trippler (s1642764)
 */
public class WorldModel {

    private HashMap<Static_Item, Pair> placedItems;
    private HashMap<String, Static_Item> stringToItemMap;
    private Player player1;


    public WorldModel() {
        //new HashMap (ItemName --> Item)
        stringToItemMap = new HashMap<>();
        //new HashMap that will hold the items that are currently placed somewhere in the world
        placedItems = new HashMap<>();
        //creates the World
        createWorld();
    }


    /**
     * Function that handles an item being placed in the world
     */
    public void putDown_item(String item_name){
        //itentifies the item by name
        Static_Item placed_Item = stringToItemMap.get(item_name);
        //removes the item from the players pocket and returns a Pair(location, Direction) on where it was placed
        Pair itemPlace = player1.putDownItem(placed_Item);
        //updates the HashMap that keeps track of the items in the world
        placedItems.put(placed_Item, itemPlace);
    }


    /**
     *Function that handles when an item is picked up from the world
     */
    public void picUp_item(String item_name){
        //itentifies the item by name
        Static_Item picked_Item = stringToItemMap.get(item_name);
        //adds the item to the players pocket
        player1.pickUpItem(picked_Item);
        //removes the item from the world
        placedItems.remove(picked_Item);
    }

    /**
     *updates the players direction (right)
     */
    public void turnright(){
        player1.turnright();
    }

    /**
     *updates the players direction (left)
     */
    public void turnleft(){
        //updates the players Direction
        player1.turnleft();
    }

    /**
     *tests if the current location and direction for player 1 has an exit
     */
    public boolean has_exit(){
        //tests if the current Wall has an exit
        return player1.canexit();
    }

    /**
     * updates the players location
     */
    public void moveforward(){
        player1.go();
    }

    /**
     *returns the current image of the Location and Direction the player is facing
     */
    public Image getPlayerImage(){
        return player1.getCurrentImage();
    }

    /**
     *gets the Image of an item
     * @param item Name of the item
     */
    public Image getItemImage(String item){
        //identifies the item by its name
        Static_Item requestedItem = stringToItemMap.get(item);
        //gets the correct picture
        return requestedItem.getItemPicture();
    }

    /**
     *gets the items that are still carried by the player
     * @return HashSet with all player item names
     */
    public HashSet<String> getPlayerItems(){
        //generates a new HashSet that will hold all items that are currently in the player's posession
        HashSet<String> stringcarriedItems = new HashSet<String>();
        //tests if the player is holding any items
        if (!player1.getCarriedItems().isEmpty()) {
            //fetches the list of items that are carried by the player
            for (Static_Item carrieditem : player1.getCarriedItems()) {
                //adds the items to the HashSet
                stringcarriedItems.add(carrieditem.getName());
            }
        }
        return stringcarriedItems;
    }

    /**
     *gets the items that are currently visible
     * @return HashSet with all item names that are in the current Location+Direction
     */
    public HashSet<String> getVisibleItems(){
        //fetches the player current (Location, Direction) as a pair
        Pair playerPosition = player1.getcurrentLocatinPair();
        //generates new HashSet that will hold all item names
        HashSet<String> visibleItems = new HashSet<String>();
        //tests if no item has been placed yet
        if (placedItems != null) {
            //iterates through all placed down items
            for (Static_Item item : placedItems.keySet()) {
                //need all items that match the players current location and Direction
                if (placedItems.get(item).equals(playerPosition)) {
                    visibleItems.add(item.getName());
                }
            }
        }
        return visibleItems;
    }

    /**
     *gets all the items that are not in the current Location but are placed somewhere else
     * @return HashSet with all item names that are placed and not in the current Location
     */
    public HashSet<String> getHiddenItems(){
        //fetches the player current (Location, Direction) as a pair
        Pair playerPosition = player1.getcurrentLocatinPair();
        //generates new HashSet that will hold all item names
        HashSet<String> hiddenItems = new HashSet<String>();
        //tests if no item has been placed yet
        if (placedItems != null) {
            //iterates through all placed down items
            for (Static_Item item : placedItems.keySet()) {
                //need all items that match the players current location and Direction
                if (!placedItems.get(item).equals(playerPosition)) {
                    hiddenItems.add(item.getName());
                }
            }
        }
        return hiddenItems;
    }

    /**
     * Creates the whole world with Player, Location, Walls and Images
     */
    private void createWorld() {
        //create all Location objects
        Location Arthur1 = new Location();
        Location Arthur2 = new Location();
        Location Arthur3 = new Location();
        Location Arthur4 = new Location();
        //creating all the images
        //Image Location 1
        Image Arthur1_N = new Image("L1_North.jpg");
        Image Arthur1_S = new Image("L1_South.jpg");
        Image Arthur1_W = new Image("L1_West.jpg");
        Image Arthur1_E = new Image("L1_East.jpg");
        //Image Location 2
        Image Arthur2_N = new Image("L2_North.jpg");
        Image Arthur2_S = new Image("L2_South.jpg");
        Image Arthur2_W = new Image("L2_West.jpg");
        Image Arthur2_E = new Image("L2_East.jpg");
        //Image Location 3
        Image Arthur3_N = new Image("L3_North.jpg");
        Image Arthur3_S = new Image("L3_South.jpg");
        Image Arthur3_W = new Image("L3_West.jpg");
        Image Arthur3_E = new Image("L3_East.jpg");
        //Images Location 4
        Image Arthur4_N = new Image("L4_North.jpg");
        Image Arthur4_S = new Image("L4_South.jpg");
        Image Arthur4_W = new Image("L4_West.jpg");
        Image Arthur4_E = new Image("L4_East.jpg");

        //set all walls for the Location
        //Location 1
        LocationWall Arthur1_North = new LocationWall(Arthur1_N);
        LocationWall Arthur1_South = new LocationWall(Arthur1_S);
        LocationWall Arthur1_West = new LocationWall(Arthur1_W);
        LocationWall Arthur1_East = new LocationWall(Arthur1_E);
        //Location 2
        LocationWall Arthur2_North = new LocationWall(Arthur2_N);
        LocationWall Arthur2_South = new LocationWall(Arthur2_S);
        LocationWall Arthur2_West = new LocationWall(Arthur2_W);
        LocationWall Arthur2_East = new LocationWall(Arthur2_E);
        //Location 3
        LocationWall Arthur3_North = new LocationWall(Arthur3_N);
        LocationWall Arthur3_South = new LocationWall(Arthur3_S);
        LocationWall Arthur3_West = new LocationWall(Arthur3_W);
        LocationWall Arthur3_East = new LocationWall(Arthur3_E);
        //Location 4
        LocationWall Arthur4_North = new LocationWall(Arthur4_N);
        LocationWall Arthur4_South = new LocationWall(Arthur4_S);
        LocationWall Arthur4_West = new LocationWall(Arthur4_W);
        LocationWall Arthur4_East = new LocationWall(Arthur4_E);

        //setting the corresponding Wall to the location
        //Location 1
        Arthur1.setWalls(Direction.NORTH, Arthur1_North);
        Arthur1.setWalls(Direction.SOUTH, Arthur1_South);
        Arthur1.setWalls(Direction.WEST, Arthur1_West);
        Arthur1.setWalls(Direction.EAST, Arthur1_East);
        //Location 2
        Arthur2.setWalls(Direction.NORTH, Arthur2_North);
        Arthur2.setWalls(Direction.SOUTH, Arthur2_South);
        Arthur2.setWalls(Direction.WEST, Arthur2_West);
        Arthur2.setWalls(Direction.EAST, Arthur2_East);
        //Location 3
        Arthur3.setWalls(Direction.NORTH, Arthur3_North);
        Arthur3.setWalls(Direction.SOUTH, Arthur3_South);
        Arthur3.setWalls(Direction.WEST, Arthur3_West);
        Arthur3.setWalls(Direction.EAST, Arthur3_East);
        //Location 4
        Arthur4.setWalls(Direction.NORTH, Arthur4_North);
        Arthur4.setWalls(Direction.SOUTH, Arthur4_South);
        Arthur4.setWalls(Direction.WEST, Arthur4_West);
        Arthur4.setWalls(Direction.EAST, Arthur4_East);

        //set all exits for the Location
        Arthur1.setExit(Direction.SOUTH, Arthur2);
        Arthur2.setExit(Direction.NORTH, Arthur1);
        Arthur2.setExit(Direction.EAST, Arthur3);
        Arthur3.setExit(Direction.WEST, Arthur2);
        Arthur2.setExit(Direction.SOUTH, Arthur4);
        Arthur4.setExit(Direction.EAST, Arthur2);

        //Initiates the player
        player1 = new Player(Arthur1, Direction.NORTH);

        //Initiates the Items
        //item Images
        Image ball1 = new Image("bauble.png");
        Image ball2 = new Image("bauble-1.png");
        //generate all items
        Static_Item ball_item1 = new Static_Item(ball1, "Ball1");
        Static_Item ball_item2 = new Static_Item(ball2, "Ball2");
        //Associates the Name of the Items with the item itself
        stringToItemMap.put(ball_item1.getName(), ball_item1);
        stringToItemMap.put(ball_item2.getName(), ball_item2);
        //places the items into the "bag" of the player
        player1.pickUpItem(ball_item1);
        player1.pickUpItem(ball_item2);
    }

}
