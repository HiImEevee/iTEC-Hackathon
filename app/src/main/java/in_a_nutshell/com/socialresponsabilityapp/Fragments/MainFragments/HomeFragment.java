package in_a_nutshell.com.socialresponsabilityapp.Fragments.MainFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeoutException;

import in_a_nutshell.com.socialresponsabilityapp.Activities.MainActivity;
import in_a_nutshell.com.socialresponsabilityapp.Adapters.CustomRecyclerViewAdapter;
import in_a_nutshell.com.socialresponsabilityapp.Models.IssueModel;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.SocialResponsabilityApp;
import in_a_nutshell.com.socialresponsabilityapp.Utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    //Widgets
    private RecyclerView issuesListRecyclerView;
    private TextView welcomeTextView;
    private TextView descriptionTextView;
    private CustomRecyclerViewAdapter adapter;

    //Utils
    private ApiUtils apiUtils;
    private SocialResponsabilityApp appContext;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        issuesListRecyclerView = view.findViewById(R.id.issuesRV);
        welcomeTextView = view.findViewById(R.id.greetingTV);

        appContext = ((MainActivity)getActivity()).getAppContext();
        apiUtils = ((MainActivity)getActivity()).getApiUtils();

        issuesListRecyclerView.setNestedScrollingEnabled(false);
        welcomeTextView.setText(getString(R.string.home_greeting) + " " + appContext.getUser().getFullName() + ",");

        Call<List<IssueModel>> call = apiUtils.getIssues();
        call.enqueue(new Callback<List<IssueModel>>() {
            @Override
            public void onResponse(Call<List<IssueModel>> call, Response<List<IssueModel>> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                adapter = new CustomRecyclerViewAdapter(response.body(), getContext());
                issuesListRecyclerView.setAdapter(adapter);
                issuesListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<IssueModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

}
