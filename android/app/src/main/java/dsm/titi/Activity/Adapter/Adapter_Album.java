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
import java.util.List;

import dsm.titi.Activity.Item.Item_Album;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-05-22.
 */

public class Adapter_Album extends RecyclerView.Adapter<Adapter_Album.ViewHolder> {
    private Context context;
    private ArrayList<Item_Album> item_albums;
    LayoutInflater layoutInflater;
    public Adapter_Album(Context context, ArrayList<Item_Album> item_albums){
        this.context=context;
        this.item_albums=item_albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(item_albums.get(position).getImage());
        holder.title.setText(item_albums.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return item_albums.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.album_iv);
            title=(TextView)itemView.findViewById(R.id.album_title);

        }
    }
}
