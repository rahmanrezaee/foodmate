package com.development.footmat.viewModel;

import android.util.Log;

import com.development.footmat.constans.ListString;
import com.development.footmat.models.CategoryModel;
import com.development.footmat.component.MyDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<CategoryModel>> categoryLivaData;
    ArrayList<CategoryModel> categoryModelArrayList = new ArrayList<>();
    private FirebaseFirestore db;
    private CollectionReference colRefUsers;


    public HomeViewModel() {
        categoryLivaData = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        colRefUsers = db.collection(ListString.COLLECTION_CATEGORY);

        fetchCollection();
    }

    public MutableLiveData<ArrayList<CategoryModel>> getCategoryMutableLiveData(){
        return categoryLivaData;
    }


    private void fetchCollection() {



        Log.i(ListString.TAG, "Starting to Fetch Data");

        colRefUsers.orderBy("priority").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                MyDialog.dismissDialog();
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        Log.i(ListString.TAG, "image url : " +document.getString("image"));

                        String name = document.getString("name");
                        String items = document.getString("items");
                        String image = document.getString("image")+"?alt=media";

                        categoryModelArrayList.add( new CategoryModel(name,items,image));

                    }

                    categoryLivaData.setValue(categoryModelArrayList);
                } else {
                    Log.i(ListString.TAG, "Errpr Message: "+task.getException().getMessage());;
                }
            }
        });
    }
}