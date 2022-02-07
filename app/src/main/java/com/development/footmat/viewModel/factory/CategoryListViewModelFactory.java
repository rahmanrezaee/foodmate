package com.development.footmat.viewModel.factory;

import com.development.footmat.viewModel.CategoryListViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CategoryListViewModelFactory implements ViewModelProvider.Factory {

    private String type;


    public CategoryListViewModelFactory(String type) {

        this.type = type;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CategoryListViewModel(type);
    }
}