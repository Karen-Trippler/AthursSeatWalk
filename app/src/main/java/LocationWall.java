import java.util.*;
import javafx.scene.image.Image;

/**
 * The WorldModel class is part of the IPPO Assignment 2
 * @author Karen Trippler (s1642764)
 */
public class LocationWall {

    private Image wallPicture;
    //private HashSet<Static_Item> items;


    /**
     * sets up the wall
     * @param image the image that should be shown for that wall
     */
    public LocationWall(Image image){
        wallPicture = image;
        //items = new HashSet<>();
    }

    /**
     * @return the picture associated with the wall
     */
    public Image getWallPicture(){
        return wallPicture;
    }
}
