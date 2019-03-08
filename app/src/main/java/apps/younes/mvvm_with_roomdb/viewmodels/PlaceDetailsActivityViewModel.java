package apps.younes.mvvm_with_roomdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import apps.younes.mvvm_with_roomdb.models.NicePlace;

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
