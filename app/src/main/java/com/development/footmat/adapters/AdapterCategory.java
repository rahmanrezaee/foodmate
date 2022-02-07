package com.development.footmat.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.development.footmat.R;
import com.development.footmat.models.CategoryModel;
import com.development.footmat.pages.CategoryDetailPage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCategory  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<CategoryModel> categoryArrayList;
    Context context;

    public AdapterCategory(Context context) {
        this.categoryArrayList = new ArrayList<CategoryModel>();
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CategoryModel category = categoryArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        viewHolder.txtView_title.setText(category.getTitle());
            Glide.with(context)
                .load(category.getImgIcon())
                    .placeholder(R.drawable.ic_baseline_image)
                    .error(R.drawable.ic_baseline_info)
                .into(viewHolder.imgView_icon);

            viewHolder.container_list.setOnClickListener(view -> {

                Intent intent = new Intent(context, CategoryDetailPage.class);
                intent.putExtra("type", category.getItems());
                intent.putExtra("name", category.getTitle());
                context.startActivity(intent);


            });


    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public void updateUserList(final ArrayList<CategoryModel> categoryModelList) {
        this.categoryArrayList.clear();
        this.categoryArrayList = categoryModelList;
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {


        ImageView imgView_icon;
        TextView txtView_title;
        CardView container_list;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_icon = itemView.findViewById(R.id.image_item_list);

            txtView_title = itemView.findViewById(R.id.title_item_list);
            container_list = itemView.findViewById(R.id.container_list);

            Typeface face = Typeface.createFromAsset(context.getAssets(),
                    "fonts/font.ttf");
            txtView_title.setTypeface(face);




        }
    }
}