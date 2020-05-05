package in_a_nutshell.com.socialresponsabilityapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

public class UserModel{

    @SerializedName("Id")
    @Expose
    private UUID id;

    @SerializedName("FullName")
    @Expose
    private String fullName;

    @SerializedName("Latitude")
    @Expose
    private double latitude;

    @SerializedName("Longitude")
    @Expose
    private double longitude;

    @SerializedName("Radius")
    @Expose
    private double radius;

    @SerializedName("Age")
    @Expose
    private int age;

    @SerializedName("Gender")
    @Expose
    private int gender;

    @SerializedName("ProfilePicture")
    @Expose
    private String profilePicture;

    @SerializedName("Issues")
    @Expose
    private List<IssueModel> issues;

    public UserModel() {
    }

    public UserModel(UserModel userModel) {
        this.id = userModel.id;
        this.fullName = userModel.fullName;
        this.latitude = userModel.latitude;
        this.longitude = userModel.longitude;
        this.radius = userModel.radius;
        this.age = userModel.age;
        this.gender = userModel.gender;
        this.profilePicture = userModel.profilePicture;
        this.issues = userModel.issues;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<IssueModel> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueModel> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                ", age=" + age +
                ", gender=" + gender +
                ", profilePicture='" + profilePicture + '\'' +
                ", issues=" + issues +
                '}';
    }
}
