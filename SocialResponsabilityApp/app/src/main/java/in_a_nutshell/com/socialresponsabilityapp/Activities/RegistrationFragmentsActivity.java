package in_a_nutshell.com.socialresponsabilityapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;

import in_a_nutshell.com.socialresponsabilityapp.Adapters.SectionsPageAdapter;
import in_a_nutshell.com.socialresponsabilityapp.Fragments.RegistrationFragments.AgeFragment;
import in_a_nutshell.com.socialresponsabilityapp.Fragments.RegistrationFragments.GenderFragment;
import in_a_nutshell.com.socialresponsabilityapp.R;

public class RegistrationFragmentsActivity extends AppCompatActivity {

    //Widgets
    private ViewPager viewPager;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_fragments);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new AgeFragment());
        adapter.addFragment(new GenderFragment());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
