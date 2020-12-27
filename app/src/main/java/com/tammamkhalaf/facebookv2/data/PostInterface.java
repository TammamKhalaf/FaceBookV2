package com.tammamkhalaf.facebookv2.data;

import com.tammamkhalaf.facebookv2.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
    Call<List<PostModel>> getPosts();
}
