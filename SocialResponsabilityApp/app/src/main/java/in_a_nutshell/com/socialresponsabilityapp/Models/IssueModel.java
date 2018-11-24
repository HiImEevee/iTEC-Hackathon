package in_a_nutshell.com.socialresponsabilityapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

public class IssueModel {

    @SerializedName("Id")
    @Expose
    private UUID id;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Latitude")
    @Expose
    private double latitude;

    @SerializedName("Longitude")
    @Expose
    private double longitude;

    @SerializedName("UpVotes")
    @Expose
    private int upVotes;

    @SerializedName("DownVotes")
    @Expose
    private int downVotes;

    @SerializedName("CreatedAt")
    @Expose
    private long createdAt;

    @SerializedName("CreatedBy")
    @Expose
    private UUID createdBy;

    @SerializedName("Creator")
    @Expose
    private String creator;

    @SerializedName("Comments")
    @Expose
    private List<CommentModel> comments;

    @SerializedName("Images")
    @Expose
    private List<String> images;

    public IssueModel() {
    }

    public IssueModel(UUID id, String title, String description, double latitude, double longitude, int upVotes, int downVotes, long createdAt, UUID createdBy, String creator, List<CommentModel> comments, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.creator = creator;
        this.comments = comments;
        this.images = images;
    }

    public IssueModel(UUID id, String title, String description, double latitude, double longitude, UUID createdBy, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdBy = createdBy;
        this.images = images;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "IssueModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", creator='" + creator + '\'' +
                ", comments=" + comments +
                ", images=" + images +
                '}';
    }
}
