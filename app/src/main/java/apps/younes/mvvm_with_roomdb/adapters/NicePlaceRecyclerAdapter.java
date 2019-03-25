package apps.younes.mvvm_with_roomdb.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import apps.younes.mvvm_with_roomdb.R;
import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.models.PlaceCategory;
import de.hdodenhof.circleimageview.CircleImageView;

public class NicePlaceRecyclerAdapter extends RecyclerView.Adapter<NicePlaceRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NicePlaceRecyclerAdapte";

    private OnPlaceListener onPlaceListener;
    private List<PlaceCategory> mList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView image;
        TextView text;
        OnPlaceListener onPlaceListener;

        public ViewHolder(@NonNull View itemView, OnPlaceListener onPlaceListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.title);
            this.onPlaceListener = onPlaceListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int category = getCategory(getAdapterPosition());
            int index = getPlace(getAdapterPosition(),category);
            onPlaceListener.onPlaceClickListener(category,index);
        }
    }

    public class HeaderViewHolder extends ViewHolder {

        TextView text;

        public HeaderViewHolder(@NonNull View headerView) {
            super(headerView, new OnPlaceListener() {
                @Override
                public void onPlaceClickListener(int category, int index) {

                }
            });
            text = headerView.findViewById(R.id.title);
        }
    }

    public NicePlaceRecyclerAdapter(Context mContext, List<PlaceCategory> mList, OnPlaceListener onPlaceListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.onPlaceListener = onPlaceListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_categoryplace, viewGroup, false);
            return new HeaderViewHolder(view);
        } else if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_niceplace, viewGroup, false);
            return new ViewHolder(view, onPlaceListener);
        }
        throw new RuntimeException("No match for " + viewType + ".");
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof HeaderViewHolder) {
            int cat = getCategory(i);
            ((HeaderViewHolder) viewHolder).text.setText(mList.get(cat).getName());
        } else {
            int cat = getCategory(i);
            int index = getPlace(i,cat);
            Glide.with(mContext).asBitmap().load(mList.get(cat).getPlaces().get(index).getImageUrl()).into(viewHolder.image);
            viewHolder.text.setText(mList.get(cat).getPlaces().get(index).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        for (PlaceCategory category : mList)
            size += category.getPlaces().size() + 1;
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        int cat = -1;
        for (int i = 0; i < mList.size(); i++) {
            cat++;
            if (position < cat) return false;
            if (position == cat) return true;
            cat += mList.get(i).getPlaces().size();
        }
        return false;
    }

    public interface OnPlaceListener {
        void onPlaceClickListener(int category,int index);
    }

    private int getCategory(int position){
        int cat = -1;
        int j = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (position <= j) return cat;
            cat++;
            j++;
            j += mList.get(i).getPlaces().size();
        }
        return cat;
    }

    private int getPlace(int position,int category){
        int j = 0;
        for (int i = 0; i < category; i++) {
            j += mList.get(i).getPlaces().size();
            j++;
        }
        return position-j-1;
    }
}
