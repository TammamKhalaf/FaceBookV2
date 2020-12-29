package com.tammamkhalaf.facebookv2.data;

import com.tammamkhalaf.facebookv2.pojo.PostModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostInterface {
    @GET("posts")
    Single<List<PostModel>> getPosts();
    /**
     *
     * */
//    todo if you want to send multiple object to the server / rest api
    @POST("posts")
    Single<PostModel> storePost(@Body HashMap<Object,Object> map);
}
