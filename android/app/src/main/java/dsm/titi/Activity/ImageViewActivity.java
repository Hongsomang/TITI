package dsm.titi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;

import dsm.titi.Activity.Adapter.Adapter_ImageView;
import dsm.titi.R;

/**
 * Created by ghdth on 2018-05-31.
 */

public class ImageViewActivity extends AppCompatActivity {
   Adapter_ImageView adapter;
   ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        viewPager=(ViewPager)findViewById(R.id.iv_vp);
        Intent intent=getIntent();
        String address=intent.getStringExtra("address");
        int id=intent.getIntExtra("position",0);
        adapter=new Adapter_ImageView(this,id,address);
        viewPager.setAdapter(adapter);

    }
}
