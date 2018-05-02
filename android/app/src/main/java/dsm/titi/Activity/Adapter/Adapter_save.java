package dsm.titi.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dsm.titi.Activity.Item.Item_save;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-05-02.
 */

public class Adapter_save extends RecyclerView.Adapter<Adapter_save.ViewHolder> {
    LayoutInflater layoutInflater;
    private ArrayList<Item_save> mItem;
    private Context context;
    public Adapter_save(ArrayList<Item_save> mItem,Context context){
        this.mItem=mItem;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_save,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     holder.imageView.setImageResource(mItem.get(position).getImage());
    }


    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.save_iv);

        }
    }
}
