package com.development.footmat.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.development.footmat.models.ItemModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.RequiresApi;

public class FavoriteService {

    SharedPreferences appSharedPrefs;
    Context c;

    Type listType = new TypeToken<List<ItemModel>>() {
    }.getType();

    public FavoriteService(Context c) {
        this.c = c;
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void toggleToFavorite(ItemModel modal) {

        List<ItemModel> items = getListFavorite();


        if (isExistInList(items, modal)) {

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getTitle().equalsIgnoreCase(modal.getTitle()))
                    items.remove(i);
            }

        } else{

            items.add(modal);

        }

        Gson gson = new Gson();
        String json = gson.toJson(items, listType);
        SharedPreferences.Editor editor = appSharedPrefs.edit();
        editor.putString("favorite", json).commit();


    }

    public ArrayList<ItemModel> getListFavorite() {

        String json = appSharedPrefs.getString("favorite", "");

        Log.i("Favorite Service", "getListFavorite: " + json);
        if (json.isEmpty()) {
            return new ArrayList<>();
        } else {
            Gson gson = new Gson();
            return gson.fromJson(json, listType);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isExistInList(List<ItemModel> items, ItemModel modal) {

        AtomicBoolean isExist = new AtomicBoolean(false);

        items.forEach(itemModel -> {
            if (itemModel.getTitle().equalsIgnoreCase(modal.getTitle()))
                isExist.set(true);

        });

        Log.i("TAG", "toggleToFavorite: " + isExist.get());
        return isExist.get();


    }
}
