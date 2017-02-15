package adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jecrc.prtm.attendanceapp.R;
import jecrc.prtm.attendanceapp.Treceived;
import jecrc.prtm.attendanceapp.Tsent;

/**
 * Created by ghost on 14/2/17.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;

    private final int received = 0, sent = 1;

    public ChatRecyclerAdapter(List<Object> items) {
        this.items = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case sent:
                View v1 = inflater.inflate(R.layout.custom_chatoutgoing, parent, false);
                viewHolder = new ViewHolderSent(v1);
                break;
            case received:
                View v2 = inflater.inflate(R.layout.custom_chatincoming, parent, false);
                viewHolder = new ViewHolderReceive(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.custom_chattime, parent, false);
                viewHolder = new MyViewHolder(v);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case sent:
                ViewHolderSent vh1 = (ViewHolderSent) holder;
                configureViewHolder1(vh1, position);
                break;
            case received:
                ViewHolderReceive vh2 = (ViewHolderReceive) holder;
                configureViewHolder2(vh2, position);
                break;
            default:
                MyViewHolder vh = (MyViewHolder) holder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureDefaultViewHolder(MyViewHolder vh, int position) {
        vh.time.setText((String) items.get(position));
    }

    private void configureViewHolder1(ViewHolderSent vh1, int position) {
        Tsent tsent = (Tsent) items.get(position);
        if (tsent != null) {
            if (tsent.isMsg()) {
                vh1.label1.setText(tsent.getMessage());
                vh1.img.setVisibility(View.GONE);
                vh1.label1.setVisibility(View.VISIBLE);
            } else {
                vh1.img.setVisibility(View.VISIBLE);
                vh1.label1.setVisibility(View.GONE);
                vh1.img.setImageBitmap((Bitmap) items.get(position));
            }
        }
    }

    private void configureViewHolder2(ViewHolderReceive vh2, int position) {
        Tsent treceive = (Tsent) items.get(position);
        if (treceive.isMsg()) {
            vh2.label1.setText(treceive.getMessage());
            vh2.img.setVisibility(View.GONE);
            vh2.label1.setVisibility(View.VISIBLE);
        } else {
            vh2.img.setVisibility(View.VISIBLE);
            vh2.label1.setVisibility(View.GONE);
            vh2.img.setImageBitmap((Bitmap) items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Treceived) {
            return sent;
        } else if (items.get(position) instanceof Tsent) {
            return received;
        }
        return -1;
    }


    //sent message viewholder
    private class ViewHolderReceive extends RecyclerView.ViewHolder {

        TextView label1;
        ImageView img;

        ViewHolderReceive(View v) {
            super(v);
            label1 = (TextView) v.findViewById(R.id.textincoming);

            img = (ImageView) v.findViewById(R.id.textimgReceive);
        }
    }

    //time viewholder
    private class MyViewHolder extends RecyclerView.ViewHolder {


        TextView time;

        MyViewHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.texttime);
        }
    }

    //received message viewholder
    private class ViewHolderSent extends RecyclerView.ViewHolder {

        TextView label1;
        ImageView img;

        ViewHolderSent(View v) {
            super(v);
            label1 = (TextView) v.findViewById(R.id.textOutgoing);
            img = (ImageView) v.findViewById(R.id.textimgSent);
        }
    }
}
