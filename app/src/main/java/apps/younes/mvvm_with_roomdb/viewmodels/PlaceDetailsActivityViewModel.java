package apps.younes.mvvm_with_roomdb.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.repositories.NicePlaceRepository;

public class PlaceDetailsActivityViewModel extends ViewModel {
    private MutableLiveData<NicePlace> mNicePlace;

    public void init(NicePlace nicePlace){
        if (mNicePlace!=null){
            return;
        }
        mNicePlace = new MutableLiveData<>();
        mNicePlace.setValue(nicePlace);
    }


    public LiveData<NicePlace> getNicePlace(){
        return mNicePlace;
    }

}
