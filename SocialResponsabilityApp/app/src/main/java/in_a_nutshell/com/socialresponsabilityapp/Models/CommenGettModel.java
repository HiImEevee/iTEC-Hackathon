package in_a_nutshell.com.socialresponsabilityapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class CommenGettModel {
    @SerializedName("Id")
    @Expose
    private UUID id;

    @SerializedName("Content")
    @Expose
    private String content;

    @SerializedName("Creator")
    @Expose
    private String creator;

    @SerializedName("CreatedAt")
    @Expose
    private long createdAt;

    @SerializedName("EditedAt")
    @Expose
    private long editedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(long editedAt) {
        this.editedAt = editedAt;
    }

    public CommenGettModel(UUID id, String content, String creator, long createdAt, long editedAt) {


        this.id = id;
        this.content = content;
        this.creator = creator;
        this.createdAt = createdAt;
        this.editedAt = editedAt;
    }

    @Override
    public String toString() {
        return "CommenGettModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                ", createdAt=" + createdAt +
                ", editedAt=" + editedAt +
                '}';
    }
}
