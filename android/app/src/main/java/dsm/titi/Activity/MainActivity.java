package dsm.titi.Activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;;
import android.os.Bundle;

import dsm.titi.Activity.Adapter.TabPagerAdapter;
import dsm.titi.Activity.Fragment.Fragment_Album;
import dsm.titi.Activity.Fragment.Fragment_Map;
import dsm.titi.Activity.Fragment.Fragment_Writing;
import dsm.titi.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.map_location);
        tabLayout.getTabAt(1).setIcon(R.drawable.photo_library);
        tabLayout.getTabAt(2).setIcon(R.drawable.edit);
        TabLayout.Tab tab=tabLayout.getTabAt(2);

    }
    private void setupViewPager(ViewPager viewPager){
        TabPagerAdapter adapter=new TabPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Map(),"map");
        adapter.addFragment(new Fragment_Album(),"album");
        adapter.addFragment(new Fragment_Writing(),"writing");
        viewPager.setAdapter(adapter);
    }
}
