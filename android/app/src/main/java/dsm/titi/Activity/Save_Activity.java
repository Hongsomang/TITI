package dsm.titi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
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
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dsm.titi.Activity.Adapter.Adapter_save;
import dsm.titi.Activity.DB.DB;
import dsm.titi.Activity.DB.DB_Save;
import dsm.titi.Activity.DB.DB_Save_Image;
import dsm.titi.Activity.Item.Item_Save_Image;
import dsm.titi.Activity.Item.Item_save;
import dsm.titi.Manifest;
import dsm.titi.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static com.gun0912.tedpicker.ImagePickerActivity.EXTRA_IMAGE_URIS;

/**
 * Created by ghdth on 2018-04-26.
 */

public class Save_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;		
    private RecyclerView.Adapter madapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Item_Save_Image> mItem=new ArrayList<>();
    public RequestManager mGlideRequestManager;

    private String TAG="Save_Activity";
    private Button save_btn;
    private ImageView back_btn,puls_btn;
    private EditText title_et,content_et,address_et;
    final int REQ_CODE_SELECT_IMAGE=100;
    private ArrayList<Uri> image_item=new ArrayList<Uri>();

    private DB db=new DB();
    private Realm mRealm;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");



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

        madapter=new Adapter_save(mItem,getApplicationContext());
        recyclerView.setAdapter(madapter);

        if(getIntent().getExtras()!=null){
            modify();


        }
       /* mItem.add(new Item_save(R.drawable.splash_background));
        mItem.add(new Item_save(R.drawable.test));
        mItem.add(new Item_save(R.drawable.test2));
        mItem.add(new Item_save(R.drawable.test3));*/
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm.init(Save_Activity.this);
                if(getIntent().getExtras()!=null){
                    upate_save();
                }
                else{
                    save();

                }
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
                    }

                    else{
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
        if(title.equals("")||content.equals("")||address.equals("")){
            Toast.makeText(getApplicationContext(),"모두 입력해 주세요.",Toast.LENGTH_LONG).show();
        }else {
           // db.Realm();
            Realm();
            DB_Save db_save=mRealm.createObject(DB_Save.class);
            db_save.setTitle(title);
            db_save.setContent(content);
            db_save.setAddress(address);
            db_save.setDate(getTime());

            Log.d(TAG,String.valueOf(mItem.size()));
            for(int i=0;i<mItem.size();i++){
                DB_Save_Image db_save_image=mRealm.createObject(DB_Save_Image.class);
                db_save_image.setTitle(title);
                db_save_image.setImage(mItem.get(i).getImage());
                db_save_image.setAddress(address);
            }
            mRealm.commitTransaction();
            finish();
        }



        Toast.makeText(getApplicationContext(),"저장",Toast.LENGTH_LONG).show();
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
                mItem.clear();
                image_item=data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
                if(image_item!=null){
                    for(int i=0;i<image_item.size();i++){
                        Log.d("사진",image_item.get(i).toString());
                        mItem.add(new Item_Save_Image(image_item.get(i).toString()));
                        madapter.notifyDataSetChanged();

                        //ImageView imgView = (ImageView) recyclerView.getChildAt(i).findViewById(R.id.save_iv);
                       // Glide.with(this).load(image_item.get(i)).into(imgView);
                    }
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
    public void Realm(){

        try {
            mRealm= Realm.getDefaultInstance();

        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
        mRealm.beginTransaction();
    }

    public String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);


    }
    public void modify(){
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String content=intent.getStringExtra("content");
        String address=intent.getStringExtra("address");
        String[] list=intent.getStringArrayExtra("list");
        if(title.equals("")){

        }
        else{
            title_et.setText(title);
            address_et.setText(address);
            content_et.setText(content);

            for(int i=0;i<list.length;i++){
                mItem.add(new Item_Save_Image(list[i]));
                madapter.notifyDataSetChanged();

            }

        }


    }
    public void upate_save(){
       final String title=title_et.getText().toString();
       final String address=address_et.getText().toString();
       final String content=content_et.getText().toString();
        Realm();

        Intent intent=getIntent();

        RealmResults<DB_Save_Image> results=mRealm.where(DB_Save_Image.class)
                .equalTo("address",intent.getStringExtra("address")).findAll();
        results.deleteAllFromRealm() ;
        Log.d("results:",String.valueOf(results.size()));
        RealmResults<DB_Save> results2=mRealm.where(DB_Save.class)
                .equalTo("address",intent.getStringExtra("address")).findAll();
        results2.deleteAllFromRealm();
        Log.d("results2:",String.valueOf(results2.size()));
        mRealm.commitTransaction();

        mRealm.beginTransaction();
        DB_Save db_save=mRealm.createObject(DB_Save.class);
        db_save.setTitle(title);
        db_save.setContent(content);
        db_save.setDate(getTime());
        db_save.setAddress(address);

        for(int i=0;i<mItem.size();i++){
            DB_Save_Image db_save_image=mRealm.createObject(DB_Save_Image.class);
            db_save_image.setTitle(title);
            db_save_image.setImage(mItem.get(i).getImage());
            db_save_image.setAddress(address);
        }
        mRealm.commitTransaction();

        Toast.makeText(getApplicationContext(),"수정",Toast.LENGTH_LONG).show();



    }

}
