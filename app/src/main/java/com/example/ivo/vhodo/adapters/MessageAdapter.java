package com.example.ivo.vhodo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ivo.vhodo.R;
import com.example.ivo.vhodo.models.Message;

import java.util.List;

/**
 * Created by Ivo on 12.10.2016 г..
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView msgText;
        public TextView userText;
        public TextView dateAndTimeText;
        public RelativeLayout parentLayout;

        public ViewHolder(View v) {
            super(v);

            msgText = (TextView) v.findViewById(R.id.messageOnTextBoard);
            userText = (TextView) v.findViewById(R.id.usernameOnTextBoard);
            dateAndTimeText = (TextView) v.findViewById(R.id.dateOnTextBoard);
            parentLayout = (RelativeLayout) v.findViewById(R.id.parentLayout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MessageAdapter(List<Message> myDataset) {
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_board, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.msgText.setText(mDataset.get(position).getMsgTxt());
        holder.userText.setText(mDataset.get(position).getUsername() + " :");
        holder.dateAndTimeText.setText(mDataset.get(position).getDateAndTime());
        holder.parentLayout.setBackgroundResource(mDataset.get(position).getMsgColor());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
