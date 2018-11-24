package in_a_nutshell.com.socialresponsabilityapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in_a_nutshell.com.socialresponsabilityapp.Models.IssueModel;
import in_a_nutshell.com.socialresponsabilityapp.R;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private List<IssueModel> mIssuesList;

    public CustomRecyclerViewAdapter(List<IssueModel> mIssuesList) {
        this.mIssuesList = mIssuesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_issue, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        IssueModel issueModel = mIssuesList.get(i);

        viewHolder.titleTextView.setText(issueModel.getTitle());
        viewHolder.descriptionTextView.setText(issueModel.getDescription());
        viewHolder.longitudeTextView.setText(String.valueOf(issueModel.getLongitude()));
        viewHolder.latitudeTextView.setText(String.valueOf(issueModel.getLatitude()));
    }

    @Override
    public int getItemCount() {
        return mIssuesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView latitudeTextView;
        public TextView longitudeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.titleTextView = itemView.findViewById(R.id.titleTV);
            this.descriptionTextView = itemView.findViewById(R.id.descriptionTV);
            this.latitudeTextView = itemView.findViewById(R.id.latitudeTV);
            this.longitudeTextView = itemView.findViewById(R.id.longitudeTV);
        }
    }
}
