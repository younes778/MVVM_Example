package apps.younes.mvvm_with_roomdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

import apps.younes.mvvm_with_roomdb.models.NicePlace;
import apps.younes.mvvm_with_roomdb.models.PlaceCategory;
import apps.younes.mvvm_with_roomdb.repositories.NicePlaceRepository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<PlaceCategory>> mNicePlaces;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private NicePlaceRepository repo;
    private CompositeDisposable disposable = new CompositeDisposable();

    public void init(){
        if (mNicePlaces!=null){
            return;
        }
        repo = NicePlaceRepository.getInstance();
        mNicePlaces= repo.getNicePlaces();

    }

    public void addNewValue(final int category,final NicePlace nicePlace){
        mIsUpdating.setValue(true);
        Observable<Long> intervalObservable = Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .takeWhile(new Predicate<Long>() { // stop the process if more than 5 seconds passes
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong <= 2;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        intervalObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                List<PlaceCategory> nicePlaces = mNicePlaces.getValue();
                nicePlaces.get(category).addPlace(nicePlace.getTitle(),nicePlace.getImageUrl(),nicePlace.getDetails());
                mNicePlaces.postValue(nicePlaces);
                mIsUpdating.postValue(false);
            }
        });


        /*new AsyncTask<Void,Void,Void>(){

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
        }.execute();*/

    }

    public LiveData<List<PlaceCategory>> getNicePlaces(){
        return mNicePlaces;
    }

    public LiveData<Boolean> getisUpdating(){
        return mIsUpdating;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
