package dsm.titi.Activity.DB;

import io.realm.RealmObject;

/**
 * Created by ghdth on 2018-05-06.
 */

public class DB_Save extends RealmObject {
    private String title;
    private String content;
    private String address;
    private String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
