package in_a_nutshell.com.socialresponsabilityapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import in_a_nutshell.com.socialresponsabilityapp.Models.IssueModel;
import in_a_nutshell.com.socialresponsabilityapp.R;
import in_a_nutshell.com.socialresponsabilityapp.Utils.UniversalImageLoader;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private List<IssueModel> mIssuesList;
    private Context mContext;

    public CustomRecyclerViewAdapter(List<IssueModel> mIssuesList, Context mContext) {
        this.mIssuesList = mIssuesList;
        this.mContext = mContext;
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
        viewHolder.usernameTextView.setText(issueModel.getCreator());

        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
        UniversalImageLoader.setImage((issueModel.getImages().size() == 0) ? "" : issueModel.getImages().get(0),
                viewHolder.issuePhotoImageView, "" );
    }

    @Override
    public int getItemCount() {
        return mIssuesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameTextView;
        public TextView dateTextView;
        public ImageView issuePhotoImageView;
        public TextView titleTextView;
        public TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.titleTextView = itemView.findViewById(R.id.issueTitleTV);
            this.descriptionTextView = itemView.findViewById(R.id.descriptionTV);
            this.usernameTextView = itemView.findViewById(R.id.usernameTV);
            this.issuePhotoImageView = itemView.findViewById(R.id.issueImage);
        }
    }
}
