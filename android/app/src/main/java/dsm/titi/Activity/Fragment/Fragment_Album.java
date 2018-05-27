package dsm.titi.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dsm.titi.Activity.Adapter.Adapter_Album;
import dsm.titi.Activity.Adapter.Adapter_save;
import dsm.titi.Activity.DB.DB_Save;
import dsm.titi.Activity.DB.DB_Save_Image;
import dsm.titi.Activity.Item.Item_Album;
import dsm.titi.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-04-23.
 */

public class Fragment_Album extends Fragment{
    private ArrayList<Item_Album> mItem;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Item_Album item_album;
    private Realm mRealm;
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
        Realm.init(getContext());

       /* mItem.add(new Item_Album(R.drawable.test3,"부산"));
        mItem.add(new Item_Album(R.drawable.test, "서울"));
        mItem.add(new Item_Album(R.drawable.test2,"대전"));*/
        Realm();
        RealmResults<DB_Save> results=mRealm.where(DB_Save.class).findAll();
        for(int i=0;i<results.size();i++){
            DB_Save db_save=results.get(i);
            Log.d("Fragment_Album",db_save.getAddress());
            DB_Save_Image db_save_image=mRealm.where(DB_Save_Image.class).findFirst();
            mItem.add(new Item_Album(db_save_image.getImage(),db_save_image.getTitle()));


        }

        return layout;

    }
    public void Realm(){

        try {
            mRealm= Realm.getDefaultInstance();

        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
    }
}
