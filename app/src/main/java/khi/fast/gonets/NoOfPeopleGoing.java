package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 12-Jan-18.
 */

public class NoOfPeopleGoing {

    private String uid;
    private String id;

    public NoOfPeopleGoing(String uid, String id) {
        this.uid = uid;
        this.id = id;
    }

    public NoOfPeopleGoing() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
