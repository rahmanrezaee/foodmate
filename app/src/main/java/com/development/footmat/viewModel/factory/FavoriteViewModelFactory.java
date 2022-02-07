package com.development.footmat.viewModel.factory;

import android.content.Context;

import com.development.footmat.viewModel.CategoryListViewModel;
import com.development.footmat.viewModel.FavoriteViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FavoriteViewModelFactory  implements ViewModelProvider.Factory {

    private Context context;


    public FavoriteViewModelFactory(Context context) {

        this.context = context;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new FavoriteViewModel(this.context);
    }
}