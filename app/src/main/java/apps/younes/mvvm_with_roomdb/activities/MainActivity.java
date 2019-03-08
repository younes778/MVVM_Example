package apps.younes.mvvm_with_roomdb.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rey.material.drawable.CircularProgressDrawable;
import com.rey.material.widget.FloatingActionButton;
import com.rey.material.widget.ProgressView;

import java.util.List;

import apps.younes.mvvm_with_roomdb.R;
import apps.younes.mvvm_with_roomdb.adapters.NicePlaceRecyclerAdapter;
import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements NicePlaceRecyclerAdapter.OnPlaceListener {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ProgressView progress;
    private NicePlaceRecyclerAdapter recyclerAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initComponents();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        mainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(@Nullable List<NicePlace> nicePlaces) {
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getisUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                showProgress(aBoolean);
                recyclerView.smoothScrollToPosition(mainActivityViewModel.getNicePlaces().getValue().size() - 1);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addNewValue(new NicePlace("Washington", "https://i.imgur.com/ZcLLrkY.jpg","Talent she for lively eat led sister. Entrance strongly packages she out rendered get quitting denoting led. Dwelling confined improved it he no doubtful raptures. Several carried through an of up attempt gravity. Situation to be at offending elsewhere distrusts if. Particular use for considered projection cultivated. Worth of do doubt shall it their. Extensive existence up me contained he pronounce do. Excellence inquietude assistance precaution any impression man sufficient. "));
            }
        });

        initRecyclerView();

    }

    private void initToolBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolBar));
        setTitle(R.string.app_name);
    }

    public void initComponents() {
        progress = findViewById(R.id.progress);
        btnAdd = findViewById(R.id.btn_add);
    }

    public void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerAdapter = new NicePlaceRecyclerAdapter(this, mainActivityViewModel.getNicePlaces().getValue(),this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void showProgress(boolean isVisible) {
        if (isVisible)
            progress.setVisibility(View.VISIBLE);
        else progress.setVisibility(View.GONE);
    }

    @Override
    public void onPlaceClickListener(int position) {
        Intent i = new Intent(this,PlaceDetailsActivity.class);
        i.putExtra(PlaceDetailsActivity.SELECTED_PLACE,mainActivityViewModel.getNicePlaces().getValue().get(position));
        startActivity(i);
    }
}
