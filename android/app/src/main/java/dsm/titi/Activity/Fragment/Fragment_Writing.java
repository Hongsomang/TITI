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
import dsm.titi.Activity.Adapter.Adapter_Writing;
import dsm.titi.Activity.Item.Item_Album;
import dsm.titi.Activity.Item.Item_writing;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-04-23.
 */


    public class Fragment_Writing extends Fragment {
    private ArrayList<Item_writing> mItem;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Item_writing item_writing;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View layout=inflater.inflate(R.layout.fragment_writing,container,false);
        recyclerView=(RecyclerView)layout.findViewById(R.id.writing_rv);
        recyclerView.hasFixedSize();

        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mItem=new ArrayList<>();
        adapter=new Adapter_Writing(getContext(),mItem);
        recyclerView.setAdapter(adapter);

        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));
        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));
        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));
        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));


        return layout;
    }
}
