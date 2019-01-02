package app.lloyd.com.mocktestapp.loginorsignin;

import java.util.List;

public final class Users {

    private String userId;
    private int numOfPlaces;
    private long phoneNumber;

    public long getPhoneNo() {
        return phoneNumber;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNumber = phoneNo;
    }

    private List<Places> places;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumOfPlaces() {
        return numOfPlaces;
    }

    public void setNumOfPlaces(int numOfPlaces) {
        this.numOfPlaces = numOfPlaces;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void setPlaces(List<Places> places) {
        this.places = places;
    }
}
