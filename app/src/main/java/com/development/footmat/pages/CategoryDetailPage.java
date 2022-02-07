package com.development.footmat.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.development.footmat.R;
import com.development.footmat.adapters.AdapterCategory;
import com.development.footmat.adapters.AdapterItem;
import com.development.footmat.component.MyDialog;
import com.development.footmat.constans.ListString;
import com.development.footmat.databinding.ActivityCategoryDetailPageBinding;
import com.development.footmat.databinding.ActivityMainBinding;
import com.development.footmat.models.CategoryModel;
import com.development.footmat.models.ItemModel;
import com.development.footmat.viewModel.CategoryListViewModel;
import com.development.footmat.viewModel.HomeViewModel;
import com.development.footmat.viewModel.factory.CategoryListViewModelFactory;

import java.util.ArrayList;

public class CategoryDetailPage extends AppCompatActivity {
    private ActivityCategoryDetailPageBinding binding;
    private AdapterItem adapterCategory;
    private CategoryListViewModel categoryListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCategoryDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "fonts/font.ttf", true);

        String titleText = "لیست ";
        String type = getIntent().getExtras().getString("type");
        String name = getIntent().getExtras().getString("name");

        setTitle(titleText+ name);




        Log.i(ListString.TAG, "onCreate: type" +type);

        categoryListViewModel = new ViewModelProvider(this, new CategoryListViewModelFactory( type)).get(CategoryListViewModel.class);





        adapterCategory = new AdapterItem(getApplicationContext());
        MyDialog.showDialog(CategoryDetailPage.this);
        binding.listCategory.setAdapter(adapterCategory);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.listCategory.setLayoutManager(mLinearLayoutManager);

        categoryListViewModel.getCategoryMutableLiveData().observe(this, itemListUpdateObserver);


    }

    Observer<ArrayList<ItemModel>> itemListUpdateObserver = new Observer<ArrayList<ItemModel>>() {
        @Override
        public void onChanged(ArrayList<ItemModel> list) {
            adapterCategory.updateUserList(list);
            MyDialog.dismissDialog();
        }
    };


}