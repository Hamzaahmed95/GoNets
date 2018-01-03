package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 03-Jan-18.
 */

public class MySessionClass {

    private String Picture;
    private String Name;
    private String Time;

    public MySessionClass(String picture, String name, String time) {
        Picture = picture;
        Name = name;
        Time = time;
    }

    public MySessionClass() {
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }



}
