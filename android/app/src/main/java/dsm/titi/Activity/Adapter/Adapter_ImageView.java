package dsm.titi.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dsm.titi.Activity.DB.DB_Save_Image;
import dsm.titi.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-05-31.
 */

public class Adapter_ImageView extends PagerAdapter  {
    private Realm mRealm;
    private Intent intent;
    private ArrayList<String> image=new ArrayList<>();
    private int id;
    private Context context;
    private String address;
    private LayoutInflater inflater;

    public Adapter_ImageView(Context context ,int id,String address) {
        this.context=context;
        this.id=id;
        this.address=address;
        images();
    }
    public void images(){
        Realm();
        RealmResults<DB_Save_Image> results=mRealm.where(DB_Save_Image.class)
                .equalTo("address",address).findAll();
        for(int i=0;i<results.size();i++){
            DB_Save_Image db_save_image=results.get(i);
            image.add(db_save_image.getImage());
            Log.d("image().image",image.get(i));

        }

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view==((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.item_imageview,container,false);
        ImageView imageView=(ImageView)v.findViewById(R.id.imageview_iv);
        Glide.with(context).load(image.get(position)).into(imageView);
        Log.d("glide.image",image.get(position));
        container.addView(v);
        return v;
    }

    @Override
    public int getCount() {
        Log.d("getCount:",String.valueOf(image.size()));
        return image.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
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
