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
import android.widget.Toast;

import com.example.ivo.vhodo.GlobalData;
import com.example.ivo.vhodo.R;
import com.example.ivo.vhodo.models.Neighbour;

import java.util.List;

/**
 * Created by vilimir on 13.10.16.
 */
public class NeighbourAdapter  extends RecyclerView.Adapter<NeighbourAdapter.ViewHolder> {
    private List<Neighbour> mDataset;
    private Context ctx;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView neighbourName;
        public TextView neighbourPhone;
        public TextView neighbourEmail;
        public Button callButton;
        public Button emailButton;


        public ViewHolder(View v) {
            super(v);

            neighbourName = (TextView) v.findViewById(R.id.neigbourNameText);
            neighbourPhone = (TextView) v.findViewById(R.id.neighbourPhoneText);
            neighbourEmail = (TextView) v.findViewById(R.id.neighbourEmailText);
            callButton = (Button) v.findViewById(R.id.callNeighbourButton);
            emailButton = (Button)v.findViewById(R.id.sendNeighbourEmailButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NeighbourAdapter(List<Neighbour> myDataset,Context context) {
        this.mDataset = myDataset;
        this.ctx = context;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.neighbourName.setText(mDataset[position]);
        holder.neighbourName.setText(mDataset.get(position).getName());
        holder.neighbourPhone.setText(mDataset.get(position).getPhone());
        holder.neighbourEmail.setText(mDataset.get(position).getEmail());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButtonClicked(position);
            }
        });
        holder.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailButtonClicked(position);
            }
        });
    }

    private void emailButtonClicked(int position) {
        String email = mDataset.get(position).getEmail();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(Intent.createChooser(emailIntent, "Send mail...").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void callButtonClicked(int position){
        String phone =  mDataset.get(position).getPhone();
        Uri number = Uri.parse("tel:"+ phone);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(callIntent);
    }
}
