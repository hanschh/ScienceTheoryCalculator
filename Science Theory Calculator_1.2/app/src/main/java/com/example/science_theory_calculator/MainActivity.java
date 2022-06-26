package com.example.science_theory_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_navigation);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout ,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_cari1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frag_Menu1()).commit();
                break;
            case R.id.nav_cari2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frag_Menu2()).commit();
                break;
            case R.id.nav_cari3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frag_Menu3()).commit();
                break;
            case R.id.nav_cari4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frag_Menu4()).commit();
                break;
            case R.id.nav_cari5:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frag_Menu5()).commit();
                break;
            case R.id.nav_cari6:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frag_Menu6()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}