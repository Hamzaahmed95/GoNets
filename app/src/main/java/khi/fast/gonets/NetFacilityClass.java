package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 02-Jan-18.
 */

public class NetFacilityClass {
    private String Name;
    private String Stars;
    private String Pictures;
    private String PhoneNo;
    private String Email;
    private String Details;
    private String Address;
    private String Location;


    public NetFacilityClass(String name, String stars, String pictures, String phoneNo, String email, String details, String address, String location) {
        Name = name;
        Stars = stars;
        Pictures = pictures;
        PhoneNo = phoneNo;
        Email = email;
        Details = details;
        Address = address;
        Location = location;
    }

    public NetFacilityClass() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStars() {
        return Stars;
    }

    public void setStars(String stars) {
        Stars = stars;
    }

    public String getPictures() {
        return Pictures;
    }

    public void setPictures(String pictures) {
        Pictures = pictures;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
