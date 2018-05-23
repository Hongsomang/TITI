package dsm.titi.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dsm.titi.Activity.Item.Item_Album;
import dsm.titi.Activity.Item.Item_writing;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-05-22.
 */

public class Adapter_Writing extends RecyclerView.Adapter<Adapter_Writing.ViewHolder> {
    private Context context;
    private ArrayList<Item_writing> item_writing;
    LayoutInflater layoutInflater;
    public Adapter_Writing(Context context, ArrayList<Item_writing> item_writing){
        this.context=context;
        this.item_writing=item_writing;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_writing,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(item_writing.get(position).getTitle());
        holder.address.setText(item_writing.get(position).getAddress());
        holder.content.setText(item_writing.get(position).getContent());
        holder.date.setText(item_writing.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return item_writing.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView address;
        private TextView content;
        private TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.writing_title);
            address=(TextView)itemView.findViewById(R.id.writing_address);
            content=(TextView)itemView.findViewById(R.id.writing_content);
            date=(TextView)itemView.findViewById(R.id.writing_date);

        }
    }
}
