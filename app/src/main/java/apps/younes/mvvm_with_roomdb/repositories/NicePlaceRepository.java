package apps.younes.mvvm_with_roomdb.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import apps.younes.mvvm_with_roomdb.models.NicePlace;

//Single pattern
public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet;

    public static NicePlaceRepository getInstance(){
        if (instance==null){
            instance = new NicePlaceRepository();
        }
        return instance;
    }

    // get data from web service or database
    public MutableLiveData<List<NicePlace>> getNicePlaces(){
        setNicePlaces();

        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void setNicePlaces(){
        dataSet = new ArrayList<>();
        dataSet.add(new NicePlace("Havasu Falls","https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"));
        dataSet.add(new NicePlace("Trondheim","https://i.redd.it/tpsnoz5bzo501.jpg"));
        dataSet.add(new NicePlace("Portugal","https://i.redd.it/qn7f9oqu7o501.jpg"));
        dataSet.add(new NicePlace("Rocky Mountain National Park","https://i.redd.it/j6myfqglup501.jpg"));
        dataSet.add(new NicePlace("Mahahual","https://i.redd.it/0h2gm1ix6p501.jpg"));
        dataSet.add(new NicePlace("Frozen Lake","https://i.redd.it/k98uzl68eh501.jpg"));
        dataSet.add(new NicePlace("White Sands Desert","https://i.redd.it/glin0nwndo501.jpg"));
        dataSet.add(new NicePlace("Austrailia","https://i.redd.it/obx4zydshg601.jpg"));
    }
}
