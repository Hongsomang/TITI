package dsm.titi.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dsm.titi.Activity.Adapter.Adapter_Album;
import dsm.titi.Activity.Adapter.Adapter_save;
import dsm.titi.Activity.Item.Item_Album;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-04-23.
 */

public class Fragment_Album extends Fragment{
    private ArrayList<Item_Album> mItem;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Adapter_Album adapter_album;
    private Item_Album item_album;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View layout=inflater.inflate(R.layout.fragment_album,container,false);
        recyclerView=(RecyclerView)layout.findViewById(R.id.album_rv);
        recyclerView.hasFixedSize();

        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        mItem=new ArrayList<>();
        adapter=new Adapter_Album(getContext(),mItem);
        recyclerView.setAdapter(adapter);

        mItem.add(new Item_Album(R.drawable.test3,"부산"));
        mItem.add(new Item_Album(R.drawable.test,"서울"));
        mItem.add(new Item_Album(R.drawable.test2,"대전"));
        adapter_album=new Adapter_Album(getContext(),mItem);

        return layout;

    }
}
