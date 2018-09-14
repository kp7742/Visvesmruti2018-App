package fetr.ac.in.visvesmruti2018.models;

import android.graphics.drawable.Drawable;

public class EventModel {
    private String Name;
    private String WebPage;
    private Drawable icon;

    public EventModel(String Name, String WebPage, Drawable icon){
        this.Name = Name;
        this.WebPage = WebPage;
        this.icon = icon;
    }

    public String getName() {
        return Name;
    }

    public String getWebPage() {
        return WebPage;
    }

    public Drawable getIcon() {
        return icon;
    }
}
