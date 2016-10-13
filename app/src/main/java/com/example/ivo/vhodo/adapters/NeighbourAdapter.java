package com.example.ivo.vhodo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivo.vhodo.R;
import com.example.ivo.vhodo.models.Neighbour;

import java.util.List;

/**
 * Created by vilimir on 13.10.16.
 */
public class NeighbourAdapter  extends RecyclerView.Adapter<NeighbourAdapter.ViewHolder> {
    private List<Neighbour> mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView neighbourName;
        public TextView neighbourPhone;
        public TextView neighbourEmail;

        public ViewHolder(View v) {
            super(v);

            neighbourName = (TextView) v.findViewById(R.id.neigbourNameText);
            neighbourPhone = (TextView) v.findViewById(R.id.neighbourPhoneText);
            neighbourEmail = (TextView) v.findViewById(R.id.neighbourEmailText);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NeighbourAdapter(List<Neighbour> myDataset) {
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NeighbourAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.neighbour_bar, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.neighbourName.setText(mDataset[position]);
        holder.neighbourName.setText(mDataset.get(position).getName());
        holder.neighbourPhone.setText(mDataset.get(position).getPhone());
        holder.neighbourEmail.setText(mDataset.get(position).getEmail());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
