package dsm.titi.Activity.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dsm.titi.R;

/**
 * Created by ghdth on 2018-05-22.
 */

public class Adapter_Images extends RecyclerView.Adapter<Adapter_Images.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images,parent,false);
            ViewHolder holder=new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter_Album.ViewHolder holder, int position) {
            holder.image.setImageResource(item_albums.get(position).getImage());
        }

        @Override
        public int getItemCount() {
            return item_.size();
        }
        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.images_iv);
        }
    }

}
