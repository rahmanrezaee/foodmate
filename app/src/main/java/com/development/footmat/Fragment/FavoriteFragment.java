package com.development.footmat.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.development.footmat.adapters.AdapterItem;
import com.development.footmat.component.MyDialog;
import com.development.footmat.databinding.FragmentFavoriteBinding;
import com.development.footmat.models.ItemModel;
import com.development.footmat.pages.CategoryDetailPage;
import com.development.footmat.pages.MainActivity;
import com.development.footmat.viewModel.CategoryListViewModel;
import com.development.footmat.viewModel.FavoriteViewModel;
import com.development.footmat.viewModel.factory.CategoryListViewModelFactory;
import com.development.footmat.viewModel.factory.FavoriteViewModelFactory;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;
    private FragmentFavoriteBinding binding;
    private AdapterItem adapterCategory;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }
    @Override
    public void onResume() {

        Log.i("Test Fravorite", "onResume: Resumend Fravorite");
        favoriteViewModel.updateData();
        super.onResume();
    }

    @Override
    public void onPause() {


        super.onPause();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        favoriteViewModel = new ViewModelProvider(this, new FavoriteViewModelFactory( root.getContext())).get(FavoriteViewModel.class);


        adapterCategory = new AdapterItem(getActivity().getApplicationContext());
        MyDialog.showDialog(getActivity());
        binding.listFavorite.setAdapter(adapterCategory);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.listFavorite.setLayoutManager(mLinearLayoutManager);





        favoriteViewModel.getCategoryMutableLiveData().observe(getActivity(), itemListUpdateObserver);
        return root;


    }

    Observer<ArrayList<ItemModel>> itemListUpdateObserver = new Observer<ArrayList<ItemModel>>() {
        @Override
        public void onChanged(ArrayList<ItemModel> list) {
            adapterCategory.updateUserList(list);
            MyDialog.dismissDialog();
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}