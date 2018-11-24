package in_a_nutshell.com.socialresponsabilityapp;

import android.app.Application;
import android.util.Log;

import in_a_nutshell.com.socialresponsabilityapp.Models.UserModel;

public class SocialResponsabilityApp extends Application {
    private static final String TAG = "SocialResponsabilityApp";
    private UserModel user;
    private Boolean anonymousUser = false;

    public Boolean getAnonymousUser() {
        return anonymousUser;
    }

    public void setAnonymousUser(Boolean anonymousUser) {
        this.anonymousUser = anonymousUser;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
