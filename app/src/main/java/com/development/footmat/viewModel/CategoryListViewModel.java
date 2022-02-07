package com.development.footmat.viewModel;

import android.util.Log;

import com.development.footmat.constans.ListString;
import com.development.footmat.models.ItemModel;
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

public class CategoryListViewModel  extends ViewModel {


    MutableLiveData<ArrayList<ItemModel>> categoryLivaData;
    ArrayList<ItemModel> ItemModelArrayList = new ArrayList<>();
    private FirebaseFirestore db;
    private CollectionReference colRefUsers;


    public CategoryListViewModel(String category) {
        categoryLivaData = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();

        Log.i("TAG", "CategoryListViewModel: category:"+category);
        colRefUsers = db.collection(category);

        fetchCollection();
    }

    public MutableLiveData<ArrayList<ItemModel>> getCategoryMutableLiveData(){
        return categoryLivaData;
    }


    private void fetchCollection() {

        Log.i(ListString.TAG, "Starting to Fetch Data");

        colRefUsers.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                MyDialog.dismissDialog();
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        Log.i(ListString.TAG, "image url : " +document.getString("image"));

                        String name = document.getString("name");
                        String description = document.getString("description");
                        String image = document.getString("image")+"?alt=media";

                        ItemModelArrayList.add( new ItemModel(name,description,image));

                    }

                    categoryLivaData.setValue(ItemModelArrayList);
                } else {
                    Log.i(ListString.TAG, "Errpr Message: "+task.getException().getMessage());;
                }
            }
        });
    }
}
