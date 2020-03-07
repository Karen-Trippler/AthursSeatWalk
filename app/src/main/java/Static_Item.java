import javafx.scene.image.Image;

/**
 * The Item class is part of the IPPO Assignment 2
 * @author Karen Trippler (s1642764)
 */
public class Static_Item {

    private Image itemImage;
    private String name;

    /**
     * @param itemName Name of the Item
     * @param itemPic Picture associated with the Item
     */
    public Static_Item(Image itemPic, String itemName) {
        name = itemName;
        itemImage = itemPic;
    }


    /**
     *Returns the name of the Item
     */
    public String getName(){
        return name;
    }

    /**
     *returns the Image of the Item
     */
    public Image getItemPicture(){
        return itemImage;
    }

}
