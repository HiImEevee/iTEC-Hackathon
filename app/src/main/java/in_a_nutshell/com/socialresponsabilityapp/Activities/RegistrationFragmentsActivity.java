package in_a_nutshell.com.socialresponsabilityapp.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;

import in_a_nutshell.com.socialresponsabilityapp.Adapters.SectionsPageAdapter;
import in_a_nutshell.com.socialresponsabilityapp.Fragments.RegistrationFragments.AgeFragment;
import in_a_nutshell.com.socialresponsabilityapp.Fragments.RegistrationFragments.GenderFragment;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.Views.CustomViewPager;

public class RegistrationFragmentsActivity extends AppCompatActivity {

    //Widgets
    private CustomViewPager viewPager;
    private TabLayout tabLayout;
    private ImageButton backButton;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_fragments);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backButton = findViewById(R.id.backBtn);
        continueButton = findViewById(R.id.continueBtn);

        viewPager.setSwipeable(false);
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new AgeFragment());
        adapter.addFragment(new GenderFragment());
        viewPager.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setText(getString(R.string.continue_btn));
                if(viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
                if(viewPager.getCurrentItem() == 0) {
                    backButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewPager.getCurrentItem() + 1;
                backButton.setVisibility(View.VISIBLE);
                if(position <= viewPager.getChildCount() - 1 ) {
                    viewPager.setCurrentItem(position);
                } else {
                    Intent intent = new Intent(RegistrationFragmentsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(position == viewPager.getChildCount() - 1) {
                    continueButton.setText(getString(R.string.finish_btn));
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }
}
