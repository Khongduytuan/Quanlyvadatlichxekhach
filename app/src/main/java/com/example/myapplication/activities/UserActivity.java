package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.LichDatFragment;
import com.example.myapplication.fragment.LichDatCuaNguoiDungFragment;
import com.example.myapplication.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class UserActivity extends AppCompatActivity {

    FloatingActionButton fabClient;
    DrawerLayout drawerLayoutClient;
    BottomNavigationView bottomNavigationViewClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        bottomNavigationViewClient = findViewById(R.id.bottomNavigationViewClient);
        fabClient = findViewById(R.id.fabClient);
        drawerLayoutClient = findViewById(R.id.drawer_layoutClient);


        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutClient, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayoutClient.addDrawerListener(toggle);
        toggle.syncState();







        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layoutClient, new LichDatFragment()).commit();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_logout:

                            Toast.makeText(UserActivity.this, "Click", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    item.setChecked(true);
                    drawerLayoutClient.closeDrawers();
                    return true;
                }
            });
        }


        replaceFragment(new LichDatCuaNguoiDungFragment());

        bottomNavigationViewClient.setBackground(null);
        bottomNavigationViewClient.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new LichDatFragment());
                    break;
                case R.id.shorts:
                    replaceFragment(new LichDatCuaNguoiDungFragment());
                    break;

            }

            return true;
        });

        fabClient.setOnClickListener(view -> showBottomDialog());


    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layoutClient, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayoutclient);


        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        LinearLayout layoutAccount = dialog.findViewById(R.id.layoutAccount);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        layoutAccount.setOnClickListener(view -> {
            dialog.dismiss();
            Intent intent = new Intent(UserActivity.this, UpdateInforUserClientActivity.class);
            startActivity(intent);
        });

        liveLayout.setOnClickListener(v -> {

            dialog.dismiss();
            Intent intent = new Intent(UserActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        });


        cancelButton.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}