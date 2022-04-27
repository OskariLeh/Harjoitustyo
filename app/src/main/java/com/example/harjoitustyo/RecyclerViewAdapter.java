package com.example.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private ArrayList<Lake> lakes ;
    private ArrayList<Lake> lakesCopy ;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Lake> lakes, Context context) {
        this.lakes = lakes;
        this.context = context;
        lakesCopy = new ArrayList<>(lakes);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lake_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lake currentLake = lakes.get(position);
        holder.lakeName.setText(currentLake.getName());
        holder.townName.setText(currentLake.getTown());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lake lake = lakes.get(holder.getAdapterPosition());
                Toast.makeText(context, lake.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lakes.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Lake> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(lakesCopy);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Lake lake: lakesCopy) {
                    if (lake.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(lake);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            lakes.clear();
            lakes.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lakeName;
        public TextView townName;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            townName = itemView.findViewById(R.id.town_name);
            lakeName = itemView.findViewById(R.id.lake_name);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
