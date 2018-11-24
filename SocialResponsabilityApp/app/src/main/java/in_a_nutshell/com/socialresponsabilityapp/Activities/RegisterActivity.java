package in_a_nutshell.com.socialresponsabilityapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in_a_nutshell.com.socialresponsabilityapp.Models.RegistrationModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.UserModel;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.SocialResponsabilityApp;
import in_a_nutshell.com.socialresponsabilityapp.Utils.ApiUtils;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    //Global Context
    private SocialResponsabilityApp appContext;

    //API Helpers
    public Retrofit retrofit;
    public ApiUtils apiUtils;

    //Widgets
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPassEditText;
    private Button registerButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameET);
        passwordEditText = findViewById(R.id.passwordET);
        registerButton = findViewById(R.id.registerBtn);
        confirmPassEditText = findViewById(R.id.confirmPassET);
        backButton = findViewById(R.id.backBtn);

        appContext = (SocialResponsabilityApp) getApplicationContext();

        usernameEditText.setText("andrei.matei2010@yahoo.com");
        passwordEditText.setText("andrei2001");
        confirmPassEditText.setText("andrei2001");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButton.setEnabled(false);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confPassword = confirmPassEditText.getText().toString();
                if(username.isEmpty()) {
                    usernameEditText.setError("The email address cannot be empty");
                    registerButton.setEnabled(true);
                } else if(!username.contains("@") || !username.substring(username.indexOf("@")).contains(".")) {
                    usernameEditText.setError("Not a valid email address");
                    registerButton.setEnabled(true);
                } else if(password.isEmpty()) {
                    passwordEditText.setError("The password cannot be empty");
                    registerButton.setEnabled(true);
                } else if(confPassword.isEmpty()) {
                    confirmPassEditText.setError("Please confirm password");
                    registerButton.setEnabled(true);
                } else if(!confPassword.equals(password)) {
                    passwordEditText.setError("Passwords don't match");
                    confirmPassEditText.setError("Passwords don't match");
                } else {
                    RegistrationModel registrationModel = new RegistrationModel();
                    registrationModel.setEmail(username);
                    registrationModel.setPassword(password);
                    Intent intent = new Intent(RegisterActivity.this, RegistrationFragmentsActivity.class);
                    intent.putExtra("registration_obj", registrationModel);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
