package com.example.musclemonster_fitnessapp.AdapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musclemonster_fitnessapp.BottomBarFragments.Products.ViewProductDetail;
import com.example.musclemonster_fitnessapp.POJOClasses.ProductUpload_POJO;
import com.example.musclemonster_fitnessapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter_Prod_Shopping extends RecyclerView.Adapter<Adapter_Prod_Shopping.MyViewHolder>
{
    Context context;
    ArrayList<ProductUpload_POJO> list;

    public Adapter_Prod_Shopping(Context context, ArrayList<ProductUpload_POJO> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @NotNull
    @Override
    public Adapter_Prod_Shopping.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.prod_shopping_items,parent,false);
        return new Adapter_Prod_Shopping.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_Prod_Shopping.MyViewHolder holder, int position) {
        //ProductUpload_POJO Obj = list.get(position);
        holder.ProdName.setText(list.get(position).getProductName());
        holder.ProdPrice.setText(list.get(position).getProductPrice());
        Glide.with(context)
                .load(list.get(position).getImageUri())
                .into(holder.ImgV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Fragment ExerciseSubFragment=new ExerciseSubFragment();
                //FragmentManager fragmentManager=context.getSupportFragmentManager();
                Intent intent = new Intent(context, ViewProductDetail.class);
                intent.putExtra("ItemKey",list.get(position).getFKey());
                intent.putExtra("ItemName",list.get(position).getProductName());
                intent.putExtra("ItemPrice",list.get(position).getProductPrice());
                intent.putExtra("ItemCat",list.get(position).getProductCat());
                intent.putExtra("ItemDesc",list.get(position).getProductDesc());
                intent.putExtra("ItemImageUri",list.get(position).getImageUri());
                intent.putExtra("ItemWeight",list.get(position).getProductWeight());
                intent.putExtra("ItemGen",list.get(position).getProdGen());
                intent.putExtra("UserKey",list.get(position).getUserKey());
                intent.putExtra("UploadDate",list.get(position).getUploadDate());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ProdName,ProdPrice;
        ImageView ImgV;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ImgV = itemView.findViewById(R.id.ProdImgView);
            ProdName=itemView.findViewById(R.id.TxtProductName);
            ProdPrice=itemView.findViewById(R.id.TxtProductPrice);
        }
    }
}
