package khi.fast.gonets;

import android.app.Notification;

/**
 * Created by Hamza Ahmed on 04-Jan-18.
 */

public class UserProfile {

    private String Id;
    private String name;
    private String ScreenName;
    private String Skills;
    private String FavNets;
    private int EmailNotification;

    public UserProfile(String id, String name, String screenName, String skills, String favNets, int emailNotification) {
        Id = id;
        this.name = name;
        ScreenName = screenName;
        Skills = skills;
        FavNets = favNets;
        EmailNotification = emailNotification;
    }

    public UserProfile() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return ScreenName;
    }

    public void setScreenName(String screenName) {
        ScreenName = screenName;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getFavNets() {
        return FavNets;
    }

    public void setFavNets(String favNets) {
        FavNets = favNets;
    }

    public int getEmailNotification() {
        return EmailNotification;
    }

    public void setEmailNotification(int emailNotification) {
        EmailNotification = emailNotification;
    }
}
