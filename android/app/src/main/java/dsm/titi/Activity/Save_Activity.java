package dsm.titi.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import dsm.titi.Activity.Adapter.Adapter_save;
import dsm.titi.Activity.Item.Item_save;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-04-26.
 */

public class Save_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Item_save> mItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        recyclerView=(RecyclerView)findViewById(R.id.save_rv);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mItem=new ArrayList<>();
        madapter=new Adapter_save(mItem,getApplicationContext());
        recyclerView.setAdapter(madapter);
        mItem.add(new Item_save(R.drawable.splash_background));
        mItem.add(new Item_save(R.drawable.test));
        mItem.add(new Item_save(R.drawable.test2));
        mItem.add(new Item_save(R.drawable.test3));

    }
}
