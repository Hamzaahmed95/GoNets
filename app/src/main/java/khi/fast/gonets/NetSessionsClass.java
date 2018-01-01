package khi.fast.gonets;

/**
 * Created by Hamza Ahmed on 30-Dec-17.
 */

public class NetSessionsClass  {

    private String AcademyId;
    private String AcademyName;
    private String Academyrating;
    private String AcademyIndOutd;
    private String AcademyPictures;
    private String AcademyPhonoNo;
    private String AcademyEmailAddress;
    private String AcademyDetails;
    private String AcademyAddressLocation;
    private String AcademyMap;

    public NetSessionsClass() {
    }

    public NetSessionsClass(String academyId, String academyName, String academyrating, String academyIndOutd, String academyPictures, String academyPhonoNo, String academyEmailAddress, String academyDetails, String academyAddressLocation, String academyMap) {
        AcademyId = academyId;
        AcademyName = academyName;
        Academyrating = academyrating;
        AcademyIndOutd = academyIndOutd;
        AcademyPictures = academyPictures;
        AcademyPhonoNo = academyPhonoNo;
        AcademyEmailAddress = academyEmailAddress;
        AcademyDetails = academyDetails;
        AcademyAddressLocation = academyAddressLocation;
        AcademyMap = academyMap;
    }

    public String getAcademyId() {
        return AcademyId;
    }

    public void setAcademyId(String academyId) {
        AcademyId = academyId;
    }

    public String getAcademyName() {
        return AcademyName;
    }

    public void setAcademyName(String academyName) {
        AcademyName = academyName;
    }

    public String getAcademyrating() {
        return Academyrating;
    }

    public void setAcademyrating(String academyrating) {
        Academyrating = academyrating;
    }

    public String getAcademyIndOutd() {
        return AcademyIndOutd;
    }

    public void setAcademyIndOutd(String academyIndOutd) {
        AcademyIndOutd = academyIndOutd;
    }

    public String getAcademyPictures() {
        return AcademyPictures;
    }

    public void setAcademyPictures(String academyPictures) {
        AcademyPictures = academyPictures;
    }

    public String getAcademyPhonoNo() {
        return AcademyPhonoNo;
    }

    public void setAcademyPhonoNo(String academyPhonoNo) {
        AcademyPhonoNo = academyPhonoNo;
    }

    public String getAcademyEmailAddress() {
        return AcademyEmailAddress;
    }

    public void setAcademyEmailAddress(String academyEmailAddress) {
        AcademyEmailAddress = academyEmailAddress;
    }

    public String getAcademyDetails() {
        return AcademyDetails;
    }

    public void setAcademyDetails(String academyDetails) {
        AcademyDetails = academyDetails;
    }

    public String getAcademyAddressLocation() {
        return AcademyAddressLocation;
    }

    public void setAcademyAddressLocation(String academyAddressLocation) {
        AcademyAddressLocation = academyAddressLocation;
    }

    public String getAcademyMap() {
        return AcademyMap;
    }

    public void setAcademyMap(String academyMap) {
        AcademyMap = academyMap;
    }

}
