package com.example.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Trip> trips;

    public TripRecyclerViewAdapter(Context context, List<Trip> trips) {
        this.context = context;
        this.trips = trips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip trip = trips.get(position);
        holder.lakeName.setText(trip.getLake().getName());
        holder.tripDuration.setText(trip.getDescription());
        //holder.tripDate.setText(trip.getTime().toString());
        holder.townName.setText(trip.getLake().getTown());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trip trip1 = trips.get(holder.getAdapterPosition());
                Toast.makeText(context, trip1.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lakeName;
        public TextView tripDate;
        public TextView tripDuration;
        public TextView townName;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            townName = itemView.findViewById(R.id.lake_location);
            lakeName = itemView.findViewById(R.id.lake_name_trip);
            tripDate = itemView.findViewById(R.id.trip_date);
            tripDuration = itemView.findViewById(R.id.trip_duration);
            relativeLayout = itemView.findViewById(R.id.relative_layout_trip);
        }
    }
}
