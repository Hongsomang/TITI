package dsm.titi.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dsm.titi.R;

/**
 * Created by ghdth on 2018-04-17.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private FrameLayout fragmentContainer;
    private FrameLayout fragmentContainer2;
    private FrameLayout fragmentContainer3;
    private MapView mapView;
    private AHBottomNavigation bottomNavigation;
    private AHBottomNavigationViewPager viewPager;

    public static HomeFragment newInstance(int index){
        HomeFragment fragment=new HomeFragment();
        Bundle b=new Bundle();
        b.putInt("index",index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=null;
        Log.d("홈","시작");
        if(getArguments().getInt("index",0)==0){
            return view;
        }
        if(getArguments().getInt("index",0)==1){
            view=inflater.inflate(R.layout.fragment_map,container,false);
            mapView=(MapView)view.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
            //initmap(view);

            return view;
        }
        if(getArguments().getInt("index",0)==2){
        }
        return view;
    }


    public void willBeHidden(){
        if(fragmentContainer!=null){
            Animation fadOut= AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadOut);
        }
    }
    public void willBeDisplayed(){
        if(fragmentContainer!=null){
            Animation fadeIn=AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }
    public void refresh(){
        if(getArguments().getInt("index",0)==0){

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d("dfdf","dfdfds");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this.getActivity());
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(new LatLng(35.141233, 126.925594), 14);

        googleMap.animateCamera(cameraUpdate);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(35.141233, 126.925594))
                .title("루프리코리아"));
    }
}
