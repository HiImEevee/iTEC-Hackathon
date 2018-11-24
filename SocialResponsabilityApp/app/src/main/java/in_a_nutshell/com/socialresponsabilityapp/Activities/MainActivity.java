package in_a_nutshell.com.socialresponsabilityapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import in_a_nutshell.com.socialresponsabilityapp.Adapters.CustomRecyclerViewAdapter;
import in_a_nutshell.com.socialresponsabilityapp.Models.AuthorizationModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.IssueModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.UserModel;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.SocialResponsabilityApp;
import in_a_nutshell.com.socialresponsabilityapp.Utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    //API Helpers
    public Retrofit retrofit;
    public ApiUtils apiUtils;

    //Vars
    public List<IssueModel> issueList;
    public SocialResponsabilityApp appContext;

    //Widgets
    public RecyclerView issuesRecyclerView;
    public RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = (SocialResponsabilityApp) getApplicationContext();

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor = mPreferences.edit();

//        mEditor.putString("email", "andrei.matei2010@yahoo.com");
//        mEditor.commit();
//
//        mEditor.putString("id", "07fba2a0-38f7-4e93-8886-3a9b144faa38");
//        mEditor.commit();
//
//        mEditor.putString("token", "FdJ49zXDn63j9r77BrjX3IZEgjac89Aw_sTMVqjddjyQtAzXRW_PF81AUH7yfq-C4lhxg5vf-GfMi5HYkTr3JU9JZ2lBX0i7X_rAO6z-jMPwMjB2kJIb3tQdNQkND3VvdMYNUCuyWNvxUFuZPXfotGhNgTg7ZgSawlUFv0iCJ6Od-cVRapCmo7Ke3Y4hSr89e5sqjGODP1oxIu74Z-uTKM84cALhInExFgjnvY4FGSLg8uC9ApsTX0b6Kjw-gh7o3k8Vwra_n0F2mDJl3-RB-eQFVQraCHih3fwRdlgjOYO5Z5kTSv_whTCaKg9CMAxkS5-IDBlzBMnjuQY_gyg0MH6LRwst2eZx6KiUKREhD3a5idpJG8WA_9Db7OB6tG1r6hgTMhSqsjSSlFvFh7WTNF8qwMgX0EP8E9efJQvJe-QDB_vB5btYg5PJ1M3yNHKHrVvtmp529d70wJoZyQnneRRTIpkhr8zkRQBHlegX7Y9TyS5tIaIiaDuu2PoQpUkFhDQzBq9u4J9SxEFYrjaNlpvyrPJNn6sOcMHOKm0npk3rYwSmzFKyDC_UjRVM_3mlNmr6wYMBMu40d9pn3NQubCLEErO2UPzdomJS6kOA8BroEpICock9L4X_BxjNW_ZzKuGsSxIZwXsvPoaZlHdA7c6mNaWh-4WEQrxq8FRsQKo");
//        mEditor.commit();

        initializeRetrofit();
        initializeUser();
        initializeIssuesList();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiUtils = retrofit.create(ApiUtils.class);
    }

    private void initializeIssuesList() {
        Call<List<IssueModel>> call = apiUtils.getIssues();
        call.enqueue(new Callback<List<IssueModel>>() {
            @Override
            public void onResponse(Call<List<IssueModel>> call, Response<List<IssueModel>> response) {
                issueList = response.body();
                issuesRecyclerView = findViewById(R.id.issuesListRV);
                adapter = new CustomRecyclerViewAdapter(response.body());
                issuesRecyclerView.setAdapter(adapter);
                issuesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<List<IssueModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: something went wrong" + t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeUser() {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userEmail = mPreferences.getString(getString(R.string.preferences_email), "");
        String userId = mPreferences.getString(getString(R.string.preferences_id), "");
        String accessToken = mPreferences.getString(getString(R.string.preferences_token), "");
        Log.d(TAG, "initializeUser: " + userId + ' ' + userEmail + ' ' + accessToken);
        if(!((SocialResponsabilityApp) getApplicationContext()).getAnonymousUser()) {
            if (userEmail.isEmpty() || userId.isEmpty() || accessToken.isEmpty()) {
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
            }
        }
    }
}
