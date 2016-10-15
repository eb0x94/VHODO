package com.example.ivo.vhodo.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private Context ctx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mechanicName;
        public TextView mechanicPhone;
        public TextView mechanicDescription;
        public Button callMechanicButton;

        public ViewHolder(View v) {
            super(v);

            mechanicName = (TextView) v.findViewById(R.id.mechanicName);
            mechanicPhone = (TextView) v.findViewById(R.id.mechanicPhone);
            mechanicDescription = (TextView) v.findViewById(R.id.mechanicDescrition);
            callMechanicButton = (Button) v.findViewById(R.id.callMechanicButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MechanicAdapter(List<Mechanic> myDataset, Context context) {
        this.mDataset = myDataset;
        this.ctx = context;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mechanicName.setText(mDataset[position]);
        holder.mechanicName.setText(mDataset.get(position).getName() + " :");
        holder.mechanicPhone.setText(mDataset.get(position).getPhone());
        holder.mechanicDescription.setText(mDataset.get(position).getType());
        holder.callMechanicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallMechanicButtonClicked(position);
            }
        });
    }

    private void onCallMechanicButtonClicked(int position) {
        String phone =  mDataset.get(position).getPhone();
        Uri number = Uri.parse("tel:" + phone);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(callIntent);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

