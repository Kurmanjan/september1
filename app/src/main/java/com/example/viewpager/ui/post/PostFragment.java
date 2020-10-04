package com.example.viewpager.ui.post;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewpager.App;
import com.example.viewpager.R;
import com.example.viewpager.adepters.OnClick;
import com.example.viewpager.adepters.PostAdepter;
import com.example.viewpager.data.models.PostModel;
import com.example.viewpager.data.network.ApiCallback;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {
    ArrayList<PostModel> postModels = new ArrayList<>();


    RecyclerView recycler;
    PostAdepter adepter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
        recycler = view.findViewById(R.id.recycler_view);
        adepter = new PostAdepter();
        recycler.setAdapter(adepter);


        getList();

        adepter.setOnClick(new OnClick() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {
                Integer id = postModels.get(position).getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext()); //udalyaem post
                builder.setMessage("Delete " + postModels.get(position).getTitle() + "?");
                builder.setNegativeButton("no", null);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adepter.delete(position);
                        App.androidClient.deletePost(id, new ApiCallback() { //on udalyaet post c interneta
                            @Override
                            public void onSuccess(List<PostModel> postModels) {
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });
                    }
                });
                builder.show();

            }
        });
    }

    private void getList() {
        App.androidClient.getData(new ApiCallback() {
            @Override
            public void onSuccess(List<PostModel> postModels) {
                PostFragment.this.postModels = (ArrayList<PostModel>) postModels;
                adepter.setPostModels((ArrayList<PostModel>) postModels);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("failure", e.getMessage());

            }


        });
    }
}