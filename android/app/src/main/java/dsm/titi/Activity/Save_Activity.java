package dsm.titi.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
    private Button save_btn;
    private ImageView back_btn,puls_btn;
    private EditText title_et,content_et,address_et;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        back_btn=(ImageView) findViewById(R.id.save_back);
        save_btn=(Button)findViewById(R.id.save_btn);
        title_et=(EditText)findViewById(R.id.title_et);
        content_et=(EditText)findViewById(R.id.content_et);
        address_et=(EditText)findViewById(R.id.address_et);
        puls_btn=(ImageView)findViewById(R.id.plus_btn);

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

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        puls_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image();
            }
        });


    }
    public void save(){
        String title=title_et.getText().toString();
        String content=content_et.getText().toString();
        String address=address_et.getText().toString();


    }
    public void Image(){

    }


}
