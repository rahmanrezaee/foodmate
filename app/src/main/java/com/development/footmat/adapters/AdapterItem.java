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
import com.development.footmat.models.ItemModel;
import com.development.footmat.pages.CategoryDetailPage;
import com.development.footmat.pages.DetailItemPage;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterItem  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ItemModel> itemArrayList;
    Context context;

    public AdapterItem(Context context) {
        this.itemArrayList = new ArrayList<ItemModel>();
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new AdapterItem.RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemModel item = itemArrayList.get(position);
        AdapterItem.RecyclerViewViewHolder viewHolder= (AdapterItem.RecyclerViewViewHolder) holder;

        viewHolder.txtView_title.setText(item.getTitle());
        Glide.with(context)
                .load(item.getImgIcon())
                .into(viewHolder.imgView_icon);

        viewHolder.container_list.setOnClickListener(view -> {

            Intent intent = new Intent(context, DetailItemPage.class);
            intent.putExtra("data", item);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);


        });


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public void updateUserList(final ArrayList<ItemModel> ItemModelList) {
        this.itemArrayList.clear();
        this.itemArrayList = ItemModelList;
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