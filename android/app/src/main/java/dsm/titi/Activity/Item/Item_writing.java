package dsm.titi.Activity.Item;

import android.content.ClipData;

/**
 * Created by ghdth on 2018-05-22.
 */

public class Item_writing {
    private String title;
    private String address;
    private String content;
    private String date;

    public Item_writing(String title,String address,String content,String date){
        this.title=title;
        this.address=address;
        this.content=content;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
