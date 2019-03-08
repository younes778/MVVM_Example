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

    private OnPlaceListener onPlaceListener;
    private List<NicePlace> mList = new ArrayList<>();
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView image;
        TextView text;
        OnPlaceListener onPlaceListener;

        public ViewHolder(@NonNull View itemView,OnPlaceListener onPlaceListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.title);
            this.onPlaceListener = onPlaceListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPlaceListener.onPlaceClickListener(getAdapterPosition());
        }
    }

    public NicePlaceRecyclerAdapter(Context mContext, List<NicePlace> mList,OnPlaceListener onPlaceListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.onPlaceListener = onPlaceListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_niceplace, viewGroup, false);
        return new ViewHolder(view,onPlaceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).asBitmap().load(mList.get(i).getImageUrl()).into(viewHolder.image);
        viewHolder.text.setText(mList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnPlaceListener{
        void onPlaceClickListener(int position);
    }
}
