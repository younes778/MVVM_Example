package apps.younes.mvvm_with_roomdb.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import apps.younes.mvvm_with_roomdb.R;
import apps.younes.mvvm_with_roomdb.models.NicePlace;
import de.hdodenhof.circleimageview.CircleImageView;

public class NicePlaceRecyclerAdapter extends RecyclerView.Adapter<NicePlaceRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NicePlaceRecyclerAdapte";

    private List<NicePlace> mList = new ArrayList<>();
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.title);
        }
    }

    public NicePlaceRecyclerAdapter(Context mContext, List<NicePlace> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_niceplace, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).asBitmap().load(mList.get(i).getImageUrl()).into(viewHolder.image);
        viewHolder.text.setText(mList.get(i).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"item "+i+" clicked",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
