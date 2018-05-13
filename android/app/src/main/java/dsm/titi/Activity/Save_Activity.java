package dsm.titi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.util.ArrayList;
import java.util.List;

import dsm.titi.Activity.Adapter.Adapter_save;
import dsm.titi.Activity.Item.Item_Save_Image;
import dsm.titi.Activity.Item.Item_save;
import dsm.titi.Manifest;
import dsm.titi.R;

import static com.gun0912.tedpicker.ImagePickerActivity.EXTRA_IMAGE_URIS;

/**
 * Created by ghdth on 2018-04-26.
 */

public class Save_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter madapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Item_Save_Image> mItem=new ArrayList<>();
    ;
    private Button save_btn;
    private ImageView back_btn,puls_btn;
    private EditText title_et,content_et,address_et;
    final int REQ_CODE_SELECT_IMAGE=100;
    private ArrayList<Uri> image_item=new ArrayList<Uri>();





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

       /* mItem.add(new Item_save(R.drawable.splash_background));
        mItem.add(new Item_save(R.drawable.test));
        mItem.add(new Item_save(R.drawable.test2));
        mItem.add(new Item_save(R.drawable.test3));*/

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
                if(Build.VERSION.SDK_INT > 22){
                    int permissionCheck = ContextCompat.checkSelfPermission(Save_Activity.this, android.Manifest.permission.CAMERA)
                            | ContextCompat.checkSelfPermission(Save_Activity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Save_Activity.this,
                                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA},
                                REQ_CODE_SELECT_IMAGE);
                    }else{
                        Image();
                    }
                }else{
                    Image();
                }

            }
        });


    }
    public void save(){
        String title=title_et.getText().toString();
        String content=content_et.getText().toString();
        String address=address_et.getText().toString();


    }
    public void Image(){
        Intent intent = new Intent(this,ImagePickerActivity.class);
        if(image_item!=null){
            intent.putParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS,image_item);
        }
        startActivityForResult(intent,REQ_CODE_SELECT_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQ_CODE_SELECT_IMAGE){
                image_item=data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
                if(image_item!=null){
                    mItem.clear();
                    for(int i=0;i<image_item.size();i++){
                        Log.d("사진",image_item.get(i).toString());
                        mItem.add(new Item_Save_Image(image_item.get(i)));

                        //ImageView imgView = (ImageView) recyclerView.getChildAt(i).findViewById(R.id.save_iv);
                       // Glide.with(this).load(image_item.get(i)).into(imgView);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            madapter=new Adapter_save(mItem,getApplicationContext());
                            recyclerView.setAdapter(madapter);
                            madapter.notifyDataSetChanged();

                        }
                    });

                }
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQ_CODE_SELECT_IMAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Image();
                }else{
                    finish();
                }
                break;
            default:
                break;


        }
    }
}
