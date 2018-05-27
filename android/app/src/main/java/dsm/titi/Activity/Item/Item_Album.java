package dsm.titi.Activity.Item;

/**
 * Created by ghdth on 2018-05-22.
 */

public class Item_Album {
    private String Image;
    private String title;
    public Item_Album(String Image,String title){
        this.Image=Image;
        this.title=title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
