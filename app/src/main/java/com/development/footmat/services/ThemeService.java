package com.development.footmat.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import java.lang.reflect.Type;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

public class ThemeService {

    SharedPreferences appSharedPrefs;


    public ThemeService(Context c) {

        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());

    }

    public void toggleMood() {
        SharedPreferences.Editor editor = appSharedPrefs.edit();
        if (isLightMood()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editor.putBoolean("isLightMood", false).commit();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor.putBoolean("isLightMood", true).commit();
        }


    }


    public boolean isLightMood() {

        return appSharedPrefs.getBoolean("isLightMood", false);

    }


}