package apps.younes.mvvm_with_roomdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import apps.younes.mvvm_with_roomdb.adapters.NicePlaceRecyclerAdapter;
import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.repositories.NicePlaceUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<NicePlace> nicePlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNicePlaces();

    }

    public void initNicePlaces(){
        nicePlaces = NicePlaceUtils.fillNicePlaces();

        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        NicePlaceRecyclerAdapter recyclerAdapter = new NicePlaceRecyclerAdapter(this,nicePlaces);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
