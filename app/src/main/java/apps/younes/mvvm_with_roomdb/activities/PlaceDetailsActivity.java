package apps.younes.mvvm_with_roomdb.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import apps.younes.mvvm_with_roomdb.R;
import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.viewmodels.MainActivityViewModel;
import apps.younes.mvvm_with_roomdb.viewmodels.PlaceDetailsActivityViewModel;

public class PlaceDetailsActivity extends AppCompatActivity {
    private static final String TAG = "PlaceDetailsActivity";
    public static final String SELECTED_PLACE = "selected_place";
    private ImageView imageView;
    private TextView textView;
    private PlaceDetailsActivityViewModel placeDetailsActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        initComponents();

        ;

        placeDetailsActivityViewModel = ViewModelProviders.of(this).get(PlaceDetailsActivityViewModel.class);
        placeDetailsActivityViewModel.init((NicePlace) getIntent().getParcelableExtra(SELECTED_PLACE));

        placeDetailsActivityViewModel.getNicePlace().observe(this, new Observer<NicePlace>() {
            @Override
            public void onChanged(@Nullable NicePlace nicePlace) {
                textView.setText(nicePlace.getDetails());
                Glide.with(PlaceDetailsActivity.this).asBitmap().load(nicePlace.getImageUrl()).into(imageView);
            }
        });

    }

    private void initComponents() {
        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.details);
    }


}
