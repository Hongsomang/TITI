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
import dsm.titi.Activity.Adapter.Adapter_Writing;
import dsm.titi.Activity.DB.DB;
import dsm.titi.Activity.DB.DB_Save;
import dsm.titi.Activity.Item.Item_Album;
import dsm.titi.Activity.Item.Item_writing;
import dsm.titi.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-04-23.
 */


    public class Fragment_Writing extends Fragment {
    private ArrayList<Item_writing> mItem;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Item_writing item_writing;
    private Realm mRealm;
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
        Realm.init(getContext());
        /*mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));
        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));
        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));
        mItem.add(new Item_writing("부산","ㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇㄹ","ㅇㄹㅇㄹㅇㄹㅇㄹ","ㅇㄹㅇㄹ"));*/
        Realm();
        RealmResults<DB_Save> results=mRealm.where(DB_Save.class).findAll();
        if(results.size()==0){
            Log.d("DB_Save:","데이터 없음");
        }
        else{
            mItem.clear();
            for(int i=0;i<results.size();i++){
                DB_Save db_save=results.get(i);
                mItem.add(new Item_writing(db_save.getTitle(),db_save.getAddress(),db_save.getContent(),db_save.getDate()));
            }
            adapter.notifyDataSetChanged();
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
