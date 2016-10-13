package com.example.ivo.vhodo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ivo.vhodo.R;
import com.example.ivo.vhodo.models.Mechanic;

import java.util.List;

/**
 * Created by Ivo on 13.10.2016 г..
 */
public class MechanicAdapter extends RecyclerView.Adapter<MechanicAdapter.ViewHolder> {
    private List<Mechanic> mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mechanicName;
        public TextView mechanicPhone;
        public TextView mechanicDescription;

        public ViewHolder(View v) {
            super(v);

            mechanicName = (TextView) v.findViewById(R.id.mechanicName);
            mechanicPhone = (TextView) v.findViewById(R.id.mechanicPhone);
            mechanicDescription = (TextView) v.findViewById(R.id.mechanicDescrition);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MechanicAdapter(List<Mechanic> myDataset) {
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MechanicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_mechanics, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mechanicName.setText(mDataset[position]);
        holder.mechanicName.setText(mDataset.get(position).getName() + " :");
        holder.mechanicPhone.setText(mDataset.get(position).getPhone());
        holder.mechanicDescription.setText(mDataset.get(position).getType());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

