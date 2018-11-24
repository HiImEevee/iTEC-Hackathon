package in_a_nutshell.com.socialresponsabilityapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in_a_nutshell.com.socialresponsabilityapp.Models.UserModel;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.SocialResponsabilityApp;
import in_a_nutshell.com.socialresponsabilityapp.Utils.ApiUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "LogInActivity";

    //Global Context
    private SocialResponsabilityApp appContext;

    //API Helpers
    public Retrofit retrofit;
    public ApiUtils apiUtils;

    //Widgets
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button logInButton;
    private Button backButton;

    //Shared Preferences
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameEditText = findViewById(R.id.usernameET);
        passwordEditText = findViewById(R.id.passwordET);
        logInButton = findViewById(R.id.logInBtn);
        backButton = findViewById(R.id.backBtn);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        appContext = (SocialResponsabilityApp) getApplicationContext();

        initializeRetrofit();
        usernameEditText.setText(mPreferences.getString("username", ""));
        passwordEditText.setText(mPreferences.getString("password", ""));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInButton.setEnabled(false);
                final String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                if(username.isEmpty()) {
                    usernameEditText.setError("The email address cannot be empty");
                    logInButton.setEnabled(true);
                } else if(!username.contains("@") || !username.substring(username.indexOf("@")).contains(".")) {
                    usernameEditText.setError("Not a valid email address");
                    logInButton.setEnabled(true);
                } else if(password.isEmpty()) {
                    passwordEditText.setError("The password cannot be empty");
                    logInButton.setEnabled(true);
                } else {
                    Call<ResponseBody> call = apiUtils.getToken(username, password, "password");
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String json = response.body().string();
                                JSONObject data = new JSONObject(json);
                                Log.d(TAG, "onResponse: json" + data.toString());
                                if(data.getString(getString(R.string.access_token)).isEmpty()) {
                                    Toast.makeText(LogInActivity.this, data.getString(getString(R.string.error_description)), Toast.LENGTH_LONG).show();
                                    passwordEditText.setText("");
                                } else {
                                    appContext.setUser(new UserModel(new Gson().fromJson(data.getString(getString(R.string.user_data)), UserModel.class)));

                                    mEditor.putString(getString(R.string.preferences_email), username);
                                    mEditor.commit();

                                    mEditor.putString(getString(R.string.preferences_token), data.getString(getString(R.string.access_token)));
                                    mEditor.commit();

                                    mEditor.putString(getString(R.string.preferences_id), appContext.getUser().getId().toString());
                                    mEditor.commit();

                                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                logInButton.setEnabled(true);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                logInButton.setEnabled(true);
                            } catch (IOException e) {
                                e.printStackTrace();
                                logInButton.setEnabled(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d(TAG, "onFailure: Log In" + t.getMessage());
                        }
                    });
                }

            }
        });
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiUtils = retrofit.create(ApiUtils.class);
    }
}
