package in_a_nutshell.com.socialresponsabilityapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationModel {

    @SerializedName("Data")
    @Expose
    private UserModel data;

    @SerializedName("StatusCode")
    @Expose
    private int statusCode;

    @SerializedName("Success")
    @Expose
    private Boolean isSuccessful;

    public AuthorizationModel(UserModel data, int statusCode, Boolean isSuccessful) {
        this.data = data;
        this.statusCode = statusCode;
        this.isSuccessful = isSuccessful;
    }

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean succesful) {
        this.isSuccessful = succesful;
    }

    @Override
    public String toString() {
        return "AuthorizationModel{" +
                "data=" + data +
                ", statusCode=" + statusCode +
                ", isSuccesul=" + isSuccessful +
                '}';
    }
}
