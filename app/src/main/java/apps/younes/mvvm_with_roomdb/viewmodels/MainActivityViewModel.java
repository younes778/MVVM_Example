package apps.younes.mvvm_with_roomdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.repositories.NicePlaceRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private NicePlaceRepository repo;

    public void init(){
        if (mNicePlaces!=null){
            return;
        }
        repo = NicePlaceRepository.getInstance();
        mNicePlaces= repo.getNicePlaces();
    }

    public void addNewValue(final NicePlace nicePlace){
        mIsUpdating.setValue(true);
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> nicePlaces = mNicePlaces.getValue();
                nicePlaces.add(nicePlace);
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

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }

    public LiveData<Boolean> getisUpdating(){
        return mIsUpdating;
    }
}
