package dsm.titi.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dsm.titi.Activity.Item.Item_Images;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-05-22.
 */

public class Adapter_Images extends RecyclerView.Adapter<Adapter_Images.ViewHolder> {

    private Context context;
    private ArrayList<Item_Images> item_images;
    public Adapter_Images(Context context, ArrayList<Item_Images> item_images){
        this.context=context;
        this.item_images=item_images;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(item_images.get(position).getImages());

    }


    @Override
    public int getItemCount() {
        return item_images.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.images_iv);
        }

    }

}
