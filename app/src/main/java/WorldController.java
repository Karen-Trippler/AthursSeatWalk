import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import java.util.HashMap;
import java.util.HashSet;


/**
 * The WorldController class was developed as part of IPPO Assignment 2
 * @author: Karen Trippler (s1642764)
 * @version: 1.0
 */
public class WorldController {


    @FXML
    private ImageView imageView;
    @FXML
    private ImageView basketView;
    @FXML
    private Button forwardButton;
    @FXML
    private Button ball_1;
    @FXML
    private Button ball_2;
    @FXML
    private ImageView ballView1;

    private WorldModel world;
    private HashMap<Button, String> buttonItemMap;
    private HashMap<String, Button> itemButtonMap;
    private HashMap<String, ImageView> itemView;


    /**
     * Initialise is called by the class App to which initialises the whole world
     */
    public void Initialise() {
        //new Model
        world = new WorldModel();

        //showing the initial starting picture
        Image image = new Image("L1_North.jpg");
        imageView.setImage(image);
        forwardButton.setDisable(true);


        //initialising the HashMap that will associate the button with the item
        buttonItemMap = new HashMap<>();
        //initialising the HashMap that will associate the item with the button
        itemButtonMap = new HashMap<>();
        //relates the items to the view
        itemView = new HashMap<>();

        //populating the button-item HashMap
        buttonItemMap.put(ball_1, "Ball1");
        buttonItemMap.put(ball_2, "Ball2");
        //populating the item-button HashMap
        itemButtonMap.put("Ball1", ball_1);
        itemButtonMap.put("Ball2", ball_2);
        //populates the item-ImageView HashMap
        itemView.put("Ball1", basketView);
        itemView.put("Ball2", ballView1);

    }


    /**
     * updates the view for the user whenever the internal model is changed
     */
    private void show_Scene() {
        //Large Location Image Update
        Image currentView = world.getPlayerImage();
        imageView.setImage(currentView);

        //Item Update
        //Updates the views and buttons for the items that are currently carried by the player
        //(not displayed anywhere in the world)
        HashSet<String> placableItems = world.getPlayerItems();
        if (placableItems != null) {
            for (String item : placableItems) {
                itemButtonMap.get(item).setDisable(false);
                itemView.get(item).setImage(null);
            }
        }

        //Updates the views and buttons for the items that are hidden in a Location/Direction that is not faced
        HashSet<String> hiddenItems = world.getHiddenItems();
        if (hiddenItems != null) {
            for (String hiddenItem : hiddenItems) {
                itemButtonMap.get(hiddenItem).setDisable(true);
                itemView.get(hiddenItem).setImage(null);
            }
        }

        //Updates the views and buttons for the items that are currently visible
        HashSet<String> visibleItems = world.getVisibleItems();
        if (visibleItems != null) {
            for (String visibleItem : visibleItems) {
                Image itemImage = world.getItemImage(visibleItem);
                itemView.get(visibleItem).setImage(itemImage);
                itemButtonMap.get(visibleItem).setDisable(false);
            }
        }

        //adjusting move forward button
        if (world.has_exit()) {
            forwardButton.setDisable(false);
        } else {
            forwardButton.setDisable(true);
        }
    }

    /**
     * updates the Model when the right turn button in the view is clicked
     * @param event (Button that was clicked in the View)
     */
    public void turnr(ActionEvent event) {
        world.turnright();
        show_Scene();
    }

    /**
     * updates the Model when the left turn button in the view is clicked
     * @param event (Button that was clicked in the View)
     */
    public void turnl(ActionEvent event){
        world.turnleft();
        show_Scene();
    }

    /**
     * updates the Model when the forward button in the view is clicked
     * @param event (Button that was clicked in the View)
     */
    public void mforward(ActionEvent event) {
        world.moveforward();
        show_Scene();
    }

    /**
     * updates the Model when one of the item buttons is clicked
     * @param event (Button that was clicked in the View)
     */
    public void item_event(ActionEvent event) {
        //identifies the button that was clicked
        Button clickedButton = (Button) event.getSource();
        //associates the button with the item via a HashMap
        String clickedItem = buttonItemMap.get(clickedButton);
        //initiates the Model change depending on the current view of the item
        if (itemView.get(clickedItem).getImage() == null){
            world.putDown_item(clickedItem);
        }
        else {
            world.picUp_item(clickedItem);
        }
        show_Scene();
    }

}
