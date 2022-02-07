package com.development.footmat.viewModel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.development.footmat.R;
import com.development.footmat.databinding.FragmentFavoriteBinding;
import com.development.footmat.models.CategoryModel;
import com.development.footmat.models.ItemModel;
import com.development.footmat.services.FavoriteService;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoriteViewModel extends ViewModel {



    MutableLiveData<ArrayList<ItemModel>> categoryLivaData;
    ArrayList<ItemModel>  arrayList = new ArrayList<>();


    private FavoriteService favoriteService;



    public  FavoriteViewModel(Context context) {
        favoriteService = new FavoriteService(context);
        categoryLivaData = new MutableLiveData<>();

        updateData();
    }
    public MutableLiveData<ArrayList<ItemModel>> getCategoryMutableLiveData(){
        return categoryLivaData;
    }

    public void updateData() {


        Log.i("Test Fravorite", "updateData: Updateing Favorite");

        arrayList = favoriteService.getListFavorite();
        categoryLivaData.setValue(arrayList);

    }
}