
package com.example.smartmallcompanion.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmallcompanion.R;
import com.example.smartmallcompanion.activities.StoreDetailsActivity;
import com.example.smartmallcompanion.models.Store;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private Context context;
    private List<Store> storeList;

    public StoreAdapter(Context context, List<Store> storeList) {
        this.context = context;
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_store, parent, false);

        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull StoreViewHolder holder, int position) {

        Store store = storeList.get(position);

        holder.name.setText(store.getName());
        holder.category.setText(store.getCategory());
        holder.floor.setText(store.getFloor());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoreDetailsActivity.class);

            intent.putExtra("name", store.getName());
            intent.putExtra("category", store.getCategory());
            intent.putExtra("floor", store.getFloor());
            intent.putExtra("description", store.getDescription());
            intent.putExtra("offer", store.getOffer());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView name, category, floor;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvStoreName);
            category = itemView.findViewById(R.id.tvStoreCategory);
            floor = itemView.findViewById(R.id.tvStoreFloor);
        }
    }
}
