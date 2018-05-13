package dsm.titi.Activity.Item;

import android.net.Uri;

/**
 * Created by ghdth on 2018-05-10.
 */

public class Item_Save_Image {
    public Uri image;

    public Item_Save_Image(Uri image){
        this.image=image;
    }
    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
