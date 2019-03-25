package apps.younes.mvvm_with_roomdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.models.PlaceCategory;
import apps.younes.mvvm_with_roomdb.repositories.NicePlaceRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<PlaceCategory>> mNicePlaces;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private NicePlaceRepository repo;

    public void init(){
        if (mNicePlaces!=null){
            return;
        }
        repo = NicePlaceRepository.getInstance();
        mNicePlaces= repo.getNicePlaces();
    }

    public void addNewValue(final int category,final NicePlace nicePlace){
        mIsUpdating.setValue(true);
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<PlaceCategory> nicePlaces = mNicePlaces.getValue();
                nicePlaces.get(category).addPlace(nicePlace.getTitle(),nicePlace.getImageUrl(),nicePlace.getDetails());
                mNicePlaces.postValue(nicePlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    public LiveData<List<PlaceCategory>> getNicePlaces(){
        return mNicePlaces;
    }

    public LiveData<Boolean> getisUpdating(){
        return mIsUpdating;
    }
}
