package in_a_nutshell.com.socialresponsabilityapp.Fragments.RegistrationFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in_a_nutshell.com.socialresponsabilityapp.R;

public class GenderFragment extends Fragment {

    //Vars
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 0;
    private int genderSelcted = GENDER_MALE;

    //Widgets
    private LinearLayout femaleCardView;
    private LinearLayout maleCardView;
    private TextView maleTextView;
    private TextView femaleTextView;
    private ImageView femaleImageView;
    private ImageView maleImageView;

    public GenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);

        femaleCardView = view.findViewById(R.id.femaleCardView);
        femaleImageView = view.findViewById(R.id.femaleIV);
        femaleTextView = view.findViewById(R.id.femaleTV);

        maleCardView = view.findViewById(R.id.maleCardView);
        maleImageView = view.findViewById(R.id.maleIV);
        maleTextView = view.findViewById(R.id.maleTV);

        maleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(genderSelcted == GENDER_FEMALE) {
                    genderSelcted = GENDER_MALE;

                    maleCardView.setBackground(getResources().getDrawable(R.drawable.btn_rounded_stroke_color_primary));
                    maleImageView.setImageResource(R.drawable.ic_male_selected);
                    maleTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

                    femaleCardView.setBackground(null);
                    femaleImageView.setImageResource(R.drawable.ic_female_unselected);
                    femaleTextView.setTextColor(getResources().getColor(R.color.colorLightGray));
                }
            }
        });
        femaleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(genderSelcted == GENDER_MALE) {
                    genderSelcted = GENDER_FEMALE;

                    femaleCardView.setBackground(getResources().getDrawable(R.drawable.btn_rounded_stroke_color_primary));
                    femaleImageView.setImageResource(R.drawable.ic_female_selected);
                    femaleTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

                    maleCardView.setBackground(null);
                    maleImageView.setImageResource(R.drawable.ic_male_unselected);
                    maleTextView.setTextColor(getResources().getColor(R.color.colorLightGray));
                }
            }
        });
        return view;
    }
}
