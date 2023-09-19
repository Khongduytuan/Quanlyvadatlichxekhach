package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.myapplication.fragment.XeFragment;
import com.example.myapplication.fragment.NguoiDungFragment;
import com.example.myapplication.fragment.LoTrinhFragment;
import com.example.myapplication.fragment.GheFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new XeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_logout:
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
                            Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    return true;
                }
            });
        }


        replaceFragment(new XeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new XeFragment());
                    break;
                case R.id.shorts:
                    replaceFragment(new LoTrinhFragment());
                    break;
                case R.id.subscriptions:
                    replaceFragment(new GheFragment());
                    break;
                case R.id.library:
                    replaceFragment(new NguoiDungFragment());
                    break;
            }

            return true;
        });

        fab.setOnClickListener(view -> showBottomDialog());
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        LinearLayout layoutAccount = dialog.findViewById(R.id.layoutAccount);
        LinearLayout layoutConfirm = dialog.findViewById(R.id.layoutConfirm);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        layoutConfirm.setOnClickListener(view -> {
            dialog.dismiss();
            Intent intent = new Intent(MainActivity.this, ConFirmLichDatAdminActivity.class);
            startActivity(intent);
        });

        videoLayout.setOnClickListener(v -> {

            dialog.dismiss();
//                Toast.makeText(MainActivity.this,"Add a Car",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AddACarActivity.class);
            startActivity(intent);

        });

        shortsLayout.setOnClickListener(v -> {

            dialog.dismiss();
//                Toast.makeText(MainActivity.this,"Add a route",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AddARouteActivity.class);
            startActivity(intent);

        });

        liveLayout.setOnClickListener(v -> {

            dialog.dismiss();
//                Toast.makeText(MainActivity.this,"Chair manager",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        });

        layoutAccount.setOnClickListener(v -> {

            dialog.dismiss();
//            Toast.makeText(MainActivity.this,"Account manager",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AddAAccountActivity.class);
            startActivity(intent);

        });

        cancelButton.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}