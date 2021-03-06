package dsm.titi.Activity.Fragment;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dsm.titi.Activity.DB.DB_Save;
import dsm.titi.Activity.DB.DB_Save_Image;
import dsm.titi.Activity.Item.Item_Save_Image;
import dsm.titi.Activity.Save_Activity;
import dsm.titi.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ghdth on 2018-04-23.
 */

public class Fragment_Map extends Fragment implements OnMapReadyCallback{
    private MapView mapView = null;
    private FloatingActionButton save_click;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private Realm mRealm;
    private String Adress;
    private String title;
    private String[] array;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Fragment_Map(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         final View layout=inflater.inflate(R.layout.fragment_map,container,false);
         mapView=(MapView)layout.findViewById(R.id.map);
         mapView.getMapAsync(this);
         save_click=(FloatingActionButton)layout.findViewById(R.id.save_click);
         save_click.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(getActivity(), Save_Activity.class);
                 startActivity(intent);
             }
         });
        return layout;

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mapView!=null){
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        geocoder=new Geocoder(getContext());
       Realm();
       // mRealm= Realm.getDefaultInstance();
        RealmResults<DB_Save> results=mRealm.where(DB_Save.class).findAll();
        if(results.size()!=0){
            for(int i=0;i<results.size();i++){
                DB_Save db_save=results.get(i);
                Adress=db_save.getAddress();
                title=db_save.getTitle();
                List<Address>addressList=null;

                try {
                    addressList=geocoder.getFromLocationName(Adress,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("addressList.get",addressList.get(0).toString());
                String []splitStr=addressList.get(0).toString().split(",");
                String address_maker=splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2);
                Log.d("address",address_maker);
                String latitude=splitStr[10].substring(splitStr[10].indexOf("=") + 1);
                String longitude=splitStr[12].substring(splitStr[12].indexOf("=") + 1);
                Log.d("latitude",latitude);
                Log.d("longitude",longitude);
                LatLng point=new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                MarkerOptions mOption=new MarkerOptions();
                mOption.title(title);
                mOption.snippet(address_maker);
                mOption.position(point);
                mMap.addMarker(mOption);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));

            }
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals(title)){
                      intent_data(Adress);
                }
                return true;
            }
        });



    }
    public void intent_data(String address){
        Realm();
        DB_Save db_save=mRealm.where(DB_Save.class).equalTo("address",address).findFirst();
        RealmResults<DB_Save_Image> results=mRealm.where(DB_Save_Image.class)
                .equalTo("address",address).findAll();
        array=new String[results.size()];
        for(int i=0;i<results.size();i++){
           DB_Save_Image db_save_image=results.get(i);
           array[i]=db_save_image.getImage();
        }


        Intent intent=new Intent(getContext(),Save_Activity.class);
        intent.putExtra("title",db_save.getTitle());
        intent.putExtra("content",db_save.getContent());
        intent.putExtra("address",db_save.getAddress());
        intent.putExtra("list",array);
        startActivity(intent);
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
