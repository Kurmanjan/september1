package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.viewpager.adepters.FragmentAdepter;
import com.example.viewpager.data.local.PrefUtils;
import com.example.viewpager.ui.AuthActivity;
import com.example.viewpager.ui.user.UserFragment;
import com.example.viewpager.ui.favorite.FavoriteFragment;
import com.example.viewpager.ui.post.PostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private List<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PrefUtils.getUserName().isEmpty()){
            startActivity(new Intent(this, AuthActivity.class));

        }
        Log.d("name",PrefUtils.getUserName());

        fragmentArrayList = new ArrayList<>();
        fullFragments();

         bottomNavigationView = findViewById(R.id.nov_material);
         viewPager = findViewById(R.id.view_pager);


         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  if (item.getItemId() == R.id.menu_post){
                      viewPager.setCurrentItem(0);
                  }else if (item.getItemId() == R.id.menu_fav){
                      viewPager.setCurrentItem(1);
                  }else if (item.getItemId() == R.id.menu_user)
                      viewPager.setCurrentItem(2);

                 return true;
             }
         });
           viewPager.setAdapter(new FragmentAdepter(fragmentArrayList,getSupportFragmentManager()));
           viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
               @Override
               public void onPageSelected(int position) {
                   bottomNavigationView.getMenu().getItem(position).setChecked(true);
           }

           });


    }

    private  void fullFragments(){
        fragmentArrayList.add(new PostFragment());
        fragmentArrayList.add(new FavoriteFragment());
        fragmentArrayList.add(new UserFragment());
    }
}
