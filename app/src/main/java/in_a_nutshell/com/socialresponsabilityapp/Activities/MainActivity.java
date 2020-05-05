package in_a_nutshell.com.socialresponsabilityapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import in_a_nutshell.com.socialresponsabilityapp.Adapters.CustomRecyclerViewAdapter;
import in_a_nutshell.com.socialresponsabilityapp.Adapters.SectionsPageAdapter;
import in_a_nutshell.com.socialresponsabilityapp.Fragments.MainFragments.HomeFragment;
import in_a_nutshell.com.socialresponsabilityapp.Models.AuthorizationModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.IssueModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.UserModel;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.SocialResponsabilityApp;
import in_a_nutshell.com.socialresponsabilityapp.Utils.ApiUtils;
import in_a_nutshell.com.socialresponsabilityapp.Views.CustomViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    //API Helpers
    private Retrofit retrofit;
    private ApiUtils apiUtils;

    //Vars
    private List<IssueModel> issueList;
    private SocialResponsabilityApp appContext;

    //Widgets
    private CustomViewPager viewPager;
    private BottomNavigationView navigationView;
    private SectionsPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = (SocialResponsabilityApp) getApplicationContext();

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor = mPreferences.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeRetrofit();
        initializeUser();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiUtils = retrofit.create(ApiUtils.class);
    }

    public ApiUtils getApiUtils() {
        return apiUtils;
    }

    public SocialResponsabilityApp getAppContext() {
        return appContext;
    }

    private void initializeWidgets() {
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.bottomNavBar);

        viewPager.setSwipeable(false);
        adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        viewPager.setAdapter(adapter);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ic_home:
                        viewPager.setCurrentItem(0);
                        break;
                }
                return false;
            }
        });
    }

    private void initializeUser() {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userEmail = mPreferences.getString(getString(R.string.preferences_email), "");
        String userId = mPreferences.getString(getString(R.string.preferences_id), "");
        String accessToken = mPreferences.getString(getString(R.string.preferences_token), "");
        Log.d(TAG, "initializeUser: " + userId + ' ' + userEmail + ' ' + accessToken);
            if ((userEmail.isEmpty() || userId.isEmpty() || accessToken.isEmpty()) && !((SocialResponsabilityApp) getApplicationContext()).getAnonymousUser()) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else if(appContext.getUser() == null){
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "aplication/json");
                headers.put("Authorization", "Bearer " + accessToken);
                Call<AuthorizationModel> call = apiUtils.isAuthorized(headers, userEmail);

                call.enqueue(new Callback<AuthorizationModel>() {
                    @Override
                    public void onResponse(Call<AuthorizationModel> call, Response<AuthorizationModel> response) {
                        Log.d(TAG, "onResponse: initializeUser " + response.body().getData());
                        if (response.body().getIsSuccessful() == true) {
                            UserModel userModel = response.body().getData();
                            appContext.setUser(new UserModel(userModel));
                        } else {
                            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthorizationModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                initializeWidgets();
            }

    }
}
