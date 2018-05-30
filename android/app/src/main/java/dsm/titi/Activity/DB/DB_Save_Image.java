package dsm.titi.Activity.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-05-27.
 */

public class DB_Save_Image extends RealmObject {
    private String title;
    private String image;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
