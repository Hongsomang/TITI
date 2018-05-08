package dsm.titi.Activity.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-05-06.
 */

public class DB_Save extends RealmObject {
    private int image;
    private String title;
    private String content;
    private String address;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
