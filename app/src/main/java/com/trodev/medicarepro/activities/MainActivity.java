package com.trodev.medicarepro.activities;

import static com.trodev.medicarepro.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.trodev.medicarepro.fragments.PdfFragment;
import com.trodev.medicarepro.R;
import com.trodev.medicarepro.fragments.AboutFragment;
import com.trodev.medicarepro.fragments.HomeFragment;
import com.trodev.medicarepro.fragments.ImageFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        /*init all drawer layout*/
        drawerLayout = findViewById(id.drawerLayout);
        navigationView = findViewById(id.navigation_view);

        // #######################
        // Drawer Layout implement
        toggle = new ActionBarDrawerToggle(this, drawerLayout, string.start, string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // #################################################################
        // navigation view work process
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        bottomNavigationView = findViewById(id.bottomNavigationView);
        loadHomeFragment();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == id.bottom_menu_home) {
                    loadHomeFragment();
                } else if (itemId == id.bottom_menu_prescription) {
                    loadPrescriptionFragments();
                } else if (itemId == id.bottom_menu_pdf) {
                    loadPdfFragment();
                } /*else if (itemId == id.bottom_menu_about) {
                    loadAboutFragment();
                }*/
                return true;
            }
        });
    }

    private void loadHomeFragment() {

        setTitle("Dashboard");
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, homeFragment, "homeFragment");
        fragmentTransaction.commit();
    }

    private void loadPrescriptionFragments() {

        setTitle("Prescription List");
        ImageFragment imageFragment = new ImageFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, imageFragment, "imageFragment");
        fragmentTransaction.commit();
    }

    private void loadPdfFragment() {

        setTitle("All Pdf");
        PdfFragment pdfFragment = new PdfFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, pdfFragment, "pdfFragment");
        fragmentTransaction.commit();
    }

    private void loadAboutFragment() {
        setTitle("Notification");
        AboutFragment aboutFragment = new AboutFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, aboutFragment, "aboutFragment");
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        int itemId = item.getItemId();

        if (itemId == id.nav_reminder) {
            startActivity(new Intent(MainActivity.this, MakeAlarmActivity.class));
        } else if (itemId == id.nav_doctor) {
            startActivity(new Intent(MainActivity.this, DoctorActivity.class));
        } else if (itemId == id.nav_policy) {

        } else if (itemId == id.nav_contact) {
            Toast.makeText(this, "Contact us", Toast.LENGTH_SHORT).show();
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.contact_bottomsheet_layout);

            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
        return super.onOptionsItemSelected(item);
    }

    // In this code, android lifecycle exit on 2 times back-pressed.
    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}