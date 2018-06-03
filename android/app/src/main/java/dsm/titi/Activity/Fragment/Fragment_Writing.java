package dsm.titi.Activity.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dsm.titi.Activity.Adapter.Adapter_Album;
import dsm.titi.Activity.Adapter.Adapter_Writing;
import dsm.titi.Activity.DB.DB;
import dsm.titi.Activity.DB.DB_Save;
import dsm.titi.Activity.DB.DB_Save_Image;
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
    private SwipeRefreshLayout swipeRefreshLayout;
    private Realm mRealm;
    private String title,address;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View layout=inflater.inflate(R.layout.fragment_writing,container,false);
        recyclerView=(RecyclerView)layout.findViewById(R.id.writing_rv);
        swipeRefreshLayout=(SwipeRefreshLayout)layout.findViewById(R.id.swipeRefreshLayout);
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
                Log.d("Fragment_Wrting",db_save.toString());
                mItem.add(new Item_writing(db_save.getTitle(),db_save.getAddress(),db_save.getContent(),db_save.getDate()));
            }
            adapter.notifyDataSetChanged();
        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mItem.clear();
                Realm();
                RealmResults<DB_Save> results=mRealm.where(DB_Save.class).findAll();
                for(int i=0;i<results.size();i++){
                    DB_Save db_save=results.get(i);
                    mItem.add(new Item_writing(db_save.getTitle(),db_save.getAddress(),
                            db_save.getContent(),db_save.getDate()));
                }
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        final GestureDetector gestureDetector = new GestureDetector(getContext(),new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(final RecyclerView rv, MotionEvent e) {
                final View view=rv.findChildViewUnder(e.getX(),e.getY());
                if(view!=null&&gestureDetector.onTouchEvent(e)){
                    Toast.makeText(getContext(),String.valueOf(rv.getChildAdapterPosition(view)),Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("삭제하기");
                    builder.setMessage(mItem.get(rv.getChildAdapterPosition(view)).getTitle()+"를(을) 삭제하시겠습니까?");
                    builder.setPositiveButton("삭제",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(),"삭제 합니다.",Toast.LENGTH_LONG).show();
                                    delete(mItem.get(rv.getChildAdapterPosition(view)).getAddress());
                                    // adapter_mydiaries.deleteItem(position);
                                }
                            });
                    builder.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(),"취소합니다.",Toast.LENGTH_LONG).show();
                                }
                            });
                    builder.show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return layout;
    }

    public void delete(String address){
        Realm();
        mRealm.beginTransaction();
        RealmResults<DB_Save_Image> results=mRealm.where(DB_Save_Image.class)
                .equalTo("address",address).findAll();
        results.deleteAllFromRealm() ;
        Log.d("results:",String.valueOf(results.size()));
        RealmResults<DB_Save> results2=mRealm.where(DB_Save.class)
                .equalTo("address",address).findAll();
        results2.deleteAllFromRealm();
        Log.d("results2:",String.valueOf(results2.size()));
        mRealm.commitTransaction();

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
