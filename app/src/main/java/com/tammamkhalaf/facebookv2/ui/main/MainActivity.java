package com.tammamkhalaf.facebookv2.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tammamkhalaf.facebookv2.R;
import com.tammamkhalaf.facebookv2.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    List<PostModel> PostModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        /**
         * demo object passed to the api as post method
         * */
        PostModel newPost = new PostModel(789,"this is my fake post title","this is body of the post i have created");

        postViewModel.getPosts();

        postViewModel.storePost(newPost);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        PostsAdapter adapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        postViewModel.postsMutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                PostModelList = postModels;
                adapter.setList(PostModelList);
            }
        });

        postViewModel.postMutableLiveData.observe(this, new Observer<PostModel>() {
            @Override
            public void onChanged(PostModel postModel) {
                PostModelList.add(1,postModel);
                adapter.setList(PostModelList);
            }
        });

    }
}