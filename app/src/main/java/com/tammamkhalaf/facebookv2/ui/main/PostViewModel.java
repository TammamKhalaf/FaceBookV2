package com.tammamkhalaf.facebookv2.ui.main;

import android.util.Log;

import com.tammamkhalaf.facebookv2.data.PostsClient;
import com.tammamkhalaf.facebookv2.pojo.PostModel;

import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Single;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";
    
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();

    MutableLiveData<PostModel> postMutableLiveData = new MutableLiveData<>();

    public void getPosts() {
        Single<List<PostModel>> singleObservable =
                PostsClient.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        singleObservable.subscribe(o->postsMutableLiveData.setValue(o),e-> Log.d(TAG, "getPosts: "+e.getLocalizedMessage()));
    }


    public void storePost(HashMap<Object,Object> mapOfObjectsToSend){
        Single<PostModel> postToSend =
                     PostsClient.getINSTANCE()
                     .storePost(mapOfObjectsToSend)
                             .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread());

        postToSend.subscribe(k->postMutableLiveData.setValue(k),e-> Log.d(TAG, "storePost: "+e.getLocalizedMessage()));

    }
}
