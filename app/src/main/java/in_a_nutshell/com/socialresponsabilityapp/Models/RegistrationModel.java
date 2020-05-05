package in_a_nutshell.com.socialresponsabilityapp.Models;

import java.io.Serializable;
import java.util.UUID;

public class RegistrationModel implements Serializable {
    private UUID id;
    private String fullName;
    private String email;
    private double latitude;
    private double longitude;
    private int radius;
    private int age;
    private int gender;
    private String password;
    private String profilePicture;

    public RegistrationModel() {
    }

    public RegistrationModel(UUID id, String fullName, String email, double latitude, double longitude, int radius, int age, int gender, String password, String profilePicture) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "RegistrationModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                ", age=" + age +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
