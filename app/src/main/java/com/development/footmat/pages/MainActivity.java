package com.development.footmat.pages;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.development.footmat.R;
import com.development.footmat.services.ThemeService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import me.anwarshahriar.calligrapher.Calligrapher;

import com.development.footmat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Firebase Log";
    private ActivityMainBinding binding;
    private ThemeService themeService;
    private Menu mMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        themeService = new ThemeService(getApplicationContext());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        setSupportActionBar(binding.toolbar2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("اپلیکشن فودمیت");
        }

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "fonts/font.ttf", true);

        if (themeService.isLightMood()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu_toolbar, menu);

        mMenuItem = menu;



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.dark_mode){
            themeService.toggleMood();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }
}