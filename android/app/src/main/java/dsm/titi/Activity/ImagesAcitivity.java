package dsm.titi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dsm.titi.Activity.Adapter.Adapter_Images;
import dsm.titi.Activity.Adapter.Adapter_save;
import dsm.titi.Activity.DB.DB_Save;
import dsm.titi.Activity.DB.DB_Save_Image;
import dsm.titi.Activity.Item.Item_Save_Image;
import dsm.titi.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-05-22.
 */


public class ImagesAcitivity extends AppCompatActivity {
    private Button image_back;
    private TextView title;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Item_Save_Image> mItem;
    private LinearLayoutManager linearLayoutManager;
    private String address;
    private Realm mRealm;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        title=(TextView)findViewById(R.id.image_title);
        recyclerView=(RecyclerView)findViewById(R.id.images_rv);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),5));
        mItem=new ArrayList<>();
        mAdapter=new Adapter_Images(getApplicationContext(),mItem);
        recyclerView.setAdapter(mAdapter);
        intent=getIntent();
        Toast.makeText(getApplicationContext(),"getIntent:"+intent.getStringExtra("address"),Toast.LENGTH_LONG).show();
        title.setText(intent.getStringExtra("title"));
        address=intent.getStringExtra("address");
        Realm();
        mItem.clear();
        RealmResults<DB_Save_Image> results=mRealm.where(DB_Save_Image.class).equalTo("address",address).findAll();
        if(results.size()!=0){
            for(int i=0;i<results.size();i++){
                DB_Save_Image db_save_image=results.get(i);
                mItem.add(new Item_Save_Image(db_save_image.getImage()));

            }
        }
        mAdapter.notifyDataSetChanged();

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
