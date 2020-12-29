package com.tammamkhalaf.facebookv2.ui.main;

import android.os.Bundle;

import com.tammamkhalaf.facebookv2.R;
import com.tammamkhalaf.facebookv2.pojo.PostModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    List<PostModel> PostModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        postViewModel.getPosts();

        /**
         * demo one object to be passed to the api as post method
         * */
        //        PostModel newPost = new PostModel(789,"this is my fake post title","this is body of the post i have created");

        //multiple objects to send to the api or server
                HashMap<Object, Object> map = new HashMap<>();
                map.put("title", "tammam khalaf");
                map.put("userId", 10);
                map.put("body", "this is the body of my post");

        postViewModel.storePost(map);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        PostsAdapter adapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        postViewModel.postsMutableLiveData.observe(this, postModels -> {
            PostModelList = postModels;
            adapter.setList(PostModelList);
        });

        postViewModel.postMutableLiveData.observe(this, postModel -> {
            PostModelList.add(1,postModel);
            adapter.setList(PostModelList);
        });

    }

}