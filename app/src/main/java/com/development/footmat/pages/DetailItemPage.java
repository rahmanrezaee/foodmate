package com.development.footmat.pages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.development.footmat.R;
import com.development.footmat.databinding.ActivityCategoryDetailPageBinding;
import com.development.footmat.databinding.ActivityDetailItemPageBinding;
import com.development.footmat.models.ItemModel;
import com.development.footmat.services.FavoriteService;
import com.development.footmat.viewModel.FavoriteViewModel;
import com.development.footmat.viewModel.factory.FavoriteViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DetailItemPage extends AppCompatActivity {
    private ActivityDetailItemPageBinding binding;
    private FavoriteService favoriteService;
    private ItemModel itemFood;
    private List<ItemModel> items;
    private Menu mMenuItem;
    private File imagePath;

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailItemPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {

            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

        setSupportActionBar(binding.toolbar);

        itemFood = (ItemModel) getIntent().getSerializableExtra("data");


        binding.descriptionItem.setText(itemFood.getDescription());
        binding.nameItem.setText(itemFood.getTitle());
        Glide.with(getApplicationContext())
                .load(itemFood.getImgIcon())
                .placeholder(R.drawable.ic_baseline_image)
                .error(R.drawable.ic_baseline_info)
                .into(binding.imageItem);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "fonts/font.ttf", true);

        favoriteService = new FavoriteService(getApplicationContext());

        items = favoriteService.getListFavorite();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_food, menu);
        mMenuItem = menu;
        if (favoriteService.isExistInList(favoriteService.getListFavorite(), itemFood))
            mMenuItem.getItem(0).setIcon(R.drawable.ic_baseline_favorite);
        else
            mMenuItem.getItem(0).setIcon(R.drawable.ic_baseline_favorite_border);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem itemMenu) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = itemMenu.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addToFavorite) {


            favoriteService.toggleToFavorite(itemFood);
            if (favoriteService.isExistInList(favoriteService.getListFavorite(), itemFood)) {
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "به لیست علاقمندان اضافیه شد.", Snackbar.LENGTH_LONG)

                        .setActionTextColor(getResources().getColor(R.color.teal_200));

                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200));
                TextView snackbarTextView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);

                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fonts/font.ttf");

                snackbarTextView.setTypeface(face);


                snackbar.show();
                mMenuItem.getItem(0).setIcon(R.drawable.ic_baseline_favorite);


            } else {
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "از لیست علاقمندان پاک شد.", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(R.color.teal_200));

                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200));
                TextView snackbarTextView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);

                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fonts/font.ttf");

                snackbarTextView.setTypeface(face);
                snackbar.show();
                mMenuItem.getItem(0).setIcon(R.drawable.ic_baseline_favorite_border);
            }


        } else if (id == R.id.share) {



            Bitmap bitmap = takeScreenshot();
            saveBitmap(bitmap);
            shareIt();
        }

        return super.onOptionsItemSelected(itemMenu);
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void shareIt() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, itemFood.getTitle());
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, itemFood.getDescription());
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}