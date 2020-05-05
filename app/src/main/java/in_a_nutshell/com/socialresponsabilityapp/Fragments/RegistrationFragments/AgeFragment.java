package in_a_nutshell.com.socialresponsabilityapp.Fragments.RegistrationFragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import in_a_nutshell.com.socialresponsabilityapp.R;


public class AgeFragment extends Fragment {

    private SeekBar ageSeekBar;
    private TextView ageTextView;
    private CardView ageWrapperCardView;

    public AgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);

        ageSeekBar = view.findViewById(R.id.ageSeekBar);
        ageTextView = view.findViewById(R.id.ageTV);
        ageWrapperCardView = view.findViewById(R.id.ageLabelWrapper);

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ageWrapperCardView.setX(getFloatingTextViewPosition(i));
                ageTextView.setText(i + " years");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                ageWrapperCardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//                ageWrapperCardView.setCardElevation(8);
//                ageTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                ageWrapperCardView.setCardBackgroundColor(Color.TRANSPARENT);
//                ageWrapperCardView.setCardElevation(0);
//                ageTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });
        return view;

    }

    private float getFloatingTextViewPosition(int position) {

        //Some random dark magic
        return (ageSeekBar.getWidth() - ageWrapperCardView.getWidth()) / 100 * position + 60;
    }
}
