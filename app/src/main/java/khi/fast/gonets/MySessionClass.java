package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 03-Jan-18.
 */

public class MySessionClass {

    private String UID;
    private String ID;
    private String Username;
    private String Picture;
    private String Name;
    private String Time;
    private String message;
    private int noOfPeopleGoing;

    public MySessionClass(String UID, String ID, String username, String picture, String name, String time, String message, int noOfPeopleGoing) {
        this.UID = UID;
        this.ID = ID;
        Username = username;
        Picture = picture;
        Name = name;
        Time = time;
        this.message = message;
        this.noOfPeopleGoing = noOfPeopleGoing;
    }

    public MySessionClass() {
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNoOfPeopleGoing() {
        return noOfPeopleGoing;
    }

    public void setNoOfPeopleGoing(int noOfPeopleGoing) {
        this.noOfPeopleGoing = noOfPeopleGoing;
    }
}
